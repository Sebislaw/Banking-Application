package Banking;

public class Loan {
    private Account account;
    public int loanAmount;
    private int loanPercentage;
    private int loanPaymentsRemaining = 240;
    private int outstandingAmount;

    public Loan(Account account, int loanAmount, int loanPercentage) {
        this.account = account;
        this.loanAmount = loanAmount;
        this.loanPercentage = loanPercentage;
        this.outstandingAmount = loanAmount;
    }

    public int calculatePayment() {
        if (loanPaymentsRemaining > 1){
            int monthlyPayment = (int) (loanAmount / loanPaymentsRemaining);
            return monthlyPayment;
        } else {
            return this.outstandingAmount;
        }
    }

    public boolean makePayment(){
        int paymentAmount = this.calculatePayment();
        if(this.account.getBalance() > paymentAmount){
            this.account.removeBalance(paymentAmount);
            this.loanPaymentsRemaining -= 1;
            this.outstandingAmount -= paymentAmount;
            return true; // sufficient funds
        } else {
            return false; // insufficient funds
        }
    }
}
