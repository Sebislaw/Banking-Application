package GUIs;

import Banking.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountGUI {

    public static void displayAccountInformation(Account account) {
        JFrame frame = new JFrame("Account Information");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel, account);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(10, 120, 80, 25);
        panel.add(closeButton);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, Account account) {
        panel.setLayout(null);

        JLabel accountIdLabel = new JLabel("Account ID:");
        accountIdLabel.setBounds(10, 20, 80, 25);
        panel.add(accountIdLabel);

        JTextField accountIdText = new JTextField(String.valueOf(account.getAccountId()));
        accountIdText.setBounds(100, 20, 165, 25);
        accountIdText.setEditable(false);
        panel.add(accountIdText);

        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setBounds(10, 50, 80, 25);
        panel.add(balanceLabel);

        JTextField balanceText = new JTextField(String.valueOf(account.getBalance()));
        balanceText.setBounds(100, 50, 165, 25);
        balanceText.setEditable(false);
        panel.add(balanceText);
    }

    public static void main(String[] args) {
        Account account = new Account(1, 5000, "password");
        SwingUtilities.invokeLater(() -> displayAccountInformation(account));
    }
}
