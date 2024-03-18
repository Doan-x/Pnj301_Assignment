/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Lession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Lecturer;
import model.Room;
import model.Student;
import model.StudentGroup;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author Admin
 */
public class LessionDBContext extends DBContext<Lession> {

    public ArrayList<Attendance> getAttendanceByLesid(int leid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "                 s.sid,s.sname, s.avatar,\n"
                    + "			g.gname,lec.lname,\n"
                    + "                 a.aid,a.description,a.isPresent,a.capturedtime \n"
                    + "                 FROM Student s INNER JOIN Enrollment e ON s.sid = e.sid \n"
                    + "                 INNER JOIN StudentGroup g ON g.gid = e.gid\n"
                    + "			inner join Subject sub on sub.subid=g.subid\n"
                    + "                 INNER JOIN Lession les ON les.gid = g.gid \n"
                    + "                 inner join Lecturer lec on lec.lid = les.lid"
                    + "                 LEFT JOIN Attendence a ON a.leid = les.leid AND a.sid = s.sid "
                    + "WHERE les.leid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Student s = new Student();
                Lession les = new Lession();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setUrl(rs.getString("avatar"));
                a.setStudent(s);
                
                StudentGroup sg = new StudentGroup();
                sg.setGname(rs.getString("gname"));
                les.setGroup(sg);
                
                Lecturer lec = new Lecturer();
                lec.setLname(rs.getString("lname"));                
                les.setLecturer(lec);
                a.setLession(les);
                
                a.setAid(rs.getInt("aid"));
                if (a.getAid() != 0) {
                    a.setDescription(rs.getString("description"));
                    a.setIsPresent(rs.getBoolean("isPresent"));
                    a.setCapturedtime(rs.getTimestamp("capturedtime"));
                }
                atts.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    public ArrayList<Lession> getLessionByLid(int lid, Date from, Date to) {
        ArrayList<Lession> lessions = new ArrayList<>();
        try {
            String sql = "select\n"
                    + "les.leid,les.isAttended,les.date,\n"
                    + "sg.gid,sg.gname,\n"
                    + "sub.subid,sub.suname,sub.credit,\n"
                    + "ts.tid,ts.tname,ts.time,\n"
                    + "r.rid,r.rname,\n"
                    + "lt.lid,lt.lname\n"
                    + "from lession les \n"
                    + "inner join StudentGroup sg on les.gid = sg.gid\n"
                    + "inner join Subject sub on sub.subid = sg.subid \n"
                    + "inner join TimeSlot ts on ts.tid = les.tid\n"
                    + "inner join Room r on r.rid = les.rid\n"
                    + "inner join Lecturer lt on lt.lid = les.lid\n"
                    + "where les.lid = ? and les.date >= ? and les.date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lession les = new Lession();
                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                StudentGroup sg = new StudentGroup();
                Subject sub = new Subject();
                sub.setSubid(rs.getInt("subid"));
                sub.setSubname(rs.getString("suname"));
                sub.setCredit(rs.getInt("credit"));
                sg.setSubject(sub);
                sg.setGid(rs.getInt("gid"));
                sg.setGname(rs.getString("gname"));
                les.setGroup(sg);

                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                les.setRoom(r);

                Lecturer lt = new Lecturer();
                lt.setLid(rs.getInt("lid"));
                lt.setLname(rs.getString("lname"));
                les.setLecturer(lt);

                TimeSlot ts = new TimeSlot();
                ts.setTid(rs.getInt("tid"));
                ts.setTname(rs.getString("tname"));
                ts.setTime(rs.getString("time"));
                les.setSlot(ts);

                lessions.add(les);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessions;
    }

    @Override
    public ArrayList<Lession> list() {
        return null;
    }

    @Override
    public void insert(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lession entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lession get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Student> getStudentsByLession(int lesid) {
        ArrayList<Student> students = new ArrayList<>();
        try {

            String sql = "SELECT \n"
                    + "s.sid,s.sname\n"
                    + "FROM Student s INNER JOIN Enrollment e ON s.sid = e.sid\n"
                    + "						INNER JOIN StudentGroup g ON g.gid = e.gid\n"
                    + "						INNER JOIN Lession les ON les.gid = g.gid\n"
                    + "WHERE les.leid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setSid(rs.getInt("sid"));
                st.setSname(rs.getString("sname"));
                students.add(st);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public void takeAttendances(int leid, int sid, Attendance att) {
        try {
            connection.setAutoCommit(false);
            String sql_remove_att = "DELETE Attendence WHERE leid = ? and sid = ?";
            PreparedStatement stm_remove_att = connection.prepareStatement(sql_remove_att);
            stm_remove_att.setInt(1, leid);
            stm_remove_att.setInt(2, sid);
            stm_remove_att.executeUpdate();

            String sql_insert_att = "INSERT INTO [Attendence]\n"
                    + "           ([leid]\n"
                    + "           ,[sid]\n"
                    + "           ,[description]\n"
                    + "           ,[isPresent]\n"
                    + "           ,[capturedtime])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
            stm_insert_att.setInt(1, leid);
            stm_insert_att.setInt(2, att.getStudent().getSid());
            stm_insert_att.setString(3, att.getDescription());
            stm_insert_att.setBoolean(4, att.isIsPresent());
            stm_insert_att.executeUpdate();

            String sql_update_lession = "UPDATE Lession SET isAttended = 1 WHERE leid =?";
            PreparedStatement stm_update_lession = connection.prepareStatement(sql_update_lession);
            stm_update_lession.setInt(1, leid);
            stm_update_lession.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Lession> getLessionBySid(int sid, Date from, Date to) {
        ArrayList<Lession> lessions = new ArrayList<>();
        try {
            String sql = "select "
                    + "       les.leid,les.isAttended,les.date,\n"
                    + "       sg.gid,sg.gname,\n"
                    + "       sub.subid,sub.suname,sub.credit,\n"
                    + "       ts.tid,ts.tname,ts.time,\n"
                    + "       r.rid,r.rname,\n"
                    + "       lt.lid,lt.lname\n"
                    + "from student s inner join Enrollment e on s.sid = e.sid\n"
                    + "			   inner join StudentGroup sg on e.gid =sg.gid\n"
                    + "			   inner join [Subject] sub on sg.subid = sub.subid\n"
                    + "			   inner join Lession les on sg.gid = les.gid\n"
                    + "			   inner join Lecturer lt on lt.lid = les.lid\n"
                    + "			   inner join TimeSlot ts on ts.tid = les.tid\n"
                    + "			   inner join Room r on r.rid = les.rid\n"
                    + "where s.sid = ? and les.date >= ? and les.date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lession les = new Lession();
                les.setId(rs.getInt("leid"));
                les.setAttended(rs.getBoolean("isAttended"));
                les.setDate(rs.getDate("date"));

                StudentGroup sg = new StudentGroup();
                Subject sub = new Subject();
                sub.setSubid(rs.getInt("subid"));
                sub.setSubname(rs.getString("suname"));
                sub.setCredit(rs.getInt("credit"));
                sg.setSubject(sub);
                sg.setGid(rs.getInt("gid"));
                sg.setGname(rs.getString("gname"));
                les.setGroup(sg);

                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                les.setRoom(r);

                Lecturer lt = new Lecturer();
                lt.setLid(rs.getInt("lid"));
                lt.setLname(rs.getString("lname"));
                les.setLecturer(lt);

                TimeSlot ts = new TimeSlot();
                ts.setTid(rs.getInt("tid"));
                ts.setTname(rs.getString("tname"));
                ts.setTime(rs.getString("time"));
                les.setSlot(ts);

                lessions.add(les);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessions;
    }

}
