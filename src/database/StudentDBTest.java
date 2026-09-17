package database;

public class StudentDBTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        studentDAO.displayStudent(1);
    }
}