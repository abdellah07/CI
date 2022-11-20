package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.dining.*;
import fr.unice.bff.dto.menu.MenuItem;
import fr.unice.bff.models.preparation.*;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.exception.OrderException;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.exception.TableWithoutOrderId;
import fr.unice.bff.models.lines.Lines;
import fr.unice.bff.models.preparation.PreparationItem;
import fr.unice.bff.models.preparation.PreparationResponse;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    private static String dinningURL = BaseUrl.getDinning();
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final String tableOrderSubdirectory = "/tableOrders";
    private static final String prepareSubdirectory = "/prepare";

    @Autowired
    private TableService tableService;
    @Autowired
    private MenuService menuService;


    public ResponseEntity<String> makeAnOrder(List<OrderItem> itemList, Table table) {
        try {
            Order order;
            if (!table.isTaken()) {
                TableOrderResponse tableOrderResponse = createAnOrder(table.getNumber());
                order = new Order(tableOrderResponse.getId(), itemList);
            } else {
                logger.info("Table " + table.getNumber() + "is taken adding item directly to command id " + table.getTableOrderId());
                order = new Order(table.getTableOrderId(), itemList);
            }
            addItemsToOrder(order);
            ResponseEntity<String> response = prepareTheOrder(order);
            return response;
        } catch (Exception e) {
            logger.error("Cant Create ORDER : " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    private TableOrderResponse createAnOrder(int tableID) throws JsonProcessingException, OrderException {
        TableOrderRequest tableOrderRequest = new TableOrderRequest(tableID, 1);
        ResponseEntity<String> response = ExternalCall.send(dinningURL + tableOrderSubdirectory, tableOrderRequest);
        if (!response.getStatusCode().equals(HttpStatus.CREATED)) {
            throw new OrderException();
        }
        TableOrderResponse tableOrderResponse = JsonMapper.objectMapper.readValue(response.getBody(), TableOrderResponse.class);
        return tableOrderResponse;
    }


    private void addItemsToOrder(Order order) {
        String postItemUrl = dinningURL + tableOrderSubdirectory + "/" + order.getId();
        for (OrderItem orderItem : order.getItemInfoList()) {
            ResponseEntity<String> response = ExternalCall.send(postItemUrl, orderItem);
            logger.debug("Sending " + orderItem + " to " + postItemUrl);
            if (response.getStatusCode().isError()) {
                logger.warn("Cant Add Item with id " + orderItem.getId() + " named " + orderItem.getShortName() + ".");
            } else {
                logger.info("Item with id " + orderItem.getId() + " named " + orderItem.getShortName() + " was added successfully.");
            }
        }
    }

    private ResponseEntity<String> prepareTheOrder(Order order) {
        String prepareItemsUrl = dinningURL + tableOrderSubdirectory + "/" + order.getId() + "/" + prepareSubdirectory;
        ResponseEntity<String> response = ExternalCall.send(prepareItemsUrl);
        if (response.getStatusCode().isError()) {
            logger.error("Cant prepare Order dinning service is responding with error.");
        } else {
            logger.info("order with id " + order.getId() + " is sent for preparation.");
        }
        return response;
    }

    public PreparationResponse getAllPreparations(int tableId) throws TableNotFoundException, JsonProcessingException, TableWithoutOrderId {
        String orderTable = tableService.getTableInfo(tableId).getTableOrderId();
        if (orderTable == null) {
            logger.warn("Table id is null");
            throw new TableWithoutOrderId(tableId);
        }
        String url = dinningURL + tableOrderSubdirectory + "/" + orderTable;

        String preperationsJson = ExternalCall.call(url);

        PreparationResponse preparationResponse = JsonMapper.objectMapper.readValue(preperationsJson, PreparationResponse.class);
        return preparationResponse;
    }

    public Lines getOrderLines(String orderId) throws JsonProcessingException {
        String orderJson = ExternalCall.call(dinningURL + tableOrderSubdirectory + "/" + orderId);
        Lines lines = JsonMapper.objectMapper.readValue(orderJson, Lines.class);
        return lines;
    }

    public List<MenuItem> getAllMenuItemFromTableId(int tableId) throws JsonProcessingException {

        List<Preparation> preparation;
        List<MenuItem> menuItems = new ArrayList<>();
        List<MenuItem> menu;
        try {
            preparation = getAllPreparations(tableId).getPreparations();
            menu = menuService.retrieveMenu();
            // really messy code but well it works
            for(Preparation prep: preparation){
                for(PreparationItem prepItem: prep.getPreparedItems()){
                    for(MenuItem m : menu){
                        if(m.getShortName().equals(prepItem.getShortName())){
                            menuItems.add(m);
                            break;
                        }
                    }
                }
            }
        }
        catch(TableNotFoundException|TableWithoutOrderId ex){
            return menuItems;
        } catch (JsonProcessingException ex){
            throw ex;
        }
        return menuItems;
    }
}
