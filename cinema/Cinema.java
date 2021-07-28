package cinema;

import java.util.Scanner;

public class Cinema {

    private static char[][] cinema;
    private static int rows;
    private static int seatsNumber;
    private static int bookedSeats;
    private static int totalNumberOfSeats;
    private static int currentIncome;
    private static int totalIncome;
    private static Scanner scanner = new Scanner(System.in);

    final private static int TICKET_PRICE = 10;
    final private static int FAR_TICKET_PRICE = 8;


    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seatsNumber = scanner.nextInt();

        cinema = new char[rows][seatsNumber];
        cinemaCreate();

        menu();
    }

    public static void cinemaCreate() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsNumber; j++) {
                cinema[i][j] = 'S';
            }
        }

        bookedSeats = 0;
        currentIncome = 0;
        totalNumberOfSeats = rows * seatsNumber;
        totalIncome = totalNumberOfSeats < 60 ? TICKET_PRICE * totalNumberOfSeats :
                FAR_TICKET_PRICE * (rows - rows / 2) * seatsNumber +
                        TICKET_PRICE * seatsNumber * (rows / 2);
    }

    public static void menu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int menuItem = scanner.nextInt();

        if (menuItem == 1) {
            showTheSeats();
            menu();
        } else if (menuItem == 2) {
            buyTicket();
            menu();
        } else if (menuItem == 3) {
            showStatistics();
            menu();
        }
    }

    public static void showTheSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsNumber; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < seatsNumber; j++) {
                System.out.print(cinema[i - 1][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket() {
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();

        if (rowNumber > rows || seatNumber > seatsNumber || rowNumber <= 0 || seatNumber <= 0) {
            System.out.println("Wrong input!");
            buyTicket();
        } else if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket();
        } else {
            int price = 10;
            if (totalNumberOfSeats > 60) {
                price = rowNumber <= rows / 2 ? 10 : 8;
            }
            System.out.println("Ticket price: $" + price);
            cinema[rowNumber - 1][seatNumber - 1] = 'B';
            bookedSeats++;
            currentIncome += price;
        }
    }

    public static void showStatistics() {
        System.out.println(String.format("Number of purchased tickets: %d", bookedSeats));
        System.out.println(String.format("Percentage: %.2f%%", (float) bookedSeats * 100 / totalNumberOfSeats));
        System.out.println(String.format("Current income: $%d", currentIncome));
        System.out.println(String.format("Total income: $%d", totalIncome));
    }
}