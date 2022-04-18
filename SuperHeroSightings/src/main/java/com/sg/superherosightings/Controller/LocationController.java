/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.superherosightings.Controller;

import com.sg.superherosightings.entities.Location;
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
public class LocationController
{

    public LocationController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }
    
    private final ServiceLayer serviceLayer;


    @GetMapping("locations")
    public String displayLocations(Model model)
    {
        List<Location> locations = serviceLayer.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @GetMapping("addLocation")
    public String addLocation(Model model)
    {
        return "addLocation";
    }

    @PostMapping("addLocation")
    public String addLocation(Model model, HttpServletRequest request)
    {
        String locationName = request.getParameter("locationName");
        String stringLocationLatitude = request.getParameter("latitude");
        String stringLocationLongitude = request.getParameter("longitude");
        String locaitonDescription = request.getParameter("description");
        String locationAddress = request.getParameter("addressInformation");

        //Convert longitude to double
        double locationLongitude = Double.parseDouble(stringLocationLongitude);

        //Convert latitude to double
        double locationLatitude = Double.parseDouble(stringLocationLatitude);

        //Add the location
        Location location = serviceLayer.createLocation(locationName,locaitonDescription,locationAddress,locationLongitude,locationLatitude);
        serviceLayer.addLocation(location);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(Model model, HttpServletRequest request)
    {
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        Location location = serviceLayer.getLocationById(locationID);
        
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(Model model,HttpServletRequest request)
    {
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        String locationName = request.getParameter("locationName");
        String stringLocationLatitude = request.getParameter("latitude");
        String stringLocationLongitude = request.getParameter("longitude");
        String locaitonDescription = request.getParameter("description");
        String locationAddress = request.getParameter("addressInformation");

        //Convert longitude to double
        double locationLongitude = Double.parseDouble(stringLocationLongitude);

        //Convert latitude to double
        double locationLatitude = Double.parseDouble(stringLocationLatitude);

        //Add the location
        Location location = serviceLayer.createLocation(locationName,locaitonDescription,locationAddress,locationLongitude,locationLatitude);
        serviceLayer.updateLocation(location);

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(Integer locationID) {
        serviceLayer.deleteLocationById(locationID);
        return "redirect:/locations";
    }
}
