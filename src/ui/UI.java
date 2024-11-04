package ui;

import main.GameState;
import userInput.KeyHandler;
import userInput.MouseHandler;
import main.GamePanel;

import java.awt.*;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    int count =0;
    private Rectangle[] userOptions;
    private Rectangle[] eventOptions;

    int playerHp = 1, maxHp = 100, playerSanity = 89, maxSanity = 100;


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
            drawPlayerUi(g2);
            playerHp++;

        }
        else if(gp.getCurrentState() == GameState.game_PauseState){
//            System.out.println("PAUSE!!!!!");
            drawPauseScreen(g2);

        }


    }


    public void drawPlayerUi(Graphics2D g2) {
        g2.setFont(arial_15_Bold);
        int width = gp.screenWidth; // 1104
        int height = gp.tileSize * 4; // 144
        int x = (gp.screenWidth - width) / 2;
        int y = gp.screenHeight - height;

        // Draw HUD Window
        drawUIWindow(g2, x, y, width, height);

        // Draw HP Bar
        int hpBarWidth = (int)(width * 0.3); // Adjust bar width as needed
        int hpHeight = 20;
        int hpX = x + 20;
        int hpY = y + 20;
        drawBar(g2, hpX, hpY, hpBarWidth, hpHeight, Color.RED, playerHp, maxHp);

        // Draw Sanity Bar
        int sanityBarWidth = (int)(width * 0.3);
        int sanityX = x + 20;
        int sanityY = hpY + hpHeight + 10; // Position below HP bar
        drawBar(g2, sanityX, sanityY, sanityBarWidth, hpHeight, Color.BLUE, playerSanity, maxSanity);

        // Draw Inventory
        int slotSize = gp.tileSize; // Adjust slot size
        int inventoryX = x + width - ((slotSize * 7) ); // Right side of HUD
        int inventoryY = y + (gp.screenHeight / 6);
        drawInventory(g2, inventoryX, inventoryY, slotSize);
    }

    private void drawUIWindow(Graphics2D g2, int x, int y, int width, int height) {
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.green);
        g2.drawRect(x + 5, y + 5, width - 10, height - 10);
    }

    // Draws a single bar (HP or Sanity)
    private void drawBar(Graphics2D g2, int x, int y, int width, int height, Color color, int current, int max) {
        int filledWidth = (int)((current / (double)max) * width);
        g2.setColor(Color.DARK_GRAY); // Background of the bar
        g2.fillRect(x, y, width, height);
        g2.setColor(color); // Filled portion based on current value
        g2.fillRect(x, y, filledWidth, height);
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, width, height); // Outline
        g2.drawString(current + " / " + max, x + 5, y + height - 5); // Value text
    }

    // Draws inventory slots in HUD
    private void drawInventory(Graphics2D g2, int x, int y, int slotSize) {
        for (int i = 0; i < 5; i++) { // Example: 5 slots
            int slotX = x + (i * (slotSize + 10)); // Adjust spacing
            g2.setColor(Color.pink);
            g2.fillRect(slotX, y, slotSize, slotSize); // Slot background
            g2.setColor(Color.WHITE);

            g2.drawRect(slotX, y, slotSize, slotSize); // Slot outline
            g2.setColor(Color.white);

            g2.drawString(String.valueOf(i),slotX + 5, y + slotSize - 5 );
//            g2.drawString("Item" + (i + 1), slotX + 5, y + slotSize - 5);
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
