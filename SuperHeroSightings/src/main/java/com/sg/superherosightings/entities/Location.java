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
    private double latitude;
    private double longitude;
    private List<Sighting> sightings;

    public List<Sighting> getSightings() 
    {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }
    public int getLocationID() 
    {
        return locationID;
    }

    public void setLocationID(int id) 
    {
        this.locationID = id;
    }

    public String getLocationName() 
    {
        return locationName;
    }

    public void setLocationName(String name) 
    {
        this.locationName = name;
    }

    public String getLocationDescription() 
    {
        return locationDescription;
    }

    public void setLocationDescription(String description) 
    {
        this.locationDescription = description;
    }

    public String getLocationAddress() 
    {
        return locationAddress;
    }

    public void setLocationAddress(String address) 
    {
        this.locationAddress = address;
    }

    public void setCoordinates(double latitude, double longitude) 
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() 
    {
        return longitude;
    }
    public double getLatitude() 
    {
        return latitude;
    }
}
