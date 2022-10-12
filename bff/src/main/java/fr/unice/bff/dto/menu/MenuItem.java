package fr.unice.bff.dto.menu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.unice.bff.models.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Data
public class MenuItem {
    private String id;

    @NotBlank
    private String shortName;

    private long price;

    @NotBlank
    private Category category;

    @JsonIgnoreProperties
    private int howMany;

}
