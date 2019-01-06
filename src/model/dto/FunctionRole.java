/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class FunctionRole implements Serializable, Comparable<FunctionRole>{
    
    private int id;
    private int roleid;
    private int functionsid;

    public FunctionRole() {
    }

    public FunctionRole(int id, int roleid, int functionsid) {
        this.id = id;
        this.roleid = roleid;
        this.functionsid = functionsid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getFunctionsid() {
        return functionsid;
    }

    public void setFunctionsid(int functionsid) {
        this.functionsid = functionsid;
    }

    @Override
    public String toString() {
        return "FunctionRole{" + "id=" + id + ", roleid=" + roleid + ", functionsid=" + functionsid + '}';
    }
    
    @Override
    public int compareTo(FunctionRole t) {
        return t.getId();
    }
    
}
