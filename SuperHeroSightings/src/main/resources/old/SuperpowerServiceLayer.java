/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Superpower;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface SuperpowerServiceLayer 
{
    Superpower getSuperpowerById(int id);
    List<Superpower> getAllSuperpowers();
    Superpower addSuperpower(Superpower superpower);
    void updateSuperpower(Superpower superpower);
    void deleteSuperpowerById(int id);
    
    List<Hero> getHeroWithSuperpowers(int superpowerID);
}
