package com.example.flightsearch.service;

import com.example.flightsearch.model.Airport;
import com.example.flightsearch.repository.AirportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    public Airport updateAirport(Long id, Airport updatedAirport) {
        Airport existingAirport = airportRepository.findById(id).orElse(null);

        if (existingAirport != null) {
            existingAirport.setCity(updatedAirport.getCity());
            return airportRepository.save(existingAirport);
        }

        return null;
    }
}