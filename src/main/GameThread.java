package main;

public class GameThread implements Runnable {

    private Thread gameThread;
    private final int FPS = 24;

    private final GameLogic gameLogic;

    public GameThread(GameLogic gameLogic) {
        this.gameLogic = gameLogic;


    }

    public void start() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        gameLoop();
    }


    public void gameLoop(){
        long nanoTime = 1000000000;
        double drawInterval = nanoTime / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        int frameCount = 0;
        long fpsTimer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            fpsTimer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                gameLogic.update();

                delta--;
                frameCount++;
            }

            if (fpsTimer >= nanoTime) {
                frameCount = 0; // Reset frame count
                fpsTimer = 0;   // Move to the next second
            }
        }
    }

}
