package Banking;

public class Transaction {
    private int originAccountId;
    private int destinationAccountId;
    private int originBankId;
    private int destBankId;
    private int amount;

    public Transaction(int originAccountId, int destinationAccountId, int originBankId, int destBankId, int amount) {
        this.originAccountId = originAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.originBankId = originBankId;
        this.destBankId = destBankId;
    }

    public int getOriginAccountId() {
        return originAccountId;
    }

    public int getDestinationAccountId() {
        return destinationAccountId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "originAccountId=" + originAccountId +
                ", destinationAccountId=" + destinationAccountId +
                ", amount=" + amount +
                '}';
    }
}
