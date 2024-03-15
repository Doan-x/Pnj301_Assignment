/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import controller.authentication.authorization.BaseRBACController;
import dal.LoginDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public class HomeController extends BaseRBACController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("roles", roles);

        for (Role role : roles) {
            if (role.getName().equals("Lecturer")) {
                LoginDBContext login = new LoginDBContext();
                int lid = login.getLecturerByUserName(account.getUsername()).getLid();
                session.setAttribute("lid", lid);
                break;
            } 
        }
        req.getRequestDispatcher("view/home.jsp").forward(req, resp);
    }

}
