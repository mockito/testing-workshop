import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightManagerTest {

    @Test
    public void should_show_available_seats() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight("LH123", new Seat(100), new Seat(100));
        manager.addFlight("AA900", new Seat(200));

        //when
        int seats = manager.getFlight("LH123").getSeats();

        //then
        assertEquals(2, seats);
    }

    @Test
    public void should_deal_with_missing_flight() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight("LH123", new Seat(100));

        //expect
        assertThatThrownBy(
                () -> manager.getFlight("AA900"))
                .isInstanceOf(FlightNotFoundException.class);
    }

    @Test
    public void should_show_cheapest_seat() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight("LH123", new Seat(100), new Seat(50));
        manager.addFlight("LH500", new Seat(30));

        //when
        Seat seat = manager.getFlight("LH123").getCheapestSeat();

        //then
        assertEquals(50, seat.getPrice());
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
