/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Superpower;
import com.sg.superherosightings.service.ServiceLayer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Blasc
 */
@Controller
public class HeroController 
{
    private ServiceLayer serviceLayer;
    /**
     * Displays all the heroes
     **/
    @GetMapping("heroes")
    public String displayHeroes(Model model) 
    {
        List<Hero> heros = serviceLayer.getAllHeroes();
        model.addAttribute("heros", heros);
        return "heroes";
    }

    @GetMapping("addHero")
    public String addHeroes(Model model) 
    {
        List<Hero> heroes = serviceLayer.getAllHeroes();
        List<Superpower> superpowers = serviceLayer.getAllSuperpowers();
        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        return "addHero";
    }

    @PostMapping("addHero")
    public String performAddHero(Model model, HttpServletRequest request){

        String heroName = request.getParameter("heroName");
        boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        String[] superpowerID = request.getParameterValues("superpowerID");
        String[] organizationID = request.getParameterValues("organizationID");

        List<Superpower> superpowers = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();
        for(String id : superpowerID) {
            superpowers.add(serviceLayer.getSuperpowerById(Integer.parseInt(id)));
        }
        for(String id : organizationID) 
        {
            organizations.add(serviceLayer.getOrganizationById(Integer.parseInt(id)));
        }

        Hero hero = serviceLayer.createHero(heroName,isHero,superpowers, organizations);
        serviceLayer.addHero(hero);


        return "redirect:/addHero";
    }

    @GetMapping("editHero")
    public String editHero(HttpServletRequest request, Model model) {
        int heroID = Integer.parseInt(request.getParameter("heroID"));

        Hero hero = serviceLayer.getHeroById(heroID);
        model.addAttribute("hero", hero);

        List<Superpower> superpowers = serviceLayer.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);

        List<Organization> organizations = serviceLayer.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request, Model model, @RequestParam("image") MultipartFile multipartFile){
        int heroID = Integer.parseInt(request.getParameter("heroID"));
        String heroName = request.getParameter("heroName");
        boolean isHero = Boolean.parseBoolean(request.getParameter("isHero"));
        String[] superpowerID = request.getParameterValues("superpowerID");
        String[] organizationID = request.getParameterValues("organizationID");

        List<Superpower> superpowers = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();
        for(String id : superpowerID) {
            superpowers.add(serviceLayer.getSuperpowerById(Integer.parseInt(id)));
        }
        for(String id : organizationID) 
        {
            organizations.add(serviceLayer.getOrganizationById(Integer.parseInt(id)));
        }

        Hero hero = serviceLayer.createHero(heroName, isHero, superpowers, organizations);
        hero.setHeroID(heroID);

        hero = serviceLayer.getHeroById(hero.getHeroID());
        model.addAttribute("hero", hero);

        superpowers = serviceLayer.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);

        organizations = serviceLayer.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        return "redirect:/editHero";
    }

    @GetMapping("deleteHero")
    public String performDeleteHero(Integer heroID) {
        serviceLayer.deleteHeroById(heroID);
        return "redirect:/heroes";
    }

    @GetMapping("infoHero")
    public String infoHero(HttpServletRequest request, Model model)
    {
        int heroID = Integer.parseInt(request.getParameter("heroID"));

        Hero hero = serviceLayer.getHeroById(heroID);
        model.addAttribute("hero", hero);

        List<Superpower> superpowers = serviceLayer.getSuperpowersForHero(heroID);
        model.addAttribute("superpowers", superpowers);

        List<Organization> organizations = serviceLayer.getOrganizationForHero(heroID);
        model.addAttribute("organizations", organizations);


        return "infoHero";
    }
    
    @GetMapping("infoLocation")
    public String infoLocation(HttpServletRequest request, Model model)
    {
    int locationID = Integer.parseInt(request.getParameter("locationID"));
    int sightingID = Integer.parseInt(request.getParameter("sightingID"));
    Location location = serviceLayer.getLocationById(locationID);
    model.addAttribute("location", location);

    List<Hero> heroes = serviceLayer.getHeroFromSighting(sightingID);
    model.addAttribute("heroes", heroes);

    return "detailsLocation";
    }
        //// ------ ///////
}
