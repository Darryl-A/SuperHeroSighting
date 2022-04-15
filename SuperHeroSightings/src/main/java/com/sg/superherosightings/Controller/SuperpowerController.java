/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Superpower;
import com.sg.superherosightings.service.SuperpowerServiceLayer;
import java.util.List;
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

    private SuperpowerServiceLayer spServiceLayer;
    public SuperpowerController(SuperpowerServiceLayer superpowerServiceLayer) 
    {
        this.spServiceLayer = superpowerServiceLayer;
    }
    
    @GetMapping("superpowers")
    public String displaySuperpowers(Model model)
    {
        List<Superpower> superpowers = spServiceLayer.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @PostMapping("addSuperpower")
    public String addSuperpower(String superpowerName) 
    {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName(superpowerName);
        spServiceLayer.addSuperpower(superpower);
        return "redirect:/superpowers";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer id) 
    {
        spServiceLayer.deleteSuperpowerById(id);
        return "redirect:/superpowers";
    }
    
     @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model) 
    {
        Superpower superpower = spServiceLayer.getSuperpowerById(id);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }
    @PostMapping("editSuperpower")
    public String performEditSuperpower(Superpower superpower) 
    {
        spServiceLayer.updateSuperpower(superpower);
        return "redirect:/superpowers";
    }
}
