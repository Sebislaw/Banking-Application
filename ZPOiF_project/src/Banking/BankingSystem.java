package Banking;

import GUIs.AllAccountsGUI;
import GUIs.BankingSystemGUI;
import GUIs.ScenarioDemoGUI;
import Humans.Customer;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class BankingSystem {

    public CentralBank centralBank; // Assuming there is only one central bank
    public ArrayList<Bank> banksList;
    public List<Account> accounts;
    public List<Customer> customers;
    public BankingSystemGUI bankingSystemGUI;
    public AllAccountsGUI allAccountsGUI;
    public int maxAccountNumber = 300;
    private static final String[] NAMES = {"John", "Jane", "David", "Emma", "Michael", "Sophia", "Daniel", "Olivia"};
    private static final String[] MIDDLE_NAMES = {"Robert", "Elizabeth", "Christopher", "Grace", "Joseph", "Charlotte"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller"};
    private Timer timer; // Add a Timer field
    public boolean createNewAccounts;
    public boolean createRandomTransfers;
    public boolean createRandomWithdrawals = true;
    public boolean createRandomDeposits = true;
    private int numberOfLoans = 5;
    private int numberOfWithdrawals = 5;
    private int numberOfDeposits = 5;

    public BankingSystem(ArrayList<Bank> banksList, List<Account> accounts, List<Customer> customers) {
        this.centralBank = new CentralBank(this);
        this.banksList = banksList;
        this.accounts = accounts;
        this.customers = customers;
    }

    public BankingSystem(){
        this.centralBank = new CentralBank(this); // tworzymy bank centralny
    }

    public BankingSystem(int numberOfBanks, int numberOfCustomersPerBank, int minCash, int maxCash){
        this.centralBank = new CentralBank(this);
        this.banksList = generateBanks(numberOfBanks, minCash, maxCash);
        this.accounts = new ArrayList<>();
        this.customers = generateCustomers(numberOfCustomersPerBank * numberOfBanks);
        this.createNewAccounts = true;
        this.createRandomTransfers = true;
        generateAccounts();
        Account.setLastId(0);
    }




    public void startSystem() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bankingSystemStuff();
            }
        }, 0, 1000);
    }

    public void stopSystem() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Banking system stopped.");
        } else {
            System.out.println("Banking system is not running.");
        }
    }

    private void bankingSystemStuff() {

        if(this.createRandomTransfers){
            ArrayList<Transaction> newTransactions = this.createRandomTransfers(10); // create some transactions to simulate the real world
            processBulkTransactions(newTransactions);
        }


        Random rand = new Random();
        if(this.createNewAccounts && this.accounts.size() < this.maxAccountNumber && rand.nextBoolean()){
            ArrayList<Account> newAccounts = this.generateBankAccounts(rand.nextInt(4)+1, 0);
            for(Account account:newAccounts){
                banksList.get(rand.nextInt(banksList.size())).accounts.add(account);
                this.accounts.add(account);
            }
        }
        int loansGiven = 0;
        int i = 0;

        if(numberOfLoans > 0){
            while(loansGiven < numberOfLoans && i < 200){
                Bank bank = banksList.get(rand.nextInt(banksList.size()));
                if(bank.giveLoanToAccount()){
                    loansGiven += 1;
                }
                if(loansGiven > numberOfLoans){
                    break;
                }
                i++;
            }

        }




        if(this.getNumberOfDepositsAttempted() > 0){
            generateRandomDeposits();
        }

        if(this.getNumberOfWithdrawals() > 0){
            generateRandomWithdrawals();
        }
        updateBankGUIs();


        tryToPayLoans();


        System.out.println("Executing bankingSystemStuff every second.");
    }

    private void processBulkTransactions(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int sourceAccountId = transaction.getOriginAccountId();
            int destAccountId = transaction.getDestinationAccountId();

            Bank sourceBank = findBankByAccount(sourceAccountId);
            Bank destBank = findBankByAccount(destAccountId);

            if (sourceBank != null && destBank != null) {
                sourceBank.processTransaction(transaction);
                destBank.processTransaction(transaction);
            }
        }
    }

    private Bank findBankByAccount(int accountId) {
        for (Bank bank : banksList) {
            for (Account account : bank.getAccounts()) {
                if (account.getAccountId() == accountId) {
                    return bank;
                }
            }
        }
        return null; // Account not found in any bank
    }
    private ArrayList<Transaction> createRandomTransfers(int maxPercentage){ // generates random transactions - less than maxPerc. of the balance
        ArrayList<Transaction> newTransactions = new ArrayList<>();
        Random rand = new Random();
        for (Account account : accounts) {
            if(account.getBalance() > 0){
                if(rand.nextInt(20) < 10){ // <50% that it sends a transfer
                    Account destAccount = accounts.get(rand.nextInt(accounts.size()));
                    if(account != destAccount){
                        int percanteToSend = rand.nextInt(maxPercentage);
                        int amountToSend = account.getBalance() * percanteToSend / 100; // cast na int
                        newTransactions.add(new Transaction(account.getAccountId(), destAccount.getAccountId(), 0, 0, amountToSend));
                    }
                }
            }

        }
        return newTransactions;
    }

    public ArrayList<Account> generateBankAccounts(int number, int maxBalance){
        Random rand = new Random();
        ArrayList<Account> newAccounts = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Account newAccount = null;
            if(maxBalance == 0){
                newAccount = new Account(0, "password" + rand.nextInt(1000));
            } else {
                newAccount = new Account(rand.nextInt(maxBalance), "password" + rand.nextInt(1000));
            }

            newAccounts.add(newAccount);
        }
        return newAccounts;
    }



    public void initializeSystem(int numberOfBanks, int minMoneyInBank, int maxMoneyInBank) {
        this.banksList = generateBanks(numberOfBanks, minMoneyInBank,maxMoneyInBank);
        this.accounts = new ArrayList<>();
        this.customers = generateCustomers(10);
        generateAccounts();
        for (Bank bank : banksList) {
            System.out.println("Bank Money on Hand: " + bank.getMoneyOnHand());
            System.out.println("Bank Minimum Reserve Ratio: " + bank.getMinReserveRatio());
        }

    }



    private ArrayList<Bank> generateBanks(int numberOfBanks, int minMoenyInBank, int maxMoneyInBank) {
        ArrayList<Bank> banks = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numberOfBanks; i++) {
            // Customize these values as needed for each bank
            int moneyOnHand = minMoenyInBank + rand.nextInt(maxMoneyInBank-minMoenyInBank); // Example initial money on hand
            int minReserveRatio = 10; // Example minimum reserve ratio

            Bank bank = new Bank(moneyOnHand, minReserveRatio, this.centralBank);
            banks.add(bank);
        }
        this.centralBank.setBanks(banks);

        return banks;
    }

    private List<Customer> generateCustomers(int numberOfCustomers) {
        List<Customer> customers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfCustomers; i++) {
            String name = NAMES[random.nextInt(NAMES.length)];
            String middleName = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

            int yearsAgo = random.nextInt(65 - 18 + 1) + 18;
            LocalDate birthdateLocal = LocalDate.now().minusYears(yearsAgo);
            Date birthdate = Date.from(birthdateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Customer customer = new Customer(name, middleName, lastName, birthdate);
            customers.add(customer);
        }

        return customers;
    }

    private void generateAccounts() {
        Random random = new Random();
        int numberOfBanks = banksList.size();
        int currentBankIndex = 0;

        for (Customer customer : customers) {
            int numberOfAccounts = random.nextInt(3) + 1;

            for (int i = 0; i < numberOfAccounts; i++) {
                int accountId = generateUniqueAccountId();
                int balance = 1000 + random.nextInt(9000);
                String password = "password" + random.nextInt(1000);

                Account account = new Account(accountId, balance, password);

                Bank currentBank = banksList.get(currentBankIndex);
                currentBank.getAccounts().add(account);

                currentBankIndex = (currentBankIndex + 1) % numberOfBanks;

                customer.addAccount(account.getAccountId(), account.getPassword());
                this.accounts.add(account);
            }
        }
        if(allAccountsGUI != null){
            allAccountsGUI.updateGUI(this.accounts);
        }
    }

    public void generateRandomWithdrawals() {
        Random rand = new Random();
        int numberOfWithdrawals = this.numberOfWithdrawals;
        System.out.println("Number of withdrawals:" + this.numberOfWithdrawals);
        for (int i = 0; i < numberOfWithdrawals; i++) {
            Account randomAccount = getRandomAccount();
            if(randomAccount.getBalance() > 0){
                int withdrawalAmount = rand.nextInt(randomAccount.getBalance());
                boolean result = this.findBankByAccount(randomAccount.getAccountId()).withdrawAmount(randomAccount, withdrawalAmount);
                if(!result){
                    randomAccount.unableToWithdrawCash = true;
                }
                System.out.println("Generated withdrawal of " + withdrawalAmount + " from account " + randomAccount.getAccountId());
            }

        }
    }

    private void generateRandomDeposits() {
        Random rand = new Random();
        System.out.println("Number of deposits:" + this.numberOfDeposits);
        int numberOfDeposit = this.numberOfDeposits;

        for (int i = 0; i < numberOfDeposit; i++) {
            Account randomAccount = getRandomAccount();
            int depositAmount = rand.nextInt(5000) + 1;
            this.findBankByAccount(randomAccount.getAccountId()).depositCash(randomAccount, depositAmount);
            System.out.println("Generated deposit of " + depositAmount + " to account " + randomAccount.getAccountId());
        }
    }

    private Account getRandomAccount() {
        Random rand = new Random();
        return this.accounts.get(rand.nextInt(accounts.size()));
    }

    public void withdrawAllCashFromAllAccounts(){
        for (Account account:this.accounts) {
            int withdrawalAmount = account.getBalance();
            boolean result = this.findBankByAccount(account.getAccountId()).withdrawAmount(account, withdrawalAmount);
            if(!result){
                account.unableToWithdrawCash = true;
            }
            System.out.println("Generated withdrawal of " + withdrawalAmount + " from account " + account.getAccountId());
        }
    }

    private int generateUniqueAccountId() {
        Random random = new Random();
        int accountId;

        do {
            accountId = 1000 + random.nextInt(9000);
        } while (accountIdExists(accountId));

        return accountId;
    }

    private boolean accountIdExists(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return true;
            }
        }
        return false;
    }

    public void setNumberOfLoansAttempted(int value) {
        this.numberOfLoans = value;
    }

    public void setNumberOfWithdrawalsAttempted(int value) {
        this.numberOfWithdrawals = value;
    }

    public void setNumberOfDepositsAttempted(int value) {
        this.numberOfDeposits = value;
    }

    private void updateBankGUIs(){
        for (Bank bank: this.banksList) {
            if(bank.bankGUI != null){
                bank.bankGUI.updateValues();
            }
        }
    }

    public int getNumberOfDepositsAttempted() {
        return this.numberOfDeposits;
    }

    public int getNumberOfWithdrawals(){
        return this.numberOfWithdrawals;
    }

    public int getNumberOfLoans(){
        return this.numberOfLoans;
    }

    public void giveLargeLoans(){
        Random rand = new Random();
        for (Bank bank: this.banksList) {
            int amountToGive = bank.moneyOnHand/20;
            Account account = bank.getAccounts().get(rand.nextInt(bank.getAccounts().size()));
            Loan loan = new Loan(account, amountToGive, 0);
            bank.giveLoan(account, amountToGive);
        }
    }

    public void tryToPayLoans(){
        for (Account account: this.accounts) {
            if(account.loans.size() > 0){
                for (Loan loan:account.loans) {
                    if(loan.makePayment()){
                        account.removeBalance(loan.calculatePayment());
                    }
                }
            }
        }
    }

    public void checkIfCantWithdraw(){
        for (Account account: this.accounts) {
            if(account.getBalance() == 0){
                account.unableToWithdrawCash = false;
            }
        }
    }

    public CentralBank getCentralBank() {
        return centralBank;
    }

    public void setCentralBank(CentralBank centralBank) {
        this.centralBank = centralBank;
    }

    public ArrayList<Bank> getBanksList() {
        return banksList;
    }

    public void setBanksList(ArrayList<Bank> banksList) {
        this.banksList = banksList;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public BankingSystemGUI getBankingSystemGUI() {
        return bankingSystemGUI;
    }

    public void setBankingSystemGUI(BankingSystemGUI bankingSystemGUI) {
        this.bankingSystemGUI = bankingSystemGUI;
    }

    public AllAccountsGUI getAllAccountsGUI() {
        return allAccountsGUI;
    }

    public void setAllAccountsGUI(AllAccountsGUI allAccountsGUI) {
        this.allAccountsGUI = allAccountsGUI;
    }

    public int getMaxAccountNumber() {
        return maxAccountNumber;
    }

    public void setMaxAccountNumber(int maxAccountNumber) {
        this.maxAccountNumber = maxAccountNumber;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isCreateNewAccounts() {
        return createNewAccounts;
    }

    public void setCreateNewAccounts(boolean createNewAccounts) {
        this.createNewAccounts = createNewAccounts;
    }

    public boolean isCreateRandomTransfers() {
        return createRandomTransfers;
    }

    public void setCreateRandomTransfers(boolean createRandomTransfers) {
        this.createRandomTransfers = createRandomTransfers;
    }

    public boolean isCreateRandomWithdrawals() {
        return createRandomWithdrawals;
    }

    public void setCreateRandomWithdrawals(boolean createRandomWithdrawals) {
        this.createRandomWithdrawals = createRandomWithdrawals;
    }

    public boolean isCreateRandomDeposits() {
        return createRandomDeposits;
    }

    public void setCreateRandomDeposits(boolean createRandomDeposits) {
        this.createRandomDeposits = createRandomDeposits;
    }

    public void setNumberOfLoans(int numberOfLoans) {
        this.numberOfLoans = numberOfLoans;
    }

    public void setNumberOfWithdrawals(int numberOfWithdrawals) {
        this.numberOfWithdrawals = numberOfWithdrawals;
    }

    public int getNumberOfDeposits() {
        return numberOfDeposits;
    }

    public void setNumberOfDeposits(int numberOfDeposits) {
        this.numberOfDeposits = numberOfDeposits;
    }

    public void resetBankingSystem(BankingSystem newBankingSystem){
        centralBank.transactionsList = new ArrayList<>();
//        this.centralBank = newBankingSystem.centralBank;
//        this.banksList = newBankingSystem.banksList;
        for(Account account: this.accounts){
            account.setBalance(10000);
            account.unableToWithdrawCash = false;
        }
        for (Bank bank:this.banksList) {
            bank.setMoneyOnHand(bank.getAmountOfMoneyInAccounts());
        }

        updateBankGUIs();
//        this.customers = newBankingSystem.customers;
//        this.createNewAccounts = newBankingSystem.createNewAccounts;
//        this.createRandomTransfers = newBankingSystem.createRandomTransfers;
//        generateAccounts();
//        Account.setLastId(0);
    }
}
