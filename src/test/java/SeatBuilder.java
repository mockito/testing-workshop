public class SeatBuilder {
    private int price;
    private String seatNumber = "Z1";

    public SeatBuilder price(int price) {
        this.price = price;
        return this;
    }

    public Seat build() {
        return new Seat(price, seatNumber);
    }

    public SeatBuilder seatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }
}
