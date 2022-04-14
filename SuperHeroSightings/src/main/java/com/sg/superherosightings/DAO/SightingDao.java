/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.superherosightings.DAO;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Sighting;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface SightingDao 
{
    Sighting getSightingByID(int sightingID);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<Hero> getHeroFromSighting(int sightingID);
}
