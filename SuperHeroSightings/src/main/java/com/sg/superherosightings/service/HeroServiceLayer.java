/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface HeroServiceLayer 
{
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
}
