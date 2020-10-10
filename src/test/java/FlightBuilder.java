import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class FlightBuilder {

    private static int counter = 0;
    private String from;
    private String to;
    private String flightNumber = null;
    private List<Seat> seats = new LinkedList<>();

    public FlightBuilder between(String from, String to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public Flight build() {
        String flightNo = (this.flightNumber == null) ? "XX10" + counter++ : flightNumber;
        return new Flight(flightNo, seats, from, to);
    }

    public FlightBuilder flightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightBuilder seats(Seat... seats) {
        this.seats = asList(seats);
        return this;
    }
}
