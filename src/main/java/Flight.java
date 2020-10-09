import java.util.*;

import static java.util.Arrays.asList;

public class Flight {

    private final List<Seat> seatsByPrice;
    private final Map<String, Seat> seats = new HashMap<>();

    public Flight(List<Seat> seats) {
        Collections.sort(seats, Comparator.comparing(Seat::getPrice));
        this.seatsByPrice = seats;
    }

    public Flight(Seat... seats) {
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
        seat.setBooked(true);
        return seat;
    }
}
