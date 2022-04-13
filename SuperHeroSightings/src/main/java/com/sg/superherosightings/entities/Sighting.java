/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.security.Timestamp;
import java.util.Objects;

/**
 *
 * @author Blasc
 */
public class Sighting 
{

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.sightings);
        hash = 97 * hash + Objects.hashCode(this.hero);
        hash = 97 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.id != other.id) 
        {
            return false;
        }
        if (!Objects.equals(this.sightings, other.sightings)) 
        {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) 
        {
            return false;
        }
        return Objects.equals(this.location, other.location);
    }
    private int id;

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public Timestamp getSightings() 
    {
        return sightings;
    }

    public void setSightings(Timestamp sightings) 
    {
        this.sightings = sightings;
    }

    public Hero getHero() 
    {
        return hero;
    }

    public void setHero(Hero hero) 
    {
        this.hero = hero;
    }

    public Location getLocation() 
    {
        return location;
    }

    public void setLocation(Location location) 
    {
        this.location = location;
    }
    Timestamp sightings;
    private Hero hero;
    private Location location;
}
