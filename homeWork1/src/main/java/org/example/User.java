package org.example;

import java.util.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String natId;
    private Date birthDate;
    private Date joinedDate;

    public User() {
    }

    public User(int id, String firstName, String lastName, String natId, Date birthDate, Date joinedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.natId = natId;
        this.birthDate = birthDate;
        this.joinedDate = joinedDate;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNatId() {
        return natId;
    }

    public void setNatId(String natId) {
        this.natId = natId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}
