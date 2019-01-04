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
import utils.DBConnector;

/**
 *
 * @author USER
 */
public class RolesDAO {

    final String SQL_CREATE = "INSERT INTO ROLES(ROLEID, ROLENAME, DESCRIPTION) VALUES(?,?,?)";
    final String SQL_READALL = "SELECT * FROM ROLES";
    final String SQL_READBYID = "SELECT * FROM ROLES WHERE ROLEID = ?";
    final String SQL_UPDATE = "UPDATE ROLES SET ROLENAME = ?, DESCRIPTION = ? WHERE ROLEID = ?";
    final String SQL_DELETE = "DELETE FROM ROLES WHERE ROLEID = ?";
    List<Roles> l = null;
    Connection con = null;

    public RolesDAO() {
        con = new DBConnector().getCon();
        l = new ArrayList<>();
    }

    public Roles create(Roles r) {
        PreparedStatement pr ;
        try {
            pr = con.prepareStatement(SQL_CREATE);
            pr.setInt(1, r.getRoleid());
            pr.setString(2, r.getRolename());
            pr.setString(3, r.getDescription());
            pr.execute();
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Roles> readAll() {
        l = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_READALL);
            if(rs != null){
                while(rs.next()){
                    Roles r = new Roles();
                    r.setRoleid(rs.getInt(1));
                    r.setRolename(rs.getString(2));
                    r.setDescription(rs.getString(3));
                    l.add(r);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Roles update(Roles r) {
        try {
            PreparedStatement pr = con.prepareStatement(SQL_UPDATE);
            pr.setString(1, r.getRolename());
            pr.setString(2, r.getDescription());
            pr.setInt(3, r.getRoleid());
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
    
    public Roles readById(int id){
        try {
            PreparedStatement pr = con.prepareStatement(SQL_READBYID);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery(SQL_READALL);
            if(rs != null){
                Roles r = new Roles();
                while(rs.next()){
                    r.setRoleid(rs.getInt(1));
                    r.setRolename(rs.getString(2));
                    r.setDescription(rs.getString(3));
                }
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
