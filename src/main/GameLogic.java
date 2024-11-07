package main;

import entity.Player;
import events.GameState;
import events.GameUpdate;
import ui.Utility;
import userInput.KeyHandler;
import userInput.MouseHandler;

import java.util.ArrayList;


public class GameLogic{
    private final Utility util = new Utility();
    private GameState currentState;
    private final ArrayList<GameUpdate> updates = new ArrayList<>();

    private final Player player = new Player("Cedric", "Researcher");
    private  MouseHandler mouseIn;
    private  KeyHandler keyH;



    public GameLogic(){
        currentState = GameState.game_PlayState;
        mouseIn = new MouseHandler(this);
        keyH = new KeyHandler(this);



    }

    public void update(){
        updateGameState();
    }




    public void updateGameState(){
        notifyGameUpdates();

        if(currentState == GameState.game_PlayState){


        }
        else if(currentState == GameState.game_PauseState){
            System.out.println("Pause");

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
