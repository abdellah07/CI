package fr.unice.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableOrder {
    private int tableID;
    private int customerCount;
}
