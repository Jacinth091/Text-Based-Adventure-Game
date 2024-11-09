package main;

public class EnemySpawnerThread implements Runnable{

    private Thread enemySpawner;
    private GameLogic gameLogic;

    public EnemySpawnerThread(GameLogic gameLogic){
        this.gameLogic = gameLogic;

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
            startEnemySpawn();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void startEnemySpawn() throws InterruptedException{
        while(enemySpawner != null){
            try{
                System.out.println("Enemy Thread Running!");
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
