package application.component;

import Banking.Account;
import Banking.BankingSystem;
import Humans.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserAccountInformationTab extends javax.swing.JPanel{

    private Account account;
    private Customer user;

    public UserAccountInformationTab(Customer user, Account account, BankingSystem bankingSystem) {
        this.account = account;
        this.user = user;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new BorderLayout());

        this.add(new JLabel("Account information"), BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name: " + user.getFullName()));
        panel.add(new JLabel());
        panel.add(new JLabel("Account id: " + account.getAccountId()));
        panel.add(new JLabel("Balance: " + account.getBalance()));
        panel.add(new JLabel());
        panel.add(new JLabel());

        this.add(panel, BorderLayout.CENTER);



    }


}
