package config;

import com.mysql.cj.xdevapi.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigJdbc {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "testgis";
        String username = "root";
        String password = "admin";

        return getConnection(hostName, dbName, username, password);
    }

    public static Connection getConnection(String hostName, String dbName,
                                           String username, String password)
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName
                + "?serverTimezone=Europe/Moscow&userSSL=false";

        return DriverManager.getConnection(connectionURL, username, password);
    }
}