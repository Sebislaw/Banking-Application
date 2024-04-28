package GUIs;

import Banking.Account;
import Banking.Bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BankGUI {
    private static BankGUI instance;
    private Bank bank;

    private JTextField moneyOnHandField;
    private JTextField minReserveRatioField;
    private JTextField reserveRatioField;
    private JTextField accountsField;
    private JTextField transactionsField;
    private JTextField bankNameField;
    private JTextField amountInAllAccountsField;
    private JTable table;
    private DefaultTableModel model;
    public JFrame frame;

    public BankGUI(Bank bank) {
        this.bank = bank;
    }

    public void openGui() {
        this.bank.setBankGUI(this);
        frame = new JFrame("Bank GUI");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bank.bankGUI = null;
            }
        });
        frame.setSize(600, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel bankInfoPanel = new JPanel();
        bankInfoPanel.setLayout(new GridLayout(0, 2));

        JLabel moneyOnHandLabel = new JLabel("Money On Hand:");
        JLabel minReserveRatioLabel = new JLabel("Min Reserve Ratio:");
        JLabel reserveRatioLabel = new JLabel("Reserve Ratio:");
        JLabel accountsLabel = new JLabel("Number of Accounts:");
        JLabel transactionsLabel = new JLabel("Number of Transactions:");
        JLabel bankNameLabel = new JLabel("Bank name:");
        JLabel amountInAllAccountsLabel = new JLabel("Amount In All Accounts:");

        moneyOnHandField = new JTextField();
        minReserveRatioField = new JTextField();
        reserveRatioField = new JTextField();
        accountsField = new JTextField();
        transactionsField = new JTextField();
        bankNameField = new JTextField(bank.bankName);
        amountInAllAccountsField = new JTextField();

        moneyOnHandField.setEditable(false);
        minReserveRatioField.setEditable(false);
        reserveRatioField.setEditable(false);
        accountsField.setEditable(false);
        transactionsField.setEditable(false);
        bankNameField.setEditable(false);
        amountInAllAccountsField.setEditable(false);

        bankInfoPanel.add(moneyOnHandLabel);
        bankInfoPanel.add(moneyOnHandField);
        bankInfoPanel.add(minReserveRatioLabel);
        bankInfoPanel.add(minReserveRatioField);
        bankInfoPanel.add(reserveRatioLabel);
        bankInfoPanel.add(reserveRatioField);
        bankInfoPanel.add(accountsLabel);
        bankInfoPanel.add(accountsField);
        bankInfoPanel.add(transactionsLabel);
        bankInfoPanel.add(transactionsField);
        bankInfoPanel.add(bankNameLabel);
        bankInfoPanel.add(bankNameField);
        bankInfoPanel.add(amountInAllAccountsLabel);
        bankInfoPanel.add(amountInAllAccountsField);

        mainPanel.add(bankInfoPanel, BorderLayout.NORTH);

        String[] columnNames = {"Account ID", "Balance", "Password"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);

                boolean unableToWithdrawCash = false;
                if (row < bank.getAccounts().size()) {
                    Account account = bank.getAccounts().get(row);
                    unableToWithdrawCash = account.unableToWithdrawCash;
                }

                if (unableToWithdrawCash) {
                    component.setBackground(Color.RED);
                } else {
                    component.setBackground(Color.GREEN);
                }

                return component;
            }
        };

        Object[][] data = new Object[bank.getAccounts().size()][3];
        for (int i = 0; i < bank.getAccounts().size(); i++) {
            data[i][0] = bank.getAccounts().get(i).getAccountId();
            data[i][1] = bank.getAccounts().get(i).getBalance();
        }

        for (Object[] row : data) {
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int accountId = (int) table.getValueAt(row, 0);

                Account selectedAccount = bank.getAccountById(accountId);
                if (selectedAccount != null) {
                }
            }
        });

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

        updateGuiFields(moneyOnHandField, minReserveRatioField, reserveRatioField, accountsField, transactionsField, amountInAllAccountsField); // Updated method call
    }

    public static BankGUI getInstance() {
        return instance;
    }

    public void updateValues() {
        updateGuiFields(
                moneyOnHandField,
                minReserveRatioField,
                reserveRatioField,
                accountsField,
                transactionsField,
                amountInAllAccountsField
        );
        updateTable((ArrayList<Account>) this.bank.accounts);
    }

    private void updateGuiFields(JTextField moneyOnHandField, JTextField minReserveRatioField,
                                 JTextField reserveRatioField, JTextField accountsField, JTextField transactionsField,
                                 JTextField amountInAllAccountsField) {
        System.out.println("Updating fields");
        DecimalFormat df = new DecimalFormat("#.##");

        moneyOnHandField.setText(df.format(bank.getMoneyOnHand()));
        minReserveRatioField.setText(Integer.toString(bank.getMinReserveRatio()));
        DecimalFormat df2 = new DecimalFormat("0.0000%");
        reserveRatioField.setText(df2.format(bank.howMuchIsCovered()));
        accountsField.setText(Integer.toString(bank.getAccounts().size()));
        transactionsField.setText(Integer.toString(bank.getTransactions().size()));
        amountInAllAccountsField.setText(df.format(bank.getAmountOfMoneyInAccounts()));
        updateTable((ArrayList<Account>) this.bank.accounts);
    }

    public void updateTable(ArrayList<Account> accounts) {
        model.setRowCount(0);

        for (Account account : accounts) {
            model.addRow(new Object[]{account.getAccountId(), account.getBalance(), "*****"});
        }

        model.fireTableDataChanged();
    }



}
