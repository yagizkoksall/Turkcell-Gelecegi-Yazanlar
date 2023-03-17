package org.example;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Player player1= new Player(18,"Melike","Ağırcan","12345678912",new Date(),new Date(),
                2324);
        Game game1= new Game(45,"Ördek Vurmaca","Eğlence",10,"Ördekleri vakvak");
        Offer offer1= new Offer(94,4);
        Sale sale1= new Sale(17,player1,true,game1,offer1);

        PlayerService playerService= new PlayerService(new VerifyServiceImpl());
        playerService.createPlayer(player1);
        playerService.updatePlayer(player1);
        playerService.deletePlayer(player1);

        OfferService offerService= new OfferService();
        offerService.createOffer(offer1);
        offerService.updateOffer(offer1);
        offerService.deleteOffer(offer1);

        SaleService saleService= new SaleService();
        saleService.createSale(sale1);
        saleService.updateSale(sale1);
        saleService.deleteSale(sale1);


    }
}