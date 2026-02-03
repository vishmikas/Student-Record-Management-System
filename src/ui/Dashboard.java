package ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private JButton addStudentBtn;
    private JButton addResultBtn;
    private JButton viewResultsBtn;

    public Dashboard() {
        setTitle("Student Result Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(3, 1, 15, 15));

        addStudentBtn = new JButton("Add Student");
        addResultBtn = new JButton("Add Result");
        viewResultsBtn = new JButton("View Results");

        add(addStudentBtn);
        add(addResultBtn);
        add(viewResultsBtn);

        addStudentBtn.addActionListener(e -> new AddStudentForm());
        addResultBtn.addActionListener(e -> new AddResultForm());
        viewResultsBtn.addActionListener(e -> new ViewResultsForm());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
