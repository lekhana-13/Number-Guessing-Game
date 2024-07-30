import java.util.HashMap;
import java.util.Scanner;

// Class to hold user information
class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
// Class to hold ticket information
class Ticket {
    String PNR;
    String trainName;
    String trainNumber;
    String classType;
    String dateOfJourney;
    String from;
    String to;

    public Ticket(String PNR, String trainName, String trainNumber, String classType, String dateOfJourney, String from, String to) {
        this.PNR = PNR;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "Ticket{" +
               "PNR='" + PNR + '\'' +
               ", trainName='" + trainName + '\'' +
               ", trainNumber='" + trainNumber + '\'' +
               ", classType='" + classType + '\'' +
               ", dateOfJourney='" + dateOfJourney + '\'' +
               ", from='" + from + '\'' +
               ", to='" + to + '\'' +
               '}';
    }
}
public class OnlineReservationSystem {
    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, Ticket> tickets = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pre-adding some users and tickets for testing
        users.put("user1", new User("user1", "pass1"));
        tickets.put("PNR123", new Ticket("PNR123", "Express", "Train1", "AC", "2024-05-05", "CityA", "CityB"));

        while (true) {
            System.out.println("Welcome to Online Reservation System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
  private static void login(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            System.out.println("Login successful!");
            afterLogin(scanner);
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private static void afterLogin(Scanner scanner) {
        while (true) {
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
private static void makeReservation(Scanner scanner) {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type (e.g., AC, Sleeper): ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("From: ");
        String from = scanner.nextLine();
        System.out.print("To: ");
        String to = scanner.nextLine();
        String PNR = "PNR" + (tickets.size() + 1);  // simplistic PNR generation

        Ticket newTicket = new Ticket(PNR, trainName, trainNumber, classType, dateOfJourney, from, to);
        tickets.put(PNR, newTicket);
System.out.println("Reservation successful. Your PNR is: " + PNR);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR Number: ");
        String PNR = scanner.nextLine();

        Ticket ticket = tickets.get(PNR);
        if (ticket != null) {
            System.out.println("Ticket Found: " + ticket);
            System.out.print("Confirm Cancellation (yes/no): ");
            String confirm = scanner.nextLine();

            if ("yes".equalsIgnoreCase(confirm)) {
                tickets.remove(PNR);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
 else {
            System.out.println("No ticket found with PNR: " + PNR);
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}