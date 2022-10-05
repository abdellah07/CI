package fr.unice.bff.dto.dining;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableOrderRequest {
    private int tableId;
    private int customersCount;
}
