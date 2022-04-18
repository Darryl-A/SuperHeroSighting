/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.DAO;

import com.sg.superherosightings.DAO.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
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
public class OrganizationDaoDB implements OrganizationDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    HeroDao heroDao;
//
//    @Autowired
//    LocationDao locationDao;
    
    /**
     * Gets an organization based on ID.
     * @param organizationID
     * @return
     */
    @Override
    public Organization getOrganizationById(int organizationID)
    {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organization WHERE organizationID = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationID);
            organization.setHeroes(getHeroesFromOrganization(organizationID));
            return organization;
        } catch (DataAccessException ex) 
        {
            return null;
        }
    }

    /**
     * Returns all organizations
     * @return
     */
    @Override
    public List<Organization> getAllOrganizations() 
    {
        final String GET_ALL_ORGANIZATIONS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(GET_ALL_ORGANIZATIONS, new OrganizationMapper());
        assignHeros(organizations);
        return organizations;
    }

    /**
     * Adds an organization to the HeroOrganization table
     * @param organization
     */
    private void addHeroOrganization(Organization organization)
    {    
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganization(heroID, organizationID) VALUES(?,?)";
        for(Hero hero : organization.getHeroes()) {
            jdbc.update(INSERT_HERO_ORGANIZATION,
                    hero.getHeroID(),
                    organization.getOrganizationID());
        }
    }

    /**
     * Adds an organization to the table
     * @param organization
     * @return
     */
    @Override
    @Transactional
    public Organization addOrganization(Organization organization) 
    {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization(organizationName, organizationDescription, organizationAddress) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddress());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationID(newId);
        addHeroOrganization(organization);
        return organization;
    }

    /**
     * Updates the information about an organization
     * @param organization
     */
    @Override
    public void updateOrganization(Organization organization) 
    {
        final String UPDATE_ORGANIZATION = "UPDATE Organization SET orgazanitionName = ?,  organizationDescription = ?, organizationAddress = ?"
                + "WHERE organizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationAddress(),
                organization.getOrganizationID());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE organizationID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organization.getOrganizationID());
        addHeroOrganization(organization);
    }

    /**
     * Deletes an organization give an ID.
     * @param organizationID
     */
    @Override
    @Transactional
    public void deleteOrganizationById(int organizationID)
    {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE organizationID = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organizationID);

        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE organizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationID);
    }

    /**
     * Gets heroes based on organizationID
     * @param organizationID
     * @return
     */
    
    @Override
    public List<Hero> getHeroesFromOrganization(int organizationID)
    {
        final String GET_HEROES_FROM_ORGANIZATION = "SELECT h.* FROM Hero h "
                + "JOIN HeroOrganization hOrg ON h.heroID = hOrg.heroID WHERE hOrg.organizationID = ?";
        List<Hero> heroes = jdbc.query(GET_HEROES_FROM_ORGANIZATION, new HeroMapper(), organizationID);
        heroDao.assignHeroSuperpowers(heroes);
        heroDao.assignHeroSightings(heroes);
        return heroes;
    }

    /**
     * Assigns heroes for an organization
     * @param org
     */
    
    private void assignHeros(List<Organization> org)
    {
        for(Organization o: org){
            o.setHeroes(getHeroesFromOrganization(o.getOrganizationID()));
        }
    }

    public static final class OrganizationMapper implements RowMapper<Organization> 
   {
        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException 
        {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setOrganizationName(rs.getString("organizationName"));
            organization.setOrganizationDescription(rs.getString("organizationDescription"));
            organization.setOrganizationAddress(rs.getString("organizationAddress"));
            return organization;
        }
    }    
}
