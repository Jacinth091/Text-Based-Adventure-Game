package src.main;

import events.GameState;

import javax.swing.Timer;
import java.awt.event.ActionListener;

public class GameTimer{



    private Timer gameTimer;
    private int totalGameTime = 15 * 60;
    private long timeElapsedInSeconds = 0;
    private long timerMinutes = 0;
    private long timerSeconds = 0;



    public GameTimer(){

    }



    // TIMER Methods
    public void createTimer(){
        gameTimer = new Timer(1000, (ae) -> {setTime();});
        gameTimer.setDelay(1000);
    }

    public void setTime(){
        timeElapsedInSeconds++; // Increase time every second
        timerSeconds = timeElapsedInSeconds % 60; // Seconds part of the timer
        timerMinutes = timeElapsedInSeconds / 60; // Minutes part of the timer
            System.out.println(String.format("Time Remaining: %02d:%02d", timerMinutes, timerSeconds)); // Print remaining time
//        System.out.println(timeElapsedInSeconds);
    }


    public void startTimer() throws NullPointerException{
        try{
            if(gameTimer == null){
                throw new NullPointerException("Timer is Null!, Instantiate it first!");
            }
            else {
                gameTimer.start();
            }

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void pauseTimer() throws NullPointerException{
        try{
            if(gameTimer == null){
                throw new NullPointerException("Timer is Null!, Instantiate it first!");
            }
            else {
                gameTimer.stop();
            }

        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
        return gameTimer;
    }

    public long getTotalGameTime() {
        return totalGameTime;
    }


}
