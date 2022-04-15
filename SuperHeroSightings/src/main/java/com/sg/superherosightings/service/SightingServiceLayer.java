/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Sighting;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface SightingServiceLayer 
{
    Sighting getSightingById(int sightingID);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);
    List<Hero> getHeroFromSighting(int sightingID);

    public Sighting createSighting(int heroID, int locationID, LocalDate aDate);
}
