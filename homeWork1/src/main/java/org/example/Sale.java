package org.example;

public class Sale {
    private int id;
    private User user;
    private boolean isPaid;
    private Game game;
    private  Offer offer;

    public Sale() {
    }

    public Sale(int id, User user, boolean isPaid, Game game, Offer offer) {
        this.id = id;
        this.user = user;
        this.isPaid = isPaid;
        this.game = game;
        this.offer = offer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
