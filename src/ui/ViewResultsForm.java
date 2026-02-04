package ui;

import model.Result;
import service.ResultService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewResultsForm extends JFrame {

    private JTextField studentIdField;
    private JButton loadButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewResultsForm() {
        setTitle("View Results");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Student ID:"));

        studentIdField = new JTextField(10);
        topPanel.add(studentIdField);

        loadButton = new JButton("Search");
        topPanel.add(loadButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[]{"Result ID", "Student ID", "Subject", "Marks"}, 0
        );
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadButton.addActionListener(e -> searchResults());

        loadAllResults();

        setVisible(true);
    }

    private void loadResults() {
        tableModel.setRowCount(0);

        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            List<Result> results = ResultService.getResultsByStudentId(studentId);

            if (results == null || results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No results found");
                return;
            }

            for (Result r : results) {
                tableModel.addRow(new Object[]{
                        r.getResultId(),
                        r.getSubject(),
                        r.getMarks()
                });
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Student ID must be a number");
        }
    }

    private void loadAllResults() {
        tableModel.setRowCount(0);

        List<Result> results = ResultService.getAllResults();

        for (Result r : results) {
            tableModel.addRow(new Object[]{
                    r.getResultId(),
                    r.getStudentId(),
                    r.getSubject(),
                    r.getMarks()
            });
        }
    }

    private void searchResults() {
        tableModel.setRowCount(0);

        String input = studentIdField.getText().trim();

        if (input.isEmpty()) {
            loadAllResults();
            return;
        }

        try {
            int studentId = Integer.parseInt(input);
            List<Result> results = ResultService.getResultsByStudentId(studentId);

            if (results == null || results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No results found for this student");
                return;
            }

            for (Result r : results) {
                tableModel.addRow(new Object[]{
                        r.getResultId(),
                        r.getStudentId(),
                        r.getSubject(),
                        r.getMarks()
                });
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Student ID must be a number");
        }
    }

}
