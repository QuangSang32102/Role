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
import model.dto.Functions;
import utils.DBConnector;

/**
 *
 * @author USER
 */
public class FunctionsDAO {
    final String SQL_CREATE = "INSERT INTO FUNCTIONS(FUNCTIONSID, FUNCTIONNAME, DESCRIPTION) VALUES(?,?,?)";
    final String SQL_READALL = "SELECT * FROM FUNCTIONS";
    final String SQL_READBYID = "SELECT * FROM FUNCTIONS WHERE FUNCTIONSID = ?";
    final String SQL_UPDATE = "UPDATE FUNCTIONS SET FUNCTIONNAME = ?, DESCRIPTION = ? WHERE FUNCTIONSID = ?";
    final String SQL_DELETE = "DELETE FROM FUNCTIONS WHERE FUNCTIONSID = ?";
    List<Functions> l = null;
    Connection con = null;    
    
    public FunctionsDAO() {
        con = new DBConnector().getCon();
        l = new ArrayList<>();
    }    
    
    public Functions create(Functions f) {
        PreparedStatement pr ;
        try {
            pr = con.prepareStatement(SQL_CREATE);
            pr.setInt(1, f.getFunctionsid());
            pr.setString(2, f.getFunctionname());
            pr.setString(3, f.getDescription());
            pr.execute();
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    
  public List<Functions> readAll() {
        l = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_READALL);
            if(rs != null){
                while(rs.next()){
                    Functions f = new Functions();
                    f.setFunctionsid(rs.getInt(1));
                    f.setFunctionname(rs.getString(2));
                    f.setDescription(rs.getString(3));
                    l.add(f);
                }
                return l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   

   public Functions update(Functions f) {
        try {
            PreparedStatement pr = con.prepareStatement(SQL_UPDATE);
            pr.setString(1, f.getFunctionname());
            pr.setString(2, f.getDescription());
            pr.setInt(3, f.getFunctionsid());            
            pr.executeUpdate();
            return f;
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

    public Functions readById(int functionsid){
        try {
            PreparedStatement pr = con.prepareStatement(SQL_READBYID);
            pr.setInt(1, functionsid);
            ResultSet rs = pr.executeQuery(SQL_READALL);
            if(rs != null){
                Functions f = new Functions();
                while(rs.next()){
                    f.setFunctionsid(rs.getInt(1));
                    f.setFunctionname(rs.getString(2));
                    f.setDescription(rs.getString(3));
                }
                return f;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }         
}
