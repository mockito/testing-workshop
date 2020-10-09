import java.util.LinkedList;
import java.util.List;

public class FlightBuilder {

    private static int counter = 0;
    private String from;
    private String to;
    private String flightNumber = "LH10";
    private List<Seat> seats = new LinkedList<>();

    public FlightBuilder between(String from, String to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public Flight build() {
        return new Flight(flightNumber + counter++, seats, from, to);
    }
}
