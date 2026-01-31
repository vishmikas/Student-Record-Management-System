package service;

import dao.StudentDAO;
import model.Student;

public class StudentService {

    public static boolean addStudent(Student student) {

        if (student.getRegNo() == null || student.getRegNo().isEmpty()) {
            System.out.println("Registration number cannot be empty");
            return false;
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            System.out.println("Name cannot be empty");
            return false;
        }
        if (student.getStudentClass() == null || student.getStudentClass().isEmpty()) {
            System.out.println("Student class cannot be empty");
            return false;
        }

        return StudentDAO.addStudent(student);
    }
}
