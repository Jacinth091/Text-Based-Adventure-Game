package main;

import ui.UI;
import ui.Utility;
import updates.EnemySpawnerThread;

import javax.swing.*;
import java.awt.*;

public class GameApp extends JFrame {

    private final GameLogic gameLogic;
    private final UI gameUI;
    private final GamePanel gPanel;
    private final GameHud gHud;
    private EnemySpawnerThread enemySpawner;
    private final Utility util = new Utility();

    private String title;

    private GridBagConstraints gbc;
    private JPanel parentContainer;



    public GameApp(GameLogic gameLogic, String title){
        this.gameLogic = gameLogic;
        this.title = title;
        gameUI = new UI(gameLogic);
        gPanel = new GamePanel(gameLogic);
        gHud = new GameHud(gameLogic);


        setTitle("Text Based - Adventure Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());

        initializeUI();



    }

    public void initializeUI(){
        setLayout(new BorderLayout());

        parentContainer = util.createPanel( Color.CYAN, new GridBagLayout(),true );
        parentContainer.setBorder(util.createEmptyBorder(10,10,10,10));


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;          // Expand horizontally
        gbc.weighty = 1.0;          // Take available vertical space
        gbc.fill = GridBagConstraints.BOTH; // Fill space both horizontally and vertically
        parentContainer.add(gPanel, gbc);

        // Bottom component (ui)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;            // No extra vertical space, stays at the bottom
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill only horizontally
        parentContainer.add(gHud, gbc);



        this.add(parentContainer, BorderLayout.CENTER);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
