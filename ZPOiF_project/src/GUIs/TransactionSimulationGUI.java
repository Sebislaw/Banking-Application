package GUIs;

import Banking.Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TransactionSimulationGUI extends JFrame {
    private JSlider numberOfBanksSlider;
    private JSlider numberOfClientsSlider;
    private JSlider startingAmountInBanksSlider;
    private JSlider startingAmountInAccountsMinSlider;
    private JSlider startingAmountInAccountsMaxSlider;

    public TransactionSimulationGUI() {
        super("Transaction Simulation");

        numberOfBanksSlider = createSlider("Number of Banks", 2, 4, 1);
        numberOfClientsSlider = createSlider("Number of Clients per Bank", 1, 5, 1);
        startingAmountInBanksSlider = createSlider("Starting Amount in Banks", 10000, 20000, 3000);
        startingAmountInAccountsMinSlider = createSlider("Starting Amount in Accounts (Min)", 100, 1000, 100);
        startingAmountInAccountsMaxSlider = createSlider("Starting Amount in Accounts (Max)", 1000, 5000,  500);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(numberOfBanksSlider);
        panel.add(numberOfClientsSlider);
        panel.add(startingAmountInBanksSlider);
        panel.add(new JLabel(""));
        panel.add(startingAmountInAccountsMinSlider);
        panel.add(startingAmountInAccountsMaxSlider);
        panel.add(new JLabel(""));
        panel.add(startButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
    }

    private JSlider createSlider(String label, int min, int max, int spacing) {
        JSlider slider = new JSlider(min, max);
        slider.setMajorTickSpacing(spacing);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(label));
        return slider;
    }

    private void startSimulation() {
        int numBanks = numberOfBanksSlider.getValue();
        int numClientsPerBank = numberOfClientsSlider.getValue();
        int startingAmountInBanks = startingAmountInBanksSlider.getValue();
        int startingAmountInAccountsMin = startingAmountInAccountsMinSlider.getValue();
        int startingAmountInAccountsMax = startingAmountInAccountsMaxSlider.getValue();

        List<Bank> banks = new ArrayList<>();
    }

    private void openBankDetailsGUI(Bank bank) {
        SwingUtilities.invokeLater(() -> {
            new BankDetailsGUI(bank).setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TransactionSimulationGUI().setVisible(true);
        });
    }
}