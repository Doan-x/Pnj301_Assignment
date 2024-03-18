/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import model.AssessmentType;
import model.CourseraTotal;
import model.Grade;

/**
 *
 * @author Admin
 */
public class ManageCourseraTotal {

    public CourseraTotal checkStatus(ArrayList<AssessmentType> at) {
        CourseraTotal ct = new CourseraTotal();
        float avg = 0;
        int count=0;
        for (int i = 0; i < at.size(); i++) {
            AssessmentType a = at.get(i);
            if (a.getMarkTotal() >= 0) {
                if (a.getAtname().endsWith("Resit".toUpperCase())) {
                    float grade_fe = at.get(i-1).getMarkTotal();
                    avg += (a.getMarkTotal() -grade_fe) * a.getWeightTotal();
                }
                avg += a.getMarkTotal() * a.getWeightTotal()/100;
                count++;
            }
        }        
        if(count == at.size()-1){
            ct.setAverage(avg);
        }else{
            ct.setAverage(-1);
        }
        ct.setSubject(at.get(0).getSub());
        ct.setStudent(at.get(0).getGrades().get(0).getStudent());
        if (avg < 4 && avg
                >= 0 && checkZeroMark(at)) {
            ct.setStatus(-1);
        } else if (avg >= 4 && avg
                <= 10 && checkZeroMark(at)) {
            ct.setStatus(1);
        }
        return ct;
    }

    public boolean checkZeroMark(ArrayList<AssessmentType> assessmentType) {
        for (AssessmentType i : assessmentType) {
            if (i.getMarkTotal()<= 0) {
                if (i.getAtname().toUpperCase().endsWith("Resit".toUpperCase())){
                    return true;
                }
                return false;                
            }
        }
        return true;
    }
}
