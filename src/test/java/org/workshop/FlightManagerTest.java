package org.workshop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void should_provide_available_seats() throws Exception {
        //given
        manager.addFlight("AA101", 10);

        //when
        int seats = manager.getAvailableSeats("AA101");

        //then
        assertEquals(10, seats);
    }

    @Test
    public void should_fail_when_flight_not_found() throws Exception {
        //given
        try {
            //when
            manager.getAvailableSeats("UA999");
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
        Seat cheapest = manager.getCheapestSeat("AA101");

        //then
        assertEquals(100, cheapest.getPrice());
    }

    @Test
    public void should_fail_when_no_flight_when_getting_cheapest_seat() throws Exception {
        //given

        //when

        //then
    }
}
