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
            subid = Integer.parseInt(subid_raw);
            GradeDBContext gdb = new GradeDBContext();
            ArrayList<Grade> grades = gdb.getGradeBySidAndSubID(subid, 1);
            req.setAttribute("grades", grades);

        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("../view/student/grade.jsp").forward(req, resp);
    }

}
