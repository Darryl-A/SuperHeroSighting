/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.security.Timestamp;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Blasc
 */
public class Sighting 
{
    private int sightingID;
    private Date sightingDate;
    private int heroID;
    private int locationID;
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 61 * hash + this.sightingID;
        hash = 61 * hash + Objects.hashCode(this.sightingDate);
        hash = 61 * hash + this.heroID;
        hash = 61 * hash + this.locationID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (this.heroID != other.heroID) {
            return false;
        }
        if (this.locationID != other.locationID) {
            return false;
        }
        return Objects.equals(this.sightingDate, other.sightingDate);
    }    
    public int getSightingID() 
    {
        return sightingID;
    }

    public void setSightingID(int id) 
    {
        this.sightingID = id;
    }

    public Date getSightingDate() 
    {
        return sightingDate;
    }

    public void setSightingDate(Date sightingDate) 
    {
        this.sightingDate = sightingDate;
    }

    public int getHeroID() 
    {
        return heroID;
    }

    public void setHeroID(int heroID) 
    {
        this.heroID = heroID;
    }

    public int getLocationID() 
    {
        return locationID;
    }

    public void setLocationID(int locationID) 
    {
        this.locationID = locationID;
    }
}
