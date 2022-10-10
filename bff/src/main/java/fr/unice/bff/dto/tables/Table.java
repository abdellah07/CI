package fr.unice.bff.dto.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    private int number;
    private boolean taken;
    private String tableOrderId;

    public int getNumber() {
        return number;
    }
}

