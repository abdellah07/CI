package fr.unice.bff.service;

import fr.unice.bff.dto.dining.OrderItem;
import fr.unice.bff.dto.dining.Table;
import fr.unice.bff.dto.menu.MenuItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuService menuService;

    @Test
    public void testMakeAnOrder(){
        MenuItem menuItem1 =  menuService.retrieveMenu().get(0);
        MenuItem menuItem2 =  menuService.retrieveMenu().get(1);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(menuItem1.getId(),menuItem1.getShortName(),1));
        orderItemList.add(new OrderItem(menuItem2.getId(),menuItem2.getShortName(),1));
        assertTrue(!orderService.makeAnOrder(orderItemList,new Table(1)).isError());
    }
}
