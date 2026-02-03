package ui;

import model.Result;
import service.ResultService;

import javax.swing.*;
import java.awt.*;

public class AddResultForm extends JFrame {

    private JTextField studentIdField, subjectField, marksField;
    private JButton addButton;

    public AddResultForm() {
        setTitle("Add Result");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        add(studentIdField);

        add(new JLabel("Subject:"));
        subjectField = new JTextField();
        add(subjectField);

        add(new JLabel("Marks:"));
        marksField = new JTextField();
        add(marksField);

        addButton = new JButton("Add Result");
        add(addButton);

        addButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentIdField.getText());
                String subject = subjectField.getText();
                int marks = Integer.parseInt(marksField.getText());

                Result result = new Result(studentId, subject, marks);
                boolean success = ResultService.addResult(result);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Result Added Successfully!");
                    studentIdField.setText("");
                    subjectField.setText("");
                    marksField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error Adding Result. Check console.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Student ID and Marks must be numbers.");
            }
        });

        setVisible(true);
    }
}
