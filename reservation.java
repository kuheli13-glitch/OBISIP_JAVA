import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Reservation {
    private String name;
    private int roomNumber;

    public Reservation(String name, int roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}

class ReservationSystem {
    private Map<Integer, Reservation> reservations;
    private int nextRoomNumber;

    public ReservationSystem() {
        reservations = new HashMap<>();
        nextRoomNumber = 101; // Start room numbers from 101
    }

    public void makeReservation(String name) {
        Reservation reservation = new Reservation(name, nextRoomNumber);
        reservations.put(nextRoomNumber, reservation);
        nextRoomNumber++;
        System.out.println("Reservation for " + name + " made. Room number: " + reservation.getRoomNumber());
    }

    public void showReservations() {
        System.out.println("Current Reservations:\n");
        for (Reservation reservation : reservations.values()) {
            System.out.println("Room " + reservation.getRoomNumber() + ": " + reservation.getName());
        }
    }

    public void cancelReservation(int roomNumber) {
        if (reservations.containsKey(roomNumber)) {
            Reservation canceledReservation = reservations.remove(roomNumber);
            System.out.println("Reservation for " + canceledReservation.getName() + " in room " + roomNumber + " canceled.");
        } else {
            System.out.println("No reservation found for room " + roomNumber + ".");
        }
    }

    public void modifyReservation(int roomNumber, String newName) {
        if (reservations.containsKey(roomNumber)) {
            Reservation reservation = reservations.get(roomNumber);
            reservation.setName(newName);
            System.out.println("Reservation for room " + roomNumber + " updated with new guest name: " + newName);
        } else {
            System.out.println("No reservation found for room " + roomNumber + ".");
        }
    }
}

class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make Reservation");
            System.out.println("2. Show Reservations");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Modify Reservation");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    reservationSystem.makeReservation(name);
                    break;
                case 2:
                    reservationSystem.showReservations();
                    break;
                case 3:
                    System.out.print("Enter room number to cancel reservation: ");
                    int roomToCancel = scanner.nextInt();
                    reservationSystem.cancelReservation(roomToCancel);
                    break;
                case 4:
                    System.out.print("Enter room number to modify reservation: ");
                    int roomToModify = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new guest name: ");
                    String newGuestName = scanner.nextLine();
                    reservationSystem.modifyReservation(roomToModify, newGuestName);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
