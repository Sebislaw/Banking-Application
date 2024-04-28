package application.component;

import Banking.Account;
import Banking.CentralBank;
import Banking.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TransactionTab extends javax.swing.JPanel{

    private CentralBank centralBank;
    private List<Integer> accountIds;
    private JComboBox<Integer> originAccountDropdown;
    private JComboBox<Integer> destinationAccountDropdown;
    private JTextField amountTextField;
    private JButton sendButton;

    public TransactionTab(CentralBank centralBank) {
        this.centralBank = centralBank;
        this.accountIds = centralBank.getAllAccountNumbers();
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new GridLayout(4, 2));

        JLabel originLabel = new JLabel("Origin Account:");
        originAccountDropdown = new JComboBox<>(accountIds.toArray(new Integer[0]));

        JLabel destinationLabel = new JLabel("Destination Account:");
        destinationAccountDropdown = new JComboBox<>(accountIds.toArray(new Integer[0]));

        JLabel amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField();

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });


        this.add(originLabel);
        this.add(originAccountDropdown);
        this.add(destinationLabel);
        this.add(destinationAccountDropdown);
        this.add(amountLabel);
        this.add(amountTextField);
        this.add(sendButton);

    }

    private void send() {
        int originAccountId = (Integer) originAccountDropdown.getSelectedItem();
        int destinationAccountId = (Integer) destinationAccountDropdown.getSelectedItem();
        int amount = parseAmount(amountTextField.getText());
        if(amount <= centralBank.getBankByAccountId(originAccountId).getAccountById(originAccountId).getBalance()){
            Transaction transaction = new Transaction(originAccountId, destinationAccountId, 0, 0, amount);
            boolean wynik  = centralBank.processTransaction(transaction);
            System.out.println("Wynik transferu:" + Boolean.toString(wynik));
            System.out.println("Sending amount " + amount + " from account " + originAccountId +
                    " to account " + destinationAccountId);

        } else {
            System.out.println("Zla kwota");
        }

    }

    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount input");
            return 0;
        }
    }

}
