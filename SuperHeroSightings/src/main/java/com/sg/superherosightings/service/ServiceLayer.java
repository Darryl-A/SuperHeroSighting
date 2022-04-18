/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface ServiceLayer 
{
    //Hero functions
    Hero getHeroById(int heroID);
    List<Hero> getAllHeroes();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroById(int id);
    
    void addHeroSuperpowers(Hero hero);
    void addHeroSightings(Hero hero);
    void addHeroOrganizations(Hero hero);
   
    void assignHeroSuperpowers(List<Hero> heroList);
    void assignHeroSightings(List<Hero> heroList);
    void assignHeroOrganizations(List<Hero> heroList);
    
    List<Superpower> getSuperpowersForHero(int heroID);
    List<Organization> getOrganizationForHero(int heroID);
    List<Sighting> getAllSightingsForHero(int heroID);

    public Hero createHero(String heroName, boolean hero, List<Superpower> superpowers, List<Organization> organizations);
    
    //Location functions
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
    
    List<Sighting> getAllSightingsForLocation(Location location);

    public Location createLocation(String locationName, String locaitonDescription, String locationAddress, double locationLongitude, double locationLatitude);
    
    //Organization functions
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
    
    List<Hero> getHeroesFromOrganization(int organizationID);

    public Organization createAnOrganzation(String organizationName, String organizationDescription, List<Hero> Hero, String organizationAddress);
    
    //Sighting functions
    Sighting getSightingById(int sightingID);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<Hero> getHeroFromSighting(int sightingID);

    public Sighting createSighting(int heroID, int locationID, LocalDate aDate);
    
    //Superpower functions
    Superpower getSuperpowerById(int id);
    List<Superpower> getAllSuperpowers();
    Superpower addSuperpower(Superpower superpower);
    void updateSuperpower(Superpower superpower);
    void deleteSuperpowerById(int id);
    
    List<Hero> getHeroWithSuperpowers(int superpowerID);
}
