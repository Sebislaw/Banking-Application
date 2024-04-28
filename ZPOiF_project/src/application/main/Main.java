package application.main;

import Banking.Account;
import Banking.BankingSystem;
import Humans.Customer;
import application.component.*;

import java.awt.Component;

/**
 * W Main(): ustawianie stron do wyświetlania wybranych w menu
 * W Menu(): ustawianie zawartości menu, kolor menu
 * W MenuItem(): kolor submenu, kolor linii submenu
 * W Header(): kolor header
 * W folderze component znajdują się strony aplikacji
 */

public class Main extends javax.swing.JFrame {

    private javax.swing.JPanel body;
    private application.menu.Menu menu1;

    public Main(BankingSystem bankingSystem, Customer user, Account userAccouont) {
        initComponents();
        menu1.setEvent((index, subIndex) -> {
            switch (index){
                case 0:
                    switch (subIndex) {
                        case 1 -> showForm(new UserAccountInformationTab(user, userAccouont, bankingSystem));
                        case 2 -> showForm(new UserTransactionTab(bankingSystem.centralBank, userAccouont));
                        case 3 -> showForm(new UserGiveLoanTab(bankingSystem, userAccouont));
                        default -> showForm(new DefaultForm("Strona : " + index + " " + subIndex));
                    }
                    break;
                case 1:
                    switch (subIndex) {
                        case 1 -> showForm(new CentralBankTab(bankingSystem.centralBank));
                        case 2 -> showForm(new AllBanksTab(bankingSystem.banksList));
                        case 3 -> showForm(new AllAccountsTab(bankingSystem.accounts));
                        case 4 -> showForm(new AllCustomersTab(bankingSystem.customers));
                        default -> showForm(new DefaultForm("Strona : " + index + " " + subIndex));
                    }
                    break;
                case 2:
                    switch (subIndex) {
                        case 1 -> showForm(new TransactionTab(bankingSystem.centralBank));
                        case 2 -> showForm(new GiveLoanTab(bankingSystem));
                        default -> showForm(new DefaultForm("Strona : " + index + " " + subIndex));
                    }
                    break;
                case 3:
                    switch (subIndex) {
                        case 1 -> showForm(new BankingSystemTab(bankingSystem));
                        case 2 -> showForm(new InteractionsTab(bankingSystem));
                        default -> showForm(new DefaultForm("Strona : " + index + " " + subIndex));
                    }
                    break;
                default:
                    showForm(new DefaultForm("Strona : " + index + " " + subIndex));
            }
        });
    }

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    private void initComponents() {
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        application.scroll.ScrollPaneWin11 scrollPaneWin111 = new application.scroll.ScrollPaneWin11();
        menu1 = new application.menu.Menu();
        application.component.Header header1 = new application.component.Header();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(163, 163, 163)));

        scrollPaneWin111.setBorder(null);
        scrollPaneWin111.setViewportView(menu1);

        body.setBackground(new java.awt.Color(245, 245, 245));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        showForm(new DefaultForm("Welcome. To get started select a tab from sidebar menu."));
        pack();
        setLocationRelativeTo(null);
    }

}