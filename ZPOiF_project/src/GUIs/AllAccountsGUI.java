package GUIs;

import Banking.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AllAccountsGUI {

    private List<Account> accounts;
    private JFrame frame;
    private JTable table;

    public AllAccountsGUI(List<Account> accounts) {
        this.accounts = accounts;
        initializeGUI();
    }

    public void initializeGUI() {
        frame = new JFrame("All Accounts GUI");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

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

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> closeWindow());
        mainPanel.add(closeButton, BorderLayout.SOUTH);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateGUI(accounts));
        mainPanel.add(updateButton, BorderLayout.NORTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void closeWindow() {
        frame.dispose();
    }

    public void updateGUI(List<Account> updatedAccounts) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Account account : updatedAccounts) {
            model.addRow(new Object[]{account.getAccountId(), account.getBalance(), account.getPassword()});
        }
    }

    public static void main(String[] args) {
        List<Account> accountsList = List.of(
                new Account(1, 1000, "password1"),
                new Account(2, 2000, "password2"),
                new Account(3, 3000, "password3")
        );

        AllAccountsGUI allAccountsGUI = new AllAccountsGUI(accountsList);
    }
}