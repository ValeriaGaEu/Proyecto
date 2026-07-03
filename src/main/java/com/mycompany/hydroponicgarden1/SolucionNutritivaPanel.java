package com.mycompany.hydroponicgarden1;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import com.mycompany.hydroponicgarden1.dao.NutrientSolutionDAO;
import com.mycompany.hydroponicgarden1.model.NutrientSolution;
import java.sql.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class SolucionNutritivaPanel extends JFrame {
    private NutrientSolutionDAO solutionDAO = new NutrientSolutionDAO();
    // ===== Paleta de colores (igual que SistemaHuertoPanel) =====
    private static final Color COLOR_VERDE_PRINCIPAL  = new Color(34, 139, 34);
    private static final Color COLOR_AZUL_MODIFICAR   = new Color(33, 150, 243);
    private static final Color COLOR_ROJO_ELIMINAR    = new Color(220, 53, 69);
    private static final Color COLOR_FONDO            = new Color(248, 249, 250);
    private static final Color COLOR_BORDE            = new Color(200, 220, 200);
    private static final Color COLOR_TEXTO_TITULO     = new Color(30, 100, 30);
    private static final Color COLOR_ENCABEZADO_TABLA = new Color(34, 139, 34);
    private static final Color COLOR_FILA_PAR         = Color.WHITE;
    private static final Color COLOR_FILA_IMPAR       = new Color(245, 255, 245);
    private static final Color COLOR_SELECCION        = new Color(198, 239, 206);
    private static final Color VERDE_OSCURO_SIDEBAR   = new Color(0x0E4B2E);
    private static final Color BLANCO                 = Color.WHITE;

    private static final String FUENTE = "Segoe UI";
    private static final int SIDEBAR_ANCHO = 230;

    private static class IconoSimple implements Icon {
        private final String tipo;
        private final int size;
        private final Color color;

        IconoSimple(String tipo, int size, Color color) {
            this.tipo = tipo;
            this.size = size;
            this.color = color;
        }

        @Override public int getIconWidth()  { return size; }
        @Override public int getIconHeight() { return size; }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.translate(x, y);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            int s = size;
            g2.setStroke(new BasicStroke(Math.max(1.5f, s / 9f)));

            switch (tipo) {
                case "house":
                    g2.fillPolygon(new int[]{s / 2, s - 1, 1}, new int[]{1, s / 2 + 1, s / 2 + 1}, 3);
                    g2.fillRect(s / 4, s / 2, s / 2, s / 2 - 1);
                    break;
                case "people":
                    g2.fillOval(1, s / 6, s * 2 / 5, s * 2 / 5);
                    g2.fillOval(s * 2 / 5, s / 6, s * 2 / 5, s * 2 / 5);
                    g2.fillArc(-1, s / 2, s * 3 / 5, s / 2, 0, 180);
                    g2.fillArc(s / 3, s / 2, s * 3 / 5, s / 2, 0, 180);
                    break;
                case "seedling":
                case "plant":
                case "leaf":
                    g2.fillOval(s / 5, s / 8, s * 3 / 5, s * 3 / 5);
                    g2.setStroke(new BasicStroke(1.6f));
                    g2.drawLine(s / 2, s / 2, s / 2, s - 1);
                    break;
                case "grid": {
                    int cell = s / 2 - 2;
                    g2.fillRect(1, 1, cell, cell);
                    g2.fillRect(s / 2 + 1, 1, cell, cell);
                    g2.fillRect(1, s / 2 + 1, cell, cell);
                    g2.fillRect(s / 2 + 1, s / 2 + 1, cell, cell);
                    break;
                }
                case "monitor":
                    g2.drawRoundRect(1, 1, s - 3, s * 3 / 5, 2, 2);
                    g2.fillRect(s / 2 - 2, s * 3 / 5 + 1, 4, s / 5);
                    g2.fillRect(s / 3, s - 3, s / 3, 2);
                    break;
                case "flask": {
                    Polygon p = new Polygon();
                    p.addPoint(s * 2 / 5, 1);
                    p.addPoint(s * 3 / 5, 1);
                    p.addPoint(s * 3 / 5, s / 3);
                    p.addPoint(s - 1, s - 1);
                    p.addPoint(1, s - 1);
                    p.addPoint(s * 2 / 5, s / 3);
                    g2.fillPolygon(p);
                    break;
                }
                case "person":
                    g2.fillOval(s / 3, 1, s / 3, s / 3);
                    g2.fillArc(s / 6, s / 2, s * 2 / 3, s / 2, 0, 180);
                    break;
                case "logout":
                    g2.drawRoundRect(1, 1, s * 3 / 5, s - 2, 2, 2);
                    g2.drawLine(s * 3 / 5, s / 2, s - 1, s / 2);
                    g2.drawLine(s - 4, s / 2 - 3, s - 1, s / 2);
                    g2.drawLine(s - 4, s / 2 + 3, s - 1, s / 2);
                    break;
                case "document":
                    g2.drawRoundRect(2, 1, s - 5, s - 2, 2, 2);
                    for (int ly = s / 3; ly < s - 3; ly += Math.max(3, s / 4)) {
                        g2.drawLine(4, ly, s - 4, ly);
                    }
                    break;
                case "info":
                    g2.drawOval(1, 1, s - 2, s - 2);
                    g2.fillOval(s / 2 - 1, s / 4, 2, 2);
                    g2.fillRect(s / 2 - 1, s / 2 - 1, 2, s / 3);
                    break;
                case "list":
                    for (int ly = 2; ly < s - 2; ly += Math.max(4, s / 3)) {
                        g2.fillOval(1, ly, 3, 3);
                        g2.drawLine(6, ly + 1, s - 1, ly + 1);
                    }
                    break;
                case "droplet": {
                    Polygon p = new Polygon();
                    p.addPoint(s / 2, 1);
                    p.addPoint(s - 2, s * 2 / 3);
                    p.addPoint(s / 2, s - 1);
                    p.addPoint(2, s * 2 / 3);
                    g2.fillPolygon(p);
                    break;
                }
                case "thermometer":
                    g2.fillRoundRect(s / 2 - 2, 1, 4, s * 2 / 3, 3, 3);
                    g2.fillOval(s / 2 - 4, s * 2 / 3 - 2, 8, 8);
                    break;
                case "pencil": {
                    Graphics2D gp = (Graphics2D) g2.create();
                    gp.rotate(Math.toRadians(45), s / 2.0, s / 2.0);
                    gp.fillRect(s / 2 - 2, 1, 4, s - 5);
                    gp.fillPolygon(new int[]{s / 2 - 2, s / 2 + 2, s / 2}, new int[]{s - 4, s - 4, s - 1}, 3);
                    gp.dispose();
                    break;
                }
                case "trash":
                    g2.fillRect(s / 4, s / 3, s / 2, s * 2 / 3 - 2);
                    g2.fillRect(s / 4 - 1, s / 4, s / 2 + 2, 3);
                    g2.fillRect(s / 2 - 3, 1, 6, 3);
                    break;
                case "search":
                    g2.drawOval(1, 1, s * 2 / 3, s * 2 / 3);
                    g2.drawLine(s * 2 / 3 - 1, s * 2 / 3 - 1, s - 1, s - 1);
                    break;
                case "plus":
                    g2.setStroke(new BasicStroke(Math.max(2f, s / 6f)));
                    g2.drawLine(s / 2, 2, s / 2, s - 2);
                    g2.drawLine(2, s / 2, s - 2, s / 2);
                    break;
                case "menu":
                    for (int ly = 2; ly < s; ly += Math.max(3, s / 3)) {
                        g2.drawLine(1, ly, s - 1, ly);
                    }
                    break;
                default:
                    g2.fillOval(1, 1, s - 2, s - 2);
            }
            g2.dispose();
        }
    }

    // ===== Sidebar deslizable =====
    private JPanel sidebarWrapper;
    private JPanel sidebarContenido;
    private boolean sidebarAbierto = true;
    private Timer animacionTimer;

    // ===== Componentes del formulario =====
    private JComboBox<String> cbSistemaHuerto;
    private JTextField txtFecha;
    private JTextField txtConductividad;
    private JTextField txtTemperatura;
    private JTextField txtPh;
    private JTextArea  txtObservaciones;
    private JButton btnRegistrar;
    private Integer filaEnEdicion = null;

    // ===== Tabla de historial =====
    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    private JLabel lblRangoRegistros;
    private JTextField txtBuscar;
    private int siguienteId = 2;

    // ===== Panel "Información actual" =====
    private JLabel lblUltimaFecha;
    private JLabel lblValConductividad;
    private JLabel lblValTemperatura;
    private JLabel lblValPh;
    private JLabel lblValPreparada;

    public SolucionNutritivaPanel() {
        setTitle("Solucion Nutritiva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1360, 1000);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(COLOR_FONDO);
        setContentPane(content);

        sidebarWrapper = new JPanel(new BorderLayout());
        sidebarWrapper.setBackground(VERDE_OSCURO_SIDEBAR);
        sidebarWrapper.setPreferredSize(new Dimension(SIDEBAR_ANCHO, 0));
        sidebarWrapper.setMinimumSize(new Dimension(0, 0));
        sidebarContenido = crearSidebar();
        sidebarWrapper.add(sidebarContenido, BorderLayout.CENTER);

        content.add(sidebarWrapper, BorderLayout.WEST);
        content.add(crearContenidoPrincipal(), BorderLayout.CENTER);
        cargarTabla();
    }

    // SIDEBAR IZQUIERDO (deslizable)
    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(VERDE_OSCURO_SIDEBAR);
        sidebar.setBorder(new EmptyBorder(15, 0, 15, 0));

        JButton btnCerrarMenu = new JButton(new IconoSimple("menu", 20, BLANCO));
        btnCerrarMenu.setForeground(BLANCO);
        btnCerrarMenu.setBackground(VERDE_OSCURO_SIDEBAR);
        btnCerrarMenu.setBorder(new EmptyBorder(0, 20, 25, 0));
        btnCerrarMenu.setFocusPainted(false);
        btnCerrarMenu.setContentAreaFilled(false);
        btnCerrarMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCerrarMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrarMenu.addActionListener(e -> toggleSidebar());
        sidebar.add(btnCerrarMenu);

        String[][] items = {
            {"house", "Inicio"},
            {"people", "Sistema de Huerto"},
            {"seedling", "Plantas"},
            {"grid", "Lotes"},
            {"monitor", "Monitoreo"},
            {"flask", "Solución Nutritiva"},
            {"person", "Mi Perfil"},
            {"logout", "Cerrar Sesión"}
        };

        for (String[] item : items) {
            boolean activo = item[1].equals("Solución Nutritiva");
            sidebar.add(crearItemMenu(item[0], item[1], activo));
        }

        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JPanel crearItemMenu(String tipoIcono, String texto, boolean activo) {
        JPanel item = new JPanel(new BorderLayout(10, 0));
        item.setMaximumSize(new Dimension(SIDEBAR_ANCHO, 46));
        item.setPreferredSize(new Dimension(SIDEBAR_ANCHO, 46));
        item.setBackground(activo ? new Color(0x145C38) : VERDE_OSCURO_SIDEBAR);
        item.setBorder(new EmptyBorder(0, 20, 0, 0));

        JLabel lblIcono = new JLabel(new IconoSimple(tipoIcono, 18, BLANCO));
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(BLANCO);
        lbl.setFont(new Font(FUENTE, activo ? Font.BOLD : Font.PLAIN, 14));

        JPanel contenido = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        contenido.setOpaque(false);
        contenido.add(lblIcono);
        contenido.add(lbl);
        item.add(contenido, BorderLayout.CENTER);

        item.addMouseListener(new MouseAdapter() {

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!activo) {
            item.setBackground(new Color(0x145C38));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!activo) {
            item.setBackground(VERDE_OSCURO_SIDEBAR);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        dispose();

        switch (texto) {

            case "Inicio":
                new Inicio().setVisible(true);
                break;

            case "Sistema de Huerto":
                new VentanaPrincipal().setVisible(true);
                break;

            case "Plantas":
                new planta().setVisible(true);
                break;

            case "Lotes":
                new GestionLotes().setVisible(true);
                break;

            case "Monitoreo":
                new InterfazChida().setVisible(true);
                break;

            case "Solución Nutritiva":
                new SolucionNutritivaPanel().setVisible(true);
                break;

            /*case "Mi Perfil":
                new Profile().setVisible(true);
                break;

            case "Cerrar Sesión":

                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Desea cerrar sesión?",
                        "Cerrar sesión",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    new Login().setVisible(true);
                }

                break;*/
        }
    }
});
        return item;
    }

    /** Anima el ancho del sidebar entre 0 y SIDEBAR_ANCHO (efecto deslizable). */
    private void toggleSidebar() {
        if (animacionTimer != null && animacionTimer.isRunning()) {
            animacionTimer.stop();
        }
        final boolean abrir = !sidebarAbierto;
        final int paso = abrir ? 15 : -15;

        animacionTimer = new Timer(8, null);
        animacionTimer.addActionListener(e -> {
            int anchoActual = sidebarWrapper.getPreferredSize().width;
            int nuevoAncho = anchoActual + paso;

            if (abrir && nuevoAncho >= SIDEBAR_ANCHO) {
                nuevoAncho = SIDEBAR_ANCHO;
                animacionTimer.stop();
                sidebarAbierto = true;
            } else if (!abrir && nuevoAncho <= 0) {
                nuevoAncho = 0;
                animacionTimer.stop();
                sidebarAbierto = false;
            }
            sidebarWrapper.setPreferredSize(new Dimension(nuevoAncho, sidebarWrapper.getPreferredSize().height));
            sidebarWrapper.revalidate();
        });
        animacionTimer.start();
    }


    // CONTENIDO PRINCIPAL (derecha) 

    private JPanel crearContenidoPrincipal() {
        JPanel contenedor = new JPanel(new BorderLayout(0, 0));
        contenedor.setBackground(COLOR_FONDO);
        contenedor.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        contenedor.add(crearEncabezado(), BorderLayout.NORTH);

        JPanel cuerpo = new JPanel(new BorderLayout(12, 12));
        cuerpo.setBackground(COLOR_FONDO);

        JPanel fila = new JPanel(new BorderLayout(12, 0));
        fila.setBackground(COLOR_FONDO);
        fila.add(crearPanelFormulario(), BorderLayout.CENTER);
        fila.add(crearPanelInformacion(), BorderLayout.EAST);

        cuerpo.add(fila, BorderLayout.NORTH);
        cuerpo.add(crearPanelHistorial(), BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(cuerpo);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        contenedor.add(scroll, BorderLayout.CENTER);

        return contenedor;
    }

    // Encabezado
    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout(12, 0));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

        JPanel izquierda = new JPanel(new BorderLayout(12, 0));
        izquierda.setBackground(COLOR_FONDO);

        JLabel icono = new JLabel(new IconoSimple("flask", 34, COLOR_VERDE_PRINCIPAL));
        icono.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));

        JPanel textoPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        textoPanel.setBackground(COLOR_FONDO);

        JLabel titulo = new JLabel("Solución Nutritiva");
        titulo.setFont(new Font(FUENTE, Font.BOLD, 26));
        titulo.setForeground(COLOR_TEXTO_TITULO);

        JLabel subtitulo = new JLabel("Registra y administra la información de las soluciones nutritivas preparadas para tus cultivos.");
        subtitulo.setFont(new Font(FUENTE, Font.PLAIN, 13));
        subtitulo.setForeground(new Color(100, 120, 100));

        textoPanel.add(titulo);
        textoPanel.add(subtitulo);

        izquierda.add(icono, BorderLayout.WEST);
        izquierda.add(textoPanel, BorderLayout.CENTER);

        // Botón de menú (para reabrir el sidebar aunque esté cerrado)
        JButton btnAbrirMenu = new JButton(new IconoSimple("menu", 20, COLOR_TEXTO_TITULO));
        btnAbrirMenu.setFocusPainted(false);
        btnAbrirMenu.setContentAreaFilled(false);
        btnAbrirMenu.setBorder(new EmptyBorder(0, 0, 0, 15));
        btnAbrirMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAbrirMenu.addActionListener(e -> toggleSidebar());

        JPanel izquierdaConMenu = new JPanel(new BorderLayout());
        izquierdaConMenu.setBackground(COLOR_FONDO);
        izquierdaConMenu.add(btnAbrirMenu, BorderLayout.WEST);
        izquierdaConMenu.add(izquierda, BorderLayout.CENTER);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        derecha.setBackground(COLOR_FONDO);
        JComponent avatar = crearAvatarIcono();
        JPanel textoUsuario = new JPanel(new GridLayout(2, 1));
        textoUsuario.setBackground(COLOR_FONDO);
        JLabel usuario = new JLabel("Usuario");
        usuario.setFont(new Font(FUENTE, Font.BOLD, 13));
        JLabel rol = new JLabel("Administrador");
        rol.setFont(new Font(FUENTE, Font.PLAIN, 11));
        rol.setForeground(new Color(100, 120, 100));
        textoUsuario.add(usuario);
        textoUsuario.add(rol);
        derecha.add(avatar);
        derecha.add(textoUsuario);

        panel.add(izquierdaConMenu, BorderLayout.WEST);
        panel.add(derecha, BorderLayout.EAST);
        return panel;
    }

  
    private JComponent crearAvatarIcono() {
        JComponent circulo = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 240, 220));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                new IconoSimple("person", 20, COLOR_TEXTO_TITULO)
                        .paintIcon(this, g, (getWidth() - 20) / 2, (getHeight() - 20) / 2);
            }
        };
        circulo.setPreferredSize(new Dimension(38, 38));
        return circulo;
    }

    //  Panel formulario 
    private JPanel crearPanelFormulario() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 4, 6, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        JLabel titulo = new JLabel("Registro de Solución Nutritiva", new IconoSimple("document", 16, COLOR_TEXTO_TITULO), SwingConstants.LEFT);
        titulo.setIconTextGap(8);
        titulo.setFont(new Font(FUENTE, Font.BOLD, 15));
        titulo.setForeground(COLOR_TEXTO_TITULO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        card.add(titulo, gbc);

        gbc.gridwidth = 1; gbc.weightx = 0;

        cbSistemaHuerto = new JComboBox<>(new String[]{
            "Invernadero Secundario (ID: 3)", "Invernadero Principal (ID: 1)", "Huerto Exterior (ID: 2)"
        });
        estilizarCombo(cbSistemaHuerto);

        txtFecha = crearTextField("2025-05-23");
        txtConductividad = crearTextField("1.35");
        txtTemperatura = crearTextField("22.8");
        txtPh = crearTextField("5.8");

        txtObservaciones = new JTextArea("Solución preparada con fertilizante A y B. pH ajustado con ácido fosfórico.", 3, 20);
        txtObservaciones.setFont(new Font(FUENTE, Font.PLAIN, 13));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        txtObservaciones.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        agregarCampo(card, gbc, 1, "Sistema de huerto:", cbSistemaHuerto);
        agregarCampo(card, gbc, 2, "Fecha de preparación:", txtFecha);
        agregarCampo(card, gbc, 3, "Conductividad (mS/cm):", txtConductividad);
        agregarCampo(card, gbc, 4, "Temperatura (°C):", txtTemperatura);
        agregarCampo(card, gbc, 5, "Nivel de pH:", txtPh);

        JScrollPane scrollObs = new JScrollPane(txtObservaciones);
        scrollObs.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 200), 1, true),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        agregarCampo(card, gbc, 6, "Observaciones:", scrollObs);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.insets = new Insets(14, 4, 4, 4);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelBotones.setBackground(Color.WHITE);

        btnRegistrar = crearBoton("Registrar", COLOR_VERDE_PRINCIPAL, "plus");
        btnRegistrar.addActionListener(e -> onRegistrarClick());
        panelBotones.add(btnRegistrar);
        card.add(panelBotones, gbc);

        return card;
    }
    private void cargarTabla() {

    modeloTabla.setRowCount(0);

    ArrayList<NutrientSolution> lista = solutionDAO.getAllSolutions();

    for (NutrientSolution s : lista) {

        modeloTabla.addRow(new Object[]{
            s.getSolutionId(),
            s.getSystemId(),
            s.getPreparationDate(),
            s.getConductivity(),
            s.getTemperature(),
            s.getLevelPh(),
            "",
            ""
        });

    }

}
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String etiqueta, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila; gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.insets = new Insets(6, 4, 6, 4);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font(FUENTE, Font.PLAIN, 13));
        lbl.setPreferredSize(new Dimension(160, 28));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(campo, gbc);
    }

    private JTextField crearTextField(String texto) {
        JTextField tf = new JTextField(texto, 22);
        tf.setFont(new Font(FUENTE, Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        tf.setPreferredSize(new Dimension(280, 32));
        return tf;
    }

    private void estilizarCombo(JComboBox<?> combo) {
        combo.setFont(new Font(FUENTE, Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 200), 1));
        combo.setPreferredSize(new Dimension(280, 32));
    }

    //Botón 
    private JButton crearBoton(String texto, Color color) {
        return crearBoton(texto, color, null);
    }

    private JButton crearBoton(String texto, Color color, String tipoIcono) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? color.darker()
                        : getModel().isRollover() ? color.brighter() : color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        if (tipoIcono != null) {
            btn.setIcon(new IconoSimple(tipoIcono, 14, Color.WHITE));
            btn.setIconTextGap(8);
        }
        btn.setFont(new Font(FUENTE, Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 38));
        return btn;
    }

    // Registra 
   private void onRegistrarClick() {

    try {

        NutrientSolution solution = new NutrientSolution();

        String sistema = cbSistemaHuerto.getSelectedItem().toString();

        int idSystem = Integer.parseInt(
                sistema.substring(
                        sistema.indexOf("ID:") + 3,
                        sistema.indexOf(")")
                ).trim()
        );

        solution.setSystemId(idSystem);

        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        java.util.Date fecha = formato.parse(txtFecha.getText().trim());

        solution.setPreparationDate(new java.sql.Date(fecha.getTime()));

        solution.setConductivity(
                Double.parseDouble(txtConductividad.getText().trim())
        );

        solution.setTemperature(
                Double.parseDouble(txtTemperatura.getText().trim())
        );

        solution.setLevelPh(
                Double.parseDouble(txtPh.getText().trim())
        );

        Object id = btnRegistrar.getClientProperty("idSolution");

        boolean correcto;

        if (id == null) {

            correcto = solutionDAO.registerSolution(solution);

        } else {

            solution.setSolutionId((Integer) id);
            correcto = solutionDAO.updateSolution(solution);

        }

        if (correcto) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registro guardado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            btnRegistrar.putClientProperty("idSolution", null);
            btnRegistrar.setText("Registrar");

            filaEnEdicion = null;

            limpiarFormulario();

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible guardar el registro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Verifica los datos.\nLa fecha debe tener el formato dd/MM/yyyy.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        ex.printStackTrace();

    }

}
    private void limpiarFormulario() {
        txtFecha.setText("");
        txtConductividad.setText("");
        txtTemperatura.setText("");
        txtPh.setText("");
        txtObservaciones.setText("");
    }

    // Panel información 
    private JPanel crearPanelInformacion() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(260, 0));

        JPanel tituloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        tituloPanel.setBackground(Color.WHITE);
        tituloPanel.setAlignmentX(LEFT_ALIGNMENT);
        JLabel tituloInfo = new JLabel("Información actual", new IconoSimple("info", 16, COLOR_TEXTO_TITULO), SwingConstants.LEFT);
        tituloInfo.setIconTextGap(8);
        tituloInfo.setFont(new Font(FUENTE, Font.BOLD, 14));
        tituloInfo.setForeground(COLOR_TEXTO_TITULO);
        tituloPanel.add(tituloInfo);
        card.add(tituloPanel);
        card.add(Box.createVerticalStrut(16));

        lblUltimaFecha = new JLabel("23/05/2025 10:15 AM");
        lblValConductividad = new JLabel("1.35 mS/cm");
        lblValTemperatura = new JLabel("22.8 °C");
        lblValPh = new JLabel("5.8");
        lblValPreparada = new JLabel("Invernadero Secundario");

        String[] etiquetas = {"Última solución registrada", "Conductividad", "Temperatura", "Nivel de pH", "Preparada para"};
        String[] iconos = {"flask", "droplet", "thermometer", "droplet", "plant"};
        JLabel[] valores = {lblUltimaFecha, lblValConductividad, lblValTemperatura, lblValPh, lblValPreparada};

        for (int i = 0; i < etiquetas.length; i++) {
            card.add(crearFilaInfo(iconos[i], etiquetas[i], valores[i]));
            if (i < etiquetas.length - 1) card.add(Box.createVerticalStrut(10));
        }

        return card;
    }

    private JPanel crearFilaInfo(String tipoIcono, String etiqueta, JLabel valorLabel) {
        JPanel fila = new JPanel(new BorderLayout(8, 0));
        fila.setBackground(new Color(245, 255, 245));
        fila.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 230, 200), 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        fila.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lblIcono = new JLabel(new IconoSimple(tipoIcono, 18, COLOR_VERDE_PRINCIPAL));
        fila.add(lblIcono, BorderLayout.WEST);

        JPanel textosPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        textosPanel.setBackground(new Color(245, 255, 245));

        JLabel etiquetaLabel = new JLabel(etiqueta);
        etiquetaLabel.setFont(new Font(FUENTE, Font.PLAIN, 11));
        etiquetaLabel.setForeground(new Color(100, 130, 100));

        valorLabel.setFont(new Font(FUENTE, Font.BOLD, 13));
        valorLabel.setForeground(COLOR_TEXTO_TITULO);

        textosPanel.add(etiquetaLabel);
        textosPanel.add(valorLabel);
        fila.add(textosPanel, BorderLayout.CENTER);
        return fila;
    }

    private void actualizarInformacionActual(String sistema, String fecha, String conductividad, String temperatura, String ph) {
        lblUltimaFecha.setText(fecha);
        lblValConductividad.setText(conductividad + " mS/cm");
        lblValTemperatura.setText(temperatura + " °C");
        lblValPh.setText(ph);
        lblValPreparada.setText(sistema);
    }

    // Panel historial 
    private JPanel crearPanelHistorial() {
        JPanel card = new JPanel(new BorderLayout(0, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JLabel titulo = new JLabel("Historial de Soluciones Nutritivas", new IconoSimple("list", 16, COLOR_TEXTO_TITULO), SwingConstants.LEFT);
        titulo.setIconTextGap(8);
        titulo.setFont(new Font(FUENTE, Font.BOLD, 14));
        titulo.setForeground(COLOR_TEXTO_TITULO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        card.add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBackground(Color.WHITE);

        JPanel controles = new JPanel(new BorderLayout());
        controles.setBackground(Color.WHITE);

        lblRangoRegistros = new JLabel();
        lblRangoRegistros.setFont(new Font(FUENTE, Font.PLAIN, 13));
        lblRangoRegistros.setForeground(new Color(90, 90, 90));

        JPanel der = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        der.setBackground(Color.WHITE);
        JLabel buscarLbl = new JLabel("Buscar:", new IconoSimple("search", 14, new Color(90, 90, 90)), SwingConstants.LEFT);
        buscarLbl.setIconTextGap(5);
        buscarLbl.setFont(new Font(FUENTE, Font.PLAIN, 13));
        txtBuscar = new JTextField(15);
        txtBuscar.setFont(new Font(FUENTE, Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 200), 1, true),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        txtBuscar.setPreferredSize(new Dimension(180, 30));
        der.add(buscarLbl);
        der.add(txtBuscar);

        controles.add(lblRangoRegistros, BorderLayout.WEST);
        controles.add(der, BorderLayout.EAST);
        centro.add(controles, BorderLayout.NORTH);

        String[] columnas = {"ID", "Sistema de Huerto", "Fecha de Preparación", "Conductividad (mS/cm)", "Temperatura (°C)", "Nivel de pH", "Observaciones", "Acciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return col == 7; }
        };
        modeloTabla.addTableModelListener(e -> actualizarRangoRegistros());

        tablaHistorial = new JTable(modeloTabla) {
            @Override
            public Component prepareRenderer(TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (col != 7) { // la columna de acciones tiene su propio color
                    c.setBackground(isRowSelected(row) ? COLOR_SELECCION
                            : (row % 2 == 0 ? COLOR_FILA_PAR : COLOR_FILA_IMPAR));
                    c.setForeground(isRowSelected(row) ? COLOR_TEXTO_TITULO : Color.DARK_GRAY);
                }
                return c;
            }
        };
        tablaHistorial.setFont(new Font(FUENTE, Font.PLAIN, 13));
        tablaHistorial.setRowHeight(36);
        tablaHistorial.setShowVerticalLines(false);
        tablaHistorial.setGridColor(new Color(225, 240, 225));
        tablaHistorial.setSelectionBackground(COLOR_SELECCION);
        tablaHistorial.setSelectionForeground(COLOR_TEXTO_TITULO);
        tablaHistorial.getTableHeader().setFont(new Font(FUENTE, Font.BOLD, 13));
        tablaHistorial.getTableHeader().setBackground(COLOR_ENCABEZADO_TABLA);
        tablaHistorial.getTableHeader().setForeground(Color.WHITE);
        tablaHistorial.getTableHeader().setPreferredSize(new Dimension(0, 38));
        tablaHistorial.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tablaHistorial.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tablaHistorial.getColumnModel().getColumn(3).setCellRenderer(centrado);
        tablaHistorial.getColumnModel().getColumn(4).setCellRenderer(centrado);
        tablaHistorial.getColumnModel().getColumn(5).setCellRenderer(centrado);

        tablaHistorial.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaHistorial.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaHistorial.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaHistorial.getColumnModel().getColumn(6).setPreferredWidth(220);

        // Columna "Acciones": botones reales de Editar / Eliminar por fila
        tablaHistorial.getColumnModel().getColumn(7).setCellRenderer(new AccionesRenderer());
        tablaHistorial.getColumnModel().getColumn(7).setCellEditor(new AccionesEditor());
        tablaHistorial.getColumnModel().getColumn(7).setPreferredWidth(190);

        JScrollPane scroll = new JScrollPane(tablaHistorial);
        scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDE, 1, true));
        scroll.setPreferredSize(new Dimension(0, 240));
        centro.add(scroll, BorderLayout.CENTER);

        card.add(centro, BorderLayout.CENTER);

        actualizarRangoRegistros();
        return card;
    }

    private void actualizarRangoRegistros() {
        int total = modeloTabla.getRowCount();
        int hasta = Math.min(10, total);
        int desde = total == 0 ? 0 : 1;
        lblRangoRegistros.setText("Mostrando registros del " + desde + " al " + hasta + " de " + total);
    }

    private void editarFila(int fila) {
        int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
        if (fila < 0 || fila >= modeloTabla.getRowCount()) return;
        txtFecha.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
        txtConductividad.setText(String.valueOf(modeloTabla.getValueAt(fila, 3)));
        txtTemperatura.setText(String.valueOf(modeloTabla.getValueAt(fila, 4)));
        txtPh.setText(String.valueOf(modeloTabla.getValueAt(fila, 5)));
        txtObservaciones.setText(String.valueOf(modeloTabla.getValueAt(fila, 6)));

        filaEnEdicion = fila;
        btnRegistrar.setText("Actualizar"); btnRegistrar.setIcon(new IconoSimple("pencil", 14, Color.WHITE));
        btnRegistrar.putClientProperty("idSolution", id);
    }

    private void eliminarFila(int fila) {
        if (fila < 0 || fila >= modeloTabla.getRowCount()) return;
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar el registro seleccionado?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
      if (confirmacion == JOptionPane.YES_OPTION) {

    int id = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());

    if (solutionDAO.deleteSolution(id)) {

        modeloTabla.removeRow(fila);

        if (filaEnEdicion != null && filaEnEdicion == fila) {
            filaEnEdicion = null;
            btnRegistrar.setText("Registrar");
            btnRegistrar.setIcon(new IconoSimple("plus", 14, Color.WHITE));
            limpiarFormulario();
        }

        actualizarRangoRegistros();

        JOptionPane.showMessageDialog(
                this,
                "Solución eliminada correctamente."
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No fue posible eliminar la solución."
        );

    }
}
    }

   
    private class AccionesRenderer extends JPanel implements TableCellRenderer {
        AccionesRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
            setOpaque(true);
            add(crearBotonTabla("Editar", COLOR_AZUL_MODIFICAR));
            add(crearBotonTabla("Eliminar", COLOR_ROJO_ELIMINAR));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(isSelected ? COLOR_SELECCION
                    : (row % 2 == 0 ? COLOR_FILA_PAR : COLOR_FILA_IMPAR));
            return this;
        }
    }

    private class AccionesEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private int filaActual;

        AccionesEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
            panel.setOpaque(true);

            JButton btnEditar = crearBotonTabla("Editar", COLOR_AZUL_MODIFICAR);
            JButton btnEliminar = crearBotonTabla("Eliminar", COLOR_ROJO_ELIMINAR);

            btnEditar.addActionListener(e -> {
                fireEditingStopped();
                editarFila(filaActual);
            });
            btnEliminar.addActionListener(e -> {
                fireEditingStopped();
                eliminarFila(filaActual);
            });

            panel.add(btnEditar);
            panel.add(btnEliminar);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            filaActual = row;
            panel.setBackground(COLOR_SELECCION);
            return panel;
        }

        @Override
        public Object getCellEditorValue() { return ""; }
    }


    private JButton crearBotonTabla(String texto, Color color) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? color.darker()
                        : getModel().isRollover() ? color.brighter() : color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setIcon(new IconoSimple(texto.equals("Editar") ? "pencil" : "trash", 12, Color.WHITE));
        btn.setIconTextGap(5);
        btn.setFont(new Font(FUENTE, Font.BOLD, 11));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(84, 26));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new SolucionNutritivaPanel().setVisible(true);
        });
    }
}