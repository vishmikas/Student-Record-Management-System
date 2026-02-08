package dao;

import db.DBConnection;
import model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Result> getResultsByStudentId(int studentId) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM results WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Result result = new Result();
                result.setResultId(rs.getInt("result_id"));
                result.setStudentId(rs.getInt("student_id"));
                result.setSubject(rs.getString("subject"));
                result.setMarks(rs.getInt("marks"));

                results.add(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public static List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM results";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Result result = new Result();
                result.setResultId(rs.getInt("result_id"));
                result.setStudentId(rs.getInt("student_id"));
                result.setSubject(rs.getString("subject"));
                result.setMarks(rs.getInt("marks"));

                results.add(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public static boolean updateResult(Result result) {
        String sql = "UPDATE results SET subject = ?, marks = ? WHERE result_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, result.getSubject());
            pst.setInt(2, result.getMarks());
            pst.setInt(3, result.getResultId());

            int row = pst.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteResult(int resultId) {
        String sql = "DELETE FROM results WHERE result_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, resultId);

            int row = pst.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
