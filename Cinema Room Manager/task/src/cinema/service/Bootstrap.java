package cinema.service;

import cinema.modal.BookedSeat;
import cinema.modal.Hall;

import java.util.Scanner;


public class Bootstrap {


    public Bootstrap( ) {
        init();
    }

    /**
     * method to initialize the Cinema Hall
     */
    public void init() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int column = scanner.nextInt();

        Hall hall = new Hall(row, column);
        scanner.close();
        showMenu();
        int menuOption = readMenuOption();
        handleMenu(hall, menuOption);

    }



    public void showMenu() {
        // show Menu
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public int readMenuOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void handleMenu(Hall hall, int menuOption) {


        switch (menuOption) {
            case 1:
                // show the seats
                hall.updateSeatList();
                showMenu();
                handleMenu(hall, readMenuOption());
                break;

            case 2:
                //Buy a ticket
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a row number:");
                int selectedRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int selectedColumn = scanner.nextInt();
                if (selectedColumn <= 0 || selectedColumn > hall.getTotalColumns() || selectedRow <= 0 || selectedRow > hall.getTotalRows()) {
                    System.out.println("Wrong input!");
                    handleMenu(hall, 2);
                } else {
                    boolean isAlreadyBooked = hall.isAlreadyBooked(selectedRow, selectedColumn);
                    if(!isAlreadyBooked) {
                        BookedSeat bookedSeat = new BookedSeat(selectedRow, selectedColumn, true);
                        hall.getBookedSeatList().add(bookedSeat);
                        hall.updateSeatList();
                        int ticketPrice =  hall.calculateTicketPrice(selectedRow);
                        System.out.printf("Ticket price: $%d%n", ticketPrice);
                        showMenu();
                        handleMenu(hall, readMenuOption());
                    } else {
                        System.out.println("That ticket has already been purchased!");
                        handleMenu(hall, 2);
                    }
                }



                break;

            case 3:
                // show stats
                hall.showStats();
                showMenu();
                handleMenu(hall, readMenuOption());
                break;
            case 0:
                return;

            default:
                handleMenu(hall, readMenuOption());
                break;
        }
    }


















}
