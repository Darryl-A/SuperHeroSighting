/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.service.HeroServiceLayer;
import com.sg.superherosightings.service.OrganizationServiceLayer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Blasc
 */
@Controller
public class OrganizationController
{
    private final OrganizationServiceLayer osl;
    private final HeroServiceLayer hs;

    //Controller for the organization 
    public OrganizationController(OrganizationServiceLayer osl, HeroServiceLayer hs)
    {
        this.osl = osl;
        this.hs = hs;
    }

    /**
     * Gets all organizations
     * @param model
     * @return
     */
    @GetMapping("organizations")
    public String displayOrganizations(Model model)
    {
        List<Organization> organizations = osl.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "organizations";
    }

    /**
     * Adds an organization
     * @param model
     * @param request
     * @return
     */
    @PostMapping("addOrganization")
    public String addOrganization(Model model, HttpServletRequest request) 
    {
        String organizationName = request.getParameter("organizationName");
        String organizationDescription = request.getParameter("organizationDescription");
        String organizationAddress = request.getParameter("organizationAddress");
        String[] heroID = request.getParameterValues("heroID");
        
        
        List<Hero> heroes = new ArrayList<>();
        for(String id : heroID) 
        {
            heroes.add(hs.getHeroById(Integer.parseInt(id)));
        }

        //Create a new organization given the values
        Organization o = osl.createAnOrganzation(organizationName, organizationDescription, heroes, organizationAddress);
        osl.addOrganization(o);
        
        return "redirect:/organizations";
    } 
    /**
     * Looks for the organization
     * @param request
     * @param model
     * @return
     */
    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) 
    {
        int organizationID = Integer.parseInt(request.getParameter("organizationID"));
        Organization organization = osl.getOrganizationById(organizationID);

        List<Hero> heroes = hs.getAllHeroes();
        model.addAttribute("organization", organization);
        model.addAttribute("heroes", heroes);
    
        return "editOrganization";
    }

    /**
     * Edits an organization
     * @param id
     * @return
     */
    @PostMapping("editOrganization")
    public String performEditOrganization(Model mode, HttpServletRequest request){
        
        int organizationID = Integer.parseInt(request.getParameter("organizationID"));
        String organizationName = request.getParameter("organizationName");
        String organizationDescription = request.getParameter("organizationDescription");
        String organizationAddress = request.getParameter("organizationAddress");
        String[] heroID = request.getParameterValues("heroID");
        
        List<Hero> heroes = new ArrayList<>();
        for(String id : heroID) 
        {
            heroes.add(hs.getHeroById(Integer.parseInt(id)));
        }

        Organization o = osl.createAnOrganzation(organizationName, organizationDescription, heroes, organizationAddress);
        o.setOrganizationID(organizationID);

        //Update the organization
        osl.updateOrganization(o);

        return "redirect:/organizations";
    }

    /**
     * Delets an organization
     */
    @GetMapping("/organizations/deleteOrganization")
    public String deleteOrganization(Integer id) 
    {
        osl.deleteOrganizationById(id);
        return "redirect:/organizations";
    }
}
