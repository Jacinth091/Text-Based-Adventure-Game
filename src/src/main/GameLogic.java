package src.main;

import entity.Player;
import events.GameState;
import events.GameUpdate;
import map.GameMap;
import ui.Utility;
import src.updates.EnemySpawnerThread;
import src.updates.GameThread;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class GameLogic implements ActionListener{
    // Threads and Timers
    private GameThread mGThread;
    private EnemySpawnerThread enemySpawner;

    private final Utility util = new Utility();

    // Entities
    private final Player player = new Player("Cedric", "Researcher");
    // Game Map
    private GameMap gameMap;


    // ------------------------ Player ------------------------
    private PlayerEvents playerEvents;
    private boolean isEncounter;
    private int noOfKeys =0;

    // Labels or actions for each set

    private String[] directions ;
    private String[] playerActions = {
            "Search for Item",
            "Search for Enemy",
            "Attack",
            "Restore Status",
            "Use Item",
            "Escape",

    };


    public GameLogic(GameThread mGThread){
        this.mGThread = mGThread;
        this.playerEvents = new PlayerEvents(this, player);
        this.gameMap = new GameMap();
        this.directions = gameMap.getDirection();
        this.enemySpawner = new EnemySpawnerThread(this);
        player.setCurrentLocation(gameMap.getStartingPoint());
        player.setWeapon("None");



        enemySpawner.start();





    }





    // Gameplay Logic
    @Override
    public void actionPerformed(ActionEvent ae){
        playerEvents.actionPerformed(ae);
    }










    // Getters and Setters


    public GameThread getmGThread() {
        return mGThread;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public String[] getDirections() {
        return directions;
    }

    public Player getPlayer() {
        return player;
    }

    public String[] getPlayerActions() {
        return playerActions;
    }



    public String getCurrentLocation(){
        return player.getCurrentLocation();
    }

    public void setCurrentLocation(String newLocation){
        player.setCurrentLocation(newLocation);
    }

}