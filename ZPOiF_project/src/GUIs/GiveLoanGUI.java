package GUIs;

import Banking.Bank;
import Banking.BankingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GiveLoanGUI {

    private BankingSystem bankingSystem;

    public GiveLoanGUI(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;

        JFrame frame = new JFrame("Give Loan");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        List<Integer> accountNumbers = bankingSystem.centralBank.getAllAccountNumbers();
        Integer[] accountNumbersArray = accountNumbers.toArray(new Integer[0]);
        JComboBox<Integer> accountNumberDropdown = new JComboBox<>(accountNumbersArray);
        frame.add(new JLabel("Select Account Number:"));
        frame.add(accountNumberDropdown);

        JTextField loanAmountField = new JTextField(10);
        frame.add(new JLabel("Loan Amount:"));
        frame.add(loanAmountField);

        JButton loanButton = new JButton("Loan");
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAccountNumber = (Integer) accountNumberDropdown.getSelectedItem();
                int loanAmount = Integer.parseInt(loanAmountField.getText());
                Bank chosenBank = bankingSystem.centralBank.getBankByAccountId(selectedAccountNumber);

                boolean loanSuccess = chosenBank.giveLoan(selectedAccountNumber, loanAmount);

                if (loanSuccess) {
                    JOptionPane.showMessageDialog(frame, "Loan granted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Loan could not be granted. Please check the input.");
                }
            }
        });
        frame.add(loanButton);

        frame.setVisible(true);
    }

}
