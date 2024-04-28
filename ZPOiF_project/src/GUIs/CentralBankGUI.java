package GUIs;

import Banking.CentralBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentralBankGUI extends JFrame {

    private static JFrame frame;

    private CentralBank centralBank;

    private JLabel reserveRatioLabel;
    private JTextField reserveRatioField;
    private JLabel moneyInAllAccountsLabel;
    private JLabel numberOfBanksLabel;

    private JTextArea transactionsTextArea;

    private JButton closeButton;

    private boolean initialized = false;

    public CentralBankGUI(CentralBank centralBank) {
        this.centralBank = centralBank;
    }

    private void initializeGUI() {
        setTitle("Central Bank GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel reserveRatioTextLabel = new JLabel("Reserve Ratio:");
        reserveRatioField = new JTextField(Integer.toString(centralBank.reserveRatio));
        reserveRatioLabel = new JLabel("Reserve Ratio: " + centralBank.reserveRatio);

        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Save");

        moneyInAllAccountsLabel = new JLabel("Money in All Accounts: " + centralBank.getMoneyInAllAccounts());
        numberOfBanksLabel = new JLabel("Number of Banks: " + centralBank.getBanks().size());

        transactionsTextArea = new JTextArea();
        JScrollPane transactionsScrollPane = new JScrollPane(transactionsTextArea);

        panel.add(reserveRatioTextLabel);
        panel.add(reserveRatioLabel);
        panel.add(reserveRatioField);
        panel.add(editButton);
        panel.add(saveButton);
        panel.add(moneyInAllAccountsLabel);
        panel.add(numberOfBanksLabel);
        panel.add(transactionsScrollPane);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveRatioField.setEditable(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newReserveRatio = Integer.parseInt(reserveRatioField.getText());
                    centralBank.reserveRatio = newReserveRatio;
                    reserveRatioLabel.setText("Reserve Ratio: " + centralBank.reserveRatio);
                    reserveRatioField.setEditable(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for Reserve Ratio. Please enter a valid number.");
                }
            }
        });

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });

        panel.add(closeButton);

        add(panel);
        setVisible(true);

        initialized = true;
    }

    public void showGUI() {
        if (!initialized) {
            initializeGUI();
        } else {
            updateGUI(centralBank);
        }
    }

    public void updateGUI(CentralBank updatedCentralBank) {
        reserveRatioLabel.setText("Reserve Ratio: " + updatedCentralBank.reserveRatio);
        reserveRatioField.setText(Integer.toString(updatedCentralBank.reserveRatio));
        transactionsTextArea.setText(updatedCentralBank.transactionsList.toString());

        moneyInAllAccountsLabel.setText("Money in All Accounts: " + updatedCentralBank.getMoneyInAllAccounts());
        numberOfBanksLabel.setText("Number of Banks: " + updatedCentralBank.getBanks().size());
    }

    private void closeWindow() {
        dispose();
        this.initialized = false;
    }

}
