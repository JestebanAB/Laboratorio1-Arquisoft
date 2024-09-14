package com.udea.vueloudea1.repository;

import com.udea.vueloudea1.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDateBetweenAndOriginContainingIgnoreCaseAndDestinationContainingIgnoreCaseAndPriceLessThanEqual(
            LocalDate startDate, LocalDate endDate, String origin, String destination, double maxPrice);

    List<Flight> findByDateBetweenAndOriginContainingIgnoreCaseAndDestinationContainingIgnoreCase(
            LocalDate startDate, LocalDate endDate, String origin, String destination);

    List<Flight> findByDateBetweenAndOriginContainingIgnoreCaseAndPriceLessThanEqual(
            LocalDate startDate, LocalDate endDate, String origin, double maxPrice);

    List<Flight> findByDateBetweenAndDestinationContainingIgnoreCaseAndPriceLessThanEqual(
            LocalDate startDate, LocalDate endDate, String destination, double maxPrice);

    List<Flight> findByDateBetweenAndOriginContainingIgnoreCase(
            LocalDate startDate, LocalDate endDate, String origin);

    List<Flight> findByDateBetweenAndDestinationContainingIgnoreCase(
            LocalDate startDate, LocalDate endDate, String destination);

    List<Flight> findByDateBetweenAndPriceLessThanEqual(
            LocalDate startDate, LocalDate endDate, double maxPrice);

    List<Flight> findByDateBetweenAndPriceGreaterThanEqualAndPriceLessThanEqual(
            LocalDate startDate, LocalDate endDate, double minPrice, double maxPrice);

    List<Flight> findByDateBetweenAndDurationBetweenAndPriceGreaterThanEqualAndPriceLessThanEqual(
            LocalDate startDate, LocalDate endDate, int minDuration, int maxDuration, double minPrice, double maxPrice);

    List<Flight> findByDateBetweenAndDurationBetween(
            LocalDate startDate, LocalDate endDate, int minDuration, int maxDuration);

    List<Flight> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
