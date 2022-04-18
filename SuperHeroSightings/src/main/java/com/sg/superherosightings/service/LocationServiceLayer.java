/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface LocationServiceLayer 
{
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
    
    List<Sighting> getAllSightingsForLocation(Location location);

    public Location createLocation(String locationName, String locaitonDescription, String locationAddress, double locationLongitude, double locationLatitude);
}
