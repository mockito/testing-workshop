package org.workshop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void should_provide_available_seats() throws Exception {
        //given
        manager.addFlight(new FlightBuilder("AA101").seat().seat().seat().build());

        //when
        int seats = manager.getFlight("AA101").getAvailableSeats();

        //then
        assertEquals(3, seats);
    }

    @Test
    public void should_fail_when_flight_not_found() throws Exception {
        //given
        try {
            //when
            manager.getFlight("UA999");
            //then
            fail();
        } catch (FlightNotFoundException e) {
        }
    }

    @Test
    public void should_provide_cheapest_seat() throws Exception {
        //given
        manager.addFlight(new FlightBuilder("AA101").seat(100).seat(200).build());

        //when
        Seat cheapest = manager.getFlight("AA101").getCheapestSeat();

        //then
        assertEquals(100, cheapest.getPrice());
    }

    @Test
    public void should_book_seat() throws Exception {
        //given
        manager.addFlight(new FlightBuilder("AA101").seat("21A").seat("22K").build());

        //when
        manager.getFlight("AA101").book("21A");

        //then
        assertEquals(1, manager.getFlight("AA101").getAvailableSeats());
    }

    @Test
    public void should_not_allow_flight_with_no_seats() throws Exception {
        try {
            manager.addFlight(new FlightBuilder("AA101").build());
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void should_book_only_available_seat() throws Exception {
        //given
        manager.addFlight(new FlightBuilder("AA101").seat("21A").seat("22K").build());
        manager.getFlight("AA101").book("21A");

        try {
            //when
            manager.getFlight("AA101").book("21A");
            //then
            fail();
        } catch (AlreadyBookedException e) {}
    }
}
