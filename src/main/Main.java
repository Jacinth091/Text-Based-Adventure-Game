package main;

import ui.Utility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    private static Utility util = new Utility();

    public static void main(String[ ]args){

        appStart();




    }

    public static void appStart() {

        JFrame app = new JFrame();
        GamePanel gp = new GamePanel();
        GameHud hud = new GameHud();
        GameEngine gameEng = new GameEngine(gp);
        app.setTitle("Text Based - Adventure Game");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setLayout(new GridLayout(1, 1));


        JPanel screenContainer = new JPanel();
        screenContainer = util.createPanel(Color.white, util.setBorderLayout(5, 5), true);
        screenContainer.setBorder(util.compoundBorder);
        screenContainer.setLayout(new GridBagLayout());
        // Create a container with GridBagLayout
        screenContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Center component (gs)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;          // Expand horizontally
        gbc.weighty = 1.0;          // Take available vertical space
        gbc.fill = GridBagConstraints.BOTH; // Fill space both horizontally and vertically
        screenContainer.add(gp, gbc);

        // Bottom component (ui)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0;            // No extra vertical space, stays at the bottom
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill only horizontally
        screenContainer.add(hud, gbc);
//        gbc.anchor = GridBagConstraints.CENTER;



        app.add(screenContainer);
        app.pack();
        app.setLocationRelativeTo(null);
        app.setVisible(true);


        gameEng.start();
    }


}
