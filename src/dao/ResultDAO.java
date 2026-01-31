package dao;

import db.DBConnection;
import model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultDAO {

    public static boolean addResult(Result result) {
        String sql = "INSERT INTO results (student_id, subject, marks) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, result.getStudentId());
            pst.setString(2, result.getSubject());
            pst.setInt(3, result.getMarks());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
