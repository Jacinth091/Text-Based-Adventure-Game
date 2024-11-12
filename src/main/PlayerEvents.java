package main;

import entity.Player;
import map.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class PlayerEvents implements ActionListener {
    private GameLogic gameLogic;
    private Player player;

    private String newLoc;


    public PlayerEvents(GameLogic gameLogic, Player player){
        this.gameLogic = gameLogic;
        this.player = player;

    }




    @Override
    public void actionPerformed(ActionEvent event){

        String playerChoice = event.getActionCommand();

        System.out.println(playerChoice);

        playerMoveInMap(player.getCurrentLocation(), playerChoice);



    }


    public void playerMoveInMap(String currentLocation, String direction){
        Map<String, Room> map = gameLogic.getGameMap().getMap();
        // Find the current room using the current location
        Room currentRoom = map.get(currentLocation);
        if (currentRoom != null) {
            // Get the next room's name based on the direction
            String nextRoomName = currentRoom.getRoomViaDirection(direction);

            if (nextRoomName != null) {
                // Retrieve the actual Room object for the next location
                Room nextRoom = map.get(nextRoomName);
                if (nextRoom != null) {
                    System.out.println("Player moves to: " + nextRoomName);
                    newLoc = nextRoomName;
                    gameLogic.setCurrentLocation(newLoc);
                } else {
                    System.out.println("The room in direction " + direction + " does not exist.");
                }
            } else {
                System.out.println("No room in that direction: " + direction);
            }
        } else {
            System.out.println("Current location not found in map.");
        }




    }


}
