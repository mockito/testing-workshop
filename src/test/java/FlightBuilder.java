import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class FlightBuilder {

    private static int counter = 0;
    private String from;
    private String to;
    private String flightNumber = null;
    private List<Seat> seats = new LinkedList<>();
    private Map<Seat.Category, Integer> defaultPrices = new HashMap<>();

    public FlightBuilder between(String from, String to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public Flight build() {
        String flightNo = (this.flightNumber == null) ? "XX10" + counter++ : flightNumber;
        List<Seat> seats = (this.seats.isEmpty()) ? asList(new SeatBuilder().build()) : this.seats;
        return new Flight(flightNo, seats, from, to, defaultPrices);
    }

    public FlightBuilder flightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightBuilder seats(Seat... seats) {
        this.seats = asList(seats);
        return this;
    }

    public FlightBuilder defaultPrice(Seat.Category category, int price) {
        this.defaultPrices.put(category, price);
        return this;
    }
}
