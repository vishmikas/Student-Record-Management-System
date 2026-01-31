package dao;

import db.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {

    public static boolean addStudent(Student student) {
        String sql = "INSERT INTO students (reg_no, name, class) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getRegNo());
            pst.setString(2, student.getName());
            pst.setString(3, student.getStudentClass());

            int row = pst.executeUpdate();
            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
