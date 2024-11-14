package main;

import entity.Player;
import ui.UI;

import javax.swing.*;
import java.awt.*;

public class PlayerStats extends UI {

    private Player player;
    private int screenHeight;
    private int screenWidth;

    // PlayerSTats Components
    private JPanel mainHudPanel;
    private JPanel[] hudElementsPanel;


    // Player Hp & Sanity
    private int playerHP, playerSan;
    private JProgressBar[] statsBars;

    // Game Time Display
    private JLabel timeDisplay;
    private JTextArea timerDisplayTA;
    private String timeStr;
    private int minutes, seconds;



    public PlayerStats(GameLogic gameLogic) {
        super(gameLogic);
        this.setDoubleBuffered(true);  // Ensures double buffering
        this.player = getGameLogic().getPlayer();

        this.playerHP = player.getPlayerHealth();
        this.playerSan = player.getPlayerSanity();

        maxScreenRow = 20;
        maxScreenCol = 20;
        this.screenHeight = tileSize * maxScreenRow;
        this.screenWidth  = tileSize * maxScreenCol;


        initComponents();
    }

    public void initComponents(){
        this.setPreferredSize(new Dimension(500,500));
        setLayout(new BorderLayout());
        setBackground(null);
//        setBorder(util.createLineBorder(Color.PINK, 1));

        gbc = new GridBagConstraints();
//        System.out.println("Beta Branch");


        setHudElements();





    }


    public void setHudElements(){
        mainHudPanel = util.createPanel(null, new GridLayout(1,3), true);
//        mainHudPanel.setBorder(util.createLineBorder(Color.white, 3));

        hudElementsPanel = new JPanel[3];
        for(int i =0; i< hudElementsPanel.length; i++){
            hudElementsPanel[i] = util.createPanel(null, null, true);
//            hudElementsPanel[i].setBorder(util.createLineBorder(Color.white, 3));
        }


        setStatsBars();

        setTimeDisplay();



        for(JPanel elemPanel : hudElementsPanel){
            mainHudPanel.add(elemPanel);
        }

        add(mainHudPanel, BorderLayout.CENTER);


    }


    public void setTimeDisplay(){
        hudElementsPanel[1].setLayout(new GridBagLayout());

        timeStr = String.format("%02d:%02d", minutes, seconds);
        System.out.println(timeStr);
/*
        timeDisplay = util.createLabel(null);
        timeDisplay.setText(timeStr);
//        timeDisplay.setText(util.formatText(timeStr, Color.white, 20,true,"center"));
        timeDisplay.setHorizontalAlignment(JLabel.CENTER);*/
        
        timerDisplayTA = new JTextArea();
        timerDisplayTA.setPreferredSize(new Dimension(600, 200));
        timerDisplayTA.setBackground(Color.BLACK);
        timerDisplayTA.setBorder(util.createCompoundBorder(Color.WHITE, 5, 5, 5, 5, 5));
        timerDisplayTA.setForeground(Color.WHITE);
        timerDisplayTA.setFont(util.arial_20_Bold);
        timerDisplayTA.setLineWrap(true);
        timerDisplayTA.setWrapStyleWord(true);
        timerDisplayTA.setEditable(false);
        timerDisplayTA.setText(timeStr);


        gbc.gridx =0;
        gbc.gridy =0;
        gbc.weightx =1.0;
        gbc.weighty =1.0;
        gbc.fill =GridBagConstraints.BOTH;
        gbc.anchor =GridBagConstraints.CENTER;


        hudElementsPanel[1].add(timerDisplayTA, gbc);

    }


    public void updateTimeDisplay(){
        minutes = (int) getGameLogic().getmGThread().getPlayTime().getTimerMinutes();
        seconds = (int) getGameLogic().getmGThread().getPlayTime().getTimerSeconds();

        timeStr = String.format("%02d:%02d", minutes, seconds);

        timerDisplayTA = new JTextArea();
        timerDisplayTA.setPreferredSize(new Dimension(600, 200));
        timerDisplayTA.setBackground(Color.BLACK);
        timerDisplayTA.setBorder(util.createCompoundBorder(Color.WHITE, 5, 5, 5, 5, 5));
        timerDisplayTA.setForeground(Color.WHITE);
        timerDisplayTA.setFont(util.arial_20_Bold);
        timerDisplayTA.setLineWrap(true);
        timerDisplayTA.setWrapStyleWord(true);
        timerDisplayTA.setEditable(false);
        timerDisplayTA.setText(timeStr);


        timerDisplayTA.revalidate();
        timerDisplayTA.repaint();
    }





// Player Stats Bars
    public void setStatsBars(){
        int max;
        int current;
        int width;
//        int filledWidth = (int)(( / (double)max) * width);
        int[][] playerStats = {{playerHP, playerSan},
                {player.getMaxHealth(), player.getMaxSanity()}
        };


        hudElementsPanel[0].setLayout(new GridBagLayout());

        statsBars = new JProgressBar[2];
        for(int i =0; i< statsBars.length; i++){
            statsBars[i] = new JProgressBar(0, 100);
            statsBars[i].setValue(0);
            statsBars[i].setStringPainted(true);
            statsBars[i].setForeground(i == 0 ? Color.RED : Color.BLUE);
            statsBars[i].setFont(util.arial_15_Bold);
            statsBars[i].setBackground(new Color(33, 33, 33));
        }
//        statsBars[0].set
        statsBars[0].setPreferredSize(new Dimension(200,150));
        statsBars[1].setPreferredSize(new Dimension(200,150));


        // Configure and add health bar
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;  // Adjust weight for desired distribution of space
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the bar
        hudElementsPanel[0].add(statsBars[0], gbc);

        // Configure and add sanity bar
        gbc.gridy = 1; // Move to next row
        gbc.weighty = 0.5;
        hudElementsPanel[0].add(statsBars[1], gbc);

        updateStatsBars();
    }
    public void updateStatsBars(){
        playerHP = player.getPlayerHealth();
        playerSan = player.getPlayerSanity();
        // Update health and sanity based on player’s current stats
        statsBars[0].setMaximum(player.getMaxHealth());
        statsBars[0].setString("HP: " + playerHP + "/" + player.getMaxHealth());
        statsBars[0].setValue(playerHP);

        statsBars[1].setMaximum(player.getMaxSanity());
        statsBars[1].setString("Sanity: " + playerSan + "/" + player.getMaxSanity());
        statsBars[1].setValue(playerSan);


        // Debug Health
        debugHealth();

    }
    public void debugHealth(){
        System.out.println(getGameLogic().getmGThread().getPlayTime().getTimeElapsedInSeconds());
        if(getGameLogic().getmGThread().getPlayTime().getTimeElapsedInSeconds() == 10){
            System.out.println("Plus 50!");
            player.setPlayerHealth(playerHP+50);
        }


        if(playerHP == player.getMaxHealth() || playerSan == player.getMaxSanity()){
            player.setPlayerHealth(0);
            player.setPlayerSanity(0);
        }
        else if(playerHP < player.getMaxHealth() || playerSan < player.getMaxSanity()){

            player.increaseHealth(1);
            player.increaseSanity(1);
//
        }
    }

    public void update() {
        updateStatsBars();
        SwingUtilities.invokeLater(() -> {updateTimeDisplay();});



        repaint();
    }
}
