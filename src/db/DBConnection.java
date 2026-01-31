package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/student_result_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "student_user";
    private static final String PASSWORD = "student123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
