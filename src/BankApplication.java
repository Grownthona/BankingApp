import java.util.ArrayList;
import java.util.List;
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
        System.out.println("------- Creating Account -------");
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        System.out.println("Account Type\t\tMinimum Deposit Amount");
        System.out.println("--------------------------------------------");
        for (AccountType account : accountType) {
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
        // Take input until user has given correct Account Type
        while(!isValidAccountType) {
            System.out.println("Invalid account type. Please type again.");
            System.out.print("Enter account type: ");
            String newaccType = scanner.nextLine();
            for (AccountType account : accountType) {
                if (newaccType.equalsIgnoreCase(account.getAccountType())) {
                    isValidAccountType = true;
                    break;
                }
            }
        } 
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
