package org.example;

public class Instructor extends User{
    private String companyName;

    public Instructor(int id, String firstName, String lastName, String companyName) {
        super(id, firstName, lastName);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
