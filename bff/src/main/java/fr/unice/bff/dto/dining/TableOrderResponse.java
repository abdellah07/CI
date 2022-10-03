package fr.unice.bff.dto.dining;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TableOrderResponse {
    private String id;
    private LocalDateTime opened;
    private LocalDateTime billed;
}
