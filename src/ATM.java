import java.util.*;

public class ATM {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public ATM() {
        accounts.put("12345", new Account("12345", "1111", 5000));
        accounts.put("67890", new Account("67890", "2222", 3000));
    }

    public void start() {
        System.out.println("===== Welcome to the ATM =====");
        Account account = login();

        if (account == null) return;

        boolean session = true;
        while (session) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> System.out.println("Available Balance: $" + account.getBalance());
                case "2" -> handleDeposit(account);
                case "3" -> handleWithdrawal(account);
                case "4" -> {
                    System.out.println("Thank you for using the ATM!");
                    session = false;
                }
                default -> System.out.println("Invalid option! Please select 1, 2, 3, or 4.");
            }
        }
    }

    private Account login() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        Account account = accounts.get(accNum);
        if (account == null) {
            System.out.println("Account not found.");
            return null;
        }

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (!account.validatePin(pin)) {
            System.out.println("Incorrect PIN.");
            return null;
        }

        System.out.println("Login successful.");
        return account;
    }

    private void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Your Choice: ");
    }

    private void handleDeposit(Account account) {
        try {
            System.out.print("Enter amount to deposit: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.deposit(amount);
            System.out.println("Deposit successful. Current balance: $" + account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount! Enter numeric values only.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleWithdrawal(Account account) {
        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);
            System.out.println("Withdrawal successful. Current balance: $" + account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount! Enter numeric values only.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}