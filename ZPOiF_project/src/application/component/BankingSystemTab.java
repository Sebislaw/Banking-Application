package application.component;

import Banking.BankingSystem;
import Banking.CentralBank;
import GUIs.*;
import Simulations.SmallBankingSystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingSystemTab extends javax.swing.JPanel{

    private BankingSystem bankingSystem;

    private CentralBankGUI centralBankGUI;
    private AllBanksGUI allBanksGUI;
    public AllAccountsGUI allAccountsGUI;

    private JButton startButton;
    private JButton stopButton;
    private JCheckBox createAccountsCheckbox;
    private JCheckBox createRandomTransfersCheckbox;
    private JSlider numberOfLoansSlider;
    private JSlider numberOfWithdrawalsSlider;
    private JSlider numberOfDepositsSlider;

    public BankingSystemTab(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        initComponents();
    }

    private void initComponents() {

        this.setLayout(new GridLayout(6, 2));


        JButton centralBankButton = new JButton("Central Bank");
        JButton banksListButton = new JButton("List of Banks");
        JButton accountsButton = new JButton("Accounts");
        JButton customersButton = new JButton("Customers");

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        createAccountsCheckbox = new JCheckBox("Create New Accounts");
        createRandomTransfersCheckbox = new JCheckBox("Create Random Transfers");

        numberOfLoansSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        numberOfLoansSlider.setMajorTickSpacing(1);
        numberOfLoansSlider.setPaintTicks(true);
        numberOfLoansSlider.setPaintLabels(true);
        numberOfLoansSlider.setBorder(BorderFactory.createTitledBorder("Number of Loans Attempted"));
        numberOfLoansSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderChanged(numberOfLoansSlider);
            }
        });
        numberOfLoansSlider.setValue(this.bankingSystem.getNumberOfLoans());


        numberOfWithdrawalsSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        numberOfWithdrawalsSlider.setMajorTickSpacing(2);
        numberOfWithdrawalsSlider.setPaintTicks(true);
        numberOfWithdrawalsSlider.setPaintLabels(true);
        numberOfWithdrawalsSlider.setBorder(BorderFactory.createTitledBorder("Number of Withdrawals Attempted"));
        numberOfWithdrawalsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderChanged(numberOfWithdrawalsSlider);
            }
        });
        numberOfWithdrawalsSlider.setValue(this.bankingSystem.getNumberOfWithdrawals());

        numberOfDepositsSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        numberOfDepositsSlider.setMajorTickSpacing(2);
        numberOfDepositsSlider.setPaintTicks(true);
        numberOfDepositsSlider.setPaintLabels(true);
        numberOfDepositsSlider.setBorder(BorderFactory.createTitledBorder("Number of Deposits Attempted"));
        numberOfDepositsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderChanged(numberOfDepositsSlider);
            }
        });
        numberOfDepositsSlider.setValue(this.bankingSystem.getNumberOfDepositsAttempted());

        this.add(numberOfLoansSlider);
        this.add(numberOfWithdrawalsSlider);
        this.add(numberOfDepositsSlider);



        centralBankGUI = new CentralBankGUI(this.bankingSystem.centralBank);

        centralBankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralBankGUI.showGUI();
            }
        });

        banksListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allBanksGUI = new AllBanksGUI(bankingSystem.banksList);
            }
        });

        accountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allAccountsGUI = new AllAccountsGUI(bankingSystem.accounts);
            }
        });

        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllCustomersGUI.displayCustomersList(bankingSystem.customers);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankingSystem.startSystem();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankingSystem.stopSystem();
            }
        });

        createAccountsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonOrCheckboxChanged();
            }
        });

        createRandomTransfersCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonOrCheckboxChanged();
            }
        });

        this.add(centralBankButton);
        this.add(banksListButton);
        this.add(accountsButton);
        this.add(customersButton);
        this.add(startButton);
        this.add(stopButton);
        this.add(createAccountsCheckbox);
        this.add(createRandomTransfersCheckbox);

        onButtonOrCheckboxChanged();
    }

    private void onButtonOrCheckboxChanged() {
        bankingSystem.createNewAccounts = createAccountsCheckbox.isSelected();
        bankingSystem.createRandomTransfers = createRandomTransfersCheckbox.isSelected();

        System.out.println("Button or Checkbox changed!");
    }


    private void sliderChanged(JSlider slider) {
        if (slider == numberOfLoansSlider) {
            this.bankingSystem.setNumberOfLoansAttempted(slider.getValue());
        } else if (slider == numberOfWithdrawalsSlider) {
            this.bankingSystem.setNumberOfWithdrawalsAttempted(slider.getValue());
        } else if (slider == numberOfDepositsSlider) {
            this.bankingSystem.setNumberOfDepositsAttempted(slider.getValue());
        }

        System.out.println(slider.getName() + " changed to: " + slider.getValue());
    }

}
