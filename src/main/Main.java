package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static void main(String[ ]args){

        appStart();




    }

    public static void appStart() {
        Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border pinkBorder = BorderFactory.createLineBorder(Color.pink, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(pinkBorder, emptyBorder);
        JFrame app = new JFrame();
        GamePanel gp = new GamePanel();
        GameEngine gameEng = new GameEngine(gp);
        app.setTitle("Text Based - Adventure Game");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setLayout(new GridLayout(1, 1));


        JPanel container = new JPanel();
        container.setBorder(compoundBorder);

        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;

        container.add(gp, gbc);

        app.add(container);
        app.pack();
        app.setLocationRelativeTo(null);
        app.setVisible(true);


        gameEng.start();
    }


}
