package fr.unice.bff.dto.payment;

import fr.unice.bff.dto.menu.MenuItem;
import lombok.Data;

import java.util.List;

@Data
public class PaymentInfo {
    private List<MenuItem> menuItems;
    private long total;
}
