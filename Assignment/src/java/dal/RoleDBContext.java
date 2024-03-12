/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Role;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RoleDBContext extends DBContext<Role> {

    public ArrayList<Role> getRoleByUsernameAndUrl(String username, String url) {
        ArrayList<Role> list = new ArrayList<>();
        try {
            String sql = "select r.roleid, r.rolename\n"
                    + "from Account a\n"
                    + "	inner join Role_Account ra on a.username = ra.username\n"
                    + "	inner join Role r on r.roleid  = ra.roleid\n"
                    + "	inner join Role_Feature rf on rf.roleid = r.roleid\n"
                    + "	inner join Feature f on f.fid = rf.fid\n"
                    + "where f.url = ? and a.username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, url);
            stm.setString(2, username);
            ResultSet rs =stm.executeQuery();
            while(rs.next()){
                Role r = new Role();
                r.setId(rs.getInt("roleid"));
                r.setName(rs.getString("rolename"));
                
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<Role> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Role entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Role get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
