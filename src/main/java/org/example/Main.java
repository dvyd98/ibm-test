package org.example;

import org.example.database.DatabaseClient;
import org.example.database.entity.Provider;
import org.example.database.query.ProviderQuery;
import org.jooq.impl.DSL;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Invalid number of arguments. Usage: main <client-id>");
            return;
        }
        String clientId = args[0];
        DatabaseClient mysqlClient = new DatabaseClient("root", "root", "jdbc:mysql://localhost:3306/test");

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

        } catch (Exception e) {
            System.out.println("There was a problem reading the result set. Error: " + e.getMessage());
            return;
        }

        Charset utf8 = StandardCharsets.UTF_8;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("proveedores.txt"), utf8))) {
            writer.write("Proveedores:\n");
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