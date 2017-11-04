package org.workshop;

public class Seat {
    private final int price;
    private String seatNo;
    private boolean booked;

    public Seat(String seatNo, int price) {
        this.seatNo = seatNo;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isBooked() {
        return booked;
    }
}
