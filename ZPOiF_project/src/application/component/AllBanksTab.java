package application.component;

import Banking.Bank;
import GUIs.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AllBanksTab extends javax.swing.JPanel{

    private final ArrayList<Bank> banks;

    public AllBanksTab(ArrayList<Bank> banks) {
        this.banks = banks;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new BorderLayout());

        this.add(new JLabel("Click on a row to view bank's details"), BorderLayout.NORTH);

        String[] columnNames = {"Bank ID", "Bank name","Money On Hand", "Min Reserve Ratio", "Number of Accounts"};
        Object[][] data = new Object[banks.size()][5];

        for (int i = 0; i < banks.size(); i++) {
            Bank currentBank = banks.get(i);
            data[i][0] = currentBank.getBankId();
            data[i][1] = currentBank.bankName;
            data[i][2] = currentBank.getMoneyOnHand();
            data[i][3] = currentBank.getMinReserveRatio();
            data[i][4] = currentBank.getAccounts().size();
        }

        JTable table = new JTable(data, columnNames);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int bankIndex = row;

                if (bankIndex >= 0 && bankIndex < banks.size()) {
                    Bank selectedBank = banks.get(bankIndex);
                    if(selectedBank.bankGUI == null){
                        openBankGui(selectedBank);
                    }

                }
            }
        });

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void openBankGui(Bank bank) {
        BankGUI bankGui = new BankGUI(bank);
        bankGui.openGui();
    }

    public void updateGUI() {
        Component component = this.getComponent(0);

        if (component instanceof JScrollPane) {
            DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) this.getComponent(0)).getViewport().getView()).getModel();
            for (int i = 0; i < banks.size(); i++) {
                model.setValueAt(banks.get(i).getMoneyOnHand(), i, 1);
                model.setValueAt(banks.get(i).getMinReserveRatio(), i, 2);
                model.setValueAt(banks.get(i).getAccounts().size(), i, 3);
            }
        } else {
            System.out.println("Unexpected component type: " + component.getClass().getName());
        }
    }
}
