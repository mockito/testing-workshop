package org.workshop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class FlightManager {
    private Map<String, List<Seat>> flights = new HashMap<>();

    public void addFlight(String flightNo, int seats) {

    }

    public int getAvailableSeats(String flightNo) {
        List<Seat> seats = flights.get(flightNo);
        if (seats == null) {
            throw new FlightNotFoundException();
        }
        return seats.size();
    }

    public void addFlight(String flightNo, Seat ... seats) {
        this.flights.put(flightNo, asList(seats));
    }

    public Seat getCheapestSeat(String flightNo) {
        List<Seat> seats = flights.get(flightNo);
        Seat cheapest = null;
        for (Seat seat : seats) {
            if (cheapest == null || seat.getPrice() < cheapest.getPrice()) {
                cheapest = seat;
            }
        }
        return cheapest;
    }
}
