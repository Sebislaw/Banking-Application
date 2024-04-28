package Banking;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
    private int accountId;
    private int balance;
    private String password; // W prawdziwym swiecie to jest znacznie bardziej skomplikowane, ale nie to jest celem proj.
    private static int lastId = 0;
    public ArrayList<Loan> loans;
    public boolean unableToWithdrawCash = false;


    public Account(int accountId, int balance, String password) {
        this.accountId = lastId+1;
        this.balance = balance;
        this.password = password;
        this.loans = new ArrayList<>();
        lastId += 1;
    }

    public Account(int balance, String password) {
        this.accountId = lastId+1;
        this.balance = balance;
        this.password = password;
        this.loans = new ArrayList<>();
        lastId += 1;
    }

    public boolean checkLogin(String password){
        if (Objects.equals(this.password, password)){ //Null safe, aby nie wylecial
            return true;
        }
        return false;
    }

    public int getBalance() {
        return this.balance;
    }


    public void addBalance(int amount){
        this.balance += amount;
    }

    public void removeBalance(int amount){
        this.balance -= amount;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }



    public static void main(String[] args) {
        Account account = new Account(1, 5000, "password");
    }

    public String getPassword(){
        return this.password;
    }

    public static void setLastId(int lastId) {
        Account.lastId = lastId;
    }
}