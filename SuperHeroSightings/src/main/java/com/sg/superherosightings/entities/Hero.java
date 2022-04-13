/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Blasc
 */
public class Hero 
{

    private int heroID;
    private String heroName;
    private String heroDescription;
    private boolean isHero = true;
    private List<Superpower> superpowers;
    private List<Organization> organizations;
    private List<Sighting> sightings;

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 79 * hash + this.heroID;
        hash = 79 * hash + Objects.hashCode(this.heroName);
        hash = 79 * hash + Objects.hashCode(this.heroDescription);
        hash = 79 * hash + Objects.hashCode(this.superpowers);
        hash = 79 * hash + Objects.hashCode(this.organizations);
        hash = 79 * hash + Objects.hashCode(this.sightings);
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
        final Hero other = (Hero) obj;
        if (this.heroID != other.heroID) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.heroDescription, other.heroDescription)) {
            return false;
        }
        if (!Objects.equals(this.superpowers, other.superpowers)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return Objects.equals(this.sightings, other.sightings);
    }

    public int getId() 
    {
        return heroID;
    }

    public void setId(int id) 
    {
        this.heroID = id;
    }

    public String getName() 
    {
        return heroName;
    }

    public void setName(String name) 
    {
        this.heroName = name;
    }

    public String getDescription() 
    {
        return heroDescription;
    }

    public void setDescription(String description) 
    {
        this.heroDescription = description;
    }
    
       public List<Superpower> getSuperpowers() 
    {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) 
    {
        this.superpowers = superpowers;
    }

    public List<Organization> getOrganizations() 
    {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) 
    {
        this.organizations = organizations;
    }
    
    public List<Sighting> getSightings() 
    {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) 
    {
        this.sightings = sightings;
    }

    public boolean getHeroStatus(){
        return this.isHero;
    }

    public void setHeroStatus(boolean status){
        this.isHero = status;
    }
}
