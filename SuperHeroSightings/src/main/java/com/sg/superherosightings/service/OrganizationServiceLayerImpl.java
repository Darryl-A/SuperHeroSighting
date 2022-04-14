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
import com.sg.superherosightings.entities.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Blasc
 */
public class OrganizationServiceLayerImpl implements OrganizationServiceLayer 
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
    public Organization getOrganizationById(int id) 
    {
        return organizationDao.getOrganizationById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() 
    {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public Organization addOrganization(Organization organization) 
    {
        return organizationDao.addOrganization(organization);
    }

    @Override
    public void updateOrganization(Organization organization) 
    {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public void deleteOrganizationById(int id) 
    {
        organizationDao.deleteOrganizationById(id);
    }

    @Override
    public List<Hero> getHeroesFromOrganization(int organizationID) 
    {
        return organizationDao.getHeroesFromOrganization(organizationID);
    }
}
