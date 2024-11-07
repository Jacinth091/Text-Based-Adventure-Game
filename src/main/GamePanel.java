package main;

import events.GameUpdate;
import ui.Utility;
import userInput.KeyHandler;
import userInput.MouseHandler;
import ui.UI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameUpdate {
    private final Utility util = new Utility();
    private GameLogic gameLogic;
    final int origTileSize = 16; // 16x16 tile
    final int scale =2;
    final public int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen
    // 1200 x 720 screen size
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 12;
    // Screen Width and Height Settings
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 25 = 1200 screen width
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 15 = 720 screen height

    // UI Elements
    JLabel label;
    int count= 0;



    public GamePanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        gameLogic.addEventUpdate(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
//        this.setLayout();


        // Listeners
        this.addKeyListener(gameLogic.getKeyH());
        this.addMouseListener(gameLogic.getMouseIn());
        this.addMouseMotionListener(gameLogic.getMouseIn());


        initComponent();

    }


    public void initComponent(){

        label = util.createLabel("Hello There! ", Color.WHITE,Color.blue,20,true,"center");

        JButton btn = util.createButton("Click Me", Color.blue,null, null);
        btn.setPreferredSize(new Dimension(200, 400));


        this.add(btn);
        this.add(label);


    }


    @Override
    public void update() {
//        System.out.println("Updating!!! from GamePanel!");

        label.setText(util.formatText(String.valueOf(count++),Color.CYAN,50,true, "center"));
        repaint();
    }
}
