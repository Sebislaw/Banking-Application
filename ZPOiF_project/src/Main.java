import Banking.Account;
import Banking.BankingSystem;
import Humans.Customer;
import Simulations.SmallBankingSystem;

import javax.swing.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        SmallBankingSystem smallBankingSystem = new SmallBankingSystem();
        BankingSystem bankingSystem = smallBankingSystem.getBankingSystem();
        Customer customer = bankingSystem.customers.get(0);
        Account account = bankingSystem.accounts.get(0);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(application.main.Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new application.main.Main(bankingSystem, customer, account).setVisible(true);
            }
        });


    }
}