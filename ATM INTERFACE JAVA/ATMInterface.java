import java.util.Scanner;

class BankAccount {
    String name;
    String username;
    String password;
    String accountNumber;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";
    int pin = 7869;

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Name: ");
        this.name = scanner.nextLine();
        System.out.print("Enter your Username: ");
        this.username = scanner.nextLine();
        System.out.print("Enter your Password: ");
        this.password = scanner.nextLine();
        System.out.print("Enter your Account Number: ");
        this.accountNumber = scanner.nextLine();
        System.out.println("Registrated Successfully. Now, You can Log into your Bank Account.");
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your Username: ");
            String enteredUsername = scanner.nextLine();
            if (enteredUsername.equals(username)) {
                while (true) {
                    System.out.print("Enter your Password: ");
                    String enteredPassword = scanner.nextLine();
                    if (enteredPassword.equals(password)) {
                        System.out.println("Login Successful");
                        return true;
                    } else {
                        System.out.println("Incorrect password. Try again.");
                    }
                }
            } else {
                System.out.println("Username not found. Try again.");
            }
        }
    }

    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Amount to Withdraw: ");
        float amount = scanner.nextFloat();
        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("Withdrawn Successfully.");
            transactionHistory += amount + " Rs Withdrawn\n";
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Amount to Deposit: ");
        float amount = scanner.nextFloat();
        if (amount <= 10000f) {
            transactions++;
            balance += amount;
            System.out.println("Deposited Successfully.");
            transactionHistory += amount + " Rs Deposited\n";
        } else {
            System.out.println("Sorry. The limit is 10000.");
        }
    }

    public void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Recipient Name: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter Amount to Transfer: ");
        float amount = scanner.nextFloat();
        if (balance >= amount) {
            if (amount <= 50000f) {
                transactions++;
                balance -= amount;
                System.out.println("Transferred Successfully to " + recipient);
                transactionHistory += amount + " Rs Transferred to " + recipient + "\n";
            } else {
                System.out.println("Sorry. The limit is 50000.");
            }
        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: " + balance + " Rs");
    }

    public void showTransactionHistory() {
        if (transactions == 0) {
            System.out.println("No transactions have occurred.");
        } else {
            System.out.println(transactionHistory);
        }
    }

    public void changePin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Current PIN: ");
        int enteredPin = scanner.nextInt();
        if (enteredPin == pin) {
            System.out.print("Enter your New PIN: ");
            int newPin = scanner.nextInt();
            pin = newPin;
            System.out.println("PIN successfully changed.");
        } else {
            System.out.println("Incorrect current PIN. Please try again");
        }
    }
}

public class ATMInterface {

    private static int takeIntegerInput(int limit) {
        int input = 0;
        boolean isValid = false;
        Scanner scanner = new Scanner(System.in);
        while (!isValid) {
            try {
                input = scanner.nextInt();
                if (input >= 1 && input <= limit) {
                    isValid = true;
                } else {
                    System.out.println("Please choose a number between 1 and " + limit);
                }
            } catch (Exception e) {
                System.out.println("Enter a valid integer value.");
                scanner.next(); // clear the invalid input
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Welcome to Unity Bank ****");
        System.out.println("1. Register\n2. Exit");
        System.out.print("Choose an option: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.register();
            while (true) {
                System.out.println("1. Login\n2. Exit");
                System.out.print("Enter your choice: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (bankAccount.login()) {
                        System.out.println("**** Welcome Back " + bankAccount.name + " ****");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("1. Withdraw\n2. Deposit\n3. Transfer\n4. Check Balance\n5. Transaction History\n6. Change PIN\n7. Exit");
                            System.out.print("Enter your choice: ");
                            int option = takeIntegerInput(7);
                            switch (option) {
                                case 1:
                                    bankAccount.withdraw();
                                    break;
                                case 2:
                                    bankAccount.deposit();
                                    break;
                                case 3:
                                    bankAccount.transfer();
                                    break;
                                case 4:
                                    bankAccount.checkBalance();
                                    break;
                                case 5:
                                    bankAccount.showTransactionHistory();
                                    break;
                                case 6:
                                    bankAccount.changePin();
                                    break;
                                case 7:
                                    isFinished = true;
                                    System.out.println("Thank you for using Ushara Bank. Goodbye!");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("Exiting. Thank you for visiting Unity Bank.");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Exiting. Thank you for visiting Unity Bank.");
            System.exit(0);
        }
    }
}
