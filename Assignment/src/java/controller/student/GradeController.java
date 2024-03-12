/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.account.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;
import dal.GradeDBContext;
import dal.LoginDBContext;
import dal.StudentDBContext;
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
import model.CourseraTotal;
import model.Grade;
import model.Role;
import model.Semester;
import model.Student;
import model.Subject;
import util.ManageCourseraTotal;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GradeController", urlPatterns = {"/student/grade"})
public class GradeController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        String semid_raw = req.getParameter("semid");
        int semid = Integer.parseInt(semid_raw);
        SubjectDBContext subdb = new SubjectDBContext();

        ArrayList<Semester> semesters = subdb.getSemester();
        req.setAttribute("semesters", semesters);
        // get subject
        ArrayList<Subject> subjects = subdb.getSubjectBySemID(semid);
        int subid;
        String subid_raw = req.getParameter("subid");

        // get student
        String sid_raw = req.getParameter("sid");
        int sid = -1;
        if (sid_raw == null || sid_raw.isEmpty()) {
            LoginDBContext login = new LoginDBContext();
            sid = login.getStudentByUserName(account.getUsername()).getSid();
        } else {
            sid = Integer.parseInt(sid_raw.trim());
        }
        StudentDBContext stdb = new StudentDBContext();
        Student student = stdb.getStudentBySid(sid);
        req.setAttribute("student", student);

        if (subid_raw == null || subid_raw == "") {
            req.setAttribute("subjects", subjects);
            req.setAttribute("semid", semid);
            req.getRequestDispatcher("../view/student/grade.jsp").forward(req, resp);
        } else {
            subid = Integer.parseInt(subid_raw);
            GradeDBContext gdb = new GradeDBContext();
            ArrayList<AssessmentType> ass = gdb.getAssessmentTypeBySubID(subid);
            for (AssessmentType as : ass) {
                ArrayList<Grade> gr = gdb.getGradeBySubidAndSidAndAtid(subid, sid, as.getAtid());
                as.setGrades(gr);
            }
            ArrayList<Grade> listgrade = gdb.getGradeBySidAndSubID(sid, subid);
            ManageCourseraTotal mct = new ManageCourseraTotal();
            CourseraTotal courseratotal = mct.checkStatus(ass);

            req.setAttribute("courseratotal", courseratotal);
            req.setAttribute("ass", ass);
            req.setAttribute("subjects", subjects);
            req.setAttribute("semid", semid);
            req.setAttribute("subid", subid);

            req.getRequestDispatcher("../view/student/grade.jsp").forward(req, resp);
        }
    }

}
