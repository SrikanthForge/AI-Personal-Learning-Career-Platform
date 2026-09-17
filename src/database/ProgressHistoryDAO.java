package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressHistoryDAO {

    public void displayProgressHistory(int studentId) {

        String sql = """
                SELECT
                    ph.progress_id,
                    s.skill_name,
                    ph.old_level,
                    ph.new_level,
                    ph.updated_at
                FROM progress_history ph
                JOIN skills s
                    ON ph.skill_id = s.skill_id
                WHERE ph.student_id = ?
                ORDER BY ph.updated_at DESC
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            System.out.println("\n===== PROGRESS HISTORY =====");

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println(
                        "Progress ID : "
                        + result.getInt("progress_id")
                );

                System.out.println(
                        "Skill       : "
                        + result.getString("skill_name")
                );

                System.out.println(
                        "Old Level   : "
                        + result.getInt("old_level") + "%"
                );

                System.out.println(
                        "New Level   : "
                        + result.getInt("new_level") + "%"
                );

                System.out.println(
                        "Updated At  : "
                        + result.getTimestamp("updated_at")
                );

                System.out.println("-----------------------------------");
            }

            if (!found) {
                System.out.println("No progress history found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database error: " + e.getMessage()
            );
        }
    }
}