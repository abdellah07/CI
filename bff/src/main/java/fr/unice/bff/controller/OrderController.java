package fr.unice.bff.controller;

import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.dining.Table;
import fr.unice.bff.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = MenuController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    public static final String BASE_URI = "/order";

    @PostMapping("/{tableId}")
    public HttpStatus createOrder(@RequestBody @Valid List<OrderItem> itemInfoList, @PathVariable("tableId") int tableId) {
        return orderService.makeAnOrder(itemInfoList,new Table(tableId));
    }

}
