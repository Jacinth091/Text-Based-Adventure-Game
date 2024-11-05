package ui;

import entity.Actionable;
import entity.Player;
import main.GameState;
import userInput.KeyHandler;
import userInput.MouseHandler;
import main.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class UI {

    private Utility ui = new Utility();



    private GamePanel gp;
    private Graphics2D g2;
    private Player player;
    int count =0;
    private Rectangle[] userOptions;
    private Rectangle[] eventOptions;

    int playerHp = 1, maxHp = 100, playerSanity = 20, maxSanity = 100;

    private String[] titles = {
            "HP",
            "Sanity",
            "Panic",
            "Stealth"
    };



    private int boxWidth, boxHeight, barX, barY, barWidth, barHeight, xBox, yBox;

    private int[] playerStats;
    private int[] playerMaxStats;




    public UI(GamePanel gp) {
        this.gp = gp;
    }


    public void draw(Graphics2D g2){
        this.g2 = g2;
        player = gp.getPlayer();
        g2.setFont(ui.arial_40_Bold);

        drawPlayerUi(g2);
        if(gp.getCurrentState() == GameState.game_PlayState){
            // Game State = PLAY block

            if(player.getPlayerHealth() == player.getMaxHealth()){
                player.setPlayerHealth(0);
            }
            else if(player.getPlayerHealth() < player.getMaxHealth()){

                player.increaseHealth(1);
//
            }

        }
        else if(gp.getCurrentState() == GameState.game_PauseState){
//            System.out.println("PAUSE!!!!!");
            drawPauseScreen(g2);

        }


    }


    public void drawPlayerUis(Graphics2D g2) {
        g2.setFont(ui.arial_15_Bold);
        boxWidth = gp.screenWidth; // 1104
        boxHeight = gp.tileSize * 4; // 144
        xBox = (gp.screenWidth - boxWidth) / 2;
        yBox = gp.screenHeight - boxHeight;

        // Draw HUD Window
        drawUIWindow(g2, xBox, yBox, boxWidth, boxHeight);




        barWidth = boxWidth *2;
        barHeight = boxHeight / 2;
        barX = boxWidth + 20;
        barY = boxHeight + 20;
//        drawBar(g2, barX, barY, barWidth, barHeight, ui.red, playerHp, maxHp);


/*        bar;
        int sanityX = x + 20;
        int sanityY = hpY + hpHeight + 10; // Position below HP bar
        drawBar(g2, sanityX, sanityY, sanityBarWidth, hpHeight, Color.BLUE, playerSanity, maxSanity);







        // Draw HP Bar
        int hpBarWidth = (int)(width * 0.3); // Adjust bar width as needed
        int hpHeight = 20;
        int hpX = x + 20;
        int hpY = y + 20;

        // Draw Sanity Bar
        int sanityBarWidth = (int)(width * 0.3);
        int sanityX = x + 20;
        int sanityY = hpY + hpHeight + 10; // Position below HP bar
        drawBar(g2, sanityX, sanityY, sanityBarWidth, hpHeight, Color.BLUE, playerSanity, maxSanity);

        // Draw Inventory
        int slotSize = gp.tileSize; // Adjust slot size
        int inventoryX = x + width - ((slotSize * 7) ); // Right side of HUD
        int inventoryY = y + (gp.screenHeight / 6);
        drawInventory(g2, inventoryX, inventoryY, slotSize);*/

    }

    public void drawPlayerUi(Graphics2D g2) {
        g2.setFont(ui.arial_15_Bold);
        boxWidth = gp.screenWidth; // 1104
        boxHeight = gp.tileSize * 4; // 144
        xBox = (gp.screenWidth - boxWidth) / 2;
        yBox = gp.screenHeight - boxHeight;

        // Draw HUD Window
        drawUIWindow(g2, xBox, yBox, boxWidth, boxHeight);

        drawBars(g2);

/*        // Draw Sanity Bar
        barWidth = (int)(boxWidth * 0.3); // Adjust bar width as needed
        barHeight = 25;
        barX = xBox + 20;
        barY = yBox + 20;
        drawBar(g2, barX, barY, barWidth, barHeight, ui.barColors[0], player.getPlayerHealth(), player.getMaxHealth(), "HP");


        // Draw HP Bar
        barWidth = (int)(boxWidth * 0.3); // Adjust bar width as needed
        barHeight = 25;
        barX = xBox + 20;
        barY = yBox + 40;
        drawBar(g2, barX, barY, barWidth, barHeight, ui.barColors[1], player.getPlayerSanity(), player.getMaxSanity(), "Sanity");

        //
        barWidth = (int)(boxWidth * 0.3); // Adjust bar width as needed
        barHeight = 25;
        barX = xBox + 20;
        barY = yBox + 50;
        drawBar(g2, barX, barY, barWidth, barHeight, ui.barColors[2], player.getPlayerCalmness(), player.getMaxCalmness(), "Panic");


        barWidth = (int)(boxWidth * 0.3); // Adjust bar width as needed
        barHeight = 25;
        barX = xBox + 20;
        barY = yBox + 60;
        drawBar(g2, barX, barY, barWidth, barHeight, ui.barColors[3], player.getPlayerStealth(), player.getMaxStealth(), "Stealth");*/







//        // Draw Inventory
//        int slotSize = gp.tileSize; // Adjust slot size
//        int inventoryX = x + width - ((slotSize * 7) ); // Right side of HUD
//        int inventoryY = y + (gp.screenHeight / 6);
//        drawInventory(g2, inventoryX, inventoryY, slotSize);
    }

    private void drawBars(Graphics2D g2) {
        for(int i = 0; i < titles.length; i++) {
            int padding = 25;
            int margin = 10;
            barWidth = (int)(boxWidth * 0.3); // Adjust bar width as needed
            barHeight = 25;
            barX = xBox + margin;
            barY = yBox + (padding * i) + 14;

            // Fetch current player stats directly
            int currentStat;
            int maxStat;
            Color barColor = ui.barColors[i];

            switch (i) {
                case 0:
                    currentStat = player.getPlayerHealth();
                    maxStat = player.getMaxHealth();
                    break;
                case 1:
                    currentStat = player.getPlayerSanity();
                    maxStat = player.getMaxSanity();
                    break;
                case 2:
                    currentStat = player.getPlayerCalmness();
                    maxStat = player.getMaxCalmness();
                    break;
                case 3:
                    currentStat = player.getPlayerStealth();
                    maxStat = player.getMaxStealth();
                    break;
                default:
                    currentStat = 0;
                    maxStat = 1; // Avoid division by zero
            }

            drawBar(g2, barX, barY, barWidth, barHeight, barColor, currentStat, maxStat, titles[i]);
        }
    }



    private void drawUIWindow(Graphics2D g2, int x, int y, int width, int height) {
        g2.setColor(Color.darkGray);
        g2.fillRoundRect(x, y, width, height,  10,10);


        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x + 5, y + 5, width - 10, height -10, 15, 15); // Outline
    }

    // Draws a single bar (HP or Sanity)
    private void drawBar(Graphics2D g2, int x, int y, int width, int height, Color color, int current, int max, String title) {
        int filledWidth = (int)((current / (double)max) * width);

        g2.setColor(ui.white); // Background of the bar
        g2.fillRect(x, y, width, height);


        g2.setColor(color); // Filled portion based on current value
        g2.fillRect(x, y, filledWidth, height);

        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x, y, width, height); // Outline
        g2.drawString( title + ": "+ current + " / " + max, x + 5, y + height - 7); // Value text
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

        g2.setColor(ui.white);
        String status = mouseIn.isDragging() ? "Dragging" :
                mouseIn.isClicking() ? "Clicking" :
                        mouseIn.isMoving() ? "Moving" : "Idle";

        String format = String.format("Mouse x: %d Mouse Y: %d Status: %s", mouseIn.getMouseX(), mouseIn.getMouseY(), status);
        g2.setFont(ui.arial_15);
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
        g2.setColor(ui.white);
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
        g2.setColor(ui.white);
        g2.setFont(ui.arial_40_Bold);
        g2.drawString("PAUSED!", x, y);


    }
}
