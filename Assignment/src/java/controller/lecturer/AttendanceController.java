/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import controller.account.BaseRequiredAuthenticationController;
import dal.LessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Lession;
import model.Student;

/**
 *
 * @author Admin
 */
public class AttendanceController extends BaseRequiredAuthenticationController {
   
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
               int lesid = Integer.parseInt(req.getParameter("id"));
        LessionDBContext db = new LessionDBContext();
        ArrayList<Student> students = db.getStudentsByLession(lesid);
        ArrayList<Attendance> atts = new ArrayList<>();
        Lession lession = new Lession();
        lession.setId(lesid);
        for (Student student : students) {
            Attendance a = new Attendance();
            a.setLession(lession);
            a.setStudent(student);
            a.setDescription(req.getParameter("description"+student.getSid()));
            a.setIsPresent(req.getParameter("present"+student.getSid()).equals("yes"));
            atts.add(a);
        }
        db.takeAttendances(lesid, atts);
        resp.sendRedirect("att?id="+lesid); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        LessionDBContext les = new LessionDBContext();
        int leid = Integer.parseInt(req.getParameter("id"));
        ArrayList<Attendance> atts = les.getAttendanceByLesid(leid);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("../view/lecturer/attendance.jsp").forward(req, resp);
    }

}
