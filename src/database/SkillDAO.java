package database;

import model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillDAO {

    // Displays skills directly from the database
    public void displayStudentSkills(int studentId) {

        String sql = """
                SELECT
                    s.skill_id,
                    s.skill_name,
                    ss.skill_level
                FROM skills s
                JOIN student_skills ss
                    ON s.skill_id = ss.skill_id
                WHERE ss.student_id = ?
                ORDER BY s.skill_id
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            System.out.println("\n===== STUDENT SKILLS FROM DATABASE =====");

            while (result.next()) {

                System.out.printf(
                        "%-20s : %d%%%n",
                        result.getString("skill_name"),
                        result.getInt("skill_level")
                );
            }

        } catch (SQLException e) {

            System.out.println("Database error: " + e.getMessage());
        }
    }


    // Loads student skills from MySQL into Java ArrayList
    public ArrayList<Skill> getStudentSkills(int studentId) {

        ArrayList<Skill> skills = new ArrayList<>();

        String sql = """
                SELECT
                    s.skill_name,
                    ss.skill_level
                FROM skills s
                JOIN student_skills ss
                    ON s.skill_id = ss.skill_id
                WHERE ss.student_id = ?
                ORDER BY s.skill_id
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                String skillName = result.getString("skill_name");
                int skillLevel = result.getInt("skill_level");

                skills.add(new Skill(skillName, skillLevel));
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to load skills from database: "
                    + e.getMessage()
            );
        }

        return skills;
    }
}