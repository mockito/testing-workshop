import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class FlightManager {
    private Map<String, Flight> flights = new HashMap<>();

    public Flight getFlight(String flightNo) {
        Flight flight = flights.get(flightNo);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight;
    }

    public void addFlights(Flight... flights) {
        stream(flights).forEach((f) -> this.flights.put(f.getFlightNumber(), f));
    }

    public List<Flight> getFlights(String from, String to) {
        return flights.values().stream()
                .filter(flight -> flight.getOrigin().equals(from) && flight.getDestination().equals(to))
                .collect(Collectors.toList());
    }

    public List<Flight> getFlightsFrom(String from) {
        return flights.values().stream()
                .filter(flight -> flight.getOrigin().equals(from))
                .collect(Collectors.toList());
    }
}
