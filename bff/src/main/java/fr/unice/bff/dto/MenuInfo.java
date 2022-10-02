package fr.unice.bff.controller.dto;

import fr.unice.bff.models.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Data
public class MenuInfo {
    private UUID id;

    @NotBlank
    private String shortName;

    @NotBlank
    private Category category;

}
