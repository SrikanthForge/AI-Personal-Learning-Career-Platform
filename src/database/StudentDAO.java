package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public void displayStudent(int studentId) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println("\n===== STUDENT FROM DATABASE =====");
                System.out.println("Student ID   : " + result.getInt("student_id"));
                System.out.println("Name         : " + result.getString("name"));
                System.out.println("Branch       : " + result.getString("branch"));
                System.out.println("Year         : " + result.getInt("year"));
                System.out.println("CGPA         : " + result.getDouble("cgpa"));
                System.out.println("Career Goal  : " + result.getString("career_goal"));

            } else {

                System.out.println("Student not found.");
            }

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
}