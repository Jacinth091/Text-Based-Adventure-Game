package updates;

import events.GameState;
import events.GameUpdate;
import main.GameTimer;
//import main.PlayerStats;

import javax.swing.*;
import java.util.ArrayList;
public class GameThread implements Runnable {

    private Thread mainThread;
    private final int FPS = 10;
    private GameTimer playTime;
    private GameState currentState;
    private final ArrayList<GameUpdate> updates = new ArrayList<>();

    private boolean isPaused = false;  // Flag to manage pause state

    public GameThread() {
        currentState = GameState.state_PlayState;
        this.playTime = new GameTimer();
        playTime.createTimer();
    }

    public void start() {
        if (mainThread == null) {
            mainThread = new Thread(this);
            mainThread.start();
        }
    }

    public void gameLoop() {
        long nanoTime = 1000000000;
        double drawInterval = nanoTime / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (mainThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                try{
                    update();
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                delta--;
            }
        }
    }

    @Override
    public void run() {
        gameLoop();
    }

    // Update Methods
    public void updateGameState() {
        switch (currentState) {
            case state_PlayState:
                gameStatePlay();
                break;
            case state_PauseState:
                gameStatePause();
                break;
            case state_EncounterState:
                gameStateEncounter();
                break;
            case state_GameOverState:
                gameStateGameOver();
                break;
        }
    }

    public final void gameStatePlay() {
        if (!playTime.getTimer().isRunning()) {
            playTime.startTimer();
        }
    }

    public final void gameStatePause() {
        playTime.pauseTimer();
        System.out.println("Game is Paused!");
    }

    public final void gameStateEncounter() {
        // Handle encounter state logic
    }

    public final void gameStateGameOver() {
        // Handle game over state logic
    }

    public synchronized void update() {
        notifyGameUpdates();
        updateGameState();
//        System.out.println(currentState);


    }

    public void addEventUpdate(GameUpdate eventUpdate) {
        updates.add(eventUpdate);
    }

    private void notifyGameUpdates() {
        synchronized (updates) {
            for (GameUpdate eventUpdate : updates) {
                eventUpdate.update();
            }
        }
    }

    public synchronized void setGameState(GameState newState) {
        currentState = newState;
        if (currentState == GameState.state_PauseState) {
            isPaused = true;  // Set pause flag
            gameStatePause(); // Call pause-specific logic
        } else {
            isPaused = false; // Resume game by clearing pause flag
        }
    }

    public synchronized void resumeGame() {
        if (currentState == GameState.state_PauseState) {
            setGameState(GameState.state_PlayState);
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public GameTimer getPlayTime() {
        return playTime;
    }
}
