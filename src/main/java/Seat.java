public class Seat {

    private final int price;
    private final String seatNumber;
    private boolean booked;

    public Seat(int price, String seatNumber) {
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isBooked() {
        return this.booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
