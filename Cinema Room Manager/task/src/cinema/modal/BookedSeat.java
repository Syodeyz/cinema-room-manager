package cinema.modal;

public class BookedSeat extends Seat{
    private boolean isBooked;

    public BookedSeat(int row, int column, boolean isBooked) {
        super(row, column);
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
