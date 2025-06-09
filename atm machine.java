import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposit successful. Current balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawal successful. Current balance: $" + balance);
    }
}

public class ATM {
    private static Map<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        accounts.put(12345, new Account(12345, 1111, 5000.0));
        accounts.put(67890, new Account(67890, 2222, 3000.0));

        System.out.println("===== Welcome to the ATM =====");
        System.out.print("Enter Account Number: ");
        int accNumber = scanner.nextInt();

        if (!accounts.containsKey(accNumber)) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = accounts.get(accNumber);
        if (account.getPin() != pin) {
            System.out.println("Incorrect PIN.");
            return;
        }

        System.out.println("Login successful.\n");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid amount! Enter numeric values only.");
                        scanner.next();
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid amount! Enter numeric values only.");
                        scanner.next();
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
            System.out.println();
        }
    }
}
