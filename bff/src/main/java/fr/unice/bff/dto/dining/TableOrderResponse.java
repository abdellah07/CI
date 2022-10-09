package fr.unice.bff.dto.dining;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableOrderResponse {
    private String id;
    private LocalDateTime opened;
}
