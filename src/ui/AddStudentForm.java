package ui;

import model.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;

public class AddStudentForm extends JFrame {

    private JTextField regNoField, nameField, classField;
    private JButton addButton;

    public AddStudentForm() {
        setTitle("Add Student");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Reg No:"));
        regNoField = new JTextField();
        add(regNoField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Class:"));
        classField = new JTextField();
        add(classField);

        addButton = new JButton("Add Student");
        add(addButton);

        addButton.addActionListener(e -> {
            String regNo = regNoField.getText();
            String name = nameField.getText();
            String studentClass = classField.getText();

            Student student = new Student(regNo, name, studentClass);
            boolean success = StudentService.addStudent(student);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student Added Successfully!");
                regNoField.setText("");
                nameField.setText("");
                classField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error Adding Student. Check console.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddStudentForm();
    }
}
