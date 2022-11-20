package fr.unice.bff.dto.tables;

public enum TableStatus {
    available(0),
    waitingForPayment(1),
    payed(2),
    inPreparation(3),
    orderReady(4),
    noInfo(5);
    int number;

    TableStatus(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
