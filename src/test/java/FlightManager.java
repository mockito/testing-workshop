import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class FlightManager {
    private Map<String, Integer> flights = new HashMap<>();

    public void addFlight(String flightNo, int seats) {
        flights.put(flightNo, seats);
    }

    public int getAvailableSeats(String flightNo) {
        Integer seats = flights.get(flightNo);
        if (seats == null) {
            throw new FlightNotFoundException();
        }
        return seats;
    }
}
