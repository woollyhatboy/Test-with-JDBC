package org.example.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private Connection connection;

    public DataSource() {
        try {
            String url = "jdbc:mysql://localhost:test/test";
            connection = DriverManager.getConnection(url, "test", "test");

            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println("error " + e + " was detect");
        }catch (Exception e) {
            System.out.println("error " + e + " was detect");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
