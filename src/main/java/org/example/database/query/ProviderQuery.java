package org.example.database.query;

public class ProviderQuery {

    public static String findAllByClientId(Long clientId) {
        return "SELECT * FROM providers WHERE client_id = " + clientId;
    }
}
