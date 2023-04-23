package org.example;

import org.example.database.DatabaseClient;
import org.example.database.query.ProviderQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExampleTests {
    @Test
    public void shouldReturnEmptyResultSet() throws SQLException {
        DatabaseClient mysqlClient = new DatabaseClient("test");
        mysqlClient.connect();
        ResultSet rs = mysqlClient.executeQuery(ProviderQuery.findAllByClientId(1L));
        assertFalse(rs.next());
    }

    @Test
    public void shouldReturnTwoResults() throws SQLException {
        DatabaseClient mysqlClient = new DatabaseClient("test");
        mysqlClient.connect();
        ResultSet rs = mysqlClient.executeQuery(ProviderQuery.findAllByClientId(5L));
        int count = 0;
        while (rs.next()) {
            count++;
        }
        assertEquals(2, count);
    }
}
