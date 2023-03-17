package org.example;

import java.util.Date;

public class Player extends User{
    private double gameTimeAsHours;

    public Player() {
    }

    public Player(int id, String firstName, String lastName, String natId, Date birthDate, Date joinedDate, double gameTimeAsHours) {
        super(id, firstName, lastName, natId, birthDate, joinedDate);
        this.gameTimeAsHours = gameTimeAsHours;
    }

    public double getGameTimeAsHours() {
        return gameTimeAsHours;
    }

    public void setGameTimeAsHours(double gameTimeAsHours) {
        this.gameTimeAsHours = gameTimeAsHours;
    }
}
