package Interactions;

import Banking.Bank;
import Banking.Transaction;

import java.util.List;

public interface AccessTerminal {
    boolean login(int accountId, String password);

    Bank getBank(int accountId);
    double getBalance(int accountId, String password);
    void addBalance(int accountId);
    void removeBalance(int accountId, double amount);
    void sendBalance(int sourceAccountId, int destAccountId, double amount);
    List<Transaction> getTransactions(int accountId);
}
