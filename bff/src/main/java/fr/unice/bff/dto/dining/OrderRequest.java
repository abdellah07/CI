package fr.unice.bff.dto.dining;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {
    List<OrderItem> itemInfoList;
}
