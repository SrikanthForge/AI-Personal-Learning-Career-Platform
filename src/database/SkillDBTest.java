package database;

public class SkillDBTest {

    public static void main(String[] args) {

        SkillDAO skillDAO = new SkillDAO();

        skillDAO.displayStudentSkills(1);
    }
}