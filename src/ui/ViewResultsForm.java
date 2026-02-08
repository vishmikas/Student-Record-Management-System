package ui;

import model.Result;
import service.ResultService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewResultsForm extends JFrame {

    private JTextField studentIdField, subjectField, marksField;
    private JButton searchButton, updateButton, deleteButton;
    private JTable table;
    private DefaultTableModel tableModel;

    private int selectedResultId = -1;

    public ViewResultsForm() {
        setTitle("View Results");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Student ID:"));

        studentIdField = new JTextField(10);
        topPanel.add(studentIdField);

        searchButton = new JButton("Search");
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(
                new String[]{"Result ID", "Student ID", "Subject", "Marks"}, 0
        );
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        formPanel.add(subjectField);

        formPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        formPanel.add(marksField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchResults());
        updateButton.addActionListener(e -> updateResult());
        deleteButton.addActionListener(e -> deleteResult());

        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        refreshTable();
        setVisible(true);
    }

    private void refreshTable() {
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
            refreshTable();
            return;
        }

        try {
            int studentId = Integer.parseInt(input);
            List<Result> results = ResultService.getResultsByStudentId(studentId);

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No results found");
                refreshTable();
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
            refreshTable();
        }
    }


    private void loadSelectedRow() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        selectedResultId = (int) tableModel.getValueAt(row, 0);
        subjectField.setText(tableModel.getValueAt(row, 2).toString());
        marksField.setText(tableModel.getValueAt(row, 3).toString());
    }

    private void updateResult() {
        if (selectedResultId == -1) {
            JOptionPane.showMessageDialog(this, "Select a result to update");
            return;
        }

        try {
            String subject = subjectField.getText().trim();
            int marks = Integer.parseInt(marksField.getText().trim());

            Result result = new Result();
            result.setResultId(selectedResultId);
            result.setSubject(subject);
            result.setMarks(marks);

            boolean success = ResultService.updateResult(result);

            if (success) {
                JOptionPane.showMessageDialog(this, "Result updated successfully");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be a number");
        }
    }

    private void deleteResult() {
        if (selectedResultId == -1) {
            JOptionPane.showMessageDialog(this, "Select a result to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this result?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = ResultService.deleteResult(selectedResultId);

            if (success) {
                JOptionPane.showMessageDialog(this, "Result deleted");
                refreshTable();

                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed");
            }
        }
    }

    private void clearFields() {
        subjectField.setText("");
        marksField.setText("");
        selectedResultId = -1;
    }

}
