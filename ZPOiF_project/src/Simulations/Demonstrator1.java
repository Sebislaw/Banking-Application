package Simulations;

import Banking.Bank;
import Banking.BankingSystem;
import GUIs.BankingSystemGUI;
import GUIs.InteractionsGUI;
import GUIs.TransactionGUI;

import javax.swing.*;

public class Demonstrator1 {

    public static void main(String[] args) {
        SmallBankingSystem smallBankingSystem = new SmallBankingSystem();
        BankingSystem bankingSystem = smallBankingSystem.getBankingSystem();

        for (Bank bank:bankingSystem.banksList) {
            bank.moneyOnHand = bank.getAmountOfMoneyInAccounts();
        }
        SwingUtilities.invokeLater(() -> {
            BankingSystemGUI bsGUI = new BankingSystemGUI(bankingSystem);
            bankingSystem.bankingSystemGUI = bsGUI;
            openInteractionsGUI(bankingSystem);
        });
        bankingSystem.startSystem();
    }

    private static void openInteractionsGUI(BankingSystem bankingSystem){
        SwingUtilities.invokeLater(() -> {
            new InteractionsGUI(bankingSystem);
        });
    }

    private static void openTransactionGUI(BankingSystem bankingSystem) {
        SwingUtilities.invokeLater(() -> {
            TransactionGUI transactionGUI = new TransactionGUI(bankingSystem.centralBank, bankingSystem.centralBank.getAllAccountNumbers());
        });
    }
}
