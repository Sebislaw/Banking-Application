package GUIs;

import Banking.CentralBank;
import Banking.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TransactionGUI extends JFrame {

    private CentralBank centralBank;
    private List<Integer> accountIds;
    private JComboBox<Integer> originAccountDropdown;
    private JComboBox<Integer> destinationAccountDropdown;
    private JTextField amountTextField;
    private JButton sendButton;
    private JButton closeButton;

    public TransactionGUI(CentralBank centralBank, ArrayList<Integer> accountIds) {
        this.centralBank = centralBank;
        this.accountIds = accountIds;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Transaction GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

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

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        panel.add(originLabel);
        panel.add(originAccountDropdown);
        panel.add(destinationLabel);
        panel.add(destinationAccountDropdown);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(sendButton);
        panel.add(closeButton);

        add(panel);
        setVisible(true);
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
            closeWindow();
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

    private void closeWindow() {
        dispose();
    }

}
