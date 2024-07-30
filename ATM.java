import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM implements ATMOperations {
    private Map<String, User> users = new HashMap<>();
    private User currentUser;

    public ATM() {
        // Initialize some users for demonstration
        Account account1 = new Account(1000);
        User user1 = new User("user123", "pin123", account1);
        users.put(user1.getUserId(), user1);

        Account account2 = new Account(500);
        User user2 = new User("user456", "pin456", account2);
        users.put(user2.getUserId(), user2);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to ATM!");
            System.out.print("Enter User ID: ");
            String userId = scanner.next();
            System.out.print("Enter PIN: ");
            String pin = scanner.next();

            User user = users.get(userId);
            if (user != null && user.getPin().equals(pin)) {
                currentUser = user;
                System.out.println("Login successful!");
                showMenu(scanner);
            } else {
                System.out.println("Invalid User ID or PIN. Please try again.");
            }
        }
    }

    private void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            double amount;

            switch (choice) {
                case 1:
                    System.out.println("Current balance: $" + currentUser.getAccount().getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    amount = scanner.nextDouble();
                    deposit(amount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    amount = scanner.nextDouble();
                    withdraw(amount);
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientId = scanner.next();
                    User recipient = users.get(recipientId);
                    if (recipient != null) {
                        System.out.print("Enter amount to transfer: ");
                        amount = scanner.nextDouble();
                        transfer(recipient.getAccount(), amount);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return; // Return to login screen
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    @Override
    public void deposit(double amount) {
        Transaction transaction = new Transaction(amount, "DEPOSIT");
        currentUser.getAccount().addTransaction(transaction);
        System.out.println("Deposited: $" + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (currentUser.getAccount().getBalance() >= amount) {
            Transaction transaction = new Transaction(-amount, "WITHDRAW");
            currentUser.getAccount().addTransaction(transaction);
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        if (currentUser.getAccount().getBalance() >= amount) {
            // Withdraw from current user
            Transaction withdrawTransaction = new Transaction(-amount, "TRANSFER OUT");
            currentUser.getAccount().addTransaction(withdrawTransaction);

            // Deposit to recipient
            Transaction depositTransaction = new Transaction(amount, "TRANSFER IN");
            toAccount.addTransaction(depositTransaction);

            System.out.println("Transferred: $" + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : currentUser.getAccount().getTransactions()) {
            System.out.println(transaction);
        }
    }
}
