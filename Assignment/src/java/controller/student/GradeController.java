/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.account.BaseRequiredAuthenticationController;
import dal.GradeDBContext;
import dal.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.AssessmentType;
import model.Grade;
import model.Subject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GradeController", urlPatterns = {"/student/grade"})
public class GradeController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String semid_raw = req.getParameter("semid");
        int semid = Integer.parseInt(semid_raw);
        SubjectDBContext subdb = new SubjectDBContext();
        ArrayList<Subject> subjects = subdb.getSubjectBySemID(semid);
        int subid;
        String subid_raw = req.getParameter("subid");       
        
        
        if (subid_raw == null || subid_raw=="") {
            req.setAttribute("subjects", subjects);
            req.getRequestDispatcher("../view/student/grade.jsp").forward(req, resp);
        } else {
            subid = Integer.parseInt(subid_raw);
            GradeDBContext gdb = new GradeDBContext();
            ArrayList<AssessmentType> ass = gdb.getAssessmentTypeBySubID(subid);
            double mark=-1;
            for (AssessmentType as : ass) {
                ArrayList<Grade> gr = gdb.getGradeBySubidAndSidAndAtid(subid, 1, as.getAtid());                
                as.setGrades(gr);
            }
            
            req.setAttribute("ass", ass);
            req.setAttribute("subjects", subjects);
            req.getRequestDispatcher("../view/student/grade.jsp").forward(req, resp);
        }
    }

}
