package org.example;

import org.example.database.DatabaseClient;
import org.example.database.entity.Provider;
import org.example.database.query.ProviderQuery;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid number of arguments. Usage: ibm-prueba <client-id>");
            return;
        }
        String clientId = args[0];
        DatabaseClient mysqlClient = new DatabaseClient();

        try {
            mysqlClient.connect();
        } catch (Exception e) {
            System.out.println("There was a problem connecting to the database. Error: " + e.getMessage());
            return;
        }

        ResultSet rs;
        try {
            rs = mysqlClient.executeQuery(ProviderQuery.findAllByClientId(Long.valueOf(clientId)));
        } catch (SQLException e) {
            System.out.println("There was a problem executing the query. Error: " + e.getMessage());
            return;
        }
        List<Provider> providers = new ArrayList<>();
        try {
            while (rs.next())
                providers.add(new Provider(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDate("registration_date"),
                rs.getLong("client_id")));

        } catch (SQLException e) {
            System.out.println("There was a problem reading the result set. Error: " + e.getMessage());
            return;
        }

        try {
            rs.close();
            mysqlClient.disconnect();
        } catch (SQLException e) {
            System.out.println("There was a problem closing the connection. Error: " + e.getMessage());
            return;
        }

        if (providers.isEmpty()) {
            System.out.println("No providers found for client with id " + clientId);
            return;
        }
        Charset utf8 = StandardCharsets.UTF_8;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("proveedores.txt"), utf8))) {
            writer.write("Tabla: proveedores\n" +
                             "Campos: id_proveedor, nombre, fecha de alta, id_cliente\n");
            providers.parallelStream().forEach(
                provider -> {
                    try {
                        writer.write(provider.toString() + "\n");
                    } catch (IOException e) {
                        System.out.println("There was a problem writing to the file. Error: " + e.getMessage());
                        return;
                    }
                }
            );
        } catch (IOException e) {
            System.out.println("There was a problem writing to the file. Error: " + e.getMessage());
            return;
        }
    }
}