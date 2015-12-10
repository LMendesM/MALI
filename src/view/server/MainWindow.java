package view.server;

import controller.StationJpaController;
import java.io.File;
import javax.persistence.EntityManagerFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.table.DefaultTableModel;
import model.Station;
import persistence.PersistenceSingleton;
import util.ProcessesUpdater;
import util.StationsUpdater;

public class MainWindow extends javax.swing.JFrame {

    private void configureJDialog(JDialog jDialog, String title) {
        jDialog.setModal(true);
        jDialog.setSize(800, 600);
        jDialog.setResizable(false);
        jDialog.setLocationRelativeTo(null);
        jDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jDialog.setTitle(title);
    }

    private void listStationProcesses() {
        EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();
        StationJpaController stationJpaController = new StationJpaController(emf);

        DefaultTableModel dtm = (DefaultTableModel) jTableStations.getModel();
        long selectedStation = (long) dtm.getValueAt(jTableStations.getSelectedRow(), 0);
        Station station = stationJpaController.findStation(selectedStation);
        
        jDialogStationData.setVisible(true);
        jDialogStationData.setTitle(station.getName());
        
        ProcessesUpdater processesUpdater = new ProcessesUpdater();
        processesUpdater.setjTable(jTableProcesses);
        processesUpdater.setStation(station);
        Thread thread = new Thread(processesUpdater);
        thread.start();
    }

    public MainWindow() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        configureJDialog(jDialogSettings, "Configurações");
        configureJDialog(jDialogFileChooser, "Selecione um arquivo para envio");
        configureJDialog(jDialogStationData, null);

        StationsUpdater stationsUpdater = new StationsUpdater();
        stationsUpdater.setjTable(jTableStations);
        Thread thread = new Thread(stationsUpdater);
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogSettings = new javax.swing.JDialog();
        jDesktopPaneSettingsBody = new javax.swing.JDesktopPane();
        jLabelStationsUpdateFrequency = new javax.swing.JLabel();
        jSpinnerStationsUpdateFrequency = new javax.swing.JSpinner();
        jLabelSeconds1 = new javax.swing.JLabel();
        jLabelUpdateProcessEach = new javax.swing.JLabel();
        jLabelProcessUpdateFrequency = new javax.swing.JSpinner();
        jLabelSeconds2 = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jDialogFileChooser = new javax.swing.JDialog();
        jFileChooser = new javax.swing.JFileChooser();
        jDialogStationData = new javax.swing.JDialog();
        jDesktopPaneOuterBackground = new javax.swing.JDesktopPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPaneInnerBackground1 = new javax.swing.JDesktopPane();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelMAC = new javax.swing.JLabel();
        jTextFieldMAC = new javax.swing.JTextField();
        jLabelIPv4 = new javax.swing.JLabel();
        jTextFieldIPv4 = new javax.swing.JTextField();
        jLabelIPv6 = new javax.swing.JLabel();
        jTextFieldIPv6 = new javax.swing.JTextField();
        jLabelCPUCores = new javax.swing.JLabel();
        jTextFieldCPUCores = new javax.swing.JTextField();
        jLabelTotalRAM = new javax.swing.JLabel();
        jTextFieldTotalRAM = new javax.swing.JTextField();
        jLabelFreeRAM = new javax.swing.JLabel();
        jTextFieldFreeRAM = new javax.swing.JTextField();
        jLabelOperationalSystem = new javax.swing.JLabel();
        jTextFieldOperationalSystem = new javax.swing.JTextField();
        jLabelLastLogin = new javax.swing.JLabel();
        jTextFieldLastLogin = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProcesses = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStations = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemSettings = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        jLabelStationsUpdateFrequency.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStationsUpdateFrequency.setText("Atualizar estações a cada");

        jLabelSeconds1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSeconds1.setText("seg");

        jLabelUpdateProcessEach.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUpdateProcessEach.setText("Atualizar processos a cada");

        jLabelSeconds2.setForeground(java.awt.Color.white);
        jLabelSeconds2.setText("seg");

        jButtonCancel.setText("Cancelar");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonSave.setText("Salvar");

