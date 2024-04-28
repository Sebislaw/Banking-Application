package application.component;

import Humans.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AllCustomersTab extends javax.swing.JPanel{

    public AllCustomersTab(List<Customer> customers) {
        initComponents(customers);
    }

    private void initComponents(List<Customer> customers) {

        this.setLayout(new BorderLayout());

        this.add(new JLabel("List of all customers"), BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> customersList = new JList<>(listModel);

        for (Customer customer : customers) {
            int numAccounts = customer.getAccountIds().size();
            listModel.addElement(customer.getFullName() + " - Accounts: " + numAccounts);
        }

        JScrollPane scrollPane = new JScrollPane(customersList);

        this.add(scrollPane, BorderLayout.CENTER);

    }

}
