package Simulations;

import Banking.Bank;
import Banking.BankingSystem;
import Banking.CentralBank;

public class SmallBankingSystem {

    private BankingSystem resultBankingSystem;

    public SmallBankingSystem() {

    }

    public BankingSystem getBankingSystem() {
        Bank.latestBankId = 0;
        BankingSystem resultBankingSystem = new BankingSystem(6, 7, 10000, 20000);
        for (Bank bank:resultBankingSystem.banksList) {
            bank.moneyOnHand = bank.getAmountOfMoneyInAccounts();
        }
        resultBankingSystem.createRandomTransfers = false;
        resultBankingSystem.setNumberOfWithdrawals(0);
        resultBankingSystem.setNumberOfLoans(0);
        resultBankingSystem.setNumberOfDeposits(0);
        resultBankingSystem.setCreateNewAccounts(false);
        return resultBankingSystem;
    }

}
