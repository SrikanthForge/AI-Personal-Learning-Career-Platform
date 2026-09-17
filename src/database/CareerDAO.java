package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CareerDAO {

    public void displayCareerRequirements(int careerId) {

        String sql = """
                SELECT
                    c.career_name,
                    s.skill_name,
                    cs.required_level
                FROM career_skills cs
                JOIN careers c
                    ON cs.career_id = c.career_id
                JOIN skills s
                    ON cs.skill_id = s.skill_id
                WHERE c.career_id = ?
                ORDER BY s.skill_id
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, careerId);

            ResultSet result = statement.executeQuery();

            boolean found = false;

            System.out.println("\n===== CAREER REQUIREMENTS FROM DATABASE =====");

            while (result.next()) {

                found = true;

                System.out.println(
                        "Career        : " +
                        result.getString("career_name")
                );

                System.out.println(
                        "Skill         : " +
                        result.getString("skill_name")
                );

                System.out.println(
                        "Required Level: " +
                        result.getInt("required_level") + "%"
                );

                System.out.println("-----------------------------------");
            }

            if (!found) {
                System.out.println("Career not found.");
            }

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
}