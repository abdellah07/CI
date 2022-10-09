package fr.unice.bff.service;

public class BaseUrl {

    private static String[] dinning = new String[]{"http://localhost:3001", "http://dining-service:8080"};
    private static String[] kitchen = new String[]{"http://localhost:3002", "http://kitchen-service:8080"};
    private static String[] menu = new String[]{"http://localhost:3000", "http://menu-service:8080"};

    private static boolean local = false;

    public static void setLocalTrue() {
        local = true;
    }

    public static String getDinning() {
        if (local)
            return dinning[0];
        else
            return dinning[1];
    }

    public static String getKitchen() {
        if (local)
            return kitchen[0];
        else
            return kitchen[1];
    }

    public static String getMenu() {
        if (local)
            return menu[0];
        else
            return menu[1];
    }
}
