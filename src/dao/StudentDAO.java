package dao;

import db.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getString("reg_no"),
                        rs.getString("name"),
                        rs.getString("class")
                );
                list.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getStudentByRegNo(String regNo) {
        String sql = "SELECT * FROM students WHERE reg_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, regNo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("reg_no"),
                        rs.getString("name"),
                        rs.getString("class")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, class = ? WHERE reg_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getName());
            pst.setString(2, student.getStudentClass());
            pst.setString(3, student.getRegNo());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(String regNo) {
        String sql = "DELETE FROM students WHERE reg_no = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, regNo);
            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
