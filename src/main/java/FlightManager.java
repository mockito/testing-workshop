import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class FlightManager {
    private Map<String, List<Seat>> flights = new HashMap<>();

    public void addFlight(String flightNo, Seat... seats) {
        if (seats.length == 0) {
            throw new IllegalArgumentException("Flights with no seats are not allowed.");
        }
        flights.put(flightNo, asList(seats));
    }

    public Flight getFlight(String flightNo) {
        List<Seat> seats = flights.get(flightNo);
        if (seats == null) {
            throw new FlightNotFoundException();
        }
        return new Flight(seats);
    }
}
