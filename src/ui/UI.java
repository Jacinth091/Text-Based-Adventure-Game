package ui;

import userInput.MouseHandler;
import main.GamePanel;

import java.awt.*;

public class UI {
    private GamePanel gp;
    private int fontSize = 30;
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
        g2.setFont(arial_40);


    }


    public void drawMousePos(Graphics2D g2, MouseHandler mouseIn){


        g2.setColor(Color.CYAN);
        String status = mouseIn.isDragging() ? "Dragging" :
                mouseIn.isClicking() ? "Clicking" :
                        mouseIn.isMoving() ? "Moving" : "Idle";

        String format = String.format("Mouse x: %d Mouse Y: %d Status: %s", mouseIn.getMouseX(), mouseIn.getMouseY(), status);
        g2.setFont(arial_15);
        g2.drawString(format, 10, 20);


    }

}
