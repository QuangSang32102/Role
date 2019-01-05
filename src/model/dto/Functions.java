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
public class Functions implements Serializable, Comparable<Functions>{
    private int functionsid;
    private String functionname;
    private String description;

    public Functions() {
    }

    public int getFunctionsid() {
        return functionsid;
    }

    public void setFunctionsid(int functionsid) {
        this.functionsid = functionsid;
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Functions{" + "functionsid=" + functionsid + ", functionname=" + functionname + ", description=" + description + '}';
    }

    @Override
    public int compareTo(Functions t) {
        return t.getFunctionname().compareTo(functionname);
    }
    
}
