package Banking;

import java.util.ArrayList;
import java.util.List;

public class CentralBank{

    private List<Bank> banks;
    public int reserveRatio = 10;
    public List<Transaction> transactionsList;
    public int moneySupply = 100000000;
    public BankingSystem bankingSystem;


    public List<Transaction> getTransactionsList(){
        return this.transactionsList;
    }

    public List<Transaction> getTransactionListForBank(Bank bank){
        return null;
    }

    public Bank getBankByAccountId(int accountId){

        for (Bank bank : this.banks){
            if (bank.accountInBank(accountId)){
                return bank;
            };
        }
        System.out.println("Returned null");
        return null;
    }

    public CentralBank(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        this.transactionsList = new ArrayList<Transaction>();
        this.reserveRatio = 10;
        this.banks = new ArrayList<Bank>();
    }

    public boolean addToTransactionList(Transaction transaction){
        this.transactionsList.add(transaction);
        return true;
    }

    public boolean processTransaction(Transaction transaction){
        Bank originBank = getBankByAccountId(transaction.getOriginAccountId());
        Bank destBank = getBankByAccountId(transaction.getDestinationAccountId());
        boolean t1 = false;
        boolean t2 = false;
        if (originBank != destBank){
            t1 = originBank.processTransaction(transaction);
            t2 = destBank.processTransaction(transaction);
        } else {
            t1 = originBank.processTransaction(transaction);
            t2 = true;
        }

        if(t1 && t2){
            this.transactionsList.add(transaction);
        }
        return t1 && t2;

    }

    public ArrayList<Integer> getAllAccountNumbers(){
        ArrayList<Integer> accounts = new ArrayList<>();
        for (Bank bank : this.banks){
            for(Account account : bank.getAccounts()){
                accounts.add(account.getAccountId());
            }
        }
        return accounts;
    }

    public int getMoneyInAllAccounts(){
        int sum = 0;
        for (Bank bank : this.banks){
            sum += bank.getAmountOfMoneyInAccounts();
        }
        return sum;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }


}
