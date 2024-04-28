package application.component;

import Banking.BankingSystem;
import GUIs.*;
import Simulations.SmallBankingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionsTab extends javax.swing.JPanel{

    private BankingSystem bankingSystem;

    public InteractionsTab(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new GridLayout(4, 1));

        JButton transferButton = new JButton("Transfer");
        JButton withdrawCashButton = new JButton("Withdraw Cash");
        JButton withdrawAllCash = new JButton("Withdraw all cash from all accounts");
        JButton giveLargeLoansButton = new JButton("Give large loans");
        JButton giveLoanButton = new JButton("Give loan to account");
        JButton resetButton = new JButton("Reset");

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransactionGUI();
            }
        });

        withdrawCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankingSystem.generateRandomWithdrawals();
            }
        });

        withdrawAllCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankingSystem.withdrawAllCashFromAllAccounts();
            }
        });

        giveLargeLoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankingSystem.giveLargeLoans();
            }
        });

        giveLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiveLoanGUI(bankingSystem);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bankingSystem.stopSystem();
                bankingSystem.resetBankingSystem(new SmallBankingSystem().getBankingSystem());
//
//                bankingSystem = smallBankingSystem.getBankingSystem();
            }
        });

        this.add(transferButton);
        this.add(withdrawCashButton);
        this.add(withdrawAllCash);
        this.add(giveLargeLoansButton);
        this.add(giveLoanButton);
        this.add(resetButton);


    }

    private void openTransactionGUI() {
        SwingUtilities.invokeLater(() -> {
            TransactionGUI transactionGUI = new TransactionGUI(bankingSystem.centralBank, bankingSystem.centralBank.getAllAccountNumbers());
        });
    }

}