        javax.swing.GroupLayout jDesktopPaneSettingsBodyLayout = new javax.swing.GroupLayout(jDesktopPaneSettingsBody);
        jDesktopPaneSettingsBody.setLayout(jDesktopPaneSettingsBodyLayout);
        jDesktopPaneSettingsBodyLayout.setHorizontalGroup(
            jDesktopPaneSettingsBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneSettingsBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneSettingsBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneSettingsBodyLayout.createSequentialGroup()
                        .addComponent(jLabelStationsUpdateFrequency)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinnerStationsUpdateFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSeconds1))
                    .addGroup(jDesktopPaneSettingsBodyLayout.createSequentialGroup()
                        .addComponent(jLabelUpdateProcessEach)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelProcessUpdateFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSeconds2)))
                .addContainerGap(551, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneSettingsBodyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancel)
                .addGap(18, 18, 18)
                .addComponent(jButtonSave)
                .addContainerGap())
        );
        jDesktopPaneSettingsBodyLayout.setVerticalGroup(
            jDesktopPaneSettingsBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneSettingsBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneSettingsBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStationsUpdateFrequency)
                    .addComponent(jSpinnerStationsUpdateFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSeconds1))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneSettingsBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUpdateProcessEach)
                    .addComponent(jLabelProcessUpdateFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSeconds2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                .addGroup(jDesktopPaneSettingsBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCancel))
                .addContainerGap())
        );
        jDesktopPaneSettingsBody.setLayer(jLabelStationsUpdateFrequency, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jSpinnerStationsUpdateFrequency, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jLabelSeconds1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jLabelUpdateProcessEach, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jLabelProcessUpdateFrequency, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jLabelSeconds2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jButtonCancel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSettingsBody.setLayer(jButtonSave, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDialogSettingsLayout = new javax.swing.GroupLayout(jDialogSettings.getContentPane());
        jDialogSettings.getContentPane().setLayout(jDialogSettingsLayout);
        jDialogSettingsLayout.setHorizontalGroup(
            jDialogSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneSettingsBody)
        );
        jDialogSettingsLayout.setVerticalGroup(
            jDialogSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneSettingsBody)
        );

        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogFileChooserLayout = new javax.swing.GroupLayout(jDialogFileChooser.getContentPane());
        jDialogFileChooser.getContentPane().setLayout(jDialogFileChooserLayout);
        jDialogFileChooserLayout.setHorizontalGroup(
            jDialogFileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jDialogFileChooserLayout.setVerticalGroup(
            jDialogFileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        jLabelName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName.setText("Nome");

        jTextFieldName.setEditable(false);

        jLabelMAC.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMAC.setText("MAC");

        jTextFieldMAC.setEditable(false);

        jLabelIPv4.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIPv4.setText("IPv4");

        jTextFieldIPv4.setEditable(false);

        jLabelIPv6.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIPv6.setText("IPv6");

        jTextFieldIPv6.setEditable(false);

        jLabelCPUCores.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCPUCores.setText("Núcleos da CPU");

        jTextFieldCPUCores.setEditable(false);

        jLabelTotalRAM.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalRAM.setText("RAM total");

        jTextFieldTotalRAM.setEditable(false);

        jLabelFreeRAM.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFreeRAM.setText("RAM livre");

        jTextFieldFreeRAM.setEditable(false);

        jLabelOperationalSystem.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOperationalSystem.setText("Sistema Operacional");

        jTextFieldOperationalSystem.setEditable(false);

        jLabelLastLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLastLogin.setText("Último login");

        jTextFieldLastLogin.setEditable(false);

        javax.swing.GroupLayout jDesktopPaneInnerBackground1Layout = new javax.swing.GroupLayout(jDesktopPaneInnerBackground1);
        jDesktopPaneInnerBackground1.setLayout(jDesktopPaneInnerBackground1Layout);
        jDesktopPaneInnerBackground1Layout.setHorizontalGroup(
            jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneInnerBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelOperationalSystem)
                    .addComponent(jLabelLastLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFreeRAM, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTotalRAM, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCPUCores, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelIPv6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelIPv4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMAC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldLastLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(jTextFieldName)
                    .addComponent(jTextFieldMAC)
                    .addComponent(jTextFieldIPv4)
                    .addComponent(jTextFieldIPv6)
                    .addComponent(jTextFieldCPUCores)
                    .addComponent(jTextFieldTotalRAM)
                    .addComponent(jTextFieldFreeRAM)
                    .addComponent(jTextFieldOperationalSystem))
                .addContainerGap())
        );
        jDesktopPaneInnerBackground1Layout.setVerticalGroup(
            jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneInnerBackground1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMAC)
                    .addComponent(jTextFieldMAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIPv4)
                    .addComponent(jTextFieldIPv4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIPv6)
                    .addComponent(jTextFieldIPv6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCPUCores)
                    .addComponent(jTextFieldCPUCores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalRAM)
                    .addComponent(jTextFieldTotalRAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFreeRAM)
                    .addComponent(jTextFieldFreeRAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOperationalSystem)
                    .addComponent(jTextFieldOperationalSystem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneInnerBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLastLogin)
                    .addComponent(jTextFieldLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPaneInnerBackground1.setLayer(jLabelName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelIPv4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldIPv4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelIPv6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldIPv6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelCPUCores, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldCPUCores, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelTotalRAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldTotalRAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelFreeRAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldFreeRAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelOperationalSystem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldOperationalSystem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jLabelLastLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneInnerBackground1.setLayer(jTextFieldLastLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Informações", jDesktopPaneInnerBackground1);

        jTableProcesses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "", "PID", "CMD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableProcesses);

        jTabbedPane1.addTab("Processos", jScrollPane2);

        javax.swing.GroupLayout jDesktopPaneOuterBackgroundLayout = new javax.swing.GroupLayout(jDesktopPaneOuterBackground);
        jDesktopPaneOuterBackground.setLayout(jDesktopPaneOuterBackgroundLayout);
        jDesktopPaneOuterBackgroundLayout.setHorizontalGroup(
            jDesktopPaneOuterBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jDesktopPaneOuterBackgroundLayout.setVerticalGroup(
            jDesktopPaneOuterBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jDesktopPaneOuterBackground.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDialogStationDataLayout = new javax.swing.GroupLayout(jDialogStationData.getContentPane());
        jDialogStationData.getContentPane().setLayout(jDialogStationDataLayout);
        jDialogStationDataLayout.setHorizontalGroup(
            jDialogStationDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneOuterBackground)
        );
        jDialogStationDataLayout.setVerticalGroup(
            jDialogStationDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneOuterBackground)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MALI - Monitor Acadêmico para Laboratórios de Informática");

        jTableStations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "", "Nome", "MAC", "IPv4", "IPv6", "Núcleos da CPU", "RAM Total", "RAM Livre", "Último Login", "S.O."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableStationsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableStations);

        jButton1.setText("Enviar arquivo");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuEdit.setText("Editar");

        jMenuItemSettings.setText("Configurações");
        jMenuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSettingsActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemSettings);

        jMenuBar.add(jMenuEdit);

        jMenuHelp.setText("Ajuda");

        jMenuItemAbout.setText("Sobre");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        String message = "MALI - Monitor Acadêmico para Laboratórios de Informática\n"
                + "\n"
                + "Esse aplicativo é um projeto do PIBIC-Jr. - Programa Institucional de Bolsas de Iniciação Científica Júnior\n"
                + "Desenvolvimento de aplicativo multiplataforma para controle de laboratórios de informática\n"
                + "\n"
                + "Financiamento:\n"
                + "CEFET-MG - Centro Federal de Educação Tecnológica de Minas Gerais\n"
                + "FAPEMIG - Fundação de Amparo à Pesquisa de Minas Gerais\n"
                + "\n"
                + "Autor: Lucas Mendes Martins\n"
                + "Orientador: Weider Pereira Rodrigues\n"
                + "Co-orientador: Daniel Guimarães do Lago";
        JOptionPane.showMessageDialog(this, message, "Sobre", INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSettingsActionPerformed
        jDialogSettings.setVisible(true);
    }//GEN-LAST:event_jMenuItemSettingsActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        jDialogSettings.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooserActionPerformed
        jDialogFileChooser.dispose();
        File file = jFileChooser.getSelectedFile();
        if (file != null) {
            System.out.println(file);
            //Aqui será chamada uma janela para selecionar quais estações receberão o arquivo
        }
        jFileChooser.setSelectedFile(null);
    }//GEN-LAST:event_jFileChooserActionPerformed

    private void jTableStationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStationsMouseClicked
        if (evt.getClickCount() >= 2) {
            listStationProcesses();
        }
    }//GEN-LAST:event_jTableStationsMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPaneInnerBackground1;
    private javax.swing.JDesktopPane jDesktopPaneOuterBackground;
    private javax.swing.JDesktopPane jDesktopPaneSettingsBody;
    private javax.swing.JDialog jDialogFileChooser;
    private javax.swing.JDialog jDialogSettings;
    private javax.swing.JDialog jDialogStationData;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabelCPUCores;
    private javax.swing.JLabel jLabelFreeRAM;
    private javax.swing.JLabel jLabelIPv4;
    private javax.swing.JLabel jLabelIPv6;
    private javax.swing.JLabel jLabelLastLogin;
    private javax.swing.JLabel jLabelMAC;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelOperationalSystem;
    private javax.swing.JSpinner jLabelProcessUpdateFrequency;
    private javax.swing.JLabel jLabelSeconds1;
    private javax.swing.JLabel jLabelSeconds2;
    private javax.swing.JLabel jLabelStationsUpdateFrequency;
    private javax.swing.JLabel jLabelTotalRAM;
    private javax.swing.JLabel jLabelUpdateProcessEach;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemSettings;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerStationsUpdateFrequency;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableProcesses;
    private javax.swing.JTable jTableStations;
    private javax.swing.JTextField jTextFieldCPUCores;
    private javax.swing.JTextField jTextFieldFreeRAM;
    private javax.swing.JTextField jTextFieldIPv4;
    private javax.swing.JTextField jTextFieldIPv6;
    private javax.swing.JTextField jTextFieldLastLogin;
    private javax.swing.JTextField jTextFieldMAC;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldOperationalSystem;
    private javax.swing.JTextField jTextFieldTotalRAM;
    // End of variables declaration//GEN-END:variables
}
