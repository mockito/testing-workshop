package org.workshop;

import java.util.LinkedList;
import java.util.List;

public class FlightBuilder {
    private static final String DEFAULT_FLIGHT_NO = "AA101";
    private static final int DEFAULT_PRICE = 100;

    private final String flightNo;
    private List<Seat> seats = new LinkedList<>();
    private int seatNo = 1;

    public FlightBuilder(String flightNo) {
        this.flightNo = flightNo;
    }

    public FlightBuilder() {
        this(DEFAULT_FLIGHT_NO);
    }

    public FlightBuilder seat() {
        seats.add(new Seat(defaultSeatNo(), DEFAULT_PRICE));
        return this;
    }

    private String defaultSeatNo() {
        return "A" + (seatNo++);
    }

    public Flight build() {
        return new Flight(flightNo, seats);
    }

    public FlightBuilder seat(String seatNo) {
        seats.add(new Seat(seatNo, DEFAULT_PRICE));
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
