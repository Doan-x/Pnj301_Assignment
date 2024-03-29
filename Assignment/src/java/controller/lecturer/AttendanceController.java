/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.account.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;
import dal.LessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Attendance;
import model.Lession;
import model.Role;
import model.Student;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AttendanceController", urlPatterns = "/lecturer/att")
public class AttendanceController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        
        int lesid = Integer.parseInt(req.getParameter("id"));
        LessionDBContext db = new LessionDBContext();
        ArrayList<Student> students = db.getStudentsByLession(lesid);
        ArrayList<Attendance> atts = db.getAttendanceByLesid(lesid);
        Lession lession = new Lession();
        lession.setId(lesid);
        
        for (Attendance a : atts) {
            boolean flag = Boolean.parseBoolean(req.getParameter("present" + a.getStudent().getSid()));
            
            if (a.isIsPresent() != flag || a.getCapturedtime() == null) {
                Attendance new_a = new Attendance();
                new_a.setLession(lession);
                new_a.setStudent(a.getStudent());
                new_a.setDescription(req.getParameter("description" + a.getStudent().getSid()));
                new_a.setIsPresent(flag);
                db.takeAttendances(lesid, a.getStudent().getSid(),new_a);
            }
            
        }
        resp.sendRedirect("att?id=" + lesid);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        LessionDBContext les = new LessionDBContext();

        int leid = Integer.parseInt(req.getParameter("id"));
        ArrayList<Attendance> atts = les.getAttendanceByLesid(leid);
        req.setAttribute("atts", atts);
        Date today_raw = new Date();
        java.sql.Date today = DateTimeHelper.convertUtilDateToSqlDate(today_raw);
        req.setAttribute("today", today);
        req.getRequestDispatcher("../view/lecturer/attendance.jsp").forward(req, resp);
    }

}
