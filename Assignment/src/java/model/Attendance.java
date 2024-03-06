/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Attendance {
    private int aid;
    private Lession lession;
    private Student student;
    private String description;
    private boolean isPresent;
    private Date capturedtime;

    public Attendance() {
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Lession getLession() {
        return lession;
    }

    public void setLession(Lession lession) {
        this.lession = lession;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public Date getCapturedtime() {
        return capturedtime;
    }

    public void setCapturedtime(Date capturedtime) {
        this.capturedtime = capturedtime;
    }
    
}
