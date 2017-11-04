package org.workshop;

import java.util.List;

import static java.util.Arrays.asList;

public class Flight {

    private final List<Seat> seats;

    public Flight(Seat ... seats) {
        this.seats = asList(seats);
    }

    public int getAvailableSeats() {
        return seats.size();
    }

    public Seat getCheapestSeat() {
        Seat cheapest = null;
        for (Seat seat : seats) {
            if (cheapest == null || seat.getPrice() < cheapest.getPrice()) {
                cheapest = seat;
            }
        }
        return cheapest;
    }
}
