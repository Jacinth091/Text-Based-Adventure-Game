package main;

import userInput.KeyHandler;
import userInput.MouseHandler;
import ui.UI;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{
    final int origTileSize = 16; // 16x16 tile
    final int scale =2;
    final public int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen
    // 1200 x 720 screen size
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 15;
    // Screen Width and Height Settings
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 25 = 1200 screen width
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 15 = 720 screen height

    // Important Objects
    private final MouseHandler mouseIn = new MouseHandler(this);
    private final KeyHandler keyH = new KeyHandler();
    private final UI ui = new UI(this);



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);

        // Listeners
//
        this.addKeyListener(keyH);
        this.addMouseListener(mouseIn);
        this.addMouseMotionListener(mouseIn);



    }

    public void update(){

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        ui.drawDebugScreen(g2,mouseIn,keyH);


        g2.dispose();
    }







}
