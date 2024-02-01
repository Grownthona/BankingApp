import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * InnerBankApplication
 */
class Account {
    //private Bank bank;
    private String name;
    protected String accNumber;
    private String accType;
    protected double balance;
    private String creationDate;

    public Account(String name, String accNumber, String accType, double balance,  String creationDate) {
        this.name = name;
        this.accNumber = accNumber;
        this.accType = accType;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public void display() {
        System.out.println("Account Number: " + accNumber + ", Name: " + name + ", Type: " + accType + ", Balance: " + balance + ", Creation Date: " + creationDate);
    }

    public String getAccNumber() {
        return accNumber;
    }

    public String UpdateName(String name) {
        return this.name = name;
    }
    public String updateAccountType(String accType) {
        return this.accType = accType;
    }
    public String updateAccountCreation(String creationDate) {
        return this.creationDate = creationDate;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " into account " + accNumber + ". Current balance: " + balance);
    }

    public void withdraw(double amount,double minBalance) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from account " + accNumber + ". Current balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

class MinimumBalanceAccount extends Account {
    
    private static ArrayList<MinimumBalanceInfo> minimumBalanceInfoList = new ArrayList<>();
    private double minBalance;

    public MinimumBalanceAccount(String name, String accNumber, String accType, double balance, String creationDate, double minBalance) {
        super(name, accNumber, accType, balance, creationDate);
        this.minBalance = minBalance;
    }

    public static void addMinimumBalanceInfo(String accType, double minAmount) {
        MinimumBalanceInfo info = new MinimumBalanceInfo(accType, minAmount);
        minimumBalanceInfoList.add(info);
    }

    public static double getMinimumBalance(String accType) {
        for (MinimumBalanceInfo info : minimumBalanceInfoList) {
            if (info.getAccType().equals(accType)) {
                return info.getMinAmount();
            }
        }
        return -1; // Return -1 if account type not found
    }
}

class MinimumBalanceInfo {
    private String accType;
    private double minAmount;

    public MinimumBalanceInfo(String accType, double minAmount) {
        this.accType = accType;
        this.minAmount = minAmount;
    }

    public String getAccType() {
        return accType;
    }

    public double getMinAmount() {
        return minAmount;
    }
}

// Class to represent account type information
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

class Bank{
    
    private ArrayList<Account> accounts = new ArrayList<>();

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

    public void updateAccount(String accNumber) {

        for (Account account : accounts) {

            if (account.getAccNumber().equals(accNumber)) {

                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.println(account.UpdateName(newName));
                System.out.print("Enter new Account Type: ");
                String newType = scanner.nextLine();
                System.out.println(account.updateAccountType(newType));
                System.out.print("Enter new Account Creation Date: ");
                String newDate = scanner.nextLine();
                System.out.println(account.updateAccountCreation(newDate));
            }
        }
    }


    public void deleteAccount(String accNumber) {

        Iterator<Account> iterator = accounts.iterator();

        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getAccNumber().equals(accNumber)) {
                iterator.remove(); // Safely remove the element using iterator
                System.out.println("Account deleted successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void searchAccount(String accNumber) {

        for (Account account : accounts) {
            if (account.getAccNumber().equals(accNumber)) {
                account.display();
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void depositAmount(String accNumberToDeposit,double depositAmount) {

        for (Account account : accounts) {
            if (account.getAccNumber().equals(accNumberToDeposit)) {
                if(depositAmount > 0.0){
                    account.deposit(depositAmount);
                    return;
                }else{
                    System.out.println("Your amount is not correct.");
                    return;
                }
            }
        }
        System.out.println("Account not found.");
    }

    public void withdrawAmount(String accNumberToWithdraw,double withdrawAmount) {

        for (Account account : accounts) {
            if (account.getAccNumber().equals(accNumberToWithdraw)) {
                double minBalanceSavings = MinimumBalanceAccount.getMinimumBalance("Savings");
                //System.out.println(minBalanceSavings);
                account.withdraw(withdrawAmount, minBalanceSavings);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void displayAllAccounts(ArrayList<Account> oldaccounts) {
        for (Account account : oldaccounts) {
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
        for (Account account : accounts) {
            account.display();
        }
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

        Double initialBalance = hm.get(accType);
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

        double balance = initialBalance; 
        System.out.println("Minimum Balance : "+balance); 

        String creationDate = java.time.LocalDate.now().toString();
        System.out.println("Account Creation Date: " + creationDate);

        String accountNumber = generateAccountNumber();
        System.out.println("Generated Account Number: " + accountNumber);

        Account account = new Account(name, accountNumber, accType, balance, creationDate);
        accounts.add(account);
        System.out.println("Account created successfully.");
    }
}
class ApplicationMenu {

    private ArrayList<Account> accounts;
    Bank bank = new Bank();

    public ApplicationMenu() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    protected void Menu(){
        
        Scanner scanner = new Scanner(System.in);
        
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

            if( choice == 1 ) {
                bank.createAccount();
        
            } else if( choice == 2) {

                bank.displayAllAccounts(accounts);

            } else if( choice == 3) {

                System.out.print("Enter account number to update: ");
                scanner.nextLine(); // Consume newline
                String accNumberToUpdate = scanner.nextLine();
                bank.updateAccount(accNumberToUpdate);
                    
            } else if( choice == 4 ){

                System.out.print("Enter account number to delete: ");
                scanner.nextLine(); // Consume newline
                String accNumberToDelete = scanner.nextLine();
                bank.deleteAccount(accNumberToDelete);

            } else if( choice == 5 ){

                System.out.print("Enter account number to deposit into: ");
                scanner.nextLine(); // Consume newline
                String accNumberToDeposit = scanner.nextLine();
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                bank.depositAmount(accNumberToDeposit, depositAmount);
                
            } else if( choice == 6){

                System.out.print("Enter account number to withdraw from: ");
                scanner.nextLine(); // Consume newline
                String accNumberToWithdraw = scanner.nextLine();
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                bank.withdrawAmount(accNumberToWithdraw, withdrawAmount);

            } else if( choice == 7 ){

                System.out.print("Enter account number to search: ");
                scanner.nextLine(); // Consume newline
                String accNumberToSearch = scanner.nextLine();
                bank.searchAccount(accNumberToSearch);

            } else if( choice == 8) {

                System.out.println("Exiting...");
                System.exit(0);
                break;

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class BankApplication {
    public static void main(String[] args) {

        MinimumBalanceAccount.addMinimumBalanceInfo("Savings", 100.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Checking", 50.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Fixed Deposit", 100.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Current", 50.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Salary", 100.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Retirement", 50.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Bussiness", 100.0);
        MinimumBalanceAccount.addMinimumBalanceInfo("Foreign Currency", 50.0);

        ApplicationMenu application = new ApplicationMenu();
        Account account = new Account("Sarah", "1498273612784360", "Fixed Deposit", 10000, "2023-12-01");
        Account account02 = new Account("Jamila", "1498273612734482", "Bussiness", 20000, "2023-11-13");
        application.addAccount(account);
        application.addAccount(account02);
        application.Menu();
    }
}
