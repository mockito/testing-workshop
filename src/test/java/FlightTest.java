import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void should_provide_available_seats() {
        //given
        Flight flight = new FlightBuilder().seats(
                new SeatBuilder().build(), new SeatBuilder().build()
        ).build();

        //expect
        assertEquals(2, flight.getSeats());
    }

    @Test
    public void should_show_cheapest_seat() {
        //given
        Flight flight = new FlightBuilder().seats(
                new SeatBuilder().price(100).build(),
                new SeatBuilder().price(50).build()
        ).build();

        //expect
        assertEquals(50, flight.getCheapestSeat().getPrice());
    }

    @Test
    public void should_book_seat() {
        //given
        Seat seat1D = new SeatBuilder().seatNumber("1D").build();
        Seat seat1E = new SeatBuilder().seatNumber("1E").build();
        assertFalse(seat1D.isBooked());

        Flight flight = new FlightBuilder().seats(seat1D, seat1E).build();

        //when
        Seat seat = flight.bookSeat("1D");

        //then
        assertTrue(seat.isBooked());
        assertEquals("1D", seat.getSeatNumber());
    }

    @Test
    void should_fail_when_wrong_seat_number() {
        //expect
        assertThatThrownBy(() -> new FlightBuilder().build().bookSeat("X1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Seat number not found: 'X1'");
    }

    @Test
    public void should_not_allow_flights_with_no_seats() {
        //expect
        assertThatThrownBy(
                () -> new Flight("LH100", emptyList(), "KRK", "SFO", new HashMap<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Flights with no seats are not allowed.");
    }

    @Test
    void should_not_allow_booking_same_seat_again() {
        //given
        Flight flight = new FlightBuilder().seats(
                new SeatBuilder().seatNumber("A1").build()
        ).build();

        //when
        flight.bookSeat("A1");

        //then
        assertThatThrownBy(() -> flight.bookSeat("A1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot book seat 'A1' because it is already booked");
    }

    @Test
    void should_provide_average_price_for_unbooked() {
        //given
        Flight flight = new FlightBuilder().seats(
                new SeatBuilder().seatNumber("A1").price(50).build(),
                new SeatBuilder().seatNumber("A2").price(100).build(),
                new SeatBuilder().seatNumber("B1").price(200).build()
        ).build();

        //when
        flight.bookSeat("B1");

        //then
        assertEquals(75, flight.getAveragePriceNonBooked());
    }

    @Test
    void should_provide_average_price_for_category() {
        //given
        Flight flight = new FlightBuilder().seats(
                new SeatBuilder().category(Seat.Category.COACH).price(50).build(),
                new SeatBuilder().category(Seat.Category.COACH).price(100).build(),
                new SeatBuilder().category(Seat.Category.BUSINESS).price(200).build()
        ).build();

        //expect
        assertEquals(75, flight.getAveragePrice(Seat.Category.COACH));
        assertEquals(200, flight.getAveragePrice(Seat.Category.BUSINESS));

        assertThatThrownBy(() -> flight.getAveragePrice(Seat.Category.FIRST))
                .isInstanceOf(SeatNotFoundException.class)
                .hasMessage("No seats with 'FIRST' category found");
    }
}
