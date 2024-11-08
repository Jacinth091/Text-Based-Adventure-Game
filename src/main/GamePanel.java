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

public class GamePanel extends UI implements GameUpdate, ActionListener, ItemListener {
    private int screenHeight;
    // UI Elements

    private boolean doneInitialize = false;
    private String[] toggleCardsTitle = {
            "Settings",
            "Map",
            "Search",
            "Bag",
            "Back"
    };
    private JPanel uiCardPanel;
    private JPanel[] uiCards;
    private CardLayout cl;
    private JPanel uiBtnPanel;
    private JButton[] uiToggleBtns;


    // Main Game Screen
    private JPanel[] panelConts;
    private JPanel mainTextPanel;
    private JTextArea mainTextArea;

    private JPanel mainBtnPanel;
    private JButton[] mainButtons;



    public GamePanel(GameLogic gameLogic){
        super(gameLogic);
        getGameLogic().addEventUpdate(this);
        maxScreenRow = 15;
        this.screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth,this.screenHeight));


        initComponent();

    }


    public void initComponent(){
        setLayout(new BorderLayout());

        uiCardPanel = util.createPanel(null, new CardLayout(), true);
//        uiCardPanel.setBorder(util.createLineBorder(Color.BLACK, 5));
        cl= (CardLayout) uiCardPanel.getLayout();

        uiCards = new JPanel[5];
        for(int i=0; i < uiCards.length; i++){
            uiCards[i] = util.createPanel(Color.BLACK,new GridBagLayout(),true);
        }
        uiCards[0].setBackground(Color.gray);
        uiCards[1].setBackground(Color.pink);
        uiCards[2].setBackground(Color.blue);
        uiCards[3].setBackground(Color.green);
        uiCards[4].setBackground(null);

        // uiCards[5] = Main Game Screen
        setMainGameScreen();

        // Buttons Panel & Buttons
        uiBtnPanel = util.createPanel(Color.WHITE, new GridLayout(5,1,10,40) , true);
        uiBtnPanel.setPreferredSize(new Dimension(70, 0));
        uiBtnPanel.setBorder(util.createEmptyBorder(10,10,10,10));

        uiToggleBtns = new JButton[5];
        for(int i =0; i < uiToggleBtns.length; i++){
            uiToggleBtns[i] = util.createButton(toggleCardsTitle[i], Color.red,null,null);
            uiToggleBtns[i].setForeground(Color.WHITE);
            uiToggleBtns[i].setActionCommand(toggleCardsTitle[i]);
            uiToggleBtns[i].addActionListener(this);
//            uiToggleBtns[i].addItemListener(this);
        }






        //------------------------------------ Adding of Components ------------------------------------


        // uiCards[5] = Main Game Screen
        gbc = new GridBagConstraints();

        gbc.gridx = 0;        // Position in the grid
        gbc.gridy = 0;
        gbc.weightx = 1.0;    // Allow the panel to expand horizontally
        gbc.weighty = 1.0;    // Allow the panel to expand vertically
        gbc.fill = GridBagConstraints.BOTH;  // Allow the component to fill both axes
        gbc.anchor = GridBagConstraints.CENTER;  // This will center the JTextArea
        mainTextPanel.add(mainTextArea, gbc);



        panelConts[0].add(mainTextPanel);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        uiCards[uiCards.length-1].add(panelConts[0], gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        uiCards[uiCards.length-1].add(panelConts[1],gbc);









        for (JButton uiToggleBtn : uiToggleBtns) {
            uiBtnPanel.add(uiToggleBtn);
        }

        for(int i =0; i< uiCards.length; i++){
            uiCardPanel.add(uiCards[i], toggleCardsTitle[i]);
        }


        add(uiCardPanel, BorderLayout.CENTER);
        add(uiBtnPanel, BorderLayout.LINE_END);

        // Show default card without triggering any button action
        cl.show(uiCardPanel, "Back");
        doneInitialize = true; // Set flag after initialization
    }

    public void setMainGameScreen(){

        panelConts = new JPanel[2];
        for(int i=0; i < panelConts.length; i++){
            panelConts[i] = util.createPanel(Color.BLACK, new GridBagLayout(),true);
            panelConts[i].setBorder(util.createLineBorder(Color.WHITE, 5));

        }
        panelConts[0].setPreferredSize(new Dimension(100, 100));
        panelConts[1].setPreferredSize(new Dimension(100, 200));

//        panelConts[0].setPreferredSize(new Dimension(uiCards[uiCards.length-1].getWidth(), 100));
//        panelConts[1].setPreferredSize(new Dimension(uiCards[uiCards.length-1].getWidth(), 50));



        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(new GridBagLayout());



        mainTextArea = new JTextArea("Hello testing, Hello madafakas De joke Family Friendly ta");
        mainTextArea.setPreferredSize(new Dimension(500, 200));
        mainTextArea.setBackground(Color.black);
        mainTextArea.setBorder(util.createCompoundBorder(Color.white, 5, 5,5,5,5));
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(util.arial_20_Bold);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);






    }







    public void btnEventSwitch(ActionEvent e){
        String cardName = e.getActionCommand();
        if(doneInitialize){

            cl = (CardLayout)(uiCardPanel.getLayout());
            cl.show(uiCardPanel, cardName);
        }
    }

    @Override
    public void update() {
//        System.out.println("Updating!!! from GamePanel!");

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        btnEventSwitch(e);


    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
