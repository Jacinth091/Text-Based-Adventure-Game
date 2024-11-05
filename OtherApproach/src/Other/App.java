package Other;


import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;


public class App extends JFrame {
    private Utility util;
    private GameScreen gs;
    private UI ui;

    private JPanel screenContainer;

    public App(GameScreen gs, UI ui) {
        this.gs = gs;
        this.ui = ui;
//        this.setPreferredSize(new Dimension(, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        util = new Utility();

        initComponents();


    }

    public App(int width, int height, String title) {
        this.setPreferredSize(new Dimension(width, height));
        this.setTitle(title);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        initComponents();

    }

    public void initComponents(){



        screenContainer = new JPanel();
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
        screenContainer.add(gs, gbc);

        // Bottom component (ui)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0;            // No extra vertical space, stays at the bottom
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill only horizontally
        screenContainer.add(ui, gbc);

        add(screenContainer);

        revalidate();
        pack();


    }


}
