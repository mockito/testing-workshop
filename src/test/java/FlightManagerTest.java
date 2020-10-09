import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
    void should_show_flights_between_cities() {
        //given
        Flight f1 = new FlightBuilder().between("KRK", "SFO").build();
        Flight f2 = new FlightBuilder().between("KRK", "FRA").build();
        Flight f3 = new FlightBuilder().between("WAW", "SFO").build();
        Flight f4 = new FlightBuilder().between("KRK", "SFO").build();

        manager.addFlights(f1, f2, f3, f4);

        //expect
        assertThat(manager.getFlights("KRK", "SFO")).containsExactly(f1, f4);
        assertThat(manager.getFlights("WAW", "FRA")).isEmpty();
    }

    @Test
    public void should_deal_with_missing_flight() {
        //expect
        assertThatThrownBy(
                () -> manager.getFlight("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }
}
