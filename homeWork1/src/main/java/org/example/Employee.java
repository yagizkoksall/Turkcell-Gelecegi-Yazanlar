package org.example;

import java.util.Date;

public class Employee extends User {
    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String natId, Date birthDate, Date joinedDate) {
        super(id, firstName, lastName, natId, birthDate, joinedDate);
    }

}
