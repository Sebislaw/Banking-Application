package Interactions;

import Banking.Bank;
import Banking.Transaction;

import java.util.List;

class ATM implements AccessTerminal {
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