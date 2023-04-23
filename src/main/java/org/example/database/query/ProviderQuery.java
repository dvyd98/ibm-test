package org.example.database.query;

public class ProviderQuery {

    private static final String TABLE_NAME = "providers";
    public static String findAllByClientId(Long clientId) {
        return String.format("SELECT * FROM %s WHERE client_id = %s", TABLE_NAME, clientId.toString());
    }
}
