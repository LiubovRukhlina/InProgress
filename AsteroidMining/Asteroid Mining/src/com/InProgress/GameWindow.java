package com.InProgress;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Game game;
    private Player currentPlayer;
    private Settler currentSettler;
    private BuildWindow buildWindow;
    private TravelWindow travelWindow;
    private LeaveResourcesWindow leaveResourcesWindow;
    private JOptionPane miningMessageBox;

    Container contentPane = getContentPane();

    public GameWindow() {
        addComponent(contentPane);
        initialize();
    }

    private void addComponent(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JButton button;
        JLabel label;

        JMenu jMenu = new JMenu();


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy   = 0;

        label = new JLabel("PLAYER: ");

        constraints.gridx = 0;      // нулевая ячейка таблицы по горизонтали
        container.add(label, constraints);

        label = new JLabel("Друг!");
        constraints.gridx = 1;
        container.add(label, constraints);

        label = new JLabel("Друг!");
        constraints.gridx = 2;
        container.add(label, constraints);

        label = new JLabel("Друг!");
        constraints.gridx = 3;
        container.add(label, constraints);

        label = new JLabel("Друг!");
        constraints.gridx = 4;
        container.add(label, constraints);

        label = new JLabel("Друг!");
        constraints.gridx = 5;
        container.add(label, constraints);

       /* button = new JButton("Институт");
        constraints.gridx = 1;      // первая ячейка таблицы по горизонтали
        container.add(button, constraints);

        button = new JButton("Академия");
        constraints.gridx = 2;      // вторая ячейка таблицы по горизонтали
        container.add(button, constraints);

        button = new JButton("Высокая кнопка размером в 2 ячейки");
        constraints.ipady     = 45;   // кнопка высокая
        constraints.weightx   = 0.0;
        constraints.gridwidth = 2;    // размер кнопки в две ячейки
        constraints.gridx     = 0;    // нулевая ячейка по горизонтали
        constraints.gridy     = 1;    // первая ячейка по вертикали
        container.add(button, constraints);*/


    }

    public void initialize() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asteroid Game");
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // open in the center of a screen
        setVisible(true);
    }

    public void update() {

    }
}
