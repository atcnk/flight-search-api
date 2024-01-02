package com.example.flightsearch.service;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight updateFlight(Long id, Flight updatedFlight) {
        Flight existingFlight = flightRepository.findById(id).orElse(null);

        if (existingFlight != null) {
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
            existingFlight.setReturnTime(updatedFlight.getReturnTime());
            existingFlight.setPrice(updatedFlight.getPrice());
            return flightRepository.save(existingFlight);
        }

        return null;
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDateTime departureTime, LocalDateTime returnTime) {
        List<Flight> flights = flightRepository.findAll();
        List<Flight> result = new ArrayList<>();

        for (Flight flight : flights) {

            if (flight.getDepartureAirport().getCity().equals(departureCity) &&
                    flight.getArrivalAirport().getCity().equals(arrivalCity) &&
                    flight.getDepartureTime().isEqual(departureTime)) {
                result.add(flight);
            }
        }

        if (returnTime != null) {

            for (Flight flight : flights) {

                if (flight.getDepartureAirport().getCity().equals(arrivalCity) &&
                        flight.getArrivalAirport().getCity().equals(departureCity) &&
                        flight.getDepartureTime().isEqual(returnTime) &&
                        flight.getReturnTime() == null) {

                    result.add(flight);
                }
            }
        }

        return result;
    }
}