package main;

import entity.Enemy;
import events.GameUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class EnemySpawnerThread implements Runnable, GameUpdate {

    private final GameLogic gameLogic;

    private final int MAXIMUM_NO_SPAWN = 12;
    private long lastSpawnTime;
    private long currentSpawnTime;
    private long spawnInterval;
    private int spawnSecondInterval = 50000; // 10 seconds
    private int currentSpawned = 0;

    private Random enemyRandom;
    private float randomDiffMult;
    private Thread enemySpawner;
    private float diffMultiplier = 0.9f;


    private List<Enemy> enemyList;
    private Timer enemySpawnTimer;

    public EnemySpawnerThread(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        // gameLogic.addEventUpdate(this); 
        this.enemySpawnTimer = gameLogic.getTimer();
        enemyRandom = new Random();
        this.enemyList = new ArrayList<>();

    }

    public void start() {
        if (enemySpawner == null) {
            enemySpawner = new Thread(this);
            enemySpawner.start();
        }
    }


    @Override
    public void run() {
//        update();
        try {
            // enemySpawnTimer();
            spawnEnemyByTime();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void spawnEnemyByTime() throws InterruptedException{
         
        while(enemySpawner != null){
            try{
               randomDiffMult = setDiffMultiplier(gameLogic.getTimeElapsedInSeconds());
                  System.out.println("Diff Mult: "+randomDiffMult);
                // Calculate spawn interval using the difficulty multiplier
                
                
                
                spawnInterval = (long) ((long) spawnSecondInterval * randomDiffMult);
                
                
               if(currentSpawnTime - lastSpawnTime >= spawnInterval && currentSpawned < MAXIMUM_NO_SPAWN){
               

                    System.out.println("Spawn Interval (ms): " + spawnInterval);
                    currentSpawned++;
                    spawnEnemy();
                    lastSpawnTime = currentSpawnTime;
//                     randomDiffMult = (float) ((float)(enemyRandom.nextInt(10 - 1) + 1) / 10.0);

                }
                
                
                // Get the current time
                currentSpawnTime = System.currentTimeMillis();
                
                
                Thread.sleep(1);

            }catch(InterruptedException e){
                e.printStackTrace();
            }

            if(currentSpawned == MAXIMUM_NO_SPAWN){
                currentSpawned = 3;
            }

        }
        System.out.println("Number Of Enemies Spawned: " + currentSpawned);




    }

    public void enemySpawnTimer() throws InterruptedException{

        randomDiffMult = (float) ((float)(enemyRandom.nextInt(10 - 1) + 1) / 10.0);
        System.out.println("Number Of Enemies Spawned: " + currentSpawned);

        while(enemySpawner != null){
                try{
                    // Calculate spawn interval using the difficulty multiplier
                    spawnInterval = (long) ((long) spawnSecondInterval * randomDiffMult);
                    // Get the current time
                    currentSpawnTime = System.currentTimeMillis();
    
    
                    if(currentSpawnTime - lastSpawnTime >= spawnInterval && currentSpawned < MAXIMUM_NO_SPAWN){
    
                        System.out.println("Spawn Interval (ms): " + spawnInterval);
                        currentSpawned++;
                        spawnEnemy();
                        lastSpawnTime = currentSpawnTime;
                        randomDiffMult = (float) ((float)(enemyRandom.nextInt(10 - 1) + 1) / 10.0);
    
                    }
    
    
                    Thread.sleep(100);
    
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
    
                if(currentSpawned == MAXIMUM_NO_SPAWN){
                    currentSpawned = 3;
                
    
            }
             System.out.println("Number Of Enemies Spawned: " + currentSpawned);
        }
    }
    public void spawnEnemy(){
        System.out.printf("Spawn Enemy #%d, Difficulty Multiplier: %.2f\n",currentSpawned, randomDiffMult);

    }
    
    

    @Override
    public void update() {
//        try{
////        System.out.println(String.format("From EnemySpawner Thread - > Time Remaining: %02d:%02d", enemySpawnTimer. gameLogic.getTimerSeconds())); // Print remaining time
//            System.out.println(String.format("Time Remaining: %02d:%02d", gameLogic.getTimerMinutes(), gameLogic.getTimerSeconds())); // Print remaining time
////            System.out.println(String.format("Time Remaining: %02d:%02d", timerMinutes, timerSeconds)); // Print remaining time
//
//            Thread.sleep(1000);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
    }



    public float setDiffMultiplier(long elapsedTime){
      // 5 * 60 
      // 10 * 60
      // 15 * 60
      // 20 * 60
      // 25 * 60
      // 30 * 60  
         float diffMult = 0f;
         int seconds = (int) elapsedTime;
          if(seconds <= (5*60) && seconds >= 0){
               diffMult = (float) ((float)(enemyRandom.nextInt(10 - 8) + 8) / 10.0);
          }
          if(seconds <= (10*60) && seconds >= (5*60)){
               diffMult = (float) ((float)(enemyRandom.nextInt(8 - 6) + 6) / 10.0);
          }
          if(seconds <= (20*60) && seconds >= (10*60)){
               diffMult = (float) ((float)(enemyRandom.nextInt(6 - 4) + 4) / 10.0);
          }
          if(seconds <= (30*60) && seconds >= (20*60)){
               diffMult = (float) ((float)(enemyRandom.nextInt(4 - 1) + 1) / 10.0);
          }

        return diffMult;
    }

    public void determineMaxSpawnsByTime(long timerInMinutes){
      


    }
}
