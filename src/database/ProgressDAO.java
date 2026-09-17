package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressDAO {

    public void updateSkillLevel(int studentId, int skillId, int newLevel) {

        if (newLevel < 0 || newLevel > 100) {
            System.out.println("Skill level must be between 0 and 100.");
            return;
        }

        String findSql = """
                SELECT skill_level
                FROM student_skills
                WHERE student_id = ? AND skill_id = ?
                """;

        String updateSql = """
                UPDATE student_skills
                SET skill_level = ?
                WHERE student_id = ? AND skill_id = ?
                """;

        String historySql = """
                INSERT INTO progress_history
                    (student_id, skill_id, old_level, new_level)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (
                PreparedStatement findStatement =
                        connection.prepareStatement(findSql);
                PreparedStatement updateStatement =
                        connection.prepareStatement(updateSql);
                PreparedStatement historyStatement =
                        connection.prepareStatement(historySql)
            ) {

                // Find current skill level
                findStatement.setInt(1, studentId);
                findStatement.setInt(2, skillId);

                ResultSet result = findStatement.executeQuery();

                if (!result.next()) {
                    System.out.println("Student skill record not found.");
                    connection.rollback();
                    return;
                }

                int oldLevel = result.getInt("skill_level");

                // Update skill level
                updateStatement.setInt(1, newLevel);
                updateStatement.setInt(2, studentId);
                updateStatement.setInt(3, skillId);

                int rowsUpdated = updateStatement.executeUpdate();

                if (rowsUpdated == 0) {
                    System.out.println("Skill update failed.");
                    connection.rollback();
                    return;
                }

                // Save progress history
                historyStatement.setInt(1, studentId);
                historyStatement.setInt(2, skillId);
                historyStatement.setInt(3, oldLevel);
                historyStatement.setInt(4, newLevel);

                historyStatement.executeUpdate();

                // Commit both operations
                connection.commit();

                System.out.println("\n===== SKILL UPDATED SUCCESSFULLY =====");
                System.out.println("Student ID : " + studentId);
                System.out.println("Skill ID   : " + skillId);
                System.out.println("Old Level  : " + oldLevel + "%");
                System.out.println("New Level  : " + newLevel + "%");
                System.out.println("Progress history saved.");

            } catch (SQLException e) {

                connection.rollback();

                System.out.println(
                        "Transaction failed. Changes rolled back."
                );

                throw e;
            }

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }
}