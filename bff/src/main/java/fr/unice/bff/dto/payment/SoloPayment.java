package fr.unice.bff.dto.payment;

import lombok.Data;

@Data
public class SoloPayment {
    private int total;

    public SoloPayment() {
        this.total = 0;
    }

    public void addToTotal(int price) {
        total += price;
    }
}
