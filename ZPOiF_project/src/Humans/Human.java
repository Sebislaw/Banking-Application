package Humans;

import java.util.Date;

public class Human {
    String firstName;
    String middleName;
    String lastName;
    Date birthDate;

    public Human(String firstName, String middleName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
