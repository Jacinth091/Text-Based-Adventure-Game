package src.main;

import entity.Player;
import events.GameUpdate;
import main.GameLogic;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameHud extends UI implements GameUpdate, ActionListener, ItemListener {
    public int screenHeight;

    //Test Stats
    int hp =100;
    int sanity =76;
    int max =100;
    private PlayerStats plyStats;
    // Ui Elements
    private JPanel parentCont;


    public GameHud(main.GameLogic gameLogic){
        super(gameLogic);
        this.plyStats = new PlayerStats(gameLogic);
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
        parentCont = util.createPanel(opaque, new GridBagLayout(),true);







        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;          // Expand horizontally
        gbc.weighty = 1.0;          // Take available vertical space
        gbc.fill = GridBagConstraints.BOTH;
        parentCont.add(plyStats, gbc);



        add(parentCont, BorderLayout.CENTER);



        revalidate();
        repaint();
    }







    @Override
    public synchronized void update() {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}


class PlayerStats extends UI implements GameUpdate{

    private Graphics2D g2;
    private Player player;
    private int screenHeight;
    private int screenWidth;



    public PlayerStats(GameLogic gameLogic) {
        super(gameLogic);
        maxScreenRow = 20;
        maxScreenCol = 20;
        this.screenHeight = tileSize * maxScreenRow;
        this.screenWidth  = tileSize * maxScreenCol;


        initComponents();
    }

    public void initComponents(){
        this.setPreferredSize(new Dimension(500,500));
        setLayout(null);
        setBackground(Color.BLACK);
        setBorder(util.createLineBorder(Color.WHITE, 1));
//        System.out.println("Beta Branch");








    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;



        g2.dispose();

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
//        player = getGameLogic().getPlayer();



    }

    @Override
    public void update() {

    }
}