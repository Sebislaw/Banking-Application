package application.component;

import Banking.CentralBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentralBankTab extends javax.swing.JPanel{

    private CentralBank centralBank;

    private JLabel reserveRatioLabel;
    private JTextField reserveRatioField;
    private JLabel moneyInAllAccountsLabel;
    private JLabel numberOfBanksLabel;

    private JTextArea transactionsTextArea;


    public CentralBankTab(CentralBank centralBank) {
        this.centralBank = centralBank;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new GridLayout(4, 2));

        JLabel reserveRatioTextLabel = new JLabel("Reserve Ratio:");
        reserveRatioField = new JTextField(Integer.toString(centralBank.reserveRatio));
        reserveRatioLabel = new JLabel("Reserve Ratio: " + centralBank.reserveRatio);

        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Save");

        moneyInAllAccountsLabel = new JLabel("Money in All Accounts: " + centralBank.getMoneyInAllAccounts());
        numberOfBanksLabel = new JLabel("Number of Banks: " + centralBank.getBanks().size());

        transactionsTextArea = new JTextArea();
        JScrollPane transactionsScrollPane = new JScrollPane(transactionsTextArea);

        this.add(reserveRatioTextLabel);
        this.add(reserveRatioLabel);
        this.add(reserveRatioField);
        this.add(editButton);
        this.add(saveButton);
        this.add(moneyInAllAccountsLabel);
        this.add(numberOfBanksLabel);
        this.add(transactionsScrollPane);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveRatioField.setEditable(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newReserveRatio = Integer.parseInt(reserveRatioField.getText());
                    centralBank.reserveRatio = Integer.parseInt(reserveRatioField.getText());
                    reserveRatioLabel.setText("Reserve Ratio: " + centralBank.reserveRatio);
                    reserveRatioField.setEditable(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for Reserve Ratio. Please enter a valid number.");
                }
            }
        });

    }

    public void updateGUI(CentralBank updatedCentralBank) {
        reserveRatioLabel.setText("Reserve Ratio: " + updatedCentralBank.reserveRatio);
        reserveRatioField.setText(Integer.toString(updatedCentralBank.reserveRatio));
        transactionsTextArea.setText(updatedCentralBank.transactionsList.toString());

        moneyInAllAccountsLabel.setText("Money in All Accounts: " + updatedCentralBank.getMoneyInAllAccounts());
        numberOfBanksLabel.setText("Number of Banks: " + updatedCentralBank.getBanks().size());
    }

}
