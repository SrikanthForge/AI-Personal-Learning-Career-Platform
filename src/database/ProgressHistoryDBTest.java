package database;

public class ProgressHistoryDBTest {

    public static void main(String[] args) {

        ProgressHistoryDAO historyDAO =
                new ProgressHistoryDAO();

        historyDAO.displayProgressHistory(1);
    }
}