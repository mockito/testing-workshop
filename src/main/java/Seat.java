public class Seat {

    private final Category category;

    private final int price;
    private final String seatNumber;

    public Seat(int price, String seatNumber, Category category) {
        this.price = price;
        this.seatNumber = seatNumber;
        this.category = category;
    }

    private boolean booked;

    public Category getCategory() {
        return category;
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

    enum Category {COACH, BUSINESS, FIRST}
}
