/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.general_features;

import controller.authentication.authorization.BaseRBACController;
import dal.GroupDBContext;
import dal.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Department;
import model.Role;
import model.Semester;
import model.Student;
import model.StudentGroup;
import model.Subject;

/**
 *
 * @author Admin
 */
public class GroupController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        SubjectDBContext sdb = new SubjectDBContext();
        ArrayList<Department> departments = sdb.getDepartment();
        ArrayList<Semester> semesteres = sdb.getSemester();
        req.setAttribute("departments", departments);
        req.setAttribute("semesteres", semesteres);

        String semid_raw = req.getParameter("semid") == null ? "" : req.getParameter("semid");
        String did_raw = req.getParameter("did") == null ? "" : req.getParameter("did");
        String subid_raw = req.getParameter("subid") == null? "": req.getParameter("subid");
        try {
            int semid = Integer.parseInt(semid_raw);
            req.setAttribute("semid", semid);
        } catch (Exception e) {
        }
        GroupDBContext gdb = new GroupDBContext();
        try {
            int semid = Integer.parseInt(semid_raw);
            int did = Integer.parseInt(did_raw);
            ArrayList<Subject> subjects = sdb.getSubjectBySemIdAndDid(semid, did);
            req.setAttribute("subjects", subjects);
            req.setAttribute("did", did);
            try {
                int subid = Integer.parseInt(subid_raw);
                ArrayList<StudentGroup> group = gdb.getGroupBySubid(subid);
                req.setAttribute("group", group);
                req.setAttribute("subid", subid);
            } catch (Exception e) {
            }            
        } catch (Exception e) {
        }
        String gid_raw = req.getParameter("gid") == null?"":req.getParameter("gid");
        try {
            int gid = Integer.parseInt(gid_raw);
            ArrayList<Student> students = gdb.getStudentByGid(gid);
            req.setAttribute("gid", gid);
            req.setAttribute("students", students);
        } catch (Exception e) {
        }
        
        req.getRequestDispatcher("view/group.jsp").forward(req, resp);

    }

}
