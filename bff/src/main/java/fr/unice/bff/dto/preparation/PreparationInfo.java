package fr.unice.bff.dto.preparation;

import fr.unice.bff.models.preparation.PreparationItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class PreparationInfo {
    private List<PreparationItem> ready;
    private List<PreparationItem> served;
    private List<PreparationItem> unready;

    public PreparationInfo() {
        ready = new ArrayList<>();
        served = new ArrayList<>();
        unready = new ArrayList<>();
    }

    public void addReadyItems(List<PreparationItem> ready) {
        this.ready.addAll(ready);
    }

    public void addServedItems(List<PreparationItem> served) {
        this.ready.addAll(served);
    }

    public void addUnreadyItems(List<PreparationItem> unready) {
        this.ready.addAll(unready);
    }
}
