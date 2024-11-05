package Other;

import main.GameState;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private final Utility util = new Utility();
    final int origTileSize = 16; // 16x16 tile
    final int scale =2;
    final public int tileSize = origTileSize * scale; // 48x48 tile size displayed on screen

    public final int maxScreenCol = 25;
    public final int maxScreenRow = 11;
    // Screen Width and Height Settings
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 25 = 1200 screen width
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 15 = 720 screen height

    public GameScreen(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        // Listeners
//        initComponents();
    }

    public void initComponents(){

        JButton btn = new JButton();

        btn = util.createButton("Test",Color.white, util.createLineBorder(Color.RED, 10),Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        btn.setPreferredSize(new Dimension(500, 300));

        add(btn, BorderLayout.SOUTH);

        revalidate();
        repaint();


    }


}
