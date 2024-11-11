package main;

import entity.Player;
import events.GameState;
import events.GameUpdate;
import map.GameMap;
import ui.Utility;
import userInput.KeyHandler;
import userInput.MouseHandler;

import javax.swing.Timer;
import java.util.ArrayList;



public class GameLogic{


    // Threads and Timers
    private  EnemySpawnerThread enemySpawner;
    private Timer timer;
    private long timeElapsedInSeconds =0;
    private long timerMinutes = 0;
    private long timerSeconds = 0;


    private final Utility util = new Utility();

    // GameStates & GameUpdates
    private GameState currentState;
    private final ArrayList<GameUpdate> updates = new ArrayList<>();

    // Entities
    private final Player player = new Player("Cedric", "Researcher");


    private  MouseHandler mouseIn;
    private  KeyHandler keyH;

    // Game Map
    private GameMap gameMap;

    public GameLogic(){
        this.gameMap = new GameMap();
        this.enemySpawner = new EnemySpawnerThread(this);
        currentState = GameState.state_PlayState;
        createTimer();
        enemySpawner.start();

        mouseIn = new MouseHandler(this);
        keyH = new KeyHandler(this);



    }


    // Update Methods
    public void updateGameState(){

        if(currentState == GameState.state_PlayState){
            getMousePos();
            if(!timer.isRunning()){
                startTimer();

            }
            notifyGameUpdates();

        }
        else if(currentState == GameState.state_PauseState){
            pauseTimer();
            try{
                System.out.println("GAme is Paused!");
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
        else if(currentState == GameState.state_GameOverState){
            System.out.println("Game Over!!!");
        }
    }
    public void update(){
        updateGameState();
    }
    public void addEventUpdate(GameUpdate eventUpdate){
        updates.add(eventUpdate);
    }
    public void notifyGameUpdates(){
        for(GameUpdate eventUpdates : updates){
            eventUpdates.update();
        }
    }


    // TIMER Methods
    public void createTimer(){

        timer = new Timer(1000, (ae) -> {
            timeElapsedInSeconds++; // Decrease time every second
            timerSeconds = timeElapsedInSeconds % 60; // Seconds part of the timer
            timerMinutes = timeElapsedInSeconds / 60; // Minutes part of the timer
            System.out.println(String.format("Time Remaining: %02d:%02d", timerMinutes, timerSeconds)); // Print remaining time

            if(timeElapsedInSeconds >= (30 * 60)){
                // When time is up, stop the timer and handle the game over
                System.out.println("Time's up! Game Over!");
                currentState = GameState.state_GameOverState;
                timer.stop(); // Stop the countdown timer
                // Trigger game over actions, you can also add additional code here
            }

        });
        timer.setDelay(1);
    }
    public void startTimer() throws NullPointerException{
        try{
            if(timer == null){
                throw new NullPointerException("Timer is Null!, Instantiate it first!");
            }
            else {
                timer.start();
            }

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void pauseTimer() throws NullPointerException{
        try{
            if(timer == null){
                throw new NullPointerException("Timer is Null!, Instantiate it first!");
            }
            else {
                timer.stop();
            }

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Debug Methods
    public void getMousePos(){
        if(keyH.tKeyPressed){
            String status = mouseIn.isDragging() ? "Dragging" :
                    mouseIn.isClicking() ? "Clicking" :
                            mouseIn.isMoving() ? "Moving" : "Idle";

            String format = String.format("Mouse x: %d Mouse Y: %d Status: %s", mouseIn.getMouseX(), mouseIn.getMouseY(), status);

            System.out.println(format);
        }

    }








    // Getters and Setters
    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
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

    public long getTimerMinutes() {
        return timerMinutes;
    }

    public long getTimeElapsedInSeconds() {
        return timeElapsedInSeconds;
    }

    public long getTimerSeconds() {
        return timerSeconds;
    }

    public Timer getTimer() {
        return timer;
    }
}
