package model;

public class Result {
    private int resultId;
    private int studentId;
    private String subject;
    private int marks;

    public Result() {}

    public Result(int studentId, String subject, int marks) {
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    public int getResultId() {
        return resultId;
    }
    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
}
