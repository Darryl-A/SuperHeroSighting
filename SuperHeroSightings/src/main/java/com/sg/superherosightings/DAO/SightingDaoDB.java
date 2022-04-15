package com.sg.superherosightings.DAO;

import com.sg.superherosightings.DAO.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingDaoDB implements SightingDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    LocationDaoDB locationDaoDB;

    @Override
    public Sighting getSightingByID(int sightingID)
    {
        try 
        {
            final String GET_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE sightingID = ?";
            Sighting s = jdbc.queryForObject(GET_SIGHTING_BY_ID, new SightingMapper(), sightingID);

            return s;

        } catch (DataAccessException ex) 
        {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings()
    {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(GET_ALL_SIGHTINGS, new SightingMapper());
        return sightings;
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting)
    {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(sightingDate, heroID, locationID) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocationID(),
                sighting.getSightingDate());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting)
    {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET sighitngDate = ?, heroID = ?, locationID = ?"
                + "WHERE sightingID = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getSightingDate(),
                sighting.getHeroID(),
                sighting.getLocationID(),
                sighting.getSightingID());
    }

    @Override
    public void deleteSightingById(int sightingID)
    {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE sightingID = ?";
        jdbc.update(DELETE_SIGHTING, sightingID);
    }

    @Override
    public List<Hero> getHeroFromSighting(int sightingID)
    {
        final String GET_HEROS_FROM_SIGHTING= "SELECT h.* FROM Hero h "
                + "JOIN Sighting s ON h.heroID = s.heroID WHERE s.sightingID = ?";
        List<Hero> heros = jdbc.query(GET_HEROS_FROM_SIGHTING, new HeroMapper(), sightingID);
        return heros;
    }

    /**
     * Gets the location of a specific location
     * @param sightingID
     * @return
     */
    private Location assignLocation(int sightingID)
    {
        
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Location l "
                + "JOIN Sighting s ON s.locationID = l.locationID WHERE s.sightingID = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(), sightingID);
    }

    public static final class SightingMapper implements RowMapper<Sighting>
    {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException 
        {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSightingDate(LocalDate.parse(rs.getDate("Date").toString()));
            sighting.setHeroID(rs.getInt("heroID"));
            sighting.setLocationID(rs.getInt("locationID"));
            return sighting;
        }
    }
}