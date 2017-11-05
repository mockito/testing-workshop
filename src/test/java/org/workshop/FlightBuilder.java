package org.workshop;

import java.util.LinkedList;
import java.util.List;

public class FlightBuilder {
    private final String flightNo;
    private List<Seat> seats = new LinkedList<>();
    private int seatNo = 1;
    private int defaultPrice = 100;

    public FlightBuilder(String flightNo) {
        this.flightNo = flightNo;
    }

    public FlightBuilder seat() {
        seats.add(new Seat(defaultSeatNo(), defaultPrice));
        return this;
    }

    private String defaultSeatNo() {
        return "A" + (seatNo++);
    }

    public Flight build() {
        return new Flight(flightNo, seats);
    }

    public FlightBuilder seat(String seatNo) {
        seats.add(new Seat(seatNo, defaultPrice));
        return this;
    }

    public FlightBuilder seat(int price) {
        seats.add(new Seat(defaultSeatNo(), price));
        return this;
    }

    public FlightBuilder seat(int price, boolean booked) {
        Seat seat = new Seat(defaultSeatNo(), price);
        seat.setBooked(booked);
        seats.add(seat);
        return this;
    }
}
