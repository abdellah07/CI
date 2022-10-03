package fr.unice.bff.exception;

public class ItemException extends Exception {
    public ItemException(String msg) {
        super("Cant add Item " + msg);
    }
}
