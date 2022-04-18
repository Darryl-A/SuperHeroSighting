/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.service.ServiceLayer;
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

    public SightingController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }
    private final ServiceLayer serviceLayer;
    
   
     @GetMapping("sightings")
    public String displayCourses(Model model) 
    {
        List<Sighting> sightings = serviceLayer.getAllSightings();
        List<Hero> heroes = serviceLayer.getAllHeroes();
        List<Location> locations = serviceLayer.getAllLocations();
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
        
        sighting = serviceLayer.createSighting(heroID,locationID,date);
        

        serviceLayer.addSighting(sighting);

        return "redirect:/sightings";
    }
    
    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) 
    {
        Sighting sighting = serviceLayer.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }
    
     @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) 
    {
        serviceLayer.deleteSightingById(id);
        return "redirect:/sightings";
    }
    
    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) 
    {
        Sighting sightings = serviceLayer.getSightingById(id);
        List<Hero> heroes = serviceLayer.getAllHeroes();
        List<Location> locations = serviceLayer.getAllLocations();
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
        
        sighting = serviceLayer.createSighting(heroID,locationID,date);
        
        serviceLayer.updateSighting(sighting);
        return "redirect:/sightings";
    }
}
