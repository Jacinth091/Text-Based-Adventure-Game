package src.ui;

import main.GameLogic;
import src.updates.GameThread;
import ui.Utility;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    private GameLogic gameLogic;

    public final ui.Utility util = new Utility();
    // 1200 x 720 screen size
    public int maxScreenCol = 23;
    public int maxScreenRow = 17;

    final int origTileSize = 16; // 16x16 tile
    final int scale =2;
    final public int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen
    // Screen Width and Height Settings
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 25 = 1200 screen width
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 15 = 720 screen height


    // Ui Elements
    public GridBagConstraints gbc;


    public UI(GameLogic gameLogic){
        this.gameLogic = gameLogic;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(null);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

//        // Listeners
//        this.addKeyListener(gameLogic.getKeyH());
//        this.addMouseListener(gameLogic.getMouseIn());
//        this.addMouseMotionListener(gameLogic.getMouseIn());

    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }



}
