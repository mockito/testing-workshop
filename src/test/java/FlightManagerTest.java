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
        assertThat(manager.getFlights("KRK", "SFO")).containsOnly(f1, f4);
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
        assertThat(manager.getFlightsFrom("KRK")).containsOnly(f1, f2);
        assertThat(manager.getFlightsFrom("FRA")).isEmpty();
    }

    @Test
    void should_provide_flights_to_destination() {
        //given
        Flight f1 = new FlightBuilder().between("KRK", "SFO").build();
        Flight f2 = new FlightBuilder().between("KRK", "FRA").build();
        Flight f3 = new FlightBuilder().between("WAW", "FRA").build();

        manager.addFlights(f1, f2, f3);

        //expect
        assertThat(manager.getFlightsTo("FRA")).containsOnly(f2, f3);
        assertThat(manager.getFlightsTo("KRK")).isEmpty();
    }

    @Test
    public void should_deal_with_missing_flight() {
        //expect
        assertThatThrownBy(
                () -> manager.getFlight("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }

    @Test
    void should_provide_seats_cheaper_than_default() {
        //given
        Seat s1 = new SeatBuilder().category(Seat.Category.COACH).price(50).build();
        Seat s2 = new SeatBuilder().category(Seat.Category.BUSINESS).price(100).build();
        Seat s3 = new SeatBuilder().category(Seat.Category.BUSINESS).price(199).build();
        Seat s4 = new SeatBuilder().category(Seat.Category.BUSINESS).price(300).build();

        Flight flight = new FlightBuilder()
                .seats(s1, s2, s3, s4)
                .defaultPrice(Seat.Category.COACH, 50)
                .defaultPrice(Seat.Category.BUSINESS, 200).build();

        //expect
        assertThat(flight.getSeatsCheaperThanDefault(Seat.Category.BUSINESS)).containsOnly(s2, s3);
        assertThat(flight.getSeatsCheaperThanDefault(Seat.Category.COACH)).isEmpty();
    }

    @Test
    void should_fail_gracefully_when_no_default_price_provided() {
        //given
        Flight flight = new FlightBuilder().build();

        //expect
        assertThatThrownBy(() -> flight.getSeatsCheaperThanDefault(Seat.Category.FIRST))
                .isInstanceOf(DefaultPriceNotConfiguredException.class)
                .hasMessage("No default price configured for category 'FIRST'");
    }
}
