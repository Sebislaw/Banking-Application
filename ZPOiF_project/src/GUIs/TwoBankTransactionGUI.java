package GUIs;

import Banking.Account;
import Banking.Bank;
import Banking.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TwoBankTransactionGUI extends JFrame {
    private Bank bank1;
    private Bank bank2;

    public TwoBankTransactionGUI() {
        super("Two Bank Transaction GUI");

        JButton transferButton = new JButton("Transfer Funds");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction();
            }
        });

        JPanel panel = new JPanel();
        panel.add(transferButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(null);
    }


    private void performTransaction() {
        int sourceAccountId = 1;
        int destAccountId = 2;
        int amount = 1000;

        Transaction transaction = new Transaction(sourceAccountId, destAccountId, 1,2, amount);
        if (bank1.getAmountOfMoneyInAccounts() >= amount) {
            bank1.getTransactions().add(transaction);
            bank1.removeBalance(sourceAccountId, amount);

            bank2.getTransactions().add(transaction);
            bank2.addBalance(destAccountId, amount);

            System.out.println("Transaction performed: " + transaction);
        } else {
            System.out.println("Insufficient funds for the transaction.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TwoBankTransactionGUI().setVisible(true);
        });
    }
}
