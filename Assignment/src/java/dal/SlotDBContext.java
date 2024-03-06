/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.TimeSlot;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SlotDBContext extends DBContext<TimeSlot> {

    @Override
    public ArrayList<TimeSlot> list() {
        ArrayList<TimeSlot> slots = new ArrayList<>();
        try {
            String sql = "SELECT [tid]\n"
                    + "      ,[tname]\n"
                    + "  FROM [dbo].[TimeSlot]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TimeSlot ts = new TimeSlot();
                ts.setTid(rs.getInt("tid"));
                ts.setTname(rs.getString("tname"));
                slots.add(ts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slots;
    }
    

    @Override
    public void insert(TimeSlot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(TimeSlot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(TimeSlot entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TimeSlot get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
