package fr.unice.bff.models.preparation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Preparation {
    private String id;
    private LocalDateTime shouldBeReadyAt;
    private LocalDateTime takenForServiceAt;
    private List<PreparationItem> preparedItems;

    public Preparation(){
        preparedItems = new ArrayList<>();
    }
}
