package com.example.myksvit;

public class FacultyProfile {

    public String facultyphoneno;
    public String facultyEmail;
    public String facultyName;
    public String facultyDept;


    public FacultyProfile(){
    }

    public FacultyProfile(String facultyphoneno, String facultyEmail, String facultyName,  String facultyDept) {
        this.facultyphoneno = facultyphoneno;
        this.facultyEmail = facultyEmail;
        this.facultyName = facultyName;
        this.facultyDept= facultyDept;
    }

    public String getFacultyphoneno() {
        return facultyphoneno;
    }

    public void setFacultyphoneno(String facultyphoneno) {
        this.facultyphoneno = facultyphoneno;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyDept() {
        return facultyDept;
    }

    public void setFacultyDept(String facultyDept) {
        this.facultyDept = facultyDept;
    }
}
