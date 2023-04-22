package org.example.database;

import java.sql.*;

public class DatabaseClient {

    private String username;

    private String password;

    private String connectionUrl;

    private Connection connection;

    public DatabaseClient(String username, String password, String connectionUrl) {
        this.username = username;
        this.password = password;
        this.connectionUrl = connectionUrl;
    }

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
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
