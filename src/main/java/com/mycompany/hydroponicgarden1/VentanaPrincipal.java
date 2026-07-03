/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hydroponicgarden1;
import java.awt.Image;
import javax.swing.ImageIcon;
import com.mycompany.hydroponicgarden1.dao.HydroponicSystemDAO;
import com.mycompany.hydroponicgarden1.model.HydroponicSystem;
import com.mycompany.hydroponicgarden1.session.session;
import com.mycompany.hydroponicgarden1.model.User;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Yure
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());
    private boolean menuAbierto = false;
    private final int anchoMenu = 160;
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        cargarTabla();
         jTable1.getTableHeader().setUI(new javax.swing.plaf.basic.BasicTableHeaderUI());
         jTable1.getTableHeader().setBackground(new java.awt.Color(232, 245, 233)); 
         jTable1.getTableHeader().setForeground(new java.awt.Color(46, 125, 50));
    
        this.setPreferredSize(new java.awt.Dimension(591, 650));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        panelMenu.setLocation(-anchoMenu,55);
       configurarMenu();
        
         try {
            ImageIcon icon1 = new ImageIcon(getClass().getResource("/fotos/iconoSH.jpeg"));
            Image img1 = icon1.getImage().getScaledInstance(iconoS.getWidth(), iconoS.getHeight(), Image.SCALE_SMOOTH);
            iconoS.setIcon(new ImageIcon(img1));
        } catch (Exception e) {
            System.err.println("Error 1: " + e.getMessage());
        }
    }
    private void animarMenu() {

        Timer timer = new Timer(5, null);

        timer.addActionListener(e -> {

            if (!menuAbierto) {

                // Abrir menú
                if (panelMenu.getX() < 0) {

                    panelMenu.setLocation(
                            panelMenu.getX() + 10,
                            55);

                } else {

                    panelMenu.setLocation(0,55);
                    menuAbierto = true;
                    timer.stop();
                }

            } else {

                // Cerrar menú
                if (panelMenu.getX() > -anchoMenu) {

                    panelMenu.setLocation(
                            panelMenu.getX() - 10,
                            55);

                } else {

                    panelMenu.setLocation(-anchoMenu,55);
                    menuAbierto = false;
                    timer.stop();
                }
            }
        });

        timer.start();
    }
    private void configurarMenu() {

    systemBtn1.addActionListener(e -> {
        if (menuAbierto) animarMenu();
    });

    homeBtn1.addActionListener(e -> {
        new Inicio().setVisible(true);
        dispose();
    });

    plantBtn1.addActionListener(e -> {
        new planta().setVisible(true);
        dispose();
    });

    loteBtn1.addActionListener(e -> {
        new GestionLotes().setVisible(true);
        dispose();
    });

    monitoreoBtn1.addActionListener(e -> {
        new InterfazChida().setVisible(true);
        dispose();
    });

    solutionBtn1.addActionListener(e -> {
        new SolucionNutritivaPanel().setVisible(true);
        dispose();
    });

    perfilBtn1.addActionListener(e -> {
        new Profile().setVisible(true);
        dispose();
    });

    CloseBtn1.addActionListener(e -> {
        new Login().setVisible(true);
        dispose();
    });
}
         private void cargarTabla() {

    User user = session.getCurrentUser();

    if (user == null) {
        return;
    }

    HydroponicSystemDAO dao = new HydroponicSystemDAO();

    ArrayList<HydroponicSystem> lista =
            dao.getSystemsByUser(user.getUserId());

    DefaultTableModel modelo =
            (DefaultTableModel) jTable1.getModel();

    modelo.setRowCount(0);

    for (HydroponicSystem system : lista) {

        modelo.addRow(new Object[]{
            system.getSystemId(),
            system.getCapacityLiters(),
            system.getTypeSystem(),
            system.getUbication()
        });

    }
    }
       

          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Icono = new javax.swing.JLabel();
        iconoS = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        txtUbicacion = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        homeBtn1 = new javax.swing.JButton();
        systemBtn1 = new javax.swing.JButton();
        plantBtn1 = new javax.swing.JButton();
        perfilBtn1 = new javax.swing.JButton();
        monitoreoBtn1 = new javax.swing.JButton();
        solutionBtn1 = new javax.swing.JButton();
        loteBtn1 = new javax.swing.JButton();
        homeBtn8 = new javax.swing.JButton();
        CloseBtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 2));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Sistema de Huerto");

        jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel2.setText("Administra la información de los sistemas de tu huerto hidropónico. ");

        iconoS.setText("jLabel10");

        btnMenu.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        btnMenu.setText("☰");
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setFocusPainted(false);
        btnMenu.addActionListener(this::btnMenuActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Icono)
                .addGap(118, 118, 118)
                .addComponent(iconoS, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iconoS, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Icono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 2));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 0));
        jLabel3.setText("Registro de Sistema de Huerto");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("ID Sistema:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Capacidad (litros):");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Tipo de sistema: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Ubicación:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NFT (Nutrient Film Technique)", "Aeroponía", " " }));

        jButton1.setBackground(new java.awt.Color(0, 153, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID)
                            .addComponent(txtCapacidad)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUbicacion))))
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 0), 2));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setText("Listado de Sistemas de Huerto");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Sistema", "Capacidad (L)", "Tipo de Sistema", "Ubicación"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel9.setText("Buscar:");

        txtBuscar.addActionListener(this::txtBuscarActionPerformed);

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Modificar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelMenu.setBackground(new java.awt.Color(0, 153, 0));
        panelMenu.setForeground(new java.awt.Color(0, 102, 0));

        homeBtn1.setBackground(new java.awt.Color(66, 127, 53));
        homeBtn1.setText("Inicio");
        homeBtn1.setBorderPainted(false);
        homeBtn1.setFocusPainted(false);

        systemBtn1.setBackground(new java.awt.Color(66, 127, 53));
        systemBtn1.setText("Sistema Huerto");
        systemBtn1.setBorderPainted(false);
        systemBtn1.setFocusPainted(false);
        systemBtn1.addActionListener(this::systemBtn1ActionPerformed);

        plantBtn1.setBackground(new java.awt.Color(66, 127, 53));
        plantBtn1.setText("Plantas");
        plantBtn1.setBorderPainted(false);
        plantBtn1.setFocusPainted(false);

        perfilBtn1.setBackground(new java.awt.Color(66, 127, 53));
        perfilBtn1.setText("Mi perfil");
        perfilBtn1.setBorderPainted(false);
        perfilBtn1.setFocusPainted(false);
        perfilBtn1.addActionListener(this::perfilBtn1ActionPerformed);

        monitoreoBtn1.setBackground(new java.awt.Color(66, 127, 53));
        monitoreoBtn1.setText("Monitoreo");
        monitoreoBtn1.setBorderPainted(false);
        monitoreoBtn1.setFocusPainted(false);

        solutionBtn1.setBackground(new java.awt.Color(66, 127, 53));
        solutionBtn1.setText("Solucion Nutritiva");
        solutionBtn1.setBorderPainted(false);
        solutionBtn1.setFocusPainted(false);
        solutionBtn1.addActionListener(this::solutionBtn1ActionPerformed);

        loteBtn1.setBackground(new java.awt.Color(66, 127, 53));
        loteBtn1.setText("Lote");
        loteBtn1.setBorderPainted(false);
        loteBtn1.setFocusPainted(false);

        homeBtn8.setBackground(new java.awt.Color(66, 127, 53));
        homeBtn8.setText("Inicio");
        homeBtn8.setBorderPainted(false);
        homeBtn8.setFocusPainted(false);

        CloseBtn1.setBackground(new java.awt.Color(66, 127, 53));
        CloseBtn1.setText("Cerrar Sesion");
        CloseBtn1.setBorderPainted(false);
        CloseBtn1.setFocusPainted(false);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perfilBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(systemBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plantBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monitoreoBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(solutionBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(loteBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CloseBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(homeBtn8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(homeBtn1)
                .addGap(18, 18, 18)
                .addComponent(systemBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plantBtn1)
                .addGap(18, 18, 18)
                .addComponent(loteBtn1)
                .addGap(18, 18, 18)
                .addComponent(monitoreoBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(solutionBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(perfilBtn1)
                .addGap(12, 12, 12)
                .addComponent(CloseBtn1)
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenuLayout.createSequentialGroup()
                    .addGap(198, 198, 198)
                    .addComponent(homeBtn8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(301, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        User user = session.getCurrentUser();

if (user == null) {

    JOptionPane.showMessageDialog(
            this,
            "No hay una sesión activa.");

    return;
}

if (txtCapacidad.getText().trim().isEmpty()
        || txtUbicacion.getText().trim().isEmpty()) {

    JOptionPane.showMessageDialog(
            this,
            "Completa todos los campos.");

    return;
}

HydroponicSystem system = new HydroponicSystem();

system.setCapacityLiters(
        Double.parseDouble(txtCapacidad.getText().trim()));

system.setTypeSystem(
        jComboBox1.getSelectedItem().toString());

system.setUbication(
        txtUbicacion.getText().trim());

system.setUserId(user.getUserId());

HydroponicSystemDAO dao = new HydroponicSystemDAO();

if (dao.insertSystem(system)) {

    JOptionPane.showMessageDialog(
            this,
            "Sistema registrado correctamente.");

    cargarTabla(); // Actualiza la tabla

    txtCapacidad.setText("");
    txtUbicacion.setText("");
    txtID.setText("");
    jComboBox1.setSelectedIndex(0);

} else {

    JOptionPane.showMessageDialog(
            this,
            "No fue posible registrar el sistema.");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

    int fila = jTable1.getSelectedRow();

    HydroponicSystem system = new HydroponicSystem();

    system.setSystemId(
        Integer.parseInt(jTable1.getValueAt(fila, 0).toString())
    );

    system.setCapacityLiters(
        Double.parseDouble(jTable1.getValueAt(fila, 1).toString())
    );

    system.setTypeSystem(
        jTable1.getValueAt(fila, 2).toString()
    );

    system.setUbication(
        jTable1.getValueAt(fila, 3).toString()
    );

    
    session.setCurrentSystem(system);

    // (opcional) llenar campos visuales
    txtID.setText(String.valueOf(system.getSystemId()));
    txtCapacidad.setText(String.valueOf(system.getCapacityLiters()));
    jComboBox1.setSelectedItem(system.getTypeSystem());
    txtUbicacion.setText(system.getUbication());

    JOptionPane.showMessageDialog(this, "Sistema seleccionado ✔");


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();

if (fila == -1) {
    JOptionPane.showMessageDialog(this, "Selecciona un sistema");
    return;
}

HydroponicSystem system = new HydroponicSystem();

system.setSystemId(
    Integer.parseInt(jTable1.getValueAt(fila, 0).toString())
);

system.setCapacityLiters(
    Double.parseDouble(txtCapacidad.getText())
);

system.setTypeSystem(
    jComboBox1.getSelectedItem().toString()
);

system.setUbication(txtUbicacion.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
        User user = session.getCurrentUser();

if (user == null) {
    return;
}

HydroponicSystemDAO dao = new HydroponicSystemDAO();

ArrayList<HydroponicSystem> lista =
        dao.searchSystems(
                user.getUserId(),
                txtBuscar.getText().trim());

DefaultTableModel modelo =
        (DefaultTableModel) jTable1.getModel();

modelo.setRowCount(0);

for (HydroponicSystem system : lista) {

    modelo.addRow(new Object[]{
        system.getSystemId(),
        system.getCapacityLiters(),
        system.getTypeSystem(),
        system.getUbication()
    });

}
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        animarMenu();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void systemBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_systemBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_systemBtn1ActionPerformed

    private void solutionBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solutionBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solutionBtn1ActionPerformed

    private void perfilBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBtn1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseBtn1;
    private javax.swing.JLabel Icono;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton homeBtn1;
    private javax.swing.JButton homeBtn8;
    private javax.swing.JLabel iconoS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton loteBtn1;
    private javax.swing.JButton monitoreoBtn1;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JButton perfilBtn1;
    private javax.swing.JButton plantBtn1;
    private javax.swing.JButton solutionBtn1;
    private javax.swing.JButton systemBtn1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
