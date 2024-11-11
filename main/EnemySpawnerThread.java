package main;

import entity.Enemy;
import events.GameUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class EnemySpawnerThread implements Runnable{

    private final GameLogic gameLogic;

    private final int MAXIMUM_NO_SPAWN = 12;
    private long lastSpawnTime;
    private long currentSpawnTime;
    private long spawnInterval;
    private int spawnSecondInterval = 60000; // 30 seconds
    private int currentSpawned = 0;

    private Random enemyRandom;
    private float currentDiffMult;
    private Thread enemySpawner;
    private float diffMultiplier = 0.9f;


    private List<Enemy> enemyList;
    private Timer enemySpawnTimer;

    public EnemySpawnerThread(GameLogic gameLogic){
        this.gameLogic = gameLogic;
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
        try {
            spawnEnemyByTime();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void spawnEnemyByTime() throws InterruptedException{

      currentDiffMult = (float) ((float)(enemyRandom.nextInt(10 - 1) + 1) / 10.0);
      
       while (enemySpawner != null) {
        currentSpawnTime = System.currentTimeMillis();

        try {
           spawnInterval = (long) ((long) spawnSecondInterval * currentDiffMult);

            if (currentSpawnTime - lastSpawnTime >= spawnInterval && currentSpawned < MAXIMUM_NO_SPAWN) {
               System.out.println("Interval: " + spawnInterval);
               System.out.println("Mult: " + currentDiffMult);
               
                spawnEnemy();
                
                currentSpawned++;
                
                debugCurSpw();
//                 updateCurrentSpawned(1);

                lastSpawnTime = currentSpawnTime;
                currentDiffMult = (float) ((float)(enemyRandom.nextInt(10 - 1) + 1) / 10.0);
            }
            
            
            
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }

    public void spawnEnemy(){
        System.out.printf("Spawn Enemy #%d, Difficulty Multiplier: %.2f\n",currentSpawned, currentDiffMult);

    }
    
    public void updateCurrentSpawned(int value){
      
      currentSpawned -= value;
    
    
    
    }
    public void debugCurSpw(){
      if(gameLogic.getTimerMinutes() == 3){
         currentSpawned--;
      
      }
    
    }
    
    
    
    

}
