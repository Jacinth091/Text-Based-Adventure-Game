package src.main;

import events.GameState;
import events.GameUpdate;
import main.GameLogic;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GamePanel extends UI implements GameUpdate, ActionListener, ItemListener {
    private GameState currState;
    private int screenHeight;
    private int count = 0;
    private boolean doneInitialize = false;

    // Titles for toggle buttons and labels for choice buttons
    private String[] toggleCardsTitle = { "Settings", "Map", "Search", "Bag", "Back" };
    private String[][] buttonLabels = {
            getGameLogic().getDirections(),
            getGameLogic().getPlayerActions()
    };

    // UI Components
    private JPanel uiCardPanel;
    private JPanel[] uiCards;
    private CardLayout cl;
    private JPanel uiBtnPanel;
    private JButton[] uiToggleBtns;

    // Main Game Screen Components
    private JPanel[] panelConts;
    private JPanel mainTextPanel;
    private JTextArea mainTextArea;
    private JPanel mainChoicePanel;
    private JPanel[] choicesPanel;
    private JButton[] directions;
    private JButton[] utilityAction;
    private JPanel[] choicesButtonPanel;
    private JButton[][] choicesButtons;
    private JLabel[] infoLabels;



    private String playerLocation;
    private String playerWeapon;

    public GamePanel(GameLogic gameLogic) {
        super(gameLogic);
        getGameLogic().getmGThread().addEventUpdate(this);
        currState = getGameLogic().getmGThread().getCurrentState();
        maxScreenRow = 15;
        this.screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, this.screenHeight));

        this.playerLocation = getGameLogic().getPlayer().getCurrentLocation();
        this.playerWeapon = getGameLogic().getPlayer().getWeapon();


        initComponent();
    }

    // Initialize main components and layout to Frame
    public void initComponent() {
        setLayout(new BorderLayout());
        gbc = new GridBagConstraints();

        setCardLayouts();
        // Main Game Screen setup
        setMainGameScreen();

        setToggleBtns();

        // Add components to panels
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        uiCards[uiCards.length - 1].add(panelConts[0], gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        uiCards[uiCards.length - 1].add(panelConts[1], gbc);

        for (JButton uiToggleBtn : uiToggleBtns) {
            uiBtnPanel.add(uiToggleBtn);
        }

        for (int i = 0; i < uiCards.length; i++) {
            uiCardPanel.add(uiCards[i], toggleCardsTitle[i]);
        }

        add(uiCardPanel, BorderLayout.CENTER);
        add(uiBtnPanel, BorderLayout.LINE_END);

        // Show default card
        cl.show(uiCardPanel, "Back");
        doneInitialize = true;
    }
    public void setToggleBtns(){
        // Buttons panel and toggle buttons
        uiBtnPanel = util.createPanel(Color.BLACK, new GridLayout(5, 1, 10, 40), true);
        uiBtnPanel.setPreferredSize(new Dimension(70, 0));
        uiBtnPanel.setBorder(util.createEmptyBorder(10, 10, 10, 10));

        uiToggleBtns = new JButton[5];
        for (int i = 0; i < uiToggleBtns.length; i++) {
            uiToggleBtns[i] = util.createButton(toggleCardsTitle[i], Color.RED, null, null);
            uiToggleBtns[i].setForeground(Color.WHITE);
            uiToggleBtns[i].setActionCommand(toggleCardsTitle[i]);
            uiToggleBtns[i].addActionListener(this);
        }
    }
    public void setCardLayouts(){

        // Setup card layout and panels
        uiCardPanel = util.createPanel(null, new CardLayout(), true);
        cl = (CardLayout) uiCardPanel.getLayout();

        uiCards = new JPanel[5];
        for (int i = 0; i < uiCards.length; i++) {
            uiCards[i] = util.createPanel(Color.BLACK, new GridBagLayout(), true);
        }

        uiCards[0].setBackground(Color.GRAY);
        uiCards[1].setBackground(Color.PINK);
        uiCards[2].setBackground(Color.BLUE);
        uiCards[3].setBackground(Color.GREEN);
        uiCards[4].setBackground(null); // Main GamePanel
    }





    // Setup main game screen layout and components
    public void setMainGameScreen() {
        gbc = new GridBagConstraints();

        setMainTextPanel();

        // Choices panel for user options
        setChoicesPanel();


        addTextPanelAndChoicePanelToFrame();


    }
    public void setMainTextPanel(){

        panelConts = new JPanel[2];

        for (int i = 0; i < panelConts.length; i++) {
            panelConts[i] = util.createPanel(Color.BLACK, null, true);
        }

        panelConts[0].setPreferredSize(new Dimension(100, 100));
        panelConts[0].setLayout(new GridBagLayout());
        panelConts[1].setPreferredSize(new Dimension(100, 250));
        panelConts[1].setLayout(new BorderLayout());

        // Text area for main screen
        mainTextPanel = new JPanel(new GridBagLayout());
        mainTextPanel.setBackground(Color.GRAY);

        mainTextArea = new JTextArea("Hello testing");
        mainTextArea.setPreferredSize(new Dimension(600, 200));
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setBorder(util.createCompoundBorder(Color.WHITE, 5, 5, 5, 5, 5));
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(util.arial_20_Bold);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
    }
    public void setChoicesPanel(){


        mainChoicePanel = new JPanel(new BorderLayout());
        choicesPanel = new JPanel[2];
        for (int i = 0; i < choicesPanel.length; i++) {
            choicesPanel[i] = util.createPanel(Color.BLACK, new GridBagLayout(), true);
//            choicesPanel[i].setBorder(util.createLineBorder(Color.white, 2));
        }

        choicesPanel[1].setPreferredSize(new Dimension(290, 100));
        choicesPanel[0].setPreferredSize(new Dimension(330, 100));

        choicesButtonPanel = new JPanel[2];
        for (int i = 0; i < choicesButtonPanel.length; i++) {
            choicesButtonPanel[i] = util.createPanel(Color.BLACK, null, true);
//            choicesButtonPanel[i].setBorder(util.createLineBorder(Color.white, 2));
        }

        choicesButtonPanel[1].setPreferredSize(new Dimension(250, 190));
        choicesButtonPanel[1].setLayout(new GridLayout(4, 1, 5, 5));
        choicesButtonPanel[0].setPreferredSize(new Dimension(300, 190));
        choicesButtonPanel[0].setLayout(new GridLayout(4, 1, 5, 5));

        directions = new JButton[4];
        utilityAction = new JButton[2];
        choicesButtons = new JButton[][] { directions, utilityAction };

        for (int i = 0; i < choicesButtons.length; i++) {
            for (int j = 0; j < choicesButtons[i].length; j++) {
                choicesButtons[i][j] = util.createButton("", Color.BLACK, util.createLineBorder(Color.white, 1), null);
                choicesButtons[i][j].setForeground(Color.WHITE);
                choicesButtons[i][j].addActionListener(this);
                choicesButtons[i][j].setFocusable(false);
                choicesButtons[i][j].setFont(util.normalFont);

                String btnLabel = (i == 0) ? "Go " + buttonLabels[i][j] : buttonLabels[i][j];
                choicesButtons[i][j].setText(btnLabel);
                choicesButtons[i][j].setActionCommand(buttonLabels[i][j]);
            }

        }






        infoLabels = new JLabel[2];
        for (int i = 0; i < infoLabels.length; i++) {
            infoLabels[i] = util.createLabel(null);
        }
        String labelCurLoc = String.format("Location: %s", playerLocation);
        infoLabels[0].setText(util.formatText(labelCurLoc,Color.WHITE,13, true,"center"));

        String labelCurWeap = String.format("Weapon: %s", playerWeapon);
        infoLabels[1].setText(util.formatText(labelCurWeap,Color.WHITE,13, true,"center"));
    }
    public void addTextPanelAndChoicePanelToFrame(){
        // Add buttons and panels to choicesPanel
        choicesButtonPanel[1].add(choicesButtons[1][0]);
        choicesButtonPanel[1].add(choicesButtons[1][1]);
        choicesButtonPanel[1].add(infoLabels[1]);
        choicesButtonPanel[1].add(infoLabels[0]);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
//        choicesPanel[1].add(choicesButtonPanel[1], gbc);

        for (int i = 0; i < choicesButtons[0].length; i++) {
            choicesButtonPanel[0].add(choicesButtons[0][i]);
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        choicesPanel[0].add(choicesButtonPanel[1], gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        choicesPanel[0].add(choicesButtonPanel[0], gbc);
//        mainChoicePanel.add(choicesPanel[1], BorderLayout.EAST);
        mainChoicePanel.add(choicesPanel[0], BorderLayout.CENTER);
        panelConts[1].add(mainChoicePanel);

        // Add main text area to main text panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        mainTextPanel.add(mainTextArea, gbc);
        panelConts[0].add(mainTextPanel);
    }





    // Switch card view based on button action
    public void btnEventSwitch(ActionEvent e) {
        String cardName = e.getActionCommand();
        if (doneInitialize) {
            cl.show(uiCardPanel, cardName);
        }
    }





    public void updateUIElements(){
        // Label
        currState = getGameLogic().getmGThread().getCurrentState();
        playerLocation = getGameLogic().getCurrentLocation();
        String labelCurLoc = String.format("Location: %s", playerLocation);
        infoLabels[0].setText(util.formatText(labelCurLoc,Color.WHITE,13, true,"center"));
//        String labelCurWeap = String.format("Weapon: %s", playerWeapon);
        String labelCurWeap = String.format("Weapon: %s", String.valueOf(count++));
        infoLabels[1].setText(util.formatText(labelCurWeap,Color.WHITE,13, true,"center"));

        System.out.println("Game Panel: " + currState);
        if(getGameLogic().getmGThread().getCurrentState().equals(GameState.state_PauseState)){
            System.out.println("GAmePanel: Pause");
            choicesPanel[0].setVisible(false);
            revalidate();
            repaint();
        }


    }

    @Override
    public synchronized void update(){
        updateUIElements();
//        System.out.println("GamePanel: "+playerLocation);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        btnEventSwitch(e);

//        getGameLogic().actionPerformed(e);


    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Future use
    }
}
