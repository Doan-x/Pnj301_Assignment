/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Subject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Semester;

/**
 *
 * @author Admin
 */
public class SubjectDBContext extends DBContext<Subject> {

    public ArrayList<Subject> getSubjectBySemIdAndDid(int semid, int did) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select s.subid, s.suname\n"
                    + "from Subject s\n"
                    + "inner join Department d on s.did =d.did\n"
                    + "inner join Semester se on se.semid = s.subid\n"
                    + "where s.semid = ? and s.did = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, semid);
            stm.setInt(2, did);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubid(rs.getInt("subid"));
                s.setSubname(rs.getString("suname"));
                list.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Subject> getSubjectByDId(int did) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select*\n"
                    + "from Subject s\n"
                    + "inner join Department d on s.did =d.did\n"
                    + "where d.did =? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, did);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubid(rs.getInt("sid"));
                s.setSubname(rs.getString("sname"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Department> getDepartment() {
        ArrayList<Department> list = new ArrayList<>();
        try {
            String sql = "SELECT [did]\n"
                    + "      ,[dname]\n"
                    + "  FROM [dbo].[Department]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                list.add(d);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Subject> getSubjectBySemID(int semid, int sid) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select sub.subid ,sub.suname,sub.semid \n"
                    + "                     from [Subject] sub\n"
                    + "						\n"
                    + "                     	 inner join Semester se on se.semid = sub.semid \n"
                    + "						 inner join StudentGroup sg on sg.subid = sub.subid\n"
                    + "						 inner join Enrollment e on e.gid = sg.gid\n"
                    + "						 inner join Student s on s.sid = e.sid\n"
                    + "                     where se.semid = ? and s.sid =?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, semid);
            stm.setInt(2, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject sub = new Subject();
                sub.setSubid(rs.getInt("subid"));
                sub.setSubname(rs.getString("suname"));
                Semester sem = new Semester();
                sem.setId(rs.getInt("semid"));
                sub.setSemester(sem);
                list.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Semester> getSemester() {
        ArrayList<Semester> list = new ArrayList<>();
        try {
            String sql = "SELECT [semid]\n"
                    + "      ,[semname]\n"
                    + "  FROM [dbo].[Semester]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Semester s = new Semester();
                s.setId(rs.getInt("semid"));
                s.setName(rs.getString("semname"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public void insert(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Subject> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
