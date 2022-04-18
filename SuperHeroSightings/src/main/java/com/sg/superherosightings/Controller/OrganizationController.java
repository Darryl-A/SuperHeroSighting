/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.service.ServiceLayer;
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

    public OrganizationController(ServiceLayer serviceLayer) 
    {
        this.serviceLayer = serviceLayer;
    }
    
    private final ServiceLayer serviceLayer;

    /**
     * Gets all organizations
     * @param model
     * @return
     */
    @GetMapping("organizations")
    public String displayOrganizations(Model model)
    {
        List<Organization> organizations = serviceLayer.getAllOrganizations();
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
            heroes.add(serviceLayer.getHeroById(Integer.parseInt(id)));
        }

        //Create a new organization given the values
        Organization o = serviceLayer.createAnOrganzation(organizationName, organizationDescription, heroes, organizationAddress);
        serviceLayer.addOrganization(o);
        
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
        Organization organization = serviceLayer.getOrganizationById(organizationID);

        List<Hero> heroes = serviceLayer.getAllHeroes();
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
            heroes.add(serviceLayer.getHeroById(Integer.parseInt(id)));
        }

        Organization o = serviceLayer.createAnOrganzation(organizationName, organizationDescription, heroes, organizationAddress);
        o.setOrganizationID(organizationID);

        //Update the organization
        serviceLayer.updateOrganization(o);

        return "redirect:/organizations";
    }

    /**
     * Delets an organization
     */
    @GetMapping("/organizations/deleteOrganization")
    public String deleteOrganization(Integer id) 
    {
        serviceLayer.deleteOrganizationById(id);
        return "redirect:/organizations";
    }
    
    @GetMapping("infoOrganization")
    public String infoOrganization(HttpServletRequest request, Model model)
    {
        int organizationID = Integer.parseInt(request.getParameter("organizationID"));

        Organization organization = serviceLayer.getOrganizationById(organizationID);
        model.addAttribute("organization", organization);

        return "infoOrganization";
    }

}
