package GUIs;

import Banking.Account;
import Banking.Bank;
import Banking.CentralBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CashWithdrawalGUI extends JFrame {

    private CentralBank centralBank;
    private List<Integer> accountIds;
    private JComboBox<Integer> accountDropdown;
    private JTextField amountTextField;
    private JButton withdrawButton;
    private JButton closeButton;


    public CashWithdrawalGUI(CentralBank centralBank, ArrayList<Integer> accountIds) {
        this.centralBank = centralBank;
        this.accountIds = accountIds;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Cash Withdrawal GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel accountLabel = new JLabel("Account:");
        accountDropdown = new JComboBox<>(accountIds.toArray(new Integer[0]));

        JLabel amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField();

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        panel.add(accountLabel);
        panel.add(accountDropdown);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(withdrawButton);
        panel.add(closeButton);

        add(panel);
        setVisible(true);
    }

    private void withdraw() {
        int accountId = (Integer) accountDropdown.getSelectedItem();
        int amount = parseAmount(amountTextField.getText());
        if (amount > 0) {
            Bank accountsBank = centralBank.getBankByAccountId(accountId);
            Account account = accountsBank.getAccountById(accountId);

            if (account != null && account.getBalance() >= amount) {

                boolean result = accountsBank.withdrawAmount(account, amount);
                if(result){
                    account.unableToWithdrawCash = true;
                }
            } else {
                System.out.println("Invalid account or insufficient funds for withdrawal.");
            }
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input.replaceAll(",", ""));
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount input");
            return 0;
        }
    }

    private void closeWindow() {
        dispose();
    }

}
