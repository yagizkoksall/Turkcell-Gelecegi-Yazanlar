package org.example;

public class Manager extends Employee{

    private int projectBudget;

    public Manager(int id, String firstName, String lastName, int projectBudget) {
        super(id, firstName, lastName);
        this.projectBudget = projectBudget;
    }

    public int getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(int projectBudget) {
        this.projectBudget = projectBudget;
    }
}
