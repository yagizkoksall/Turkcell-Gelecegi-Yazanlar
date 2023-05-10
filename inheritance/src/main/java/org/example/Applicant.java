package org.example;

public class Applicant extends User{
    private String nationalIdentity;

    public Applicant(int id, String firstName, String lastName, String nationalIdentity) {
        super(id,firstName,lastName);
        this.nationalIdentity = nationalIdentity;
    }

    public String getNationalIdentity() {
        return nationalIdentity;
    }

    public void setNationalIdentity(String nationalIdentity) {
        this.nationalIdentity = nationalIdentity;
    }
}
