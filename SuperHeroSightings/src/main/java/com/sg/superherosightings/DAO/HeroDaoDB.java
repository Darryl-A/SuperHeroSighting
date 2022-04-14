/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.DAO;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Blasc
 */
@Repository
public class HeroDaoDB implements HeroDao 
{
    @Autowired
   JdbcTemplate jdbc;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    @Autowired
    SuperpowerDaoDB superpowerDaob;
            
    @Override
    public Hero getHeroById(int heroID) 
    {
        try 
        {    
            final String SELECT_HERO_BY_ID = "SELECT * FROM Hero WHERE heroID = ?";    
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), heroID);    
            hero.setHeroSuperpowers(getSuperpowersForHero(heroID));    
            hero.setHeroSightings(getAllSightingsForHero(heroID));    
            hero.setHeroOrganizations(getOrganizationForHero(heroID));    
            return hero;
        } 
        catch (DataAccessException ex) 
        {    
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() 
    {
        final String GET_ALL_HEROS = "SELECT * FROM Hero";
        List<Hero> heros = jdbc.query(GET_ALL_HEROS, new HeroMapper());
        assignHeroSuperpowers(heros);
        assignHeroSightings(heros);
        assignHeroOrganizations(heros);
        return heros;
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) 
    {
        final String INSERT_HERO = "INSERT INTO Hero(heroName, isHero, heroDescription) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_HERO,
                hero.getHeroName(),
                hero.isHero(),
                hero.getHeroDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroID(newId);
        addHeroSuperpowers(hero);
        addHeroSightings(hero);
        addHeroOrganizations(hero);
        return hero;
    }
     /**
     * Adds a superpower to the bridge table
     * @param hero
     */
    @Override
    public void addHeroSuperpowers(Hero hero)
    {
        final String query = "INSERT INTO HeroSuperpower(heroID,superpowerID) VALUES(?,?)";

        //Loop and add each superpower
        for (Superpower sp: hero.getSuperpowers())
        {
                jdbc.update(query, hero.getHeroID(), sp.getSuperpowerID());
        }
    }

    /**
     * Adds a sighting to the bridge table
     * @param hero
     */
    @Override
    public void addHeroSightings(Hero hero)
    {
        final String query = "INSERT INTO Sighting(sightings, heroID, location) VALUES(?,?)";
        for (Sighting s: hero.getHeroSightings())
        {
            jdbc.update(query, s.getSightingID(), hero.getHeroID(), s.getLocationID());
            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            s.setSightingID(newId);
        }

        //Loop and add each superpower
        for (Superpower sp: hero.getSuperpowers())
        {
            jdbc.update(query, hero.getHeroID(), sp.getSuperpowerID());
        }
    }

    /**
     * Adds an organization to the bridge table
     * @param hero
     */
    @Override
    public void addHeroOrganizations(Hero hero)
    {
        final String query = "INSERT INTO HeroOrganization(heroID, organizationID) VALUES(?,?)";

        //Loop and add each superpower
        for (Organization org: hero.getHeroOrganizations()){
            jdbc.update(query, hero.getHeroID(), org.getOrganizationID());
        }
    }
    
    
    
    @Override
    public void updateHero(Hero hero) 
    {
        final String UPDATE_HERO = "UPDATE Hero SET heroName = ?, heroDescription = ?, IsHero = ?"
                + "WHERE heroID = ?";
        jdbc.update(UPDATE_HERO,
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.isHero());

        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE heroID = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, hero.getHeroID());
        addHeroSuperpowers(hero);

        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE heroID = ?";
        jdbc.update(DELETE_SIGHTING, hero.getHeroID());
        addHeroSightings(hero);

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE heroID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, hero.getHeroID());
        addHeroOrganizations(hero);
    }

    @Override
    @Transactional
    public void deleteHeroById(int heroID)
    {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE heroID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, heroID);

        final String DELETE_HEROSIGHTING = "DELETE FROM Sighting WHERE heroID = ?";
        jdbc.update(DELETE_HEROSIGHTING, heroID);

        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE heroID = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, heroID);

        final String DELETE_HERO = "DELETE FROM Hero WHERE heroID = ?";
        jdbc.update(DELETE_HERO, heroID);
    }

    @Override
    public List<Superpower> getSuperpowersForHero(int heroID)
    {
        final String GET_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s "
                + "JOIN HeroSuperpower hs ON hs.superpowerID = s.superpowerID WHERE hs.heroID = ?";
        return jdbc.query(GET_SUPERPOWERS_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), heroID);
    }

    @Override
    public List<Organization> getOrganizationForHero(int heroID)
    {
        final String GET_ORGANIZATION_FOR_HERO = "SELECT o.* FROM Organization o "
                + "JOIN HeroOrganization ho ON ho.organizationID = o.organizationID WHERE ho.heroID = ?";
        return jdbc.query(GET_ORGANIZATION_FOR_HERO, new OrganizationDaoDB.OrganizationMapper(), heroID);
    }

    @Override
    public List<Sighting> getAllSightingsForHero(int heroID)
    {
        final String GET_SIGHTING_FOR_HERO = "SELECT * FROM Sighting WHERE heroID = ?";
        return jdbc.query(GET_SIGHTING_FOR_HERO, new SightingDaoDB.SightingMapper(), heroID);
    }

    /**
     * Gets the superpower and assigns it to the hero
     * @param heroList
     */
    @Override
    public void assignHeroSuperpowers(List<Hero> heroList)
    {
        for(Hero hero: heroList)
        {
            hero.setHeroSuperpowers(getSuperpowersForHero(hero.getHeroID()));
        }
    }

    /**
     * Gets the sighting and assigns it to the hero
     * @param heroList
     */
    @Override
    public void assignHeroSightings(List<Hero> heroList)
    {
        for(Hero hero: heroList){
            hero.setHeroSightings(getAllSightingsForHero(hero.getHeroID()));
        }
    }

    /**
     * Gets the organizations and assigns it to the hero
     * @param heroList
     */
    @Override
    public void assignHeroOrganizations(List<Hero> heroList){
        for(Hero hero: heroList){
            
            hero.setHeroOrganizations(getOrganizationForHero(hero.getHeroID()));
        }
    }
    
    public static final class HeroMapper implements RowMapper<Hero> 
   {
        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException 
        {
            Hero hero = new Hero();
            hero.setHeroID(rs.getInt("heroID"));
            hero.setHeroName(rs.getString("heroName"));
            hero.setHeroDescription(rs.getString("heroDescription"));
            return hero;
        }
    }   
}
