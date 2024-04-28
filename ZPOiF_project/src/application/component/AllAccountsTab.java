package application.component;

import Banking.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AllAccountsTab extends javax.swing.JPanel{

    private List<Account> accounts;
    private JTable table;

    public AllAccountsTab(List<Account> accounts) {
        this.accounts = accounts;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new BorderLayout());

        this.add(new JLabel("List of all accounts"), BorderLayout.NORTH);

        String[] columnNames = {"Account ID", "Balance", "Password"};
        Object[][] data = new Object[accounts.size()][3];

        for (int i = 0; i < accounts.size(); i++) {
            data[i][0] = accounts.get(i).getAccountId();
            data[i][1] = accounts.get(i).getBalance();
            data[i][2] = accounts.get(i).getPassword();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateGUI(accounts));
        this.add(updateButton, BorderLayout.SOUTH);


    }

    public void updateGUI(List<Account> updatedAccounts) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Account account : updatedAccounts) {
            model.addRow(new Object[]{account.getAccountId(), account.getBalance(), account.getPassword()});
        }
    }

}
