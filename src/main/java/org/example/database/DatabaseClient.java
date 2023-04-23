package org.example.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class DatabaseClient {

    private String username;

    private String password;

    private String connectionUrl;

    private Connection connection;

    public DatabaseClient() {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("MYSQL_USER", "root");
        this.password = dotenv.get("MYSQL_PASSWORD", "root");
        this.connectionUrl = "jdbc:mysql://localhost:3306/ibm";
    }

    public DatabaseClient(String databaseName) {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("MYSQL_USER", "root");
        this.password = dotenv.get("MYSQL_PASSWORD", "root");
        this.connectionUrl = "jdbc:mysql://localhost:3306/" + databaseName;
    }
    public DatabaseClient(String username, String password, String databaseName) {
        this.username = username;
        this.password = password;
        this.connectionUrl = "jdbc:mysql://localhost:3306/" + databaseName;
    }

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(connectionUrl, username, password);
    }

    public void disconnect() throws SQLException {
        this.connection.close();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.closeOnCompletion();
        return statement.executeQuery(query);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
