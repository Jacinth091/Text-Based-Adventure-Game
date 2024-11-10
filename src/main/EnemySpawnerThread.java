package main;

import entity.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawnerThread implements Runnable{

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


    private GameLogic gameLogic;
    private List<Enemy> enemyList;


    public EnemySpawnerThread(GameLogic gameLogic){
        this.gameLogic = gameLogic;
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
            enemySpawnTimer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

        }
        System.out.println("Number Of Enemies Spawned: " + currentSpawned);
    }

    public void spawnEnemy(){
        System.out.printf("Spawn Enemy #%d, Difficulty Multiplier: %.2f\n",currentSpawned, randomDiffMult);

    }
}
