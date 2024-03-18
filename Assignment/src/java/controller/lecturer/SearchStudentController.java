/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecturer;

import controller.authentication.authorization.BaseRBACController;
import dal.LessionDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Role;
import model.Student;

/**
 *
 * @author Admin
 */
public class SearchStudentController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        String sname = req.getParameter("sname").isBlank() ? "" : req.getParameter("sname");
        String sid_raw = req.getParameter("sid").isBlank() ? "" : req.getParameter("sid");
        String action = req.getParameter("action").isBlank() ? "" : req.getParameter("action");
        try{
            int sid = Integer.parseInt(sid_raw);
            resp.sendRedirect("");
        }catch(NumberFormatException e){
            
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        String seach = req.getParameter("search").isBlank() ? "" : req.getParameter("search");
        StudentDBContext sdb = new StudentDBContext();
        ArrayList<Student> students = sdb.searchStudent(seach);
        req.setAttribute("students", students);
        req.getRequestDispatcher("../view/home.jsp").forward(req, resp);
    }

}
