package fr.unice.bff.models.preparation;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PreparationResponse {
    private List<Preparation> preparations;

    public PreparationResponse() {
        preparations = new ArrayList<>();
    }
}
