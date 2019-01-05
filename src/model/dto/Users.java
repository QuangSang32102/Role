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
public class Users implements Serializable, Comparable<Users>{
    
    private int id;
    private String usersname;
    private String password;
    private int roleid;

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsersname() {
        return usersname;
    }

    public void setUsersname(String usersname) {
        this.usersname = usersname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", usersname=" + usersname + ", password=" + password + ", roleid=" + roleid + '}';
    }
    
    @Override
    public int compareTo(Users t) {
        return t.getUsersname().compareTo(usersname);
    }
    
}
