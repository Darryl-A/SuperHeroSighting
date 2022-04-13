/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.DAO;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface HeroDao 
{
    Hero getHeroById(int id);
    List<Hero> getAllHeroes();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroById(int id);
    
    List<Superpower> getSuperpowersForHero(Hero hero);
    List<Organization> getOrganizationForHero(Hero hero);
    List<Sighting> getAllSightingsForHero(Hero hero);    
}
