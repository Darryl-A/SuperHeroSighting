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
    private int superpowerID;
    private String superpowerName;

    @Override
    public int hashCode() 
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Superpower other = (Superpower) obj;
        if (this.superpowerID != other.superpowerID) 
        {
            return false;
        }
        return Objects.equals(this.superpowerName, other.superpowerName);
    }
    public int getId() 
    {
        return superpowerID;
    }

    public void setId(int id) 
    {
        this.superpowerID = id;
    }

    public String getName() 
    {
        return superpowerName;
    }

    public void setName(String name) 
    {
        this.superpowerName = name;
    }
}
