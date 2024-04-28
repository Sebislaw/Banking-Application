package GUIs;

import Humans.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllCustomersGUI extends JFrame {

    private static JFrame frame;

    private AllCustomersGUI() {
    }

    public static void displayCustomersList(List<Customer> customers) {
        if (frame == null) {
            frame = new JFrame("Customers List");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> customersList = new JList<>(listModel);

            for (Customer customer : customers) {
                int numAccounts = customer.getAccountIds().size();
                listModel.addElement(customer.getFullName() + " - Accounts: " + numAccounts);
            }

            JScrollPane scrollPane = new JScrollPane(customersList);

            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeWindow();
                }
            });

            frame.getContentPane().add(closeButton, BorderLayout.SOUTH);

            frame.setVisible(true);
        }
    }


    public static void closeWindow() {
        if (frame != null) {
            frame.dispose();
            frame = null;
        }
    }

    public static void main(String[] args) {
        Customer customer1 = new Customer("John", "Doe", "Smith", new Date());
        customer1.addAccount(1, "password1");
        customer1.addAccount(2, "password2");

        Customer customer2 = new Customer("Jane", "Doe", "Smith", new Date());
        customer2.addAccount(3, "password3");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        SwingUtilities.invokeLater(() -> displayCustomersList(customers));
    }


}
