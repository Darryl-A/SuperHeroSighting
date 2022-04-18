/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Superpower;
import com.sg.superherosightings.service.ServiceLayer;
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
public class SuperpowerController 
{

    public SuperpowerController(ServiceLayer servicelayer) {
        this.serviceLayer = servicelayer;
    }

   private final ServiceLayer serviceLayer;
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model)
    {
        List<Superpower> superpowers = serviceLayer.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @PostMapping("addSuperpower")
    public String addSuperpower(String superpowerName) 
    {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName(superpowerName);
        serviceLayer.addSuperpower(superpower);
        return "redirect:/superpowers";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer id) 
    {
        serviceLayer.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }
    
     @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model) 
    {
        Superpower superpower = serviceLayer.getSuperpowerById(id);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }
    @PostMapping("editSuperpower")
    public String performEditSuperpower(Superpower superpower) 
    {
        serviceLayer.updateSuperpower(superpower);
        return "redirect:/superpowers";
    }
    @GetMapping("infoSuperpower")
    public String infoSuperpower(HttpServletRequest request, Model model)
    {
        int superpowerID = Integer.parseInt(request.getParameter("superpowerID"));

        Superpower superpower = serviceLayer.getSuperpowerById(superpowerID);
        model.addAttribute("superpower", superpower);

        List<Hero> heroes = serviceLayer.getHeroWithSuperpowers(superpowerID);
        model.addAttribute("heroes", heroes);

        return "infoSuperpower";
        }
}
