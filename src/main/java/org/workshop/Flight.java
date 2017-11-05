package org.workshop;

import java.util.HashMap;
import java.util.Map;

public class Flight {

    private final Map<String, Seat> seats = new HashMap<>();

    public Flight(Seat ... seats) {
        if (seats.length == 0) {
            throw new IllegalArgumentException("Flight requires at least one seat");
        }
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
        Seat seat = seats.get(seatNo);
        if (seat.isBooked()) {
            throw new AlreadyBookedException();
        }
        seat.setBooked(true);
    }
}
