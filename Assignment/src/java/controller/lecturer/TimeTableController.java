/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.account.BaseRequiredAuthenticationController;
import dal.LecturerDBContext;
import dal.LessionDBContext;
import dal.LoginDBContext;
import dal.SlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Lecturer;
import model.Lession;
import model.TimeSlot;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
public class TimeTableController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        SlotDBContext sdb = new SlotDBContext();
        ArrayList<TimeSlot> slots = sdb.list();
        String from_raw = req.getParameter("from");
        String to_raw = req.getParameter("to");
        
        LessionDBContext ldb = new LessionDBContext();
        String lid_raw = req.getParameter("lid");
        int lid =-1;
        if(lid_raw == null || lid_raw.isEmpty()){
            LoginDBContext login = new LoginDBContext();
            lid = login.getLecturerByUserName(account.getUsername()).getLid();
        }else{
            lid = Integer.parseInt(lid_raw);
        }
        java.sql.Date from = null;
        java.sql.Date to = null;
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

        
        ArrayList<Lession> les = ldb.getLessionByLid(lid, from, to);
        
        // retrieve lecturer information
        LecturerDBContext lecturerDB = new LecturerDBContext();
        Lecturer lecturer = lecturerDB.checkLid(lid);
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("lession", les);
        req.setAttribute("account", account);
        req.setAttribute("lid", lid);
        req.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(req, resp);
    }
        public static void main(String[] args) {
        LecturerDBContext l = new LecturerDBContext();
        Lecturer lec = l.checkLid(1);
        System.out.println(lec.getLid() + lec.getLname());
    }

}
