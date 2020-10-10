import java.util.*;

public class Flight {

    private final List<Seat> seatsByPrice;
    private final Map<String, Seat> seats = new HashMap<>();
    private final String origin;
    private final String destination;
    private final String flightNumber;

    public Flight(String flightNumber, List<Seat> seats, String origin, String destination) {
        if (seats.size() == 0) {
            throw new IllegalArgumentException("Flights with no seats are not allowed.");
        }
        this.flightNumber = flightNumber;
        this.seatsByPrice = seats;
        Collections.sort(this.seatsByPrice, Comparator.comparing(Seat::getPrice));
        seats.stream().forEach((s) -> this.seats.put(s.getSeatNumber(), s));
        this.origin = origin;
        this.destination = destination;
    }

    public int getSeats() {
        return seatsByPrice.size();
    }

    public Seat getCheapestSeat() {
        return seatsByPrice.iterator().next();
    }

    public Seat bookSeat(String seatNumber) {
        Seat seat = seats.get(seatNumber);
        if (seat == null) {
            throw new IllegalArgumentException("Seat number not found: '" + seatNumber + "'");
        }

        if (seat.isBooked()) {
            throw new IllegalArgumentException("Cannot book seat '" + seatNumber + "' because it is already booked");
        }
        seat.setBooked(true);
        return seat;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public int getAveragePriceNonBooked() {
        return (int) this.seatsByPrice.stream()
                .filter(seat -> !seat.isBooked())
                .mapToInt(Seat::getPrice).average().getAsDouble();
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public int getAveragePrice(Seat.Category category) {
        OptionalDouble result = this.seatsByPrice.stream()
                .filter(seat -> seat.getCategory() == category)
                .mapToInt(Seat::getPrice).average();
        if (!result.isPresent()) {
            throw new SeatNotFoundException("No seats with '" + category + "' category found");
        }
        return (int) result.getAsDouble();
    }
}
