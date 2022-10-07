package fr.unice.bff.controller;

import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.dining.Table;
import fr.unice.bff.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class OrderController {
    public static final String BASE_URI = "/order";

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @PostMapping(BASE_URI+"/{tableId}")
    public ResponseEntity<String> createOrder(@RequestBody @Valid List<OrderItem> itemInfoList, @PathVariable("tableId") int tableId) {
        logger.info("calling ordering service ");
        return orderService.makeAnOrder(itemInfoList,new Table(tableId));
    }

    @PostMapping(BASE_URI+"/{orderId}/ready")
    public void servingOrder(){

    }

}
