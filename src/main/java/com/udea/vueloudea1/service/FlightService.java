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

    public List<Flight> findFlights(LocalDate startDate, LocalDate endDate, String origin, String destination, Double minPrice,Double maxPrice, Integer minDuration, Integer maxDuration) {
        // Generar una clave de combinación de parámetros para usar en el switch
        String key = (origin != null ? "1" : "0") +
                (destination != null ? "1" : "0") +
                (minPrice != null ? "1" : "0")+
                (maxPrice != null ? "1" : "0")+
                (minDuration != null ? "1" : "0") +
                (maxDuration != null ? "1" : "0");

        switch (key) {

            case "111111": // Todos los parámetros no son nulos
                return flightRepository.findByDateBetweenAndDurationBetweenAndPriceGreaterThanEqualAndPriceLessThanEqual(
                        startDate, endDate, minDuration, maxDuration, minPrice, maxPrice);
            case "111110": // minDuration es nulo
                return flightRepository.findByDateBetweenAndPriceGreaterThanEqualAndPriceLessThanEqual(
                        startDate, endDate,     minPrice, maxPrice);
            case "111": // origin, destination, maxPrice no son nulos
                return flightRepository.findByDateBetweenAndOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndPriceLessThanEqual(
                        startDate, endDate, origin, destination, maxPrice);

            case "110": // origin, destination no son nulos, maxPrice es nulo
                return flightRepository.findByDateBetweenAndOriginContainingIgnoreCaseAndDestinationContainingIgnoreCase(
                        startDate, endDate, origin, destination);

            case "101": // origin y maxPrice no son nulos, destination es nulo
                return flightRepository.findByDateBetweenAndOriginContainingIgnoreCaseAndPriceLessThanEqual(
                        startDate, endDate, origin, maxPrice);

            case "011": // destination y maxPrice no son nulos, origin es nulo
                return flightRepository.findByDateBetweenAndDestinationContainingIgnoreCaseAndPriceLessThanEqual(
                        startDate, endDate, destination, maxPrice);

            case "100": // solo origin no es nulo
                return flightRepository.findByDateBetweenAndOriginContainingIgnoreCase(startDate, endDate, origin);

            case "010": // solo destination no es nulo
                return flightRepository.findByDateBetweenAndDestinationContainingIgnoreCase(startDate, endDate, destination);

            case "001": // solo maxPrice no es nulo
                return flightRepository.findByDateBetweenAndPriceLessThanEqual(startDate, endDate, maxPrice);

            case "000000": // Todos los parámetros son nulos
            default:
                return flightRepository.findByDateBetween(startDate, endDate);
        }
    }


}
