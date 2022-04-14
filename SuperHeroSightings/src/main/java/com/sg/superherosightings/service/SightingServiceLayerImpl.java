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
import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Blasc
 */
public class SightingServiceLayerImpl implements SightingServiceLayer 
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
    public Sighting getSightingByID(int sightingID) 
    {
        return sightingDao.getSightingByID(sightingID);
    }

    @Override
    public List<Sighting> getAllSightings() 
    {
        return sightingDao.getAllSightings();
    }

    @Override
    public Sighting addSighting(Sighting sighting) 
    {
        return sightingDao.addSighting(sighting);
    }

    @Override
    public void updateSighting(Sighting sighting) 
    {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public void deleteSightingById(int id) 
    {
        sightingDao.deleteSightingById(id);
    }

    @Override
    public List<Hero> getHeroFromSighting(int sightingID) 
    {
        return sightingDao.getHeroFromSighting(sightingID);
    }
}
