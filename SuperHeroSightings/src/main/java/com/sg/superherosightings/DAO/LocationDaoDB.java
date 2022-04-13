/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.DAO;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
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
public class LocationDaoDB implements LocationDao
{
       @Autowired
   JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) 
    {
         try 
         {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM Location WHERE LocationID = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
        } catch (DataAccessException ex) 
        {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocation() 
    {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) 
    {
        final String INSERT_LOCATION = "INSERT INTO Location(locationName, locationDescription, locationAddress,latitude,longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());
        // Need List for Location
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) 
    {
        final String UPDATE_LOCATION = "UPDATE Location SET LocationName = ?, locationDescription = ?, locationAddress = ?, latitude = ?,longitude = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) 
    {
        final String DELETE_SIGHTING_LOCATION = "DELETE FROM Sighting WHERE LocationID = ?";
        jdbc.update(DELETE_SIGHTING_LOCATION, id);
        
        final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
        jdbc.update(DELETE_STUDENT, id);
    }

    @Override
    public List<Sighting> getAllSightingsForLocation(Location location) 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static final class LocationMapper implements RowMapper<Location> 
   {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException 
        {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setCoordinates(rs.getInt("latitude"), rs.getInt("longitude"));
            return location;
        }
    }
}
