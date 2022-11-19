package fr.unice.bff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.bff.dto.dining.Order;
import fr.unice.bff.dto.menu.MenuItem;
import fr.unice.bff.dto.payment.Payment;
import fr.unice.bff.dto.payment.PaymentInfo;
import fr.unice.bff.dto.payment.SoloPayment;
import fr.unice.bff.dto.preparation.PreparationInfo;
import fr.unice.bff.exception.TableNotFoundException;
import fr.unice.bff.models.lines.Item;
import fr.unice.bff.models.lines.Line;
import fr.unice.bff.models.lines.Lines;
import fr.unice.bff.models.preparation.PreparationItem;
import fr.unice.bff.util.ExternalCall;
import fr.unice.bff.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    private static String dinningURL = BaseUrl.getDinning();

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private static final String tableOrderSubdirectory = "/tableOrders";
    private static final String billSubdirectory = "/bill";

    private static final Map<Integer, SoloPayment> moneyByTable = new HashMap<>();

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MenuService menuService;

    public PaymentInfo getPaymentInfo(int tableid) throws TableNotFoundException, JsonProcessingException {
        String orderId = tableService.getTableInfo(tableid).getTableOrderId();
        Lines lines = orderService.getOrderLines(orderId);
        List<MenuItem> itemList = new ArrayList<>();
        long total = 0;
        for (Line line : lines.getLines()) {
            Item item = line.getItem();
            MenuItem menuItem = menuService.retrieveMenuById(item.getId());
            total += menuItem.getPrice() * line.getHowMany();
            menuItem.setHowMany(line.getHowMany());
            itemList.add(menuItem);

        }
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setMenuItems(itemList);
        paymentInfo.setTotal(total);

        return paymentInfo;
    }

    public boolean validatePayment(int tableid) throws TableNotFoundException, JsonProcessingException {
        String tableOrderId = tableService.getTableInfo(tableid).getTableOrderId();
        String url = dinningURL + tableOrderSubdirectory + "/" + tableOrderId + billSubdirectory;
        String json = ExternalCall.send(url).getBody();
        Payment paymentInfo = JsonMapper.objectMapper.readValue(json, Payment.class);
        return paymentInfo.getBilled() != null;
    }

    public void validatePayment(int tableid, int money) throws TableNotFoundException, JsonProcessingException {
        PaymentInfo paymentInfo = getPaymentInfo(tableid);
        if (!moneyByTable.containsKey(tableid)) {
            moneyByTable.put(tableid, new SoloPayment());
        }
        moneyByTable.get(tableid).addToTotal(money);
        if (moneyByTable.get(tableid).getTotal() == paymentInfo.getTotal()) {
            validatePayment(tableid);
            moneyByTable.get(tableid).setTotal(0);
        }
    }
}
