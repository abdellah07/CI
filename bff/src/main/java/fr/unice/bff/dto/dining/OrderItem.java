package fr.unice.bff.dto.dining;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
public class OrderItem {
    private String id;
    @NotBlank
    private String shortName;

    private int howMany;


}
