package org.workshop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void should_provide_available_seats() throws Exception {
        //given
        Flight flight = new FlightBuilder("AA101").seat().build();
        manager.addFlight(flight);

        //expect
        assertEquals(flight, manager.getFlight("AA101"));
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
}
