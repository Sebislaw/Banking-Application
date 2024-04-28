package GUIs;

import Banking.Account;
import Banking.Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankDetailsGUI extends JFrame {
    private Bank bank;

    public BankDetailsGUI(Bank bank) {
        super("Bank Details");

        this.bank = bank;

        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.append("Bank Information:\n");
        detailsTextArea.append("Money on Hand: " + bank.moneyOnHand + "\n");
        detailsTextArea.append("Minimum Reserve Ratio: " + bank.minReserveRatio + "\n\n");
        detailsTextArea.append("Accounts:\n");

        for (Account account : bank.getAccounts()) {
            detailsTextArea.append("Account ID: " + account.getAccountId() + ", Balance: " + account.getBalance() + "\n");
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}