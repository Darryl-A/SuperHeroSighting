/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.util.Objects;


/**
 *
 * @author Blasc
 */
public class Superpower 
{

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.superpowerID;
        hash = 37 * hash + Objects.hashCode(this.superpowerName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superpower other = (Superpower) obj;
        if (this.superpowerID != other.superpowerID) {
            return false;
        }
        return Objects.equals(this.superpowerName, other.superpowerName);
    }
    private int superpowerID;
    private String superpowerName;

    public int getSuperpowerID() 
    {
        return superpowerID;
    }

    public void setSuperpowerID(int id) 
    {
        this.superpowerID = id;
    }

    public String getSuperpowerName() 
    {
        return superpowerName;
    }

    public void setSuperpowerName(String name) 
    {
        this.superpowerName = name;
    }
}
