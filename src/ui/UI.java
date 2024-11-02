package ui;

import main.GameState;
import userInput.KeyHandler;
import userInput.MouseHandler;
import main.GamePanel;

import java.awt.*;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;

    private final Color white = new Color(255,255,255);
    private int[] fontSizes = {40, 30, 20, 15, 10,5};
    private Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    private Font arial_40_Bold = new Font("Arial", Font.BOLD, 40);


    private Font arial_20 = new Font("Arial", Font.PLAIN, 20);
    private Font arial_20_Bold = new Font("Arial", Font.BOLD, 20);

    private Font arial_15 = new Font("Arial", Font.PLAIN, 15);
    private Font arial_15_Bold = new Font("Arial", Font.BOLD, 15);

    public UI(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40_Bold);

        if(gp.getCurrentState() == GameState.game_PlayState){
            // Game State = PLAY block


        }
        else if(gp.getCurrentState() == GameState.game_PauseState){
//            System.out.println("PAUSE!!!!!");
            drawPauseScreen(g2);

        }


    }


    public void drawMousePos(Graphics2D g2, MouseHandler mouseIn){

        g2.setColor(white);
        String status = mouseIn.isDragging() ? "Dragging" :
                mouseIn.isClicking() ? "Clicking" :
                        mouseIn.isMoving() ? "Moving" : "Idle";

        String format = String.format("Mouse x: %d Mouse Y: %d Status: %s", mouseIn.getMouseX(), mouseIn.getMouseY(), status);
        g2.setFont(arial_15);
        g2.drawString(format, 10, 20);

    }

    public void drawWindow(Graphics2D g2){

        int width = gp.screenWidth - (gp.tileSize * 15); // 1104
        int height = gp.tileSize * 4; // 144

        int x = (gp.screenWidth - width) / 2; // Centered X position
        int y = (gp.screenHeight - height) / 4; // Centered Y position
        drawDialogScreen(g2, x,y,width,height);

    }

    public void drawDialogScreen(Graphics2D g2, int x, int y, int width, int height){
        int arcWidth = 5, arcHeight = 5;
        g2.setColor(Color.gray);
        g2.fillRoundRect(x, y, width, height, arcWidth + 2, arcHeight+2);

        g2.setStroke(new BasicStroke(5));
        g2.setColor(white);
        g2.drawRoundRect(x+5, y+5, width -10, height-10, arcWidth, arcHeight );
    }

    public void drawDebugScreen(Graphics2D g2, MouseHandler mouseIn, KeyHandler keyH){
        // Mouse Input Debug (temporary)

        if(keyH.tKeyPressed){
            drawMousePos(g2,mouseIn);
        }


    }


    public void drawPauseScreen(Graphics2D g2){
        int width = gp.screenWidth - (gp.tileSize * 15); // 1104
        int height = gp.tileSize * 4; // 144

        int x = (gp.screenWidth - width) / 2; // Centered X position
        int y = (gp.screenHeight - height) / 4; // Centered Y position
        g2.setColor(white);
        g2.setFont(arial_40_Bold);
        g2.drawString("PAUSED!", x, y);


    }
}
