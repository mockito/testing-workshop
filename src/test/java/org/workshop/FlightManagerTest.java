package org.workshop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void should_provide_available_seats() throws Exception {
        //given
        manager.addFlight("AA101", new Seat(100), new Seat(200), new Seat(300));

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
        manager.addFlight("AA101", new Seat(100), new Seat(200));

        //when
        Seat cheapest = manager.getFlight("AA101").getCheapestSeat();

        //then
        assertEquals(100, cheapest.getPrice());
    }
}
