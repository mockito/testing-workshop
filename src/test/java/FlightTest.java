import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void should_provide_available_seats() {
        //given
        Flight flight = new Flight(new Seat(100), new Seat(100));

        //expect
        assertEquals(2, flight.getSeats());
    }

    @Test
    public void should_show_cheapest_seat() {
        //given
        Flight flight = new Flight(new Seat(100), new Seat(50));

        //expect
        assertEquals(50, flight.getCheapestSeat().getPrice());
    }

    @Test
    public void should_book_seat() {
        //given
        Seat seat1D = new Seat(100, "1D");
        Seat seat1E = new Seat(50, "1E");
        assertFalse(seat1D.isBooked());

        Flight flight = new Flight(seat1D, seat1E);

        //when
        Seat seat = flight.bookSeat("1D");

        //then
        assertTrue(seat.isBooked());
        assertEquals("1D", seat.getSeatNumber());
    }

    @Test
    void should_fail_when_wrong_seat_number() {
        //expect
        assertThatThrownBy(() -> new Flight().bookSeat("FOO"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Seat number not found: 'FOO'");
    }

    @Test
    void should_not_allow_booking_same_seat_again() {
        //given
        Flight flight = new Flight(new Seat(100, "A1"));

        //when
        flight.bookSeat("A1");

        //then
        assertThatThrownBy(() -> flight.bookSeat("A1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot book seat 'A1' because it is already booked");
    }
}
