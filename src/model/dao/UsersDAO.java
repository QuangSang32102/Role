/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Roles;
import model.dto.Users;
import utils.DBConnector;

/**
 *
 * @author USER
 */
public class UsersDAO {
    final String SQL_CREATE = "INSERT INTO USERS(ID, USERSNAME, PASSWORD, ROLEID) VALUES(?,?,?,?)";
    final String SQL_READALL = "SELECT * FROM USERS";
    final String SQL_READBYID = "SELECT * FROM USERS WHERE ROLEID = ?";
    final String SQL_UPDATE = "UPDATE USERS SET ID = ?, USERSNAME = ?, DESCRIPTION = ? WHERE ROLEID = ?";
    final String SQL_DELETE = "DELETE FROM USERS WHERE ROLEID = ?";
    List<Users> l = null;
    Connection con = null; 
    
    public UsersDAO() {
        con = new DBConnector().getCon();
        l = new ArrayList<>();
    }
    
    public Users create(Users u) {
        PreparedStatement pr ;
        try {
            pr = con.prepareStatement(SQL_CREATE);
            pr.setInt(1, u.getId());
            pr.setString(2, u.getUsersname());
            pr.setString(3, u.getPassword());
            pr.setInt(4, u.getRoleid());
            pr.execute();
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    
  public List<Users> readAll() {
        l = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_READALL);
            if(rs != null){
                while(rs.next()){
                    Users u = new Users();
                    u.setId(rs.getInt(1));
                    u.setUsersname(rs.getString(2));
                    u.setPassword(rs.getString(3));
                    u.setRoleid(rs.getInt(4));
                    l.add(u);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
  
   public Users update(Users u) {
        try {
            PreparedStatement pr = con.prepareStatement(SQL_UPDATE);
            pr.setInt(1, u.getId());
            pr.setString(2, u.getUsersname());
            pr.setString(3, u.getPassword());
            pr.setInt(4, u.getRoleid());
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
   
    public boolean delete(int roleid) {
        try {
            PreparedStatement pr = con.prepareStatement(SQL_DELETE);
            pr.setInt(1, roleid);
            pr.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Users readById(int id){
        try {
            PreparedStatement pr = con.prepareStatement(SQL_READBYID);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery(SQL_READALL);
            if(rs != null){
                Users u = new Users();
                while(rs.next()){
                    u.setId(rs.getInt(1));
                    u.setUsersname(rs.getString(2));
                    u.setPassword(rs.getString(3));
                    u.setRoleid(rs.getInt(4));
                }
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
}
