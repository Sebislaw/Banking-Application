package application.component;

import Banking.Account;
import Banking.Bank;
import Banking.BankingSystem;
import Banking.CentralBank;
import GUIs.*;
import Simulations.SmallBankingSystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserGiveLoanTab extends javax.swing.JPanel{

    private BankingSystem bankingSystem;
    private Account userAccount;

    public UserGiveLoanTab(BankingSystem bankingSystem, Account userAccount) {
        this.bankingSystem = bankingSystem;
        this.userAccount = userAccount;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new GridLayout(2, 3));

        this.add(new JLabel("Your account number:"));
        this.add(new JLabel(String.valueOf(userAccount.getAccountId())));
        this.add(new JLabel());

        JTextField loanAmountField = new JTextField(10);
        this.add(new JLabel("Loan Amount:"));
        this.add(loanAmountField);

        JButton loanButton = new JButton("Loan");
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAccountNumber = userAccount.getAccountId();
                int loanAmount = Integer.parseInt(loanAmountField.getText());
                Bank chosenBank = bankingSystem.centralBank.getBankByAccountId(selectedAccountNumber);

                boolean loanSuccess = chosenBank.giveLoan(selectedAccountNumber, loanAmount);

                if (loanSuccess) {
                    JOptionPane.showMessageDialog(new JFrame(), "Loan granted successfully!");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Loan could not be granted. Please check the input.");
                }
            }
        });
        this.add(loanButton);

    }

}
