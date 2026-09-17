package database;

public class ProgressDBTest {

    public static void main(String[] args) {

        ProgressDAO progressDAO = new ProgressDAO();

        // Student ID = 1
        // Skill ID = 1 → Python
        // Change Python from 45% to 50%
        progressDAO.updateSkillLevel(1, 1, 50);
    }
}