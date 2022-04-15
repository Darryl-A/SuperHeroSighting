/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.DAO.HeroDao;
import com.sg.superherosightings.DAO.LocationDao;
import com.sg.superherosightings.DAO.OrganizationDao;
import com.sg.superherosightings.DAO.SightingDao;
import com.sg.superherosightings.DAO.SuperpowerDao;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Blasc
 */
public class LocationServiceLayerImpl implements LocationServiceLayer 
{
    @Autowired 
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired 
    OrganizationDao organizationDao;
    
    @Autowired 
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;

    @Override
    public Location getLocationById(int id) 
    {
        return locationDao.getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() 
    {
        return locationDao.getAllLocation();
    }

    @Override
    public Location addLocation(Location location) 
    {
        return locationDao.addLocation(location);
    }

    @Override
    public void updateLocation(Location location) 
    {
        locationDao.updateLocation(location);
    }

    @Override
    public void deleteLocationById(int id) 
    {
        locationDao.deleteLocationById(id);
    }

    @Override
    public List<Sighting> getAllSightingsForLocation(Location location) 
    {
        return locationDao.getAllSightingsForLocation(location);
    }
}
