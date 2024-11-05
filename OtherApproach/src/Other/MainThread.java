package Other;

public class MainThread implements Runnable{

    private Thread gameThread;
    private int fps = 24;
    int count =0;

    public MainThread(){

    }

    public void start(){

        if(gameThread == null){
            gameThread = new Thread(this);
            gameThread.start();

        }
    }



    @Override
    public void run() {
        // Implementation for to run for the gameThread
        gameLoop();
    }



    public void gameLoop(){
        long nanoTime = 1000000000;
        double drawInterval = nanoTime / fps;
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
//                gp.update();
//                gp.repaint();
                delta--;
                frameCount++;
            }

            if (fpsTimer >= nanoTime) {
                System.out.println("FPS: " + frameCount);
                frameCount = 0; // Reset frame count
                fpsTimer = 0;   // Move to the next second
            }
        }
    }
}
