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
import com.sg.superherosightings.entities.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 *
 * @author Blasc
 */
@Component
public class SuperpowerServiceLayerImpl implements SuperpowerServiceLayer
{

//    @Autowired 
//    HeroDao heroDao;
//    
//    @Autowired
//    LocationDao locationDao;
//    
//    @Autowired 
//    OrganizationDao organizationDao;
//    
//    @Autowired 
//    SightingDao sightingDao;
    @Autowired
    SuperpowerDao superpowerDao;

    public SuperpowerServiceLayerImpl(@Lazy SuperpowerDao superpowerDao) 
    {
        this.superpowerDao = superpowerDao;
    }    
    
    @Override
    public Superpower getSuperpowerById(int id) 
    {
        return superpowerDao.getSuperpowerById(id);
    }

    @Override
    public List<Superpower> getAllSuperpowers() 
    {
        return superpowerDao.getAllSuperpowers();
    }

    @Override
    public Superpower addSuperpower(Superpower superpower) 
    {
        return superpowerDao.addSuperpower(superpower);
    }

    @Override
    public void updateSuperpower(Superpower superpower) 
    {
        superpowerDao.updateSuperpower(superpower);
    }

    @Override
    public void deleteSuperpowerById(int id) 
    {
        superpowerDao.deleteSuperpowerById(id);
    }

    @Override
    public List<Hero> getHeroWithSuperpowers(int superpowerID) 
    {
        return superpowerDao.getHeroWithSuperpowers(superpowerID);
    }
}
