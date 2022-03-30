package cinema.modal;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Hall {
    private int totalRows;
    private int totalColumns;
    private List<Seat> seatsList = Collections.synchronizedList(new ArrayList<>());
    private List<BookedSeat> bookedSeatList = Collections.synchronizedList(new ArrayList<>());

    public Hall(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        initSeatList();
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seat> seatsList) {
        this.seatsList = seatsList;
    }

    public List<BookedSeat> getBookedSeatList() {
        return bookedSeatList;
    }

    public void setBookedSeatList(List<BookedSeat> bookedSeatList) {
        this.bookedSeatList = bookedSeatList;
    }

    /***
     * Method to initialize the seat list and display seats status
     */
    public void initSeatList() {
        if(totalRows <= 0 || totalColumns <= 0 || totalRows > 9 || totalColumns > 9 ) {
            throw new Error("Out of bounds");
        }
        System.out.println("Cinema:");
        for(int i = 0; i <= totalRows; i++) {
            for(int j = 0; j <= totalColumns; j++) {
                //if row == 0 and col == 0 print " "
                if(i == 0 && j == 0) {
                    System.out.print(" ");
                }else if(i == 0) {
                    System.out.print(j + " ");
                } else if(j == 0) {
                    System.out.print(i + " ");
                } else {
                    Seat seat = new Seat(i, j);
                    seatsList.add(seat);
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }

    public boolean isAlreadyBooked(int row, int column) {
        for(BookedSeat bookedSeat : bookedSeatList) {
            if (bookedSeat.getRow() == row && bookedSeat.getColumn() == column) return true;
        }
        return false;
    }

    /***
     * Method to update the booked seat list and update the display of the seats
     */
    public void updateSeatList() {
        if(totalRows <= 0 || totalColumns <= 0 || totalRows > 9 || totalColumns > 9 ) {
            throw new Error("Out of bounds");
        }
        System.out.println("Cinema:");
        for(int i = 0; i <= totalRows; i++) {
            for(int j = 0; j <= totalColumns; j++) {
                //if row == 0 and col == 0 print " "
                if(i == 0 && j == 0) {
                    System.out.print(" ");
                } else if(i == 0) {
                    System.out.print(j + " ");
                } else if(j == 0) {
                    System.out.print(i + " ");
                } else if(isAlreadyBooked(i, j)){
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }


    public void showStats() {
        int numberOfPurchasedTicket = bookedSeatList.size();
        float soldTicketPercentage = (float) numberOfPurchasedTicket / (totalRows * totalColumns) * 100;
        int currentIncome = calculateCurrentIncome();
        int totalIncome = calculateProfit();

        System.out.printf("Number of purchased tickets: %d%n", numberOfPurchasedTicket);
        System.out.printf("Percentage: %.2f%%%n", soldTicketPercentage );
        System.out.printf("Current income: $%d%n", currentIncome );
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    /**
     * method to calculate the profit
     */
    public int calculateProfit() {
        // calculate total number of seat
        int numberOfSeats = totalRows * totalColumns;
        int totalIncome = 0;
        if(numberOfSeats <= 60) {
            totalIncome = numberOfSeats * 10;
        } else {
            totalIncome += totalRows / 2 * 10 * totalColumns;
            totalIncome += (totalRows - totalRows / 2) * 8 * totalColumns;
        }
        return totalIncome;

    }


    /**
     * method to calculate ticket price
     */
    public int calculateTicketPrice(int row) {
        int pricePerSeat;
        if(row <= 0 || row > totalRows) return 0;
        if( totalRows * totalColumns <= 60) {
            pricePerSeat = 10;
        } else {
            int firstHalf = totalRows / 2;
            if(row <= firstHalf ) {
                pricePerSeat = 10;
            } else {
                pricePerSeat = 8;
            }
        }
        return pricePerSeat;
    }

    /***
     * method to calculate the current income of the hall
     * @return (int) current income depending on the sold tickets
     */
    public int calculateCurrentIncome() {
        int currentIncome = 0;
        for(BookedSeat seat : bookedSeatList) {
            int  pricePerSeat = calculateTicketPrice(seat.getRow());
            currentIncome += pricePerSeat;
        }
        return currentIncome;
    }

}

