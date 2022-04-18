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
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Blasc
 */
@Component
public class ServiceLayerImpl implements ServiceLayer 
{
 
    private final HeroDao heroDao;
    
    private final LocationDao locationDao;
    
    private final OrganizationDao organizationDao;
    
    private final SightingDao sightingDao;
    
    private final SuperpowerDao superpowerDao;
    
    public ServiceLayerImpl(HeroDao heroDao, LocationDao locationDao, OrganizationDao organizationDao, SightingDao sightingDao, SuperpowerDao superpowerDao) 
    {
        this.heroDao = heroDao;
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superpowerDao = superpowerDao;
    }
    
    //Hero functions
    @Override
    public Hero getHeroById(int heroID) 
    {
        return heroDao.getHeroById(heroID);
    }

    @Override
    public List<Hero> getAllHeroes() 
    {
        return heroDao.getAllHeroes();
    }

    @Override
    public Hero addHero(Hero hero) 
    {
        return heroDao.addHero(hero);
    }

    @Override
    public void updateHero(Hero hero) 
    {
        heroDao.updateHero(hero);
    }

    @Override
    public void deleteHeroById(int id) 
    {
        heroDao.deleteHeroById(id);
    }

    @Override
    public void addHeroSuperpowers(Hero hero) 
    {
        heroDao.addHeroSuperpowers(hero);
    }

    @Override
    public void addHeroSightings(Hero hero) 
    {
        heroDao.addHeroSightings(hero);
    }

    @Override
    public void addHeroOrganizations(Hero hero) 
    {
        heroDao.addHeroOrganizations(hero);
    }

    @Override
    public void assignHeroSuperpowers(List<Hero> heroList) 
    {
        heroDao.assignHeroSuperpowers(heroList);
    }

    @Override
    public void assignHeroSightings(List<Hero> heroList) 
    {
        heroDao.assignHeroSightings(heroList);
    }

    @Override
    public void assignHeroOrganizations(List<Hero> heroList) 
    {
        heroDao.assignHeroOrganizations(heroList);
    }

    @Override
    public List<Superpower> getSuperpowersForHero(int heroID) 
    {
        return heroDao.getSuperpowersForHero(heroID);
    }

    @Override
    public List<Organization> getOrganizationForHero(int heroID) 
    {
        return heroDao.getOrganizationForHero(heroID);
    }

    @Override
    public List<Sighting> getAllSightingsForHero(int heroID) 
    {
        return heroDao.getAllSightingsForHero(heroID);
    }

    @Override
    public Hero createHero(String heroName, boolean isHero, List<Superpower> superpowers, List<Organization> organizations)
    {
        Hero hero = new Hero();
        hero.setHeroName(heroName);
        hero.setIsHero(isHero);
        hero.setHeroSuperpowers(superpowers);
        hero.setHeroOrganizations(organizations);
        return hero;
    }
    //
    // Location functions
    //
    @Override
    public Location getLocationById(int id) {
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

    @Override
    public Location createLocation(String locationName, String locationDescription, String locationAddress, double locationLongitude, double locationLatitude) 
    {
            Location location = new Location();
        location.setLocationName(locationName);
        location.setLocationDescription(locationDescription);
        location.setLocationAddress(locationAddress);
        location.setCoordinates(locationLatitude,locationLongitude);
        return location;
    }
    
    //Organization Functions
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

    @Override
    public Organization createAnOrganzation(String organizationName, String organizationDescription, List<Hero> heroes, String organizationAddress) 
    {
            //Create a new organization
            Organization o = new Organization();
            o.setOrganizationName(organizationName);
            o.setOrganizationDescription(organizationDescription);
            o.setHeroes(heroes);
            o.setOrganizationAddress(organizationAddress);

    return o;
    }
    
    //Sighting Functions
    @Override
    public Sighting getSightingById(int sightingID) 
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

    @Override
    public Sighting createSighting(int heroID, int locationID, LocalDate date) 
    {
        Sighting s  = new Sighting();
        s.setLocationID(locationID);
        s.setHeroID(heroID);
        s.setSightingDate(date);
        return s;
    }
    //Superpower Functions
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
