import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    void should_keep_flights() {
        //given
        manager.addFlight("LH100", new Seat(100, "A1"), new Seat(100, "A2"));
        manager.addFlight("LH101", new Seat(100, "A1"));

        //when
        Flight flight = manager.getFlight("LH100");

        //then
        assertEquals("LH100", flight.getFlightNumber());
    }

    @Test
    public void should_deal_with_missing_flight() {
        //expect
        assertThatThrownBy(
                () -> manager.getFlight("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }
}
