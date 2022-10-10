package fr.unice.bff.dto.tables;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class TableInfo {
    private List<Table> available;
    private List<Table> waitingForPayment;
    private List<Table> payed;
    private List<Table> inPreparation;
    private List<Table> orderReady;
    private List<Table> noInfo;

    public TableInfo() {
        available = new ArrayList<>();
        waitingForPayment = new ArrayList<>();
        payed = new ArrayList<>();
        inPreparation = new ArrayList<>();
        orderReady = new ArrayList<>();
        noInfo = new ArrayList<>();
    }

    public void addAvailableTable(Table available) {
        this.available.add(available);
    }

    public void addWaitingForPaymentTable(Table waitingForPayment) {
        this.waitingForPayment.add(waitingForPayment);
    }

    public void addUPayedTable(Table payed) {
        this.payed.add(payed);
    }
    public void addInPreparationTable(Table inPreparation) {
        this.inPreparation.add(inPreparation);
    }

    public void addOrderReadyTable(Table orderReady) {
        this.orderReady.add(orderReady);
    }

    public void addNoReadyTable(Table noInfo) {
        this.noInfo.add(noInfo);
    }
}
