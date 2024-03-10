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
import model.AssessmentType;
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
            String sql = "select  sub.subid, sub.suname, sub.semid, sub.credit,                    	\n"
                    + "        e.DateBegin, e.DateEnd,\n"
                    + "        g.score,\n"
                    + "        s.sid, s.sname\n"
                    + "        from Student s \n"
                    + "                    inner join Grade g on s.sid = g.sid\n"
                    + "                    inner join Exam e on g.eid = e.eid\n"
                    + "                    inner join Assessment a on a.assid = e.assid\n"
                    + "                    inner join Subject sub on sub.subid = a.subid\n"
                    + "                    where s.sid = ? and sub.subid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, subid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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

    public ArrayList<AssessmentType> getAssessmentTypeBySubID(int subid) {
        ArrayList<AssessmentType> list = new ArrayList<>();
        try {
            String sql = "select at.atid, at.atname\n"
                    + "from Subject sub \n"
                    + "	inner join AssessmentType at on at.subid = sub.subid\n"
                    + "where sub.subid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AssessmentType at = new AssessmentType();
                at.setAtid(rs.getInt("atid"));
                at.setAtname(rs.getString("atname"));
                list.add(at);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Grade> getGradeBySubidAndSidAndAtid(int subid, int sid, int atid) {
        ArrayList<Grade> l = new ArrayList<>();
        try {
            String sql = "select  sub.suname, sub.credit, sub.semid,\n"
                    + "		a.item, a.weight,\n"
                    + "		e.DateBegin, e.DateEnd,\n"
                    + "		g.score,g.gid,\n"
                    + "		s.sid, s.sname\n"
                    + " from Subject sub\n"
                    + "         inner join AssessmentType at on at.subid = sub.subid\n"
                    + "         inner join Assessment a  on a.atid = at.atid\n"
                    + "         inner join Exam e on e.assid = a.assid\n"
                    + "         inner join Grade g on g.eid = e.eid\n"
                    + "         inner join Student s on s.sid =g.sid\n"
                    + "where sub.subid = ? and s.sid = ? and at.atid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subid);
            stm.setInt(2, sid);
            stm.setInt(3, atid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                Assessment ass = new Assessment();
                Exam e = new Exam();
                Subject sub = new Subject();
                Student st = new Student();

                g.setId(rs.getInt("gid"));
                if(checkGradeNull(g.getId())){
                    g.setScore(-1);
                }else{
                    g.setScore(rs.getFloat("score"));
                }

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
                ass.setItem(rs.getString("item"));
                ass.setWeight(rs.getInt("weight"));
                e.setDateBegin(rs.getTimestamp("DateBegin"));
                e.setDateEnd(rs.getTimestamp("DateEnd"));
                e.setAssessment(ass);
                g.setExam(e);
                l.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public boolean checkGradeNull(int gid) {
        try {
            String sql = "Select gid,score\n"
                    + "from grade\n"
                    + "where score is null and gid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        GradeDBContext gdb = new GradeDBContext();
        ArrayList<Grade> g = gdb.getGradeBySubidAndSidAndAtid(1, 1, 6);
        System.out.println(g.get(0).getExam().getAssessment().getItem());
        System.out.println(g.get(0).getExam().getAssessment().getWeight());
        System.out.println(gdb.checkGradeNull(1));;

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
