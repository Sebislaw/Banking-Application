package GUIs;

import Banking.BankingSystem;
import Simulations.SmallBankingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionsGUI extends JFrame {

    private BankingSystem bankingSystem;

    public InteractionsGUI(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Interactions GUI");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

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

                SmallBankingSystem smallBankingSystem = new SmallBankingSystem();
                bankingSystem = smallBankingSystem.getBankingSystem();
            }
        });

        panel.add(transferButton);
        panel.add(withdrawCashButton);
        panel.add(withdrawAllCash);
        panel.add(giveLargeLoansButton);
        panel.add(giveLoanButton);
        panel.add(resetButton);

        add(panel);
        setVisible(true);
    }

    private void openTransactionGUI() {
        SwingUtilities.invokeLater(() -> {
            TransactionGUI transactionGUI = new TransactionGUI(bankingSystem.centralBank, bankingSystem.centralBank.getAllAccountNumbers());
        });
    }


}
