package main;

public class GameEngine implements Runnable {

    private Thread gameThread;
    private final int FPS = 24;

    private final GamePanel gp;

    public GameEngine(GamePanel gp) {
        this.gp = gp;
    }

    public void start() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
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
                gp.update();     // Update game logic
                gp.repaint();    // Repaint the screen
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
