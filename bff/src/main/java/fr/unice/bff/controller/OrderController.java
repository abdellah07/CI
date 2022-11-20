package fr.unice.bff.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.menu.MenuItem;
import fr.unice.bff.dto.tables.Table;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.service.OrderService;
import fr.unice.bff.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class OrderController {
    public static final String BASE_URI = "/order" ;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private TableService tableService;

    @PostMapping(BASE_URI + "/{tableId}")
    public ResponseEntity<String> createOrder(@RequestBody @Valid List<OrderItem> itemInfoList, @PathVariable("tableId") int tableId) throws TableNotFoundException {
        logger.info("calling ordering service ");
        Table table;
        try {
            table = tableService.getTableInfo(tableId);
        } catch (TableNotFoundException tableNotFoundException) {
            logger.error(tableNotFoundException.getMessage());
            throw new TableNotFoundException(tableId);
        }
        return orderService.makeAnOrder(itemInfoList, table);
    }

    @GetMapping(BASE_URI + "/{tableId}")
    public ResponseEntity<List<MenuItem>> getOrder(@PathVariable("tableId") int tableId) throws JsonProcessingException{
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            menuItems = this.orderService.getAllMenuItemFromTableId(tableId);
        } catch (JsonProcessingException ex) {
            logger.error("Error in Json");
            throw ex;
        }

        return ResponseEntity.ok(menuItems);

    }

    @PostMapping(BASE_URI + "/{tableId}/status")
    public void orderStatus() {

    }

}
