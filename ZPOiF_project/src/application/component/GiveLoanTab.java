package application.component;

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

public class GiveLoanTab extends javax.swing.JPanel{

    private BankingSystem bankingSystem;

    public GiveLoanTab(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new FlowLayout());

        List<Integer> accountNumbers = bankingSystem.centralBank.getAllAccountNumbers();
        Integer[] accountNumbersArray = accountNumbers.toArray(new Integer[0]);
        JComboBox<Integer> accountNumberDropdown = new JComboBox<>(accountNumbersArray);
        this.add(new JLabel("Select Account Number:"));
        this.add(accountNumberDropdown);

        JTextField loanAmountField = new JTextField(10);
        this.add(new JLabel("Loan Amount:"));
        this.add(loanAmountField);

        JButton loanButton = new JButton("Loan");
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAccountNumber = (Integer) accountNumberDropdown.getSelectedItem();
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
