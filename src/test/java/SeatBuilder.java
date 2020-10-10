public class SeatBuilder {
    private int price;
    private String seatNumber = "Z1";
    private Seat.Category category;

    public SeatBuilder price(int price) {
        this.price = price;
        return this;
    }

    public Seat build() {
        return new Seat(price, seatNumber, category);
    }

    public SeatBuilder seatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public SeatBuilder category(Seat.Category category) {
        this.category = category;
        return this;
    }
}
