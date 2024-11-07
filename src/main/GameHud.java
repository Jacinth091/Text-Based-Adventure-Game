package main;

import events.GameUpdate;
import ui.Utility;
import entity.Player;
import userInput.KeyHandler;
import userInput.MouseHandler;
import ui.UI;

import javax.swing.*;
import java.awt.*;

public class GameHud extends JPanel implements GameUpdate {
    private GameLogic gameLogic;
    private final Utility util = new Utility();
    final int origTileSize = 16; // 16x16 tile
    final int scale =2;
    final public int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen
    // 1200 x 720 screen size
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 3;
    // Screen Width and Height Settings
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 25 = 1200 screen width
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 15 = 720 screen height

    public GameHud(GameLogic gameLogic){
        this.gameLogic = gameLogic;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        // Listeners
        this.addKeyListener(gameLogic.getKeyH());
        this.addMouseListener(gameLogic.getMouseIn());
        this.addMouseMotionListener(gameLogic.getMouseIn());

    }

    @Override
    public void update() {

    }
}