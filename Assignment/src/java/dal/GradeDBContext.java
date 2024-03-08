/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Grade;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Exam;
import model.Semester;
import model.Student;
import model.Subject;

/**
 *
 * @author Admin
 */
public class GradeDBContext extends DBContext<Grade> {

    public ArrayList<Grade> getGradeBySidAndSubID(int sid, int subid) {
        ArrayList<Grade> list = new ArrayList<>();
        try {
            String sql = "select  sub.subid, sub.suname, sub.semid, sub.credit,\n"
                    + "		a.weight, a.name,\n"
                    + "		e.DateBegin, e.DateEnd,\n"
                    + "		g.score,\n"
                    + "		s.sid, s.sname\n"
                    + "from Student s \n"
                    + "	inner join Grade g on s.sid = g.sid\n"
                    + "	inner join Exam e on g.eid = e.eid\n"
                    + "	inner join Assessment a on a.assid = e.assid\n"
                    + "	inner join Subject sub on sub.subid = a.subid\n"
                    + "where s.sid = ? and sub.subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, subid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Grade g = new Grade();
                Assessment ass = new Assessment();
                Exam e = new Exam();
                Subject sub = new Subject();
                Student st = new Student();
                
                g.setScore(rs.getFloat("score")); 
                
                st.setSid(sid);
                st.setSname(rs.getString("sname"));
                g.setStudent(st);                
                            
                sub.setSubid(subid);
                sub.setSubname(rs.getString("suname"));
                sub.setCredit(rs.getInt("credit"));
                Semester sem = new Semester();
                sem.setId(rs.getInt("semid"));
                sub.setSemester(sem);
                
                ass.setSubject(sub);                
                ass.setName(rs.getString("name"));
                ass.setWeight(rs.getFloat("weight"));
                
                e.setDateBegin(rs.getTimestamp("DateBegin"));
                e.setDateEnd(rs.getTimestamp("DateEnd"));  
                e.setAssessment(ass);
                g.setExam(e);
                
                list.add(g);
                                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;        
    }

    @Override
    public ArrayList<Grade> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Grade entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Grade get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
