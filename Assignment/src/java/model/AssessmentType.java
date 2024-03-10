/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AssessmentType {

    private int atid;
    private String atname;
    private Subject sub;
    private ArrayList<Grade> grades;

    public float getMarkTotal() {
        float total = 0;
        for (Grade g : grades) {
            if (g.getScore() > 0) {
                total += g.getScore();
            } else {
                return -1;
            }
        }
        return total / grades.size();
    }

    public float getWeightTotal() {
        int total = 0;
        for (Grade g : grades) {
            total+= g.getExam().getAssessment().getWeight();
        }
        return total;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public AssessmentType() {
    }

    public int getAtid() {
        return atid;
    }

    public void setAtid(int atid) {
        this.atid = atid;
    }

    public String getAtname() {
        return atname;
    }

    public void setAtname(String atname) {
        this.atname = atname;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

}
