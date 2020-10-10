import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    void should_keep_flights() {
        //given
        manager.addFlights(
                new FlightBuilder().flightNumber("LH100").build(),
                new FlightBuilder().flightNumber("LH101").build()
        );

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
    void should_provide_flights_from_origin() {
        //given
        Flight f1 = new FlightBuilder().between("KRK", "SFO").build();
        Flight f2 = new FlightBuilder().between("KRK", "FRA").build();
        Flight f3 = new FlightBuilder().between("WAW", "SFO").build();

        manager.addFlights(f1, f2, f3);

        //expect
        assertThat(manager.getFlightsFrom("KRK")).containsExactly(f1, f2);
        assertThat(manager.getFlightsFrom("FRA")).isEmpty();
    }

    @Test
    public void should_deal_with_missing_flight() {
        //expect
        assertThatThrownBy(
                () -> manager.getFlight("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }
}
