package main;

import entity.Player;
import events.GameState;
import events.GameUpdate;
import map.GameMap;
import ui.Utility;
import userInput.KeyHandler;
import userInput.MouseHandler;

import java.util.ArrayList;


public class GameLogic{
    private final Utility util = new Utility();
    private GameState currentState;
    private final ArrayList<GameUpdate> updates = new ArrayList<>();

    // Entities
    private final Player player = new Player("Cedric", "Researcher");
    private final EnemySpawnerThread enemySpawner = new EnemySpawnerThread(this);



    private  MouseHandler mouseIn;
    private  KeyHandler keyH;

    // Game Map
    private GameMap gameMap;

    public GameLogic(){
        this.gameMap = new GameMap();
        currentState = GameState.game_PlayState;

        enemySpawner.start();

        mouseIn = new MouseHandler(this);
        keyH = new KeyHandler(this);



    }

    public void update(){
        updateGameState();
    }




    public void updateGameState(){
        notifyGameUpdates();

        if(currentState == GameState.game_PlayState){
            getMousePos();

        }
        else if(currentState == GameState.game_PauseState){
            System.out.println("Pause");

        }
    }


    public void getMousePos(){
        if(keyH.tKeyPressed){
            String status = mouseIn.isDragging() ? "Dragging" :
                    mouseIn.isClicking() ? "Clicking" :
                            mouseIn.isMoving() ? "Moving" : "Idle";

            String format = String.format("Mouse x: %d Mouse Y: %d Status: %s", mouseIn.getMouseX(), mouseIn.getMouseY(), status);

            System.out.println(format);
        }

    }

    public void addEventUpdate(GameUpdate eventUpdate){
        updates.add(eventUpdate);
    }

    public void notifyGameUpdates(){
        for(GameUpdate eventUpdates : updates){
            eventUpdates.update();
        }
    }


    public KeyHandler getKeyH() {
        return keyH;
    }

    public MouseHandler getMouseIn() {
        return mouseIn;
    }

    public Player getPlayer() {
        return player;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }


}
