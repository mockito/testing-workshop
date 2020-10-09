import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

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
        //given

        //when

        //then
    }
}
