package org.workshop;

import java.util.HashMap;
import java.util.Map;

public class Flight {

    private final Map<String, Seat> seats = new HashMap<>();

    public Flight(Seat ... seats) {
        for (Seat seat : seats) {
            this.seats.put(seat.getSeatNo(), seat);
        }
    }

    public int getAvailableSeats() {
        return (int) seats.values().stream().filter(seat -> !seat.isBooked()).count();
    }

    public Seat getCheapestSeat() {
        return seats.values().stream().reduce((seat, seat2) -> (seat.getPrice() > seat2.getPrice())? seat2:seat).get();
    }

    public void book(String seatNo) {
        seats.get(seatNo).setBooked(true);
    }
}
