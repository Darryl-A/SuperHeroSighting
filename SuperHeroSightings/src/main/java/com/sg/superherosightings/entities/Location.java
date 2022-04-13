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
public class Location 
{

    private int locationID;
    private String locationName;
    private String locationDescription;
    private String locationAddress;
    private int latitude;
    private int longitude;
    private List<Sighting> sightings;

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 37 * hash + this.locationID;
        hash = 37 * hash + Objects.hashCode(this.locationName);
        hash = 37 * hash + Objects.hashCode(this.locationDescription);
        hash = 37 * hash + Objects.hashCode(this.locationAddress);
        hash = 37 * hash + this.latitude;
        hash = 37 * hash + this.longitude;
        hash = 37 * hash + Objects.hashCode(this.sightings);
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
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (this.latitude != other.latitude) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        return Objects.equals(this.sightings, other.sightings);
    }

    public List<Sighting> getSightings() 
    {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }
    public int getId() 
    {
        return locationID;
    }

    public void setId(int id) 
    {
        this.locationID = id;
    }

    public String getName() 
    {
        return locationName;
    }

    public void setName(String name) 
    {
        this.locationName = name;
    }

    public String getDescription() 
    {
        return locationDescription;
    }

    public void setDescription(String description) 
    {
        this.locationDescription = description;
    }

    public String getAddress() 
    {
        return locationAddress;
    }

    public void setAddress(String address) 
    {
        this.locationAddress = address;
    }

    public void setCoordinates(int latitude, int longitude) 
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLongitude() 
    {
        return longitude;
    }
    public int getLatitude() 
    {
        return latitude;
    }
}
