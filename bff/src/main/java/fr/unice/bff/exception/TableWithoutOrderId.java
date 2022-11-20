package fr.unice.bff.exception;

public class TableWithoutOrderId extends Exception{
    public TableWithoutOrderId(int id) {
        super("TABLE with id " + id + " dont have order.");
    }
}
