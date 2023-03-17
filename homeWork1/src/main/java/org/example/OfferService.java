package org.example;

public class OfferService {
    public void createOffer(Offer offer){
        System.out.println(offer.getId() + " Created.");
    }

    public void updateOffer(Offer offer){
        System.out.println(offer.getId() + " Updated.");
    }
    public void deleteOffer(Offer offer){
        System.out.println(offer.getId() + " Deleted.");
    }
}
