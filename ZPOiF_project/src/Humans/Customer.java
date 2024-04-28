package Humans;

import Interactions.AccessTerminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends Human {
    List<Integer> accountIds;
    List<String> accountPasswords;

    public Customer(String name, String middleName, String lastName, Date birthdate){
        super(name, middleName, lastName, birthdate);
        this.accountIds = new ArrayList<>();
        this.accountPasswords = new ArrayList<>();
    }

    public void addAccount(int accountId, String accountPassword){ // nie ma potrzeby weryfikacji popr. danych logowania
        accountIds.add(accountId);
        accountPasswords.add(accountPassword);
    }

    public List<Integer> getAccountIds(){
        return this.accountIds;
    }

    public String getFullName(){
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }
}