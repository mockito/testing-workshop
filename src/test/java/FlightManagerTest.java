import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void should_deal_with_missing_flight() {
        //expect
        assertThatThrownBy(
                () -> manager.getFlight("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }

    @Test
    public void should_not_allow_flights_with_no_seats() {
        //expect
        assertThatThrownBy(
                () -> new FlightManager().addFlight("LH100"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Flights with no seats are not allowed.");
    }
}
