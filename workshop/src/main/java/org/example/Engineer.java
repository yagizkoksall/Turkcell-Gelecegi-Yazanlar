package org.example;

public class Engineer extends Employee{

   private String jobTitle;

    public Engineer(int id, String firstName, String lastName, String jobTitle) {
        super(id, firstName, lastName);
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
