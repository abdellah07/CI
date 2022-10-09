package fr.unice.bff.models.preparation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreparationItem {
    private String id;
    private String shortName;
    @JsonIgnoreProperties
    private boolean isTakenForService;

    public PreparationItem() {
        isTakenForService = false;
    }
}
