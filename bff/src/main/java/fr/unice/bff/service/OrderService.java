package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.dining.*;
import fr.unice.bff.exception.OrderException;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final String dinningURL = "http://localhost:3001";
    private static final String tableOrderSubdirectory = "/tableOrders";
    private static final String prepareSubdirectory = "/prepare";

    public HttpStatus makeAnOrder(List<OrderItem> itemList, Table table) {
        try {
            TableOrderResponse tableOrderResponse = createAnOrder(table.getId());
            Order order = new Order(tableOrderResponse.getId(), itemList);
            addItemsToOrder(order);
            prepareTheOrder(order);
        } catch (Exception e) {
            logger.error("Cant Create ORDER : " + e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.CREATED;
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
                logger.error("Cant Add Item with id " + orderItem.getId() + " named " + orderItem.getShortName() + ".");
            } else {
                logger.info("Item with id " + orderItem.getId() + " named " + orderItem.getShortName() + " was added successfully.");
            }
        }
    }

    private void prepareTheOrder(Order order) {
        String prepareItemsUrl = dinningURL + tableOrderSubdirectory + "/" + order.getId() + "/" + prepareSubdirectory;
        ResponseEntity<String> response = ExternalCall.send(prepareItemsUrl);
        if (response.getStatusCode().isError()) {
            logger.error("Cant prepare Order dinning service is responding with error.");
        } else {
            logger.info("order with id " + order.getId() + " is sent for preparation.");
        }
    }
}
