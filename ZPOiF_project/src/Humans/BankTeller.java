package Humans;

import Banking.Bank;
import Banking.Transaction;
import Interactions.AccessTerminal;

import java.util.Date;
import java.util.List;

public class BankTeller extends Human implements AccessTerminal  {

    Bank bank;


    public BankTeller(String firstName, String middleName, String lastName, Date birthDate) {
        super(firstName, middleName, lastName, birthDate);
    }

    void openAccount(Customer customer) {
    }

    void getLargeLoan() {
    }

    @Override
    public boolean login(int accountId, String password) {
        return false;
    }

    @Override
    public Bank getBank(int accountId) {
        return null;
    }

    @Override
    public double getBalance(int accountId, String password) {
        return 0;
    }

    @Override
    public void addBalance(int accountId) {

    }

    @Override
    public void removeBalance(int accountId, double amount) {

    }

    @Override
    public void sendBalance(int sourceAccountId, int destAccountId, double amount) {

    }

    @Override
    public List<Transaction> getTransactions(int accountId) {
        return null;
    }
}
