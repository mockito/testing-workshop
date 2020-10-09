import java.util.HashMap;
import java.util.Map;

public class FlightManager {
    private Map<String, Flight> flights = new HashMap<>();

    public void addFlight(String flightNo, Seat... seats) {
        flights.put(flightNo, new Flight(flightNo, seats));
    }

    public Flight getFlight(String flightNo) {
        Flight flight = flights.get(flightNo);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight;
    }
}
