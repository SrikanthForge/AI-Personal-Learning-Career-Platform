package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final Properties properties = new Properties();

    static {

        try (FileInputStream input =
                     new FileInputStream("config/db.properties")) {

            properties.load(input);

            Class.forName(
                    properties.getProperty("db.driver")
            );

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Database configuration error: "
                    + e.getMessage()
            );
        }
    }

    public static Connection getConnection()
            throws SQLException {

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(
                url,
                user,
                password
        );
    }
}