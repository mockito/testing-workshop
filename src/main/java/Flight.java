import java.util.*;

import static java.util.Arrays.asList;

public class Flight {

    private final List<Seat> seatsByPrice;
    private final Map<String, Seat> seats = new HashMap<>();
    private final String flightNumber;

    public Flight(Seat... seats) {
        this("FOO", seats);
    }

    public Flight(String flightNumber, Seat... seats) {
        if (seats.length == 0) {
            throw new IllegalArgumentException("Flights with no seats are not allowed.");
        }
        this.flightNumber = flightNumber;
        this.seatsByPrice = asList(seats);
        Collections.sort(this.seatsByPrice, Comparator.comparing(Seat::getPrice));
        for (Seat s : seats) {
            this.seats.put(s.getSeatNumber(), s);
        }
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
}
