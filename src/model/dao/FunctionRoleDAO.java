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
import model.dto.FunctionRole;
import model.dto.Functions;
import utils.DBConnector;

/**
 *
 * @author USER
 */
public class FunctionRoleDAO {
    final String SQL_CREATE = "INSERT INTO FUNCTIONROLE(ID, ROLEID, FUNCTIONSID) VALUES(?,?,?)";
    final String SQL_READALL = "SELECT * FROM FUNCTIONROLE";
    final String SQL_READBYID = "SELECT * FROM FUNCTIONROLE WHERE FUNCTIONSID = ?";
    final String SQL_UPDATE = "UPDATE FUNCTIONROLE SET ID = ?, ROLEID = ? WHERE FUNCTIONSID = ?";
    final String SQL_DELETE = "DELETE FROM FUNCTIONROLE WHERE FUNCTIONSID = ?";
    List<FunctionRole> l = null;
    Connection con = null;      
    
    public FunctionRoleDAO() {
        con = new DBConnector().getCon();
        l = new ArrayList<>();
    }     
    
    public FunctionRole create(FunctionRole f) {
        PreparedStatement pr ;
        try {
            pr = con.prepareStatement(SQL_CREATE);
            pr.setInt(1, f.getId());
            pr.setInt(2, f.getRoleid());
            pr.setInt(3, f.getFunctionsid());
            pr.execute();
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }     
    
  public List<FunctionRole> readAll() {
        l = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_READALL);
            if(rs != null){
                while(rs.next()){
                    FunctionRole f = new FunctionRole();
                    f.setId(rs.getInt(1));
                    f.setRoleid(rs.getInt(2));
                    f.setFunctionsid(rs.getInt(3));
                    l.add(f);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
  
   public FunctionRole update(FunctionRole f) {
        try {
            PreparedStatement pr = con.prepareStatement(SQL_UPDATE);
            pr.setInt(1, f.getId());
            pr.setInt(2, f.getRoleid());
            pr.setInt(3, f.getFunctionsid());            
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
   
    public boolean delete(int functionsid) {
        try {
            PreparedStatement pr = con.prepareStatement(SQL_DELETE);
            pr.setInt(1, functionsid);
            pr.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
    
    public FunctionRole readById(int functionsid){
        try {
            PreparedStatement pr = con.prepareStatement(SQL_READBYID);
            pr.setInt(1, functionsid);
            ResultSet rs = pr.executeQuery(SQL_READALL);
            if(rs != null){
                FunctionRole f = new FunctionRole();
                while(rs.next()){
                    f.setId(rs.getInt(1));
                    f.setRoleid(rs.getInt(2));
                    f.setFunctionsid(rs.getInt(3));                    
                }
                return f;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }     
}
