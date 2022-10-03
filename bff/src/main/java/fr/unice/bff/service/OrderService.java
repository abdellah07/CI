package fr.unice.bff.service;

import fr.unice.bff.dto.Item;
import fr.unice.bff.dto.Order;
import fr.unice.bff.dto.Table;
import fr.unice.bff.dto.TableOrder;
import fr.unice.bff.util.ExternalCall;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final String tableOrderURL = "http://localhost:3001/tableOrders";

    public Order makeAnOrder(List<Item> itemList, Table table){
        TableOrder tableOrder = new TableOrder(table.getId(),0);
        Order order = new Order(itemList, false, tableOrder);

        ExternalCall.send(tableOrderURL,tableOrder);
     
        return order;
    }
}
