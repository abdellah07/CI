package fr.unice.bff.dto.dining;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@ToString
public class OrderItem {
    private String id;
    @NotBlank
    private String shortName;

    private int howMany;


}
