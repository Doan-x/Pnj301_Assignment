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
import model.Semester;

/**
 *
 * @author Admin
 */
public class SubjectDBContext extends DBContext<Subject> {

    public ArrayList<Subject> getSubjectBySemID(int semid) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "select sub.subid ,sub.suname,sub.semid\n"
                    + "from [Subject] sub \n"
                    + "	 inner join Semester se on se.semid = sub.semid\n"
                    + "where se.semid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, semid);
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
            ResultSet rs =  stm.executeQuery();
            while(rs.next()){
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

    public static void main(String[] args) {
        SubjectDBContext subdb = new SubjectDBContext();
        ArrayList<Subject> s = subdb.getSubjectBySemID(4);
        System.out.println(s.get(0).getSemester().getId());
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
