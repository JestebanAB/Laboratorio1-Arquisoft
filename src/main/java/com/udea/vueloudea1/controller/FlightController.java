package com.udea.vueloudea1.controller;

import com.udea.vueloudea1.model.Flight;
import com.udea.vueloudea1.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")

public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate,
                                      @RequestParam(value = "origin", required = false) String origin,
                                      @RequestParam(value = "destination", required = false) String destination,
                                      @RequestParam(value = "minPrice", required = false) Double minPrice,
                                      @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                      @RequestParam(value = "minDuration", required = false) Integer minDuration,
                                      @RequestParam(value = "maxDuration", required = false) Integer maxDuration) {

        return flightService.findFlights(LocalDate.parse(startDate), LocalDate.parse(endDate), origin, destination, minPrice, maxPrice, minDuration, maxDuration);
    }
}
