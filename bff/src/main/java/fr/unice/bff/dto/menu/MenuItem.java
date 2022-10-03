package fr.unice.bff.dto.menu;

import fr.unice.bff.models.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Data
public class MenuItem {
    private UUID id;

    @NotBlank
    private String shortName;

    @NotBlank
    private Category category;

}
