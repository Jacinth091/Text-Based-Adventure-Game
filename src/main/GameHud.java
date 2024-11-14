package main;

import entity.Player;
import events.GameUpdate;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameHud extends UI implements GameUpdate, ActionListener, ItemListener {
    public int screenHeight;


    private PlayerStats playerStats;
    // Ui Elements
    private JPanel parentCont;






    public GameHud(GameLogic gameLogic){
        super(gameLogic);
        gbc = new GridBagConstraints();
        this.playerStats = new PlayerStats(gameLogic);
        getGameLogic().getmGThread().addEventUpdate(this);
        maxScreenRow = 3;
        this.screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, this.screenHeight));


        initComponents();

    }



    public void initComponents(){
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(util.createLineBorder(Color.WHITE, 1));


        Color opaque = new Color(231, 231, 231, 107);
        parentCont = util.createPanel(null, new GridBagLayout(), true);
        parentCont.setOpaque(true); // Ensure the parent panel is opaque
        playerStats.setOpaque(true); // Ensure playerStats panel is also opaque







        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;          // Expand horizontally
        gbc.weighty = 1.0;          // Take available vertical space
        gbc.fill = GridBagConstraints.BOTH;
        parentCont.add(playerStats, gbc);



        add(parentCont, BorderLayout.CENTER);



        revalidate();
        repaint();
    }











    @Override
    public synchronized void update() {
        playerStats.update();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}


