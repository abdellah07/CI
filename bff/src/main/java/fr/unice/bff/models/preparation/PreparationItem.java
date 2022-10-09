package fr.unice.bff.models.preparation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreparationItem {
    private String id;
    private String shortName;
    @JsonIgnore
    private boolean isTakenForService;

    public PreparationItem() {
        isTakenForService = false;
    }
}
