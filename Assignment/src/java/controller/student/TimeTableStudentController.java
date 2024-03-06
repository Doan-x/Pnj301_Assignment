/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.account.BaseRequiredAuthenticationController;
import dal.LessionDBContext;
import dal.LoginDBContext;
import dal.SlotDBContext;
import dal.StudentDBContext;
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
import model.Lession;
import model.Student;
import model.TimeSlot;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TimeTableStudentController", urlPatterns = {"/student/timetable"})
public class TimeTableStudentController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        SlotDBContext sdb = new SlotDBContext();
        ArrayList<TimeSlot> slots = sdb.list();

        String from_raw = req.getParameter("from");
        String to_raw = req.getParameter("to");
        java.sql.Date from = null;
        java.sql.Date to = null;
        
        LoginDBContext login = new LoginDBContext();
        int sid = login.getStudentByUserName(account.getUsername()).getSid();
        
        Date today = new Date();
        if (from_raw == null || from_raw.isEmpty()) {
            from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.addDaysToDate(DateTimeHelper.getWeekStart(today), 1));
        } else {
            from = java.sql.Date.valueOf(from_raw);
        }

        if (to_raw == null || to_raw.isEmpty()) {
            to = DateTimeHelper.convertUtilDateToSqlDate(
                    DateTimeHelper.addDaysToDate(DateTimeHelper.getWeekStart(today), 6));
        } else {
            to = java.sql.Date.valueOf(to_raw);
        }

        ArrayList<java.sql.Date> dates = DateTimeHelper.getListBetween(
                DateTimeHelper.convertSqlDateToUtilDate(from),
                DateTimeHelper.convertSqlDateToUtilDate(to));

        LessionDBContext ldb = new LessionDBContext();
        ArrayList<Lession> les = ldb.getLessionBySid(sid, from, to);
        
        
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("lession", les);
        req.setAttribute("account", account);
        req.getRequestDispatcher("../view/student/stimetable.jsp").forward(req, resp);
    }

    public static void main(String[] args) {
        LessionDBContext ldb = new LessionDBContext();
        java.sql.Date from = null;
        java.sql.Date to = null;
        Date today = new Date();
        from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.addDaysToDate(
                DateTimeHelper.getWeekStart(today), 1));
        to = DateTimeHelper.convertUtilDateToSqlDate(
                DateTimeHelper.addDaysToDate(DateTimeHelper.getWeekStart(today), 6));
        ArrayList<Lession> les = ldb.getLessionBySid(1, from, to);
        System.out.println(les.get(0).getLecturer().getLname());
    }

}
