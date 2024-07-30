public interface ATMOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(Account toAccount, double amount);
    void showTransactionHistory();
}
