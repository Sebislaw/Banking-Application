package Banking;

import GUIs.BankGUI;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Bank{
    public static int latestBankId = 0; // Static variable to track the latest assigned bankId
    private static final String[] predefinedNames = {"BankTysiaclecie", "MiastoBank", "Nbank", "PrzystanBank", "SamochodLudziBank", "ŻywicaZłoto"};
    public int moneyOnHand; //physical money that the bank has
    public int minReserveRatio; // sum all the money in the bank accounts / moneyOnHand, it has to be more than minReserveRatio
    public List<Account> accounts; // all the account
    List<Transaction> transactions; // all the transactions
    private CentralBank centralBank;
    private int bankId; // Unique bankId for each bank
    public BankGUI bankGUI = null;
    public String bankName;

    public Bank(int moneyOnHand, int minReserveRatio, CentralBank centralBank) {
        this.bankId = latestBankId;
        this.moneyOnHand = moneyOnHand;
        this.minReserveRatio = minReserveRatio;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.centralBank = centralBank;
        this.bankName = generateBankName();
        latestBankId++;
    }

    private String generateBankName(){
        if(latestBankId-1<predefinedNames.length){
            return predefinedNames[latestBankId];
        } else {
            return "NewBank"+latestBankId;
        }
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        this.transactions = centralBank.getTransactionsList();
        return transactions;
    }

    public boolean accountInBank(int accountNumber) {
        for (Account account : this.accounts) {
            if (account.getAccountId() == accountNumber) {
                return true; // Account found in the bank
            }
        }
        return false; // Account not found in the bank
    }

    public boolean giveLoanToAccount(){
        List<Account> eligibleAccounts = accounts.stream()
                .filter(account -> account.getBalance() > 0).toList();



        if (!eligibleAccounts.isEmpty()) {
            Random rand = new Random();

            Account selectedAccount = eligibleAccounts.get(rand.nextInt(eligibleAccounts.size()));
            int loanAmount = rand.nextInt(2* selectedAccount.getBalance());
            if (giveLoan(selectedAccount, loanAmount)) {
                System.out.println("Loan successfully given to account " + selectedAccount.getAccountId());
                return true;
            } else {
                System.out.println("Failed to give a loan to account " + selectedAccount.getAccountId());
            }
        } else {
            System.out.println("No eligible accounts with positive balance found.");
        }
        return false;
    }

    public boolean giveLoan(Account account, int amount){
        if(this.checkIfCanLend(amount)){
            Loan newLoan = new Loan(account, amount, 0); //0% oprocentowanie, aby popr. symulowac
            account.addBalance(amount); // jak widać dodano pieniądze, które nie istnieją !!!
            account.loans.add(newLoan);
            return true; //loan given
        }
        return false; //loan not given
    }

    private boolean checkIfCanLend(int amountToLend){
        System.out.println("Amount to lend:" + amountToLend);
        Random rand = new Random();
        return rand.nextBoolean();
    }


    public int getAmountOfMoneyInAccounts() {
        int sum =accounts.stream().mapToInt(Account::getBalance).sum();
        return sum;
    }

    public boolean processTransaction(Transaction transaction){ //TODO obsługa jak konto nie w banku
        if(this.accountInBank(transaction.getOriginAccountId())){
            Account account = getAccountById(transaction.getOriginAccountId());
            account.removeBalance(transaction.getAmount());
            this.moneyOnHand -= transaction.getAmount();
            this.transactions.add(transaction);
            System.out.println("Wysłano");

        }
        if (this.accountInBank(transaction.getDestinationAccountId())) {
            Account account = getAccountById(transaction.getDestinationAccountId());
            account.addBalance(transaction.getAmount());
            this.transactions.add(transaction);
            this.moneyOnHand += transaction.getAmount();
            System.out.println("Przyjeto");

        }
        this.updateGUI();
        System.out.println("Update GUI");
        return true;


    }


    public void removeBalance(int sourceAccountId, int amount) {
        Account sourceAccount = getAccountById(sourceAccountId);

        if (sourceAccount != null && sourceAccount.getBalance() >= amount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            System.out.println("Balance removed from account " + sourceAccountId + ": " + amount);
        } else {
            System.out.println("Insufficient funds or invalid account for removing balance.");
        }
    }



    public Account getAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    public void addBalance(int destAccountId, int amount) {
        Account destAccount = getAccountById(destAccountId);

        if (destAccount != null) {
            destAccount.setBalance(destAccount.getBalance() + amount);
            System.out.println("Balance added to account " + destAccountId + ": " + amount);
        } else {
            System.out.println("Invalid destination account for adding balance.");
        }
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void updateGUI(){
        if(this.bankGUI != null){
            this.bankGUI.updateValues();
        }
        try {
            if(this.centralBank.bankingSystem.bankingSystemGUI != null){
                if(this.centralBank.bankingSystem.bankingSystemGUI.allAccountsGUI != null){
                    this.centralBank.bankingSystem.bankingSystemGUI.allAccountsGUI.updateGUI(this.centralBank.bankingSystem.accounts);
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getMinReserveRatio() {
        this.minReserveRatio = this.centralBank.reserveRatio;
        return this.minReserveRatio;
    }

    public int getMoneyOnHand() {
        return this.moneyOnHand;
    }

    public void setMoneyOnHand(int amount) {
        this.moneyOnHand = amount;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankGUI(BankGUI bankGUI) {
        this.bankGUI = bankGUI;
    }

    public boolean withdrawAmount(Account account, int amount){
        if(this.moneyOnHand >= amount){
            account.removeBalance(amount);
            this.moneyOnHand -= amount;
            updateGUI();
            return true;
        } else {
            updateGUI();
            return false;
        }

    }

    public void depositCash(Account account, int amount){
        account.addBalance(amount);
        this.moneyOnHand += amount;
        updateGUI();
    }

    public Float howMuchIsCovered(){
        if(this.getAmountOfMoneyInAccounts() == 0){
            return new Float(0);
        }
        return (float) this.moneyOnHand/this.getAmountOfMoneyInAccounts();
    }

    public boolean giveLoan(int selectedAccountNumber, int loanAmount) {
        Account account = getAccountById(selectedAccountNumber);
        account.addBalance(loanAmount);
        account.loans.add(new Loan(account, loanAmount, 0));
        return true;
    }



}
