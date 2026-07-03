/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hydroponicgarden1;

import com.mycompany.hydroponicgarden1.dao.HydroponicSystemDAO;
import com.mycompany.hydroponicgarden1.model.HydroponicSystem;
import com.mycompany.hydroponicgarden1.model.User;
import com.mycompany.hydroponicgarden1.session.session;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import com.mycompany.hydroponicgarden1.model.Plant;
import com.mycompany.hydroponicgarden1.dao.PlantDAO;


/**
 *
 * @author josea
 */
public class planta extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(planta.class.getName());
    private boolean menuAbierto = false;
    private final int anchoMenu = 160;
    /**
     * Creates new form planta
     */
    public planta() {
        initComponents();
//        configurarMenu();
        getContentPane().setBackground(Color.WHITE);
        jLabel1.setForeground(java.awt.Color.decode("#3A5A40"));
        jLabel5.setForeground(java.awt.Color.decode("#3A5A40"));
        jLabel12.setForeground(java.awt.Color.decode("#3A5A40"));
        jLabel22.setForeground(java.awt.Color.decode("#3A5A40"));
        jLabel13.setText("");
        
        jTable1.getTableHeader().setUI(new javax.swing.plaf.basic.BasicTableHeaderUI());
        jTable1.getTableHeader().setBackground(new java.awt.Color(232, 245, 233)); 
        jTable1.getTableHeader().setForeground(new java.awt.Color(46, 125, 50));

       
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagen/logo.png"));

        Image img = icon.getImage().getScaledInstance(
                jLabel23.getWidth(),
                jLabel23.getHeight(),
                Image.SCALE_SMOOTH);

        jLabel23.setIcon(new ImageIcon(img));
        
        ImageIcon icono_usuario = new ImageIcon(getClass().getResource("/imagen/icono_usuario.png"));
        jLabel24.setIcon(icono_usuario);
        
        ImageIcon icono1 = new ImageIcon(getClass().getResource("/imagen/icono1.png"));
        jLabel25.setIcon(icono1);
        
        ImageIcon icono2 = new ImageIcon(getClass().getResource("/imagen/icono2.png"));
        jLabel26.setIcon(icono2);
        
        ImageIcon icono3 = new ImageIcon(getClass().getResource("/imagen/icono3.png"));
        jLabel27.setIcon(icono3);
        
        ImageIcon icono4 = new ImageIcon(getClass().getResource("/imagen/icono4.png"));
        jLabel28.setIcon(icono4);
        
        ImageIcon icono5 = new ImageIcon(getClass().getResource("/imagen/icono5.png"));
        jLabel29.setIcon(icono5);
        
        ImageIcon icono6 = new ImageIcon(getClass().getResource("/imagen/icono6.png"));
        jLabel30.setIcon(icono6);
        
        ImageIcon icono7 = new ImageIcon(getClass().getResource("/imagen/icono7.png"));
        jLabel31.setIcon(icono7);
        
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
  /* private void configurarMenu() {

    plantBtn1.addActionListener(e -> {
        if (menuAbierto) animarMenu();
    });

    homeBtn1.addActionListener(e -> {
        new Inicio().setVisible(true);
        dispose();
    });

    systemBtn1.addActionListener(e -> {
        new VentanaPrincipal().setVisible(true);
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
}*/
    private void cargarTabla() {

    HydroponicSystem system = session.getCurrentSystem();

    if (system == null) {
        JOptionPane.showMessageDialog(this, "No hay un sistema seleccionado.");
        return;
    }

    PlantDAO dao = new PlantDAO();

    ArrayList<Plant> lista = dao.getPlantsBySystem(system.getSystemId());

    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

    modelo.setRowCount(0);

    for (Plant plant : lista) {

        modelo.addRow(new Object[]{
            plant.getPlantId(),
            plant.getNamePlant(),
            plant.getVariety(),
            plant.getHarvestTimeDays(),
            plant.getPhIdealMax(),
            plant.getPhIdealMin()
        });
    }
}
    public void mostrarImagen() {

    String nombre = txtname.getText().trim().toLowerCase();

    String ruta = null;

    switch (nombre) {
        case "lechuga":
            ruta = "/imagen/lechuga.jpg";
            jLabel15.setText("Hortaliza de hoja");
            jLabel17.setText("45 - 60 días");
            jLabel19.setText("5.5 - 6.5");
            jLabel21.setText("Sí");
            jLabel32.setText("Planta de hojas verdes,");
            jLabel33.setText("ideal para sistemas hidropónicos");
            jLabel34.setText("Crecimiento rápido y alta demanda");
            jLabel35.setText("de nutrientes ");

            break;
            case "Lechuga":
            ruta = "/imagen/lechuga.jpg";
            jLabel15.setText("Hortaliza de hoja");
            jLabel17.setText("45 - 60 días");
            jLabel19.setText("5.5 - 6.5");
            jLabel21.setText("Sí");
            jLabel32.setText("Planta de hojas verdes,");
            jLabel33.setText("ideal para sistemas hidropónicos");
            jLabel34.setText("Crecimiento rápido y alta demanda");
            jLabel35.setText("de nutrientes ");

            break;

        case "jitomate":
            ruta = "/imagen/jitomate.jpg";
              jLabel15.setText("Hortaliza de fruto");
            jLabel17.setText("90 - 120 días");
            jLabel19.setText("5.8 - 6.8");
            jLabel21.setText("Sí");
            jLabel32.setText("Planta de fruto rica en licopeno.");
            jLabel33.setText("Ideal para cultivo hidropónico.");
            jLabel34.setText("Necesita abundante luz solar");
            jLabel35.setText("y un buen sistema de soporte.");
            break;
         case "Jitomate":
            ruta = "/imagen/jitomate.jpg";
              jLabel15.setText("Hortaliza de fruto");
            jLabel17.setText("90 - 120 días");
            jLabel19.setText("5.8 - 6.8");
            jLabel21.setText("Sí");
            jLabel32.setText("Planta de fruto rica en licopeno.");
            jLabel33.setText("Ideal para cultivo hidropónico.");
            jLabel34.setText("Necesita abundante luz solar");
            jLabel35.setText("y un buen sistema de soporte.");
            break;

        default:
            jLabel13.setIcon(null);
            jLabel15.setText("");
            jLabel17.setText("");
            jLabel19.setText("");
            jLabel21.setText("");
            jLabel32.setText("");

            return;
    }

    java.net.URL url = getClass().getResource(ruta);

    if (url == null) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "No se encontró la imagen:\n" + ruta);
        return;
    }

    ImageIcon icono = new ImageIcon(url);

    Image imagen = icono.getImage().getScaledInstance(
            jLabel13.getWidth(),
            jLabel13.getHeight(),
            Image.SCALE_SMOOTH);

    jLabel13.setIcon(new ImageIcon(imagen));
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnmenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        idplant = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtvariety = new javax.swing.JTextField();
        txtharvest = new javax.swing.JTextField();
        txtphmax = new javax.swing.JTextField();
        txtphmin = new javax.swing.JTextField();
        btnregister = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        panelMenu = new javax.swing.JPanel();
        systemBtn1 = new javax.swing.JButton();
        plantBtn1 = new javax.swing.JButton();
        perfilBtn1 = new javax.swing.JButton();
        monitoreoBtn1 = new javax.swing.JButton();
        solutionBtn1 = new javax.swing.JButton();
        loteBtn1 = new javax.swing.JButton();
        CloseBtn1 = new javax.swing.JButton();
        homeBtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        jLabel1.setText("Gestion de Plantas");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Registra y administra las plantas que puedes cultivar en tu huerto hidroponico");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Usuario");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Administrador");

        jLabel23.setText("jLabel23");

        jLabel24.setText("jLabel24");

        btnmenu.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N
        btnmenu.setText("☰");
        btnmenu.setBorderPainted(false);
        btnmenu.setContentAreaFilled(false);
        btnmenu.setFocusPainted(false);
        btnmenu.addActionListener(this::btnmenuActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnmenu)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(189, 189, 189))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(94, 94, 94)))
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnmenu)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

        jLabel5.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel5.setText("Registro de Planta");

        jLabel6.setText("ID Planta");

        jLabel7.setText("Nombre");

        jLabel8.setText("Variedad");

        jLabel9.setText("Tiempo de Cosecha(Dias)");

        jLabel10.setText("pH ideal Maximo:");

        jLabel11.setText("pH ideal Minimo:");

        txtname.addActionListener(this::txtnameActionPerformed);

        txtphmin.addActionListener(this::txtphminActionPerformed);

        btnregister.setBackground(new java.awt.Color(0, 153, 51));
        btnregister.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnregister.setForeground(new java.awt.Color(255, 255, 255));
        btnregister.setText("Registrar");
        btnregister.addActionListener(this::btnregisterActionPerformed);

        jLabel31.setText("jLabel31");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtphmin))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtphmax))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtharvest))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(txtvariety))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(32, 32, 32)
                        .addComponent(txtname))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(idplant, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(btnregister)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(idplant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtvariety, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtharvest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtphmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtphmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnregister)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

        jLabel12.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel12.setText("Información de la planta");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("imagen");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setText("Categoria:");

        jLabel15.setText("jLabelcategoria");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel16.setText("Ciclo promedio:");

        jLabel17.setText("jLabelciclo");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel18.setText("Nivel de pH ideal:");

        jLabel19.setText("jLabelpHideal");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel20.setText("Requiere luz:");

        jLabel21.setText("jLabelluz");

        jLabel25.setText("jLabel25");

        jLabel26.setText("jLabel26");

        jLabel27.setText("jLabel27");

        jLabel28.setText("jLabel28");

        jLabel30.setText("jLabel30");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addGap(51, 51, 51))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel14))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel15)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel27))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel28))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)))
                .addGap(19, 19, 19))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Planta", "Nombre", "Variedad", "Tiempo de Cosecha", "pH Ideal maximo", "pH ideal maximo"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jLabel22.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel22.setText("Listado de plantas Registradas");

        btndelete.setBackground(new java.awt.Color(255, 51, 51));
        btndelete.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btndelete.setForeground(new java.awt.Color(255, 255, 255));
        btndelete.setText("Borrar Historial");
        btndelete.addActionListener(this::btndeleteActionPerformed);

        jLabel29.setText("jLabel29");

        jLabel36.setText("Buscar:");

        txtBuscar.addActionListener(this::txtBuscarActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btndelete))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel22)
                    .addComponent(jLabel36)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndelete)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        panelMenu.setBackground(new java.awt.Color(0, 153, 0));
        panelMenu.setPreferredSize(new java.awt.Dimension(162, 0));

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

        CloseBtn1.setBackground(new java.awt.Color(66, 127, 53));
        CloseBtn1.setText("Cerrar Sesion");
        CloseBtn1.setBorderPainted(false);
        CloseBtn1.setFocusPainted(false);

        homeBtn1.setBackground(new java.awt.Color(66, 127, 53));
        homeBtn1.setText("Inicio");
        homeBtn1.setBorderPainted(false);
        homeBtn1.setFocusPainted(false);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(systemBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plantBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loteBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monitoreoBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(solutionBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(perfilBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CloseBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(homeBtn1)
                .addGap(18, 18, 18)
                .addComponent(systemBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plantBtn1)
                .addGap(18, 18, 18)
                .addComponent(loteBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(monitoreoBtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(solutionBtn1)
                .addGap(18, 18, 18)
                .addComponent(perfilBtn1)
                .addGap(18, 18, 18)
                .addComponent(CloseBtn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        // TODO add your handling code here:

    User user = session.getCurrentUser();

    if (user == null) {
        JOptionPane.showMessageDialog(this, "No hay una sesión activa.");
        return;
    }

    if (txtname.getText().trim().isEmpty()
            || txtvariety.getText().trim().isEmpty()
            || txtharvest.getText().trim().isEmpty()
            || txtphmax.getText().trim().isEmpty()
            || txtphmin.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this, "Completa todos los campos.");
        return;
    }

    // 🔥 SISTEMA ACTUAL
    HydroponicSystem system = session.getCurrentSystem();

    if (system == null) {
        JOptionPane.showMessageDialog(this, "No hay sistema seleccionado.");
        return;
    }

    int id = system.getSystemId();

    System.out.println("ID del sistema: " + id);

    // 🔥 CREAR PLANTA
    Plant plant = new Plant();
    plant.setNamePlant(txtname.getText().trim());
    plant.setVariety(txtvariety.getText().trim());
    plant.setHarvestTimeDays(Integer.parseInt(txtharvest.getText().trim()));
    plant.setPhIdealMax(Double.parseDouble(txtphmax.getText().trim()));
    plant.setPhIdealMin(Double.parseDouble(txtphmin.getText().trim()));

    // 🔥 AQUÍ FALTABA ESTO
    plant.setSystemId(id);

    PlantDAO dao = new PlantDAO();

    if (dao.insertPlant(plant)) {

        JOptionPane.showMessageDialog(this, "Planta registrada correctamente.");

        cargarTabla();

        txtname.setText("");
        txtvariety.setText("");
        txtharvest.setText("");
        txtphmax.setText("");
        txtphmin.setText("");

    } else {
        JOptionPane.showMessageDialog(this, "No fue posible registrar la planta.");
    }

    mostrarImagen();

    }//GEN-LAST:event_btnregisterActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:

    User user = session.getCurrentUser();

    if (user == null) {
        JOptionPane.showMessageDialog(this, "No hay sesión activa.");
        return;
    }

    HydroponicSystem system = session.getCurrentSystem();

    if (system == null) {
        JOptionPane.showMessageDialog(this, "Selecciona un sistema primero.");
        return;
    }

    String texto = txtBuscar.getText().trim();

    if (texto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Escribe algo para buscar.");
        return;
    }

    PlantDAO dao = new PlantDAO();

    ArrayList<Plant> lista =
            dao.searchPlants(
                    system.getSystemId(),
                    texto
            );

    DefaultTableModel modelo =
            (DefaultTableModel) jTable1.getModel();

    modelo.setRowCount(0);

    for (Plant plant : lista) {

        modelo.addRow(new Object[]{
            plant.getPlantId(),
            plant.getNamePlant(),
            plant.getVariety(),
            plant.getHarvestTimeDays(),
            plant.getPhIdealMin(),
            plant.getPhIdealMax()
        });
    }

    if (lista.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No se encontraron plantas.");
    }

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:

    int fila = jTable1.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una planta primero.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que quieres eliminar esta planta?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    int idPlant = Integer.parseInt(
            jTable1.getValueAt(fila, 0).toString()
    );

    PlantDAO dao = new PlantDAO();

    if (dao.deletePlant(idPlant)) {

        JOptionPane.showMessageDialog(this, "Planta eliminada correctamente.");

        // refrescar tabla
        cargarTabla();

    } else {
        JOptionPane.showMessageDialog(this, "No se pudo eliminar la planta.");
    }

         
    }//GEN-LAST:event_btndeleteActionPerformed

    private void txtphminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtphminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtphminActionPerformed

    private void systemBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_systemBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_systemBtn1ActionPerformed

    private void perfilBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_perfilBtn1ActionPerformed

    private void solutionBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solutionBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solutionBtn1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();
    idplant.setText(jTable1.getValueAt(fila, 0).toString());
    txtname.setText(jTable1.getValueAt(fila, 1).toString());
    txtvariety.setText(jTable1.getValueAt(fila, 2).toString());
    txtharvest.setText(jTable1.getValueAt(fila, 3).toString());
    txtphmax.setText(jTable1.getValueAt(fila, 4).toString());
    txtphmin.setText(jTable1.getValueAt(fila, 5).toString());
        mostrarImagen();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuActionPerformed
        // TODO add your handling code here:
        animarMenu();
    }//GEN-LAST:event_btnmenuActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseBtn1;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnmenu;
    private javax.swing.JButton btnregister;
    private javax.swing.JButton homeBtn1;
    private javax.swing.JTextField idplant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton loteBtn1;
    private javax.swing.JButton monitoreoBtn1;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JButton perfilBtn1;
    private javax.swing.JButton plantBtn1;
    private javax.swing.JButton solutionBtn1;
    private javax.swing.JButton systemBtn1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtharvest;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtphmax;
    private javax.swing.JTextField txtphmin;
    private javax.swing.JTextField txtvariety;
    // End of variables declaration//GEN-END:variables
}
