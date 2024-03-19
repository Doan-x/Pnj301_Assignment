/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Student;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;

/**
 *
 * @author Admin
 */
public class StudentDBContext extends DBContext<Student> {

    public Attendance getAttendanceBySID(int sid) {
        try {
            String sql = "select a.isPresent, s.sid\n"
                    + "from Student s \n"
                    + "inner join Attendence a on s.sid = a.sid\n"
                    + "Where s.sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Attendance a = new Attendance();
                a.setIsPresent(rs.getBoolean("isPresent"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Student getStudentBySid(int sid) {
        try {
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[username]\n"
                    +",avatar"
                    + "  FROM [dbo].[Student]\n"
                    + "  where sid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setSid(sid);
                s.setSname(rs.getString("sname"));
                s.setUrl(rs.getString("avatar"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Student> searchStudent(String search) {
        ArrayList<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[username],avatar\n"
                    + "  FROM [dbo].[Student]\n"
                    + "  where sid like ? or sname like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+search+"%");
            stm.setString(2, "%"+search+"%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setUrl(rs.getString("avatar"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
