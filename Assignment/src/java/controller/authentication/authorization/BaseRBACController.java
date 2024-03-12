/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication.authorization;

import controller.account.BaseRequiredAuthenticationController;
import dal.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Role;

/**
 *
 * @author Admin
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    public ArrayList<Role> getRoles(HttpServletRequest req, Account account) {
        String url = req.getServletPath();
        RoleDBContext rdb = new RoleDBContext();
        ArrayList<Role> roles = rdb.getRoleByUsernameAndUrl(account.getUsername(), url);
        return roles;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = getRoles(req, account);
        if (roles.size() < 1) {
            resp.getWriter().println("Acces denied");
        } else {
            doPost(req, resp, account, roles);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = getRoles(req, account);
        if (roles.size() < 1) {
            resp.getWriter().println("Acces denied");
        } else {
            doGet(req, resp, account, roles);
        }
    }
    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)throws ServletException, IOException ;
    // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)throws ServletException, IOException;
    // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
