package fr.unice.bff.exception;

public class TableNotFoundException extends Exception {
    public TableNotFoundException(int id) {
        super("TABLE with id " + id + " not found.");
    }
}
