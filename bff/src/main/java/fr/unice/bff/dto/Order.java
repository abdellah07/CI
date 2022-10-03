package fr.unice.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Order {
    private List<Item> itemInfoList;

    private boolean ready;

    private TableOrder tableOrder;
}
