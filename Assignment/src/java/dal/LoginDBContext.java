/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Account;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Student;

/**
 *
 * @author Admin
 */
public class LoginDBContext extends DBContext<Account> {

    public Account check(String username, String password) {
        try {
            String sql = "Select displayname, [password], username\n"
                    + "from Account \n"
                    + "where username = ? and password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account ac = new Account();
                ac.setUsername(rs.getString("username"));
                ac.setPassword(rs.getString("password"));
                ac.setDisplayname(rs.getString("displayname"));
                return ac;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lecturer getLecturerByUserName(String username) {
        try {
            String sql = "SELECT [lid]\n"
                    + "      ,[lname]\n"
                    + "      ,[username]\n"
                    + "  FROM [dbo].[Lecturer]\n"
                    + "where username = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Lecturer lec = new Lecturer();
                lec.setLid(rs.getInt("lid"));
                lec.setLname(rs.getString("lname"));
                lec.setUsername(username);
                return lec;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public static void main(String[] args) {
        LoginDBContext l = new LoginDBContext();
           System.out.println(l.getLecturerByUserName("sonnt").getLid());
    }
  
        public Student getStudentByUserName(String username) {
        try {
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[username]\n"
                    + "  FROM [dbo].[Student]\n"
                    + "where username = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Student lec = new Student();
                lec.setSid(rs.getInt("sid"));
                lec.setSname(rs.getString("sname"));
                lec.setUsername(username);
                return lec;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
