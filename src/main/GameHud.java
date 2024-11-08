package main;

import events.GameUpdate;
import ui.UI;
import ui.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameHud extends UI implements GameUpdate, ActionListener, ItemListener {
    public int screenHeight;

    public GameHud(GameLogic gameLogic){
        super(gameLogic);
        getGameLogic().addEventUpdate(this);
        maxScreenRow = 3;
        this.screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, this.screenHeight));


        initComponents();

    }

    public void initComponents(){
        setLayout(new GridBagLayout());
        setBackground(Color.orange);






    }







    @Override
    public void update() {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}