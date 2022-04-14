/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.DAO;

import com.sg.superherosightings.entities.Hero;
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
public class SuperpowerDaoDB implements SuperpowerDao 
{
   @Autowired
   JdbcTemplate jdbc;
   
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    HeroDaoDB heroDaoDB;
   
    @Override
    public Superpower getSuperpowerById(int id) 
    {
        try
        {
            final String SELECT_SUPERPOWER_BY_ID = "SELECT * FROM Superpower WHERE superpowerID = ?";
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(), id);
        }
        catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() 
    {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * from Superpower";
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) 
    {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(superpowerName)"
        + "VALUES(?)";
        
        jdbc.update(INSERT_SUPERPOWER, superpower.getSuperpowerName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperpowerID(newId);
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) 
    {
        final String UPDATE_SUPERPOWER = "UPDATE SUPERPOWER SET superpowerID"
                + "WHERE id = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                superpower.getSuperpowerName(),
                superpower.getSuperpowerID());
    }

    @Override
    @Transactional
    public void deleteSuperpowerById(int superpowerID) 
    {
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpower WHERE superpowerID = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, superpowerID);
        
        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE superpowerID = ?";
        jdbc.update(DELETE_SUPERPOWER, superpowerID);
    }

    @Override
    public List<Hero> getHeroWithSuperpowers(int superpowerID) 
    {
         final String GET_HEROES_WITH_SUPERPOWERS = "SELECT h.* FROM Hero h "
                + "JOIN HeroSuperpower hSuper ON h.heroID = hSuper.heroID WHERE hSuper.superpowerID = ?";
        List<Hero> heroes = jdbc.query(GET_HEROES_WITH_SUPERPOWERS, new HeroDaoDB.HeroMapper(), superpowerID);
        heroDao.assignHeroSuperpowers(heroes);
        heroDao.assignHeroSightings(heroes);
        return heroes;
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> 
   {
        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException 
        {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerID(rs.getInt("superpowerID"));
            superpower.setSuperpowerName(rs.getString("superpowerName"));
            return superpower;
        }
    }
}
