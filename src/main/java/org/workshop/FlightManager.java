package org.workshop;

import java.util.HashMap;
import java.util.Map;

public class FlightManager {
    private Map<String, Flight> flights = new HashMap<>();

    public void addFlight(Flight flight) {
        this.flights.put(flight.getFlightNo(), flight);
    }

    public Flight getFlight(String flightNo) {
        Flight flight = flights.get(flightNo);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight;
    }
}
