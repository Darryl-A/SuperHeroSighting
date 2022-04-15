/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.service.HeroServiceLayer;
import com.sg.superherosightings.service.LocationServiceLayer;
import com.sg.superherosightings.service.SightingServiceLayer;
import java.sql.Date;
import java.time.LocalDate;
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
public class SightingController 
{
    private SightingServiceLayer sightingServiceLayer;
    private HeroServiceLayer heroServiceLayer;
    private LocationServiceLayer locationServiceLayer;
    
    public SightingController(SightingServiceLayer sightingServiceLayer, HeroServiceLayer heroServiceLayer, LocationServiceLayer locationServiceLayer) 
    {
        this.sightingServiceLayer = sightingServiceLayer;
        this.heroServiceLayer = heroServiceLayer;
        this.locationServiceLayer = locationServiceLayer;
    }
   

    
     @GetMapping("sightings")
    public String displayCourses(Model model) 
    {
        List<Sighting> sightings = sightingServiceLayer.getAllSightings();
        List<Hero> heroes = heroServiceLayer.getAllHeroes();
        List<Location> locations = locationServiceLayer.getAllLocations();
        model.addAttribute("courses", sightings);
        model.addAttribute("teachers", heroes);
        model.addAttribute("students", locations);
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) 
    {
        Sighting sighting;
        int heroID = Integer.parseInt(request.getParameter("heroID"));
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        String stringDate = request.getParameter("sightingDate");
        LocalDate date = LocalDate.parse(stringDate);
        
        sighting = sightingServiceLayer.createSighting(heroID,locationID,date);
        

        sightingServiceLayer.addSighting(sighting);

        return "redirect:/sightings";
    }
    
    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) 
    {
        Sighting sighting = sightingServiceLayer.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }
    
     @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) 
    {
        sightingServiceLayer.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) 
    {
        Sighting sightings = sightingServiceLayer.getSightingById(id);
        List<Hero> heroes = heroServiceLayer.getAllHeroes();
        List<Location> locations = locationServiceLayer.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "editSightings";
    }
    
    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request) 
    {
         Sighting sighting;
        int heroID = Integer.parseInt(request.getParameter("heroID"));
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        String stringDate = request.getParameter("sightingDate");
        LocalDate date = LocalDate.parse(stringDate);
        
        sighting = sightingServiceLayer.createSighting(heroID,locationID,date);
        
        sightingServiceLayer.updateSighting(sighting);
        return "redirect:/sightings";
    }
}
