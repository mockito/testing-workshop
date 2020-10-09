import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Flight {

    private final List<Seat> seats;

    public Flight(List<Seat> seats) {
        Collections.sort(seats, Comparator.comparing(Seat::getPrice));
        this.seats = seats;
    }

    public int getSeats() {
        return seats.size();
    }

    public Seat getCheapestSeat() {
        return seats.iterator().next();
    }
}
