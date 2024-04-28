package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScenarioDemoGUI extends JFrame {
    private JTextArea statusTextArea;

    public ScenarioDemoGUI() {
        super("Demonstrator");

        JLabel subtitleLabel = new JLabel("Please choose the scenario");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton transactionButton = new JButton("1. Demonstrate a Transaction");
        JButton loanButton = new JButton("2. Demonstrate Taking a Loan");
        JButton bankRunButton = new JButton("3. Demonstrate a Bank Run");
        JButton sopockiIncidentButton = new JButton("4. Simulate the Sopocki Incident");

        statusTextArea = new JTextArea();
        statusTextArea.setEditable(false);

        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransactionSimulationGUI();

                updateStatus("Demonstrating a Transaction");
            }
        });

        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate taking a loan and update the status
                updateStatus("Demonstrating Taking a Loan");
            }
        });

        bankRunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate a bank run and update the status
                updateStatus("Demonstrating a Bank Run");
            }
        });

        sopockiIncidentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate the Sopocki incident and update the status
                updateStatus("Simulating the Sopocki Incident");
            }
        });

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(subtitleLabel);
        panel.add(transactionButton);
        panel.add(loanButton);
        panel.add(bankRunButton);
        panel.add(sopockiIncidentButton);
        panel.add(statusTextArea);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        TransactionWaitWorker worker = new TransactionWaitWorker();
        worker.execute();
    }

    private void openTransactionSimulationGUI() {
        SwingUtilities.invokeLater(() -> {
            new TransactionSimulationGUI().setVisible(true);
        });
    }

    private void updateStatus(String status) {
        statusTextArea.append(status + "\n");
    }

    private class TransactionWaitWorker extends SwingWorker<Void, String> {
        @Override
        protected Void doInBackground() throws Exception {
            while (!isCancelled()) {
                Thread.sleep(2000);

                String transactionMessage = "New Transaction Received: " + System.currentTimeMillis();
                publish(transactionMessage);
            }
            return null;
        }

        @Override
        protected void process(java.util.List<String> chunks) {
            for (String message : chunks) {
                updateStatus(message);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScenarioDemoGUI().setVisible(true);
        });
    }
}
