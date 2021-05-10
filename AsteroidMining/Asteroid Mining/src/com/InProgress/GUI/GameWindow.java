package com.InProgress.GUI;

import com.InProgress.Model.*;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends javax.swing.JFrame {

    public static  String resource;


    ImageIcon imageAsteroid = new ImageIcon("InProgress\\AsteroidMining\\Asteroid Mining\\Symbols\\Settler.png");
    ImageIcon imageGateActive = new ImageIcon("InProgress\\AsteroidMining\\Asteroid Mining\\Symbols\\Gate_Active.png");
    //ImageIcon imageAsteroid = new ImageIcon("InProgress\\AsteroidMining\\Asteroid Mining\\Symbols\\Settler.png");

     * Creates new form MainWindow
     */
    public GameWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
        labelPic.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        Asteroid asteroid = Game.getActiveSettler().getCurrentPosition();
        jPanel1 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage((Image) imageAsteroid.getImage(), 1 * 50, 1 * 50, this);
                g.drawImage((Image) imageGateActive.getImage(), 5 * 50, 5 * 50, this);
            }
        };
        CurrentPlayer = new javax.swing.JLabel();
        TravelButton = new javax.swing.JButton();
        CurrentAsteroidLabel = new javax.swing.JLabel();
        HollowLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GatesList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        InventoryList = new javax.swing.JList<>();
        StatusLabel = new javax.swing.JLabel();
        ActiveSettlerLabel = new javax.swing.JLabel();
        DeathLabel = new javax.swing.JLabel();
        HiddenLabel = new javax.swing.JLabel();
        AsteroidBeltLabel = new javax.swing.JLabel();
        MiningStatusLabel = new javax.swing.JLabel();
        GateLabel = new javax.swing.JLabel();
        PerihelionLabel = new javax.swing.JLabel();
        DestinationLabel = new javax.swing.JLabel();
        ResourceLabel = new javax.swing.JLabel();
        NumSettlerLabel = new javax.swing.JLabel();
        NumRobotLabel = new javax.swing.JLabel();
        NumGatesLabel = new javax.swing.JLabel();
        NumAsteroidsLabel = new javax.swing.JLabel();
        SunStormLabel = new javax.swing.JLabel();
        ResourcesListLabel = new javax.swing.JLabel();
        GatesLabel = new javax.swing.JLabel();
        FastTravelButton = new javax.swing.JButton();
        DrillButton = new javax.swing.JButton();
        MineButton = new javax.swing.JButton();
        LeaveButton = new javax.swing.JButton();
        PickupButton = new javax.swing.JButton();
        BuildButton = new javax.swing.JButton();
        FinishButton = new javax.swing.JButton();
        labelPic = new javax.swing.JLabel();
        SettlersListLabel = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Start = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));


        CurrentPlayerLabel.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        CurrentPlayerLabel.setForeground(new java.awt.Color(51, 204, 0));
        CurrentPlayerLabel.setText("Player:" + Game.getCurrentPlayer().getPlayerID());



        TravelButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        TravelButton.setText("Travel");
        TravelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TravelButtonActionPerformed(evt);
            }
        });

        CurrentAsteroidLabel.setFont(new java.awt.Font("Consolas", 1, 28)); // NOI18N
        CurrentAsteroidLabel.setForeground(new java.awt.Color(51, 204, 0));
        CurrentAsteroidLabel.setText("Asteroid: " + asteroid.toString());

        HollowLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        HollowLabel.setForeground(new java.awt.Color(51, 204, 0));
        if (asteroid.getHollow())
            HollowLabel.setText("Hollow: True");
        else
            HollowLabel.setText("Hollow: False");
        Inventory inv = Game.getActiveSettler().getItsInventory();


        inv.addResource(new Iron("Iron"));
        inv.addResource(new Iron("Iron"));
        inv.addResource(new WaterIce("WaterIce"));
        inv.addResource(new Uranium("Uranium"));
        inv.addResource(new Carbon("Carbon"));
        Game.getActiveSettler().setItsInventory(inv);

        if(inv.getStoredGates().size() != 0) {

            GatesList.setModel(new javax.swing.AbstractListModel<String>() {


                public int getSize() {
                    return 2;
                }

                public String getElementAt(int i) {
                    return "Gate"+i+1;
                }
            });

        }
        else
        {
            GatesList.setModel(new javax.swing.AbstractListModel<String>() {


                public int getSize() {
                    return 2;
                }

                public String getElementAt(int i) {
                    return " ";
                }
            });

        }
        jScrollPane1.setViewportView(GatesList);

        InventoryList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return inv.getStoredResources().size(); }
            public String getElementAt(int i) { return inv.getStoredResources().get(i).getResourceType(); }
        });
        jScrollPane2.setViewportView(InventoryList);

        StatusLabel.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        StatusLabel.setForeground(new java.awt.Color(51, 204, 0));
        StatusLabel.setText("Status");

        ActiveSettlerLabel.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        ActiveSettlerLabel.setForeground(new java.awt.Color(51, 204, 0));
        ActiveSettlerLabel.setText("Settler1");

        DeathLabel.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        DeathLabel.setForeground(new java.awt.Color(51, 204, 0));
        if(Game.getActiveSettler().getAlive())
            DeathLabel.setText("Alive");
        else
            DeathLabel.setText("Dead");

        HiddenLabel.setFont(new java.awt.Font("Consolas", 1, 20)); // NOI18N
        HiddenLabel.setForeground(new java.awt.Color(51, 204, 0));
        if(Game.getActiveSettler().getHidden())
            HiddenLabel.setText("Hidden");
        else
            HiddenLabel.setText("Unhidden");

        AsteroidBeltLabel.setFont(new java.awt.Font("Consolas", 1, 28)); // NOI18N
        AsteroidBeltLabel.setForeground(new java.awt.Color(51, 204, 0));
        AsteroidBeltLabel.setText("Asteroid Belt ");

        MiningStatusLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        MiningStatusLabel.setForeground(new java.awt.Color(51, 204, 0));
        if((asteroid.getDepth() == 0) && !asteroid.getHollow())
            MiningStatusLabel.setText("Mineable: Yes");
        else
            MiningStatusLabel.setText("Mineable:No"+asteroid.getDepth());

        GateLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        GateLabel.setForeground(new java.awt.Color(51, 204, 0));
        if(asteroid.getHasGate())
            GateLabel.setText("Gate: True");
        else
            GateLabel.setText("Gate: False ");

        PerihelionLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        PerihelionLabel.setForeground(new java.awt.Color(51, 204, 0));
        if(asteroid.getAtPerihelion())
            PerihelionLabel.setText("Perihelion: True");
        else
            PerihelionLabel.setText("Perihelion: False");

        DestinationLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        DestinationLabel.setForeground(new java.awt.Color(51, 204, 0));
        if(asteroid.getHasGate())
            DestinationLabel.setText("Destination:" + Game.getActiveSettler().getCurrentPosition().getGate().getPair().getCurrentPosition().toString());

        ResourceLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        ResourceLabel.setForeground(new java.awt.Color(51, 204, 0));
        try
        {
            ResourceBase resource = asteroid.getStoredResourceOfAsteroid().get(0);
            String res = resource.getResourceType();
            ResourceLabel.setText(res);
        }
        catch (IndexOutOfBoundsException ex)
        {
            ResourceLabel.setText("None");
        }

        NumSettlerLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        NumSettlerLabel.setForeground(new java.awt.Color(51, 204, 0));
        NumSettlerLabel.setText("Settler:");

        NumRobotLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        NumRobotLabel.setForeground(new java.awt.Color(51, 204, 0));
        NumRobotLabel.setText("Robot: ");

        NumGatesLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        NumGatesLabel.setForeground(new java.awt.Color(51, 204, 0));
        NumGatesLabel.setText("Gates:");

        NumAsteroidsLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        NumAsteroidsLabel.setForeground(new java.awt.Color(51, 204, 0));
        NumAsteroidsLabel.setText("Asteroids:");

        SunStormLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        SunStormLabel.setForeground(new java.awt.Color(51, 204, 0));
        SunStormLabel.setText("SunStorm:");

        ResourcesListLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        ResourcesListLabel.setForeground(new java.awt.Color(51, 204, 0));
        ResourcesListLabel.setText("Resources");

        GatesLabel.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        GatesLabel.setForeground(new java.awt.Color(51, 204, 0));
        GatesLabel.setText("Gates");

        FastTravelButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        FastTravelButton.setText("FastTravel");
        FastTravelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FastTravelButtonActionPerformed(evt);
            }
        });

        DrillButton.setFont(new java.awt.Font("Consolas", 0, 18)); //NOI18N
        DrillButton.setText("Drill");
        DrillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrillButtonActionPerformed(evt);
            }
        });

        MineButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        MineButton.setText("Mine");
        MineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MineButtonActionPerformed(evt);
            }
        });

        LeaveButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        LeaveButton.setText("Leave");
        LeaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveButtonActionPerformed(evt);
            }
        });

        PickupButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        PickupButton.setText("PickUp");
        PickupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PickupButtonActionPerformed(evt);
            }
        });

        BuildButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        BuildButton.setText("Build");
        BuildButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuildButtonActionPerformed(evt);
            }
        });

        FinishButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        FinishButton.setText("Finish");
        FinishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishButtonActionPerformed(evt);
            }
        });

        labelPic.setText("Hello!");
        labelPic.setMaximumSize(new java.awt.Dimension(46, 46));
        labelPic.setMinimumSize(new java.awt.Dimension(46, 46));
        labelPic.setName("labelPic"); // NOI18N

        SettlersListLabel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(TravelButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(FastTravelButton))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(CurrentPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(ActiveSettlerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(SettlersListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(DeathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(HiddenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(68, 68, 68))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(DrillButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(MineButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(LeaveButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(PickupButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BuildButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(FinishButton))))
                                        .addComponent(labelPic, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(106, 106, 106)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(GateLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(DestinationLabel)))
                                                .addGap(141, 141, 141))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(PerihelionLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(NumSettlerLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(NumRobotLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(NumGatesLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(NumAsteroidsLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(SunStormLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(CurrentAsteroidLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(MiningStatusLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(AsteroidBeltLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(ResourceLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(HollowLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(ResourcesListLabel)
                                                                .addGap(105, 105, 105)
                                                                .addComponent(GatesLabel)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CurrentPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CurrentAsteroidLabel)
                                        .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ActiveSettlerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DeathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(HiddenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SettlersListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(labelPic, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(TravelButton)
                                                        .addComponent(FastTravelButton)
                                                        .addComponent(DrillButton)
                                                        .addComponent(MineButton)
                                                        .addComponent(LeaveButton)
                                                        .addComponent(PickupButton)
                                                        .addComponent(BuildButton)
                                                        .addComponent(FinishButton)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(MiningStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(HollowLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(GateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(DestinationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(11, 11, 11)
                                                .addComponent(PerihelionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(ResourceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(AsteroidBeltLabel)
                                                .addGap(20, 20, 20)
                                                .addComponent(NumSettlerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(NumRobotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(NumGatesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(NumAsteroidsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(SunStormLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ResourcesListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(GatesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        jMenuBar1.setForeground(new java.awt.Color(153, 153, 153));

        jMenu1.setText("Menu");

        Start.setText("Start New Game");
        jMenu1.add(Start);

        Exit.setText("Exit");
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>

    private void TravelButtonActionPerformed(java.awt.event.ActionEvent evt) {

        TravelWindow travelWindow = new TravelWindow(this);
        travelWindow.initialize(this);

        labelPic.setIcon(imageAsteroid);
       
    }

    private void FastTravelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void DrillButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Game.getActiveSettler().drill(Game.getActiveSettler().getCurrentPosition());
        setVisible(false);
        dispose();
        GameWindow gameWindow = new GameWindow();
        gameWindow.initialize();

    }

    private void MineButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Game.getActiveSettler().mine(Game.getActiveSettler().getCurrentPosition());
        MineMessage message = new MineMessage();
        setVisible(false);
        dispose();
        message.initialize();


    }

    private void LeaveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        LeaveResourcesWindow leave = new LeaveResourcesWindow(this);
        leave.initialize(this);

    }

    private void PickupButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Game.getActiveSettler().pickUpResources();
        PickUpMessage message = new PickUpMessage(resource,this);
        message.initialize(resource,this);
    }

    private void BuildButtonActionPerformed(java.awt.event.ActionEvent evt) {

        BuildWindow build = new BuildWindow(this);
        build.initialize(this);//2
        // TODO add your handling code here:
    }

    private void FinishButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    public void initialize() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }
    public static void infobox(String message,String title)
    {
        JOptionPane.showMessageDialog(null,message,title, JOptionPane.INFORMATION_MESSAGE);
    }
    // Variables declaration - do not modify
    private javax.swing.JLabel ActiveSettlerLabel;
    private javax.swing.JLabel AsteroidBeltLabel;
    private javax.swing.JButton BuildButton;
    private javax.swing.JLabel CurrentAsteroidLabel;
    private javax.swing.JLabel CurrentPlayer;
    private javax.swing.JLabel DeathLabel;
    private javax.swing.JLabel DestinationLabel;
    private javax.swing.JButton DrillButton;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JButton FastTravelButton;
    private javax.swing.JButton FinishButton;
    private javax.swing.JLabel GateLabel;
    private javax.swing.JLabel GatesLabel;
    private javax.swing.JLabel HiddenLabel;
    private javax.swing.JLabel HollowLabel;
    private javax.swing.JButton LeaveButton;
    private javax.swing.JButton MineButton;
    private javax.swing.JLabel MiningStatusLabel;

    private javax.swing.JList<String> GatesList;
    private javax.swing.JList<String> InventoryList;

    private javax.swing.JLabel NumAsteroidsLabel;
    private javax.swing.JLabel NumGatesLabel;
    private javax.swing.JLabel NumRobotLabel;
    private javax.swing.JLabel NumSettlerLabel;
    private javax.swing.JLabel PerihelionLabel;
    private javax.swing.JButton PickupButton;
    private javax.swing.JLabel ResourceLabel;
    private javax.swing.JLabel ResourcesListLabel;
    private javax.swing.JComboBox<String> SettlersListLabel;
    private javax.swing.JMenuItem Start;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JLabel SunStormLabel;
    private javax.swing.JButton TravelButton;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelPic;
    // End of variables declaration
}
