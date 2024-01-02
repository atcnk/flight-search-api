package com.example.flightsearch.service;

import com.example.flightsearch.model.Airport;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ScheduledTasksService {

    @Autowired
    private FlightRepository flightRepository;


    @Scheduled(cron = "0 0 12 * * ?")
    public void fetchFlightsFromMockApi() {

        Flight mockFlight = generateMockFlight();
        flightRepository.save(mockFlight);
    }

    private Flight generateMockFlight() {
        Random rand = new Random();

        Long randomDepartureAirportId = 1L + rand.nextInt(10);
        Long randomArrivalAirportId = 1L + rand.nextInt(10);

        LocalDateTime randomDepartureTime = LocalDateTime.now().plusDays(rand.nextInt(30));
        LocalDateTime randomReturnTime = randomDepartureTime.plusDays(1 + rand.nextInt(5));
        double randomPrice = 100.0 + (900.0 * rand.nextDouble());

        Flight flight = new Flight();

        Airport departureAirport = new Airport(randomDepartureAirportId, "Rastgele Şehir " + randomDepartureAirportId);
        Airport arrivalAirport = new Airport(randomArrivalAirportId, "Rastgele Şehir " + randomArrivalAirportId);

        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureTime(randomDepartureTime);
        flight.setReturnTime(randomReturnTime);
        flight.setPrice(randomPrice);

        return flight;
    }
}
