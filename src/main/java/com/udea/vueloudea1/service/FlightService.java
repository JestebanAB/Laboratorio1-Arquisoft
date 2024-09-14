package com.udea.vueloudea1.service;

import com.udea.vueloudea1.model.Flight;
import com.udea.vueloudea1.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findFlights(LocalDate startDate, LocalDate endDate, String origin, String destination, Double minPrice, Double maxPrice, Integer minDuration, Integer maxDuration) {
        if (origin != null && destination != null && minPrice != null && maxPrice != null && minDuration != null && maxDuration != null) {
            return flightRepository.findByDateBetweenAndDurationBetweenAndPriceGreaterThanEqualAndPriceLessThanEqual(
                    startDate, endDate, minDuration, maxDuration, minPrice, maxPrice);
        } else if (origin != null && destination != null && maxPrice != null) {
            return flightRepository.findByDateBetweenAndOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndPriceLessThanEqual(
                    startDate, endDate, origin, destination, maxPrice);
        } else if (origin != null && destination != null) {
            return flightRepository.findByDateBetweenAndOriginContainingIgnoreCaseAndDestinationContainingIgnoreCase(
                    startDate, endDate, origin, destination);
        } else if (origin != null && maxPrice != null) {
            return flightRepository.findByDateBetweenAndOriginContainingIgnoreCaseAndPriceLessThanEqual(
                    startDate, endDate, origin, maxPrice);
        } else if (destination != null && maxPrice != null) {
            return flightRepository.findByDateBetweenAndDestinationContainingIgnoreCaseAndPriceLessThanEqual(
                    startDate, endDate, destination, maxPrice);
        } else if (origin != null) {
            return flightRepository.findByDateBetweenAndOriginContainingIgnoreCase(startDate, endDate, origin);
        } else if (destination != null) {
            return flightRepository.findByDateBetweenAndDestinationContainingIgnoreCase(startDate, endDate, destination);
        } else if (maxPrice != null) {
            return flightRepository.findByDateBetweenAndPriceLessThanEqual(startDate, endDate, maxPrice);
        } else if (minPrice != null && maxPrice != null) {
            return flightRepository.findByDateBetweenAndPriceGreaterThanEqualAndPriceLessThanEqual(startDate, endDate, minPrice, maxPrice);
        } else if (minDuration != null && maxDuration != null) {
            return flightRepository.findByDateBetweenAndDurationBetween(startDate, endDate, minDuration, maxDuration);
        } else {
            return flightRepository.findByDateBetween(startDate, endDate);
        }
    }
}
