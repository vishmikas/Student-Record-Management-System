package model;

public class Student {
    private int studentId;
    private String regNo;
    private String name;
    private String studentClass;

    public Student() {}

    public Student(String regNo, String name, String studentClass) {
        this.regNo = regNo;
        this.name = name;
        this.studentClass = studentClass;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
