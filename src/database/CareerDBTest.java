package database;

public class CareerDBTest {

    public static void main(String[] args) {

        CareerDAO careerDAO = new CareerDAO();

        // Career ID 1 = AI/ML Engineer
        careerDAO.displayCareerRequirements(1);
    }
}