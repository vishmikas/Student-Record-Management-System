package service;

import dao.StudentDAO;
import model.Student;

import java.util.List;

public class StudentService {

    private static final StudentDAO studentDAO = new StudentDAO();

    public static boolean addStudent(Student student) {

        if (student.getRegNo() == null || student.getRegNo().trim().isEmpty()) {
            System.out.println("Registration number cannot be empty");
            return false;
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            System.out.println("Name cannot be empty");
            return false;
        }

        if (student.getStudentClass() == null || student.getStudentClass().trim().isEmpty()) {
            System.out.println("Student class cannot be empty");
            return false;
        }

        return studentDAO.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentByRegNo(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            return null;
        }
        return studentDAO.getStudentByRegNo(regNo);
    }

    public boolean updateStudent(Student student) {

        if (student.getRegNo() == null || student.getRegNo().trim().isEmpty()) {
            System.out.println("Registration number is required");
            return false;
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            System.out.println("Name cannot be empty");
            return false;
        }

        if (student.getStudentClass() == null || student.getStudentClass().trim().isEmpty()) {
            System.out.println("Student class cannot be empty");
            return false;
        }

        return studentDAO.updateStudent(student);
    }

    public boolean deleteStudent(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            System.out.println("Registration number is required");
            return false;
        }
        return studentDAO.deleteStudent(regNo);
    }
}
