public class SeatBuilder {
    private int price;

    public SeatBuilder price(int price) {
        this.price = price;
        return this;
    }

    public Seat build() {
        return new Seat(price);
    }
}
