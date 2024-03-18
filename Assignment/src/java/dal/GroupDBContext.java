/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.StudentGroup;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author Admin
 */
public class GroupDBContext extends DBContext<StudentGroup> {

    public ArrayList<Student> getStudentByGid(int gid) {
        ArrayList<Student> list= new ArrayList<>();
        try {
            String sql = "select s.sid, s.sname, s.avatar\n"
                    + "from StudentGroup sg\n"
                    + "inner join Enrollment e on e.gid = sg.gid\n"
                    + "inner join Student s on s.sid = e.sid\n"
                    + "where  sg.gid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setUrl(rs.getString("avatar"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        GroupDBContext gdb = new GroupDBContext();
        ArrayList<Student> s = gdb.getStudentByGid(1);
        System.out.println(s.get(2).getUrl());
    }

    public ArrayList<StudentGroup> getGroupBySubid(int subid) {
        ArrayList<StudentGroup> list = new ArrayList<>();
        try {
            String sql = "select sg.gid, sg.gname\n"
                    + "from StudentGroup sg \n"
                    + "inner join Subject su on su.subid = sg.subid\n"
                    + "where su.subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentGroup sg = new StudentGroup();
                sg.setGid(rs.getInt("gid"));
                sg.setGname(rs.getString("gname"));
                list.add(sg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<StudentGroup> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(StudentGroup entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(StudentGroup entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(StudentGroup entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentGroup get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
