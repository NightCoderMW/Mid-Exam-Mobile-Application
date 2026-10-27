package com.example.midexam;

public class StudentModel {
    private int id;
    private String name;
    private String studentId;
    private boolean isEnrolled;

    // Constructor (with ID for Edit/Delete)
    public StudentModel(int id, String name, String studentId, boolean isEnrolled) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.isEnrolled = isEnrolled;
    }

    // Constructor add new data student
    public StudentModel(String name, String studentId, boolean isEnrolled) {
        this.name = name;
        this.studentId = studentId;
        this.isEnrolled = isEnrolled;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public boolean isEnrolled() { return isEnrolled; }

    public void setName(String name) { this.name = name; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setEnrolled(boolean enrolled) { isEnrolled = enrolled; }
}
