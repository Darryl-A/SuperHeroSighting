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
public class Organization 
{
    private int organizationID;
    private String organizationName;
    private String organizationDescription;
    private String organizationAddress;
    private List<Hero> heroes;

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 37 * hash + this.organizationID;
        hash = 37 * hash + Objects.hashCode(this.organizationName);
        hash = 37 * hash + Objects.hashCode(this.organizationDescription);
        hash = 37 * hash + Objects.hashCode(this.organizationAddress);
        hash = 37 * hash + Objects.hashCode(this.heroes);
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
        final Organization other = (Organization) obj;
        if (this.organizationID != other.organizationID) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDescription, other.organizationDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizationAddress, other.organizationAddress)) {
            return false;
        }
        return Objects.equals(this.heroes, other.heroes);
    }

    public List<Hero> getHeroes() 
    {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) 
    {
        this.heroes = heroes;
    }

    public int getOrganizationID() 
    {
        return organizationID;
    }
    public void setOrganizationID(int id) 
    {
        this.organizationID = id;
    }

    public String getOrganizationName() 
    {
        return organizationName;
    }

    public void setOrganizationName(String name) 
    {
        this.organizationName = name;
    }

    public String getOrganizationDescription() 
    {
        return organizationDescription;
    }

    public void setOrganizationDescription(String description) 
    {
        this.organizationDescription = description;
    }

    public String getOrganizationAddress() 
    {
        return organizationAddress;
    }

    public void setOrganizationAddress(String address) 
    {
        this.organizationAddress = address;
    }
}
