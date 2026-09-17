package database;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        try (Connection connection = DBConnection.getConnection()) {

            System.out.println("=================================");
            System.out.println("JDBC CONNECTION SUCCESSFUL!");
            System.out.println("Database: career_platform");
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println("JDBC CONNECTION FAILED!");
            e.printStackTrace();
        }
    }
}