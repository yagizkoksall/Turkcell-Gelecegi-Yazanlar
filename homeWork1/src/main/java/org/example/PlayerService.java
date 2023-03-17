package org.example;

public class PlayerService {
    private final VerifyService verifyService;

    public PlayerService(VerifyService verifyService) {
        this.verifyService = verifyService;
    }

    public void createPlayer(Player player){
        if (verifyService.verify(player.getNatId(),player.getFirstName(),player.getLastName(), player.getBirthDate())){
            System.out.println(player.getFullName() + " Created.");

        }
        else {
            System.out.println(player.getFullName() +  " Not Verified");
        }


    }
    public void updatePlayer(Player player){
        System.out.println(player.getFullName()+ " Updated.");
    }

    public void deletePlayer(Player player){
        System.out.println(player.getFullName()+ "Deleted.");
    }

}
