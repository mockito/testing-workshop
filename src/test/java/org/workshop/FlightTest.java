package org.workshop;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class FlightTest {

    @Test
    public void should_provide_available_seats() throws Exception {
        //given
        Flight flight = new FlightBuilder("AA101").seat().seat().seat().build();

        //when
        int seats = flight.getAvailableSeats();

        //then
        assertEquals(3, seats);
    }

    @Test
    public void should_provide_cheapest_seat() throws Exception {
        //given
        Flight flight = new FlightBuilder("AA101").seat(100).seat(200).build();

        //when
        Seat cheapest = flight.getCheapestSeat();

        //then
        assertEquals(100, cheapest.getPrice());
    }

    @Test
    public void should_book_seat() throws Exception {
        //given
        Flight flight = new FlightBuilder("AA101").seat("21A").seat("22K").build();

        //when
        flight.book("21A");

        //then
        assertEquals(1, flight.getAvailableSeats());
    }

    @Test
    public void should_not_allow_flight_with_no_seats() throws Exception {
        try {
            new Flight("AA101", Collections.emptyList());
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void should_book_only_available_seat() throws Exception {
        //given
        Flight flight = new FlightBuilder("AA101").seat("21A").seat("22K").build();
        flight.book("21A");

        try {
            //when
            flight.book("21A");
            //then
            fail();
        } catch (AlreadyBookedException e) {}
    }
}