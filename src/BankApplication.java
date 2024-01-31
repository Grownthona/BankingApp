import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
/**
 * InnerBankApplication
 */
// Class to represent account information
class AccountType {
    private String accountType;
    private double minimumDeposit;

    public AccountType(String accountType, double minimumDeposit) {
        this.accountType = accountType;
        this.minimumDeposit = minimumDeposit;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getMinimumDeposit() {
        return minimumDeposit;
    }
}

class Bank {

    private static final String BANK_CODE = "1498273612"; // Example bank code
    private static final int ACCOUNT_NUMBER_LENGTH = 16;

    public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder(BANK_CODE);

        // Generate random numbers for the account number
        Random random = new Random();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - BANK_CODE.length(); i++) {
            accountNumber.append(random.nextInt(10)); // Append a random digit (0-9)
        }
        // Adding another random number at the digit.
        return accountNumber.toString();
    }

    public static boolean isValidName(String name) {
        // Check if the name is not empty and contains only letters and spaces
        return !name.isEmpty() && name.matches("[a-zA-Z ]+");
    }
    
    public void createAccount(){

        List<AccountType> accountType = new ArrayList<>();
        accountType.add(new AccountType("Savings", 100.0));
        accountType.add(new AccountType("Checking", 50.0));
        accountType.add(new AccountType("Fixed Deposit", 1000.0));
        accountType.add(new AccountType("Current", 200.0));
        accountType.add(new AccountType("Salary", 500.0));
        accountType.add(new AccountType("Retirement", 100.0));
        accountType.add(new AccountType("Bussiness", 2000.0));
        accountType.add(new AccountType("Foreign Currency", 3000.0));



        Scanner scanner = new Scanner(System.in);
        Map<String, Double> hm = new HashMap<String, Double>(); 


        System.out.println("------- Creating Account -------");
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        while (!isValidName(name) || name.isEmpty()) {
            System.out.println("Invalid name. Please enter a valid name containing only letters and spaces.");
            System.out.print("Enter account holder's name: ");
            name = scanner.nextLine();
        }

        System.out.println("Account Type\t\tMinimum Deposit Amount");
        System.out.println("--------------------------------------------");
        for (AccountType account : accountType) {
            hm.put(account.getAccountType(), account.getMinimumDeposit()); 
            System.out.printf("%-20s\t\t$%.2f%n", account.getAccountType(), account.getMinimumDeposit());
        }


        System.out.print("Enter account type: ");
        String accType = scanner.nextLine();

        // Check if the user input matches any of the known account types
        boolean isValidAccountType = false;
        for (AccountType account : accountType) {
            if (accType.equalsIgnoreCase(account.getAccountType())) {
                isValidAccountType = true;
                break;
            }
        }
        double initialBalance = hm.get(accType);
        // Take input until user has given correct Account Type
        while(!isValidAccountType) {
            System.out.println("Invalid account type. Please type again.");
            System.out.print("Enter account type: ");
            String newaccType = scanner.nextLine();
            initialBalance = hm.get(newaccType);
            for (AccountType account : accountType) {
                if (newaccType.equalsIgnoreCase(account.getAccountType())) {
                    isValidAccountType = true;
                    break;
                }
            }
        }
        System.out.println("Initial Balance : "+initialBalance); 

        System.out.print("Enter minimum balance: ");
        double minBalance = scanner.nextDouble();

        scanner.nextLine();

        String creationDate = java.time.LocalDate.now().toString();
        System.out.println("Account Creation Date: " + creationDate);

        String accountNumber = generateAccountNumber();
        System.out.println("Generated Account Number: " + accountNumber);
    }
}
class ApplicationMenu {

    protected void Menu(){
        
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        while (true) {
            System.out.println(" ===== Bank Application ===== ");
            System.out.println("1. Create a new account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Update an account");
            System.out.println("4. Delete an account");
            System.out.println("5. Deposit an amount into your account");
            System.out.println("6. Withdraw an amount from your account");
            System.out.println("7. Search for account");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if( choice == 1 ){
                bank.createAccount();
                break;
            }
            break;
        }
    }
}
public class BankApplication {
    public static void main(String[] args) {
        ApplicationMenu application = new ApplicationMenu();
        application.Menu();
    }
}
