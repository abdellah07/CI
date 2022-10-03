package fr.unice.bff.dto.dining;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Order {
    private String id;
    private List<OrderItem> itemInfoList;
}
