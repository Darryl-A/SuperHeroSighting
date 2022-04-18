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
import com.sg.superherosightings.entities.Sighting;
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
public class HeroServiceLayerImpl implements HeroServiceLayer 
{

    @Autowired 
    HeroDao heroDao;
    
//    @Autowired
//    LocationDao locationDao;
//    
//    @Autowired 
//    OrganizationDao organizationDao;
//    
//    @Autowired 
//    SightingDao sightingDao;
//    
//    @Autowired
//    SuperpowerDao superpowerDao;
    public HeroServiceLayerImpl(@Lazy HeroDao heroDao) 
    {
        this.heroDao = heroDao;
    }
    
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
}
