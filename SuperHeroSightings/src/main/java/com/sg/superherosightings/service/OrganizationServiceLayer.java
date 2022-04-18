/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
import java.util.List;

/**
 *
 * @author Blasc
 */
public interface OrganizationServiceLayer 
{
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
    
    List<Hero> getHeroesFromOrganization(int organizationID);

    public Organization createAnOrganzation(String organizationName, String organizationDescription, List<Hero> Hero, String organizationAddress);
}
