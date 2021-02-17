package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigJdbc {
    public static Connection getConnection()
            throws SQLException, ClassNotFoundException, IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\main\\resources\\config.properties"));

        String hostName = properties.getProperty("db.host");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String dbClassDriver = properties.getProperty("db.driver");

        return getConnection(hostName, username, password, dbClassDriver);
    }

    public static Connection getConnection(String hostName, String username,
                                           String password, String dbClassDriver)
            throws SQLException, ClassNotFoundException {

        Class.forName(dbClassDriver);

        return DriverManager.getConnection(hostName, username, password);
    }
}