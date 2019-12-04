package by.mukhin.finalProject.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private static final String PATH = "jdbc:mysql://localhost/inventory?serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "кщще";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(PATH, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

}
