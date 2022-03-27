package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbURL = "jdbc:mysql://localhost:3306/new_schema_1";
    private static final String dbUsername = "hrumi";
    private static final String dbPassword = "Lapoipopopeh";

    public Connection getConnection() {
        Connection connection = null;
            try {
                connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
        }

    public void closeConnection (Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
