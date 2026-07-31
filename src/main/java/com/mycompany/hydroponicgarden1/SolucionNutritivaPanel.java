package com.mycompany.hydroponicgarden1;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import com.mycompany.hydroponicgarden1.dao.NutrientSolutionDAO;
import com.mycompany.hydroponicgarden1.model.NutrientSolution;
import com.mycompany.hydroponicgarden1.dao.HydroponicSystemDAO;
import com.mycompany.hydroponicgarden1.model.HydroponicSystem;
import com.mycompany.hydroponicgarden1.session.session;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.sql.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class SolucionNutritivaPanel extends JFrame {
    private NutrientSolutionDAO solutionDAO = new NutrientSolutionDAO();
    // ===== Paleta de colores (igual que SistemaHuertoPanel) =====
    private static final Color COLOR_VERDE_PRINCIPAL  = new Color(0x2E7D32); // verde de botón Registrar / título / sidebar
    private static final Color COLOR_AZUL_MODIFICAR   = new Color(0x1565C0); // azul de botón Modificar
    private static final Color COLOR_ROJO_ELIMINAR    = new Color(0xD32F2F); // rojo de botón Eliminar
    private static final Color COLOR_FONDO            = new Color(0xF6F8F7); // fondo general de la página
    private static final Color COLOR_BORDE            = new Color(0xE8E9E8); // borde de las tarjetas
    private static final Color COLOR_TEXTO_TITULO     = new Color(0x2E7D32); // texto verde de títulos y valores
    private static final Color COLOR_SUBTITULO        = new Color(0x6B7280); // gris del subtítulo del encabezado
    private static final Color COLOR_ENCABEZADO_TABLA = new Color(0xF6F9F7); // fondo (casi blanco) del encabezado de tabla
    private static final Color COLOR_TEXTO_ENCABEZADO_TABLA = new Color(0x37474F); // texto oscuro del encabezado de tabla
    private static final Color COLOR_TEXTO_FILA       = new Color(0x333333); // texto de las filas de la tabla
    private static final Color COLOR_FILA_PAR         = Color.WHITE;
    private static final Color COLOR_FILA_IMPAR       = new Color(0xE8F5E9); // verde clarito de filas alternas
    private static final Color COLOR_SELECCION        = new Color(198, 239, 206);
    private static final Color COLOR_PLACEHOLDER_IMG  = new Color(0xE6F0E6); // caja tipo imagen del panel de información
    private static final Color COLOR_PLACEHOLDER_BORDE = new Color(0xDDE3E8);
    private static final Color COLOR_AVATAR_BG        = new Color(0xB4DCB4);
    private static final Color VERDE_OSCURO_SIDEBAR   = new Color(0x2E7D32); // fondo del sidebar (mismo verde principal)
    private static final Color VERDE_ITEM_ACTIVO      = new Color(0x1B5E20); // ítem activo del sidebar
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
    private ArrayList<HydroponicSystem> sistemas;

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

    sidebarContenido = crearSidebar();

    sidebarWrapper.add(sidebarContenido, BorderLayout.CENTER);

    content.add(sidebarWrapper, BorderLayout.WEST);

    content.add(crearContenidoPrincipal(), BorderLayout.CENTER);

    cargarTabla();
}


    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(VERDE_OSCURO_SIDEBAR);
        sidebar.setBorder(new EmptyBorder(15, 14, 15, 14));

        JButton btnCerrarMenu = new JButton(new IconoSimple("menu", 20, BLANCO));
        btnCerrarMenu.setForeground(BLANCO);
        btnCerrarMenu.setBackground(VERDE_OSCURO_SIDEBAR);
        btnCerrarMenu.setBorder(new EmptyBorder(0, 6, 15, 0));
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

        for (int i = 0; i < items.length; i++) {
            String[] item = items[i];
            boolean activo = item[1].equals("Solución Nutritiva");
            sidebar.add(crearItemMenu(item[0], item[1], activo));
            if (i < items.length - 1) {
                sidebar.add(Box.createVerticalStrut(8));
            }
        }

        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JPanel crearItemMenu(String tipoIcono, String texto, boolean activo) {
        final Color colorInactivo = VERDE_OSCURO_SIDEBAR;

        JPanel item = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        item.setOpaque(false);
        item.setBackground(activo ? VERDE_ITEM_ACTIVO : colorInactivo);
        item.setMaximumSize(new Dimension(SIDEBAR_ANCHO, 42));
        item.setPreferredSize(new Dimension(SIDEBAR_ANCHO, 42));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);
        item.setBorder(new EmptyBorder(0, 18, 0, 0));

        JLabel lbl = new JLabel(texto);
        lbl.setForeground(BLANCO);
        lbl.setFont(new Font(FUENTE, activo ? Font.BOLD : Font.PLAIN, 14));

        item.add(lbl, BorderLayout.CENTER);
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));

        item.addMouseListener(new MouseAdapter() {

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!activo) {
            item.setBackground(VERDE_ITEM_ACTIVO);
            item.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!activo) {
            item.setBackground(colorInactivo);
            item.repaint();
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

            case "Mi Perfil":
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

                break;
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

  
    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout(12, 0));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

        JPanel textoPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        textoPanel.setBackground(COLOR_FONDO);

        JLabel titulo = new JLabel("Solución Nutritiva");
        titulo.setFont(new Font(FUENTE, Font.BOLD, 28));
        titulo.setForeground(COLOR_TEXTO_TITULO);

        JLabel subtitulo = new JLabel("Registra y administra la información de las soluciones nutritivas preparadas para tus cultivos.");
        subtitulo.setFont(new Font(FUENTE, Font.PLAIN, 13));
        subtitulo.setForeground(COLOR_SUBTITULO);

        textoPanel.add(titulo);
        textoPanel.add(subtitulo);

        // Botón de menú (para reabrir/cerrar el sidebar)
        JButton btnAbrirMenu = new JButton(new IconoSimple("menu", 20, COLOR_TEXTO_TITULO));
        btnAbrirMenu.setFocusPainted(false);
        btnAbrirMenu.setContentAreaFilled(false);
        btnAbrirMenu.setBorder(new EmptyBorder(0, 0, 0, 15));
        btnAbrirMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAbrirMenu.addActionListener(e -> toggleSidebar());

        JPanel izquierdaConMenu = new JPanel(new BorderLayout());
        izquierdaConMenu.setBackground(COLOR_FONDO);
        izquierdaConMenu.add(btnAbrirMenu, BorderLayout.WEST);
        izquierdaConMenu.add(textoPanel, BorderLayout.CENTER);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        derecha.setBackground(COLOR_FONDO);
        JComponent avatar = crearAvatarIcono();
        JPanel textoUsuario = new JPanel(new GridLayout(2, 1));
        textoUsuario.setBackground(COLOR_FONDO);
        JLabel usuario = new JLabel("Usuario");
        usuario.setFont(new Font(FUENTE, Font.BOLD, 13));
        JLabel rol = new JLabel("Administrador");
        rol.setFont(new Font(FUENTE, Font.PLAIN, 11));
        rol.setForeground(COLOR_SUBTITULO);
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
                g2.setColor(COLOR_AVATAR_BG);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                new IconoSimple("person", 20, COLOR_TEXTO_TITULO)
                        .paintIcon(this, g, (getWidth() - 20) / 2, (getHeight() - 20) / 2);
            }
        };
        circulo.setPreferredSize(new Dimension(38, 38));
        return circulo;
    }


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
        JLabel titulo = new JLabel("Registro de Solución Nutritiva");
        titulo.setFont(new Font(FUENTE, Font.BOLD, 16));
        titulo.setForeground(COLOR_TEXTO_TITULO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        card.add(titulo, gbc);

        gbc.gridwidth = 1; gbc.weightx = 0;

       cbSistemaHuerto = new JComboBox<>();
       cargarSistemas();

        estilizarCombo(cbSistemaHuerto);

        txtFecha = crearTextField("dia/mes/año");
        txtConductividad = crearTextField("");
        txtTemperatura = crearTextField("");
        txtPh = crearTextField("");

        
        agregarCampo(card, gbc, 1, "Sistema de huerto:", cbSistemaHuerto);
        agregarCampo(card, gbc, 2, "Fecha de preparación:", txtFecha);
        agregarCampo(card, gbc, 3, "Conductividad (mS/cm):", txtConductividad);
        agregarCampo(card, gbc, 4, "Temperatura (°C):", txtTemperatura);
        agregarCampo(card, gbc, 5, "Nivel de pH:", txtPh);
        

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.insets = new Insets(14, 4, 4, 4);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelBotones.setBackground(Color.WHITE);

        btnRegistrar = crearBoton("Registrar", COLOR_VERDE_PRINCIPAL);
        btnRegistrar.addActionListener(e -> onRegistrarClick());

        JButton btnModificar = crearBoton("Modificar", COLOR_AZUL_MODIFICAR);
        btnModificar.addActionListener(e -> {
            int fila = tablaHistorial.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona un registro de la tabla para modificar.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            editarFila(fila);
        });

        JButton btnEliminar = crearBoton("Eliminar", COLOR_ROJO_ELIMINAR);
        btnEliminar.addActionListener(e -> {
            int fila = tablaHistorial.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this,
                        "Selecciona un registro de la tabla para eliminar.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            eliminarFila(fila);
        });

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
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
    s.getLevelPh()
});

    }

}
    private void cargarSistemas() {

    HydroponicSystemDAO dao = new HydroponicSystemDAO();

    sistemas = dao.getSystemsByUser(
            session.getCurrentUser().getUserId()
    );

    cbSistemaHuerto.removeAllItems();

    for (HydroponicSystem sistema : sistemas) {

        cbSistemaHuerto.addItem(
                sistema.getTypeSystem()
                + " (ID: "
                + sistema.getSystemId()
                + ")"
        );
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

    /** Botón con esquinas redondeadas dibujadas a mano (paintComponent), igual que la referencia. */
    private JButton crearBoton(String texto, Color color) {

    JButton btn = new JButton(texto);

    btn.setFont(new Font(FUENTE, Font.BOLD, 13));
    btn.setForeground(Color.WHITE);
    btn.setBackground(color);

    btn.setFocusPainted(true);
    btn.setContentAreaFilled(true);
    btn.setOpaque(true);
    btn.setBorderPainted(true);

    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    btn.setPreferredSize(new Dimension(130, 38));

    return btn;
}

    /** Registra una fila nueva, o actualiza la fila en edición si venimos de "Editar"/"Modificar". */
   private void onRegistrarClick() {

    try {

        NutrientSolution solution = new NutrientSolution();

        String sistema = cbSistemaHuerto.getSelectedItem().toString();

       int posicion = cbSistemaHuerto.getSelectedIndex();

int idSystem = sistemas.get(posicion).getSystemId();

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
            // Actualizar panel "Información actual"
actualizarInformacionActual(
        cbSistemaHuerto.getSelectedItem().toString(),
        txtFecha.getText(),
        txtConductividad.getText(),
        txtTemperatura.getText(),
        txtPh.getText()
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
        
    }

    private JPanel crearPanelInformacion() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(300, 0));

        JLabel tituloInfo = new JLabel("Información actual");
        tituloInfo.setFont(new Font(FUENTE, Font.BOLD, 16));
        tituloInfo.setForeground(COLOR_TEXTO_TITULO);
        tituloInfo.setAlignmentX(LEFT_ALIGNMENT);
        card.add(tituloInfo);
        card.add(Box.createVerticalStrut(14));

        ImageIcon icono = new ImageIcon(
        getClass().getResource("/imagen/lechuga.jpg")
);

Image imagen = icono.getImage().getScaledInstance(
        250, 160, Image.SCALE_SMOOTH
);

JLabel lblImagen = new JLabel(new ImageIcon(imagen));
lblImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
lblImagen.setBorder(BorderFactory.createLineBorder(COLOR_PLACEHOLDER_BORDE));

card.add(lblImagen);
card.add(Box.createVerticalStrut(16));

        lblUltimaFecha = new JLabel("");
        lblValConductividad = new JLabel("");
        lblValTemperatura = new JLabel("");
        lblValPh = new JLabel("");
        lblValPreparada = new JLabel("");

        String[] etiquetas = {"Última solución", "Conductividad", "Temperatura", "Nivel de pH", "Preparada para"};
        JLabel[] valores = {lblUltimaFecha, lblValConductividad, lblValTemperatura, lblValPh, lblValPreparada};

        for (int i = 0; i < etiquetas.length; i++) {
            card.add(crearFilaInfo(etiquetas[i], valores[i]));
            card.add(Box.createVerticalStrut(10));
        }

        return card;
    }

    private JPanel crearFilaInfo(String etiqueta, JLabel valorLabel) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(Color.WHITE);
        fila.setAlignmentX(LEFT_ALIGNMENT);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));

        JLabel etiquetaLabel = new JLabel(etiqueta + ":");
        etiquetaLabel.setFont(new Font(FUENTE, Font.PLAIN, 13));
        etiquetaLabel.setForeground(COLOR_TEXTO_ENCABEZADO_TABLA);

        valorLabel.setFont(new Font(FUENTE, Font.BOLD, 13));
        valorLabel.setForeground(COLOR_TEXTO_TITULO);
        valorLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        fila.add(etiquetaLabel, BorderLayout.WEST);
        fila.add(valorLabel, BorderLayout.EAST);
        return fila;
    }

    private void actualizarInformacionActual(String sistema, String fecha, String conductividad, String temperatura, String ph) {

    System.out.println("ENTRO A INFORMACION ACTUAL");

    lblUltimaFecha.setText(fecha);
    lblValConductividad.setText(conductividad + " mS/cm");
    lblValTemperatura.setText(temperatura + " °C");
    lblValPh.setText(ph);
    lblValPreparada.setText(sistema);

    lblUltimaFecha.repaint();
    lblValConductividad.repaint();
    lblValTemperatura.repaint();
    lblValPh.repaint();
    lblValPreparada.repaint();
}

    private JPanel crearPanelHistorial() {
        JPanel card = new JPanel(new BorderLayout(0, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JLabel titulo = new JLabel("Historial de Soluciones Nutritivas");
        titulo.setFont(new Font(FUENTE, Font.BOLD, 16));
        titulo.setForeground(COLOR_TEXTO_TITULO);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
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
        JLabel buscarLbl = new JLabel("Buscar:");
        buscarLbl.setFont(new Font(FUENTE, Font.PLAIN, 13));
        txtBuscar = new JTextField(15);
        txtBuscar.setFont(new Font(FUENTE, Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 200), 1, true),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        txtBuscar.setPreferredSize(new Dimension(180, 30));
        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        filtrarTabla();
    }

    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        filtrarTabla();
    }

    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        filtrarTabla();
    }
});
        der.add(buscarLbl);
        der.add(txtBuscar);

        controles.add(lblRangoRegistros, BorderLayout.WEST);
        controles.add(der, BorderLayout.EAST);
        centro.add(controles, BorderLayout.NORTH);

        String[] columnas = {
    "ID",
    "Sistema de Huerto",
    "Fecha de Preparación",
    "Conductividad (mS/cm)",
    "Temperatura (°C)",
    "Nivel de pH"
};

modeloTabla = new DefaultTableModel(columnas, 0) {

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
};

modeloTabla.addTableModelListener(e -> actualizarRangoRegistros());


tablaHistorial = new JTable(modeloTabla) {

    @Override
    public Component prepareRenderer(TableCellRenderer r, int row, int col) {

        Component c = super.prepareRenderer(r, row, col);

        if (col != 5) {

            c.setBackground(
                isRowSelected(row) 
                ? COLOR_SELECCION 
                : (row % 2 == 0 ? COLOR_FILA_PAR : COLOR_FILA_IMPAR)
            );

            c.setForeground(
                isRowSelected(row)
                ? COLOR_TEXTO_TITULO
                : COLOR_TEXTO_FILA
            );
        }

        return c;
    }
};


// PARA EL BUSCADOR
TableRowSorter<DefaultTableModel> sorter =
        new TableRowSorter<>(modeloTabla);

tablaHistorial.setRowSorter(sorter);
        tablaHistorial.setFont(new Font(FUENTE, Font.PLAIN, 13));
        tablaHistorial.setRowHeight(36);
        tablaHistorial.setShowVerticalLines(false);
        tablaHistorial.setGridColor(new Color(225, 240, 225));
        tablaHistorial.setSelectionBackground(COLOR_SELECCION);
        tablaHistorial.setSelectionForeground(COLOR_TEXTO_TITULO);
        tablaHistorial.getTableHeader().setFont(new Font(FUENTE, Font.BOLD, 13));
        tablaHistorial.getTableHeader().setBackground(COLOR_ENCABEZADO_TABLA);
        tablaHistorial.getTableHeader().setForeground(COLOR_TEXTO_ENCABEZADO_TABLA);
        tablaHistorial.getTableHeader().setPreferredSize(new Dimension(0, 38));
        tablaHistorial.getTableHeader().setReorderingAllowed(false);
        tablaHistorial.getSelectionModel().addListSelectionListener(e -> {

    if (!e.getValueIsAdjusting()) {
        mostrarDatosSeleccionados();
    }

});

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tablaHistorial.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tablaHistorial.getColumnModel().getColumn(3).setCellRenderer(centrado);
        tablaHistorial.getColumnModel().getColumn(4).setCellRenderer(centrado);
       

        tablaHistorial.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaHistorial.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaHistorial.getColumnModel().getColumn(2).setPreferredWidth(120);
       

        
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
        

        filaEnEdicion = fila;
        btnRegistrar.setText("Actualizar");
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

    /** Botón chico redondeado para usar dentro de las celdas de la tabla. */
   private JButton crearBotonTabla(String texto, Color color) {

    JButton btn = new JButton(texto);

    btn.setFont(new Font(FUENTE, Font.BOLD, 11));
    btn.setForeground(Color.BLACK);
    btn.setBackground(color);

    btn.setFocusPainted(true);
    btn.setContentAreaFilled(true);
    btn.setOpaque(true);
    btn.setBorderPainted(true);

    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    btn.setPreferredSize(new Dimension(84, 26));

    return btn;
}
private void filtrarTabla() {

    String texto = txtBuscar.getText();

    TableRowSorter<?> sorter =
            (TableRowSorter<?>) tablaHistorial.getRowSorter();

    if (texto.trim().isEmpty()) {

        sorter.setRowFilter(null);

    } else {

        sorter.setRowFilter(
            RowFilter.regexFilter("(?i)" + texto)
        );
    }
}
private void mostrarDatosSeleccionados() {

    int fila = tablaHistorial.getSelectedRow();

    if (fila == -1) {
        return;
    }

    // Si usas buscador, convierte la fila visual a la fila real del modelo
    fila = tablaHistorial.convertRowIndexToModel(fila);

    String id = modeloTabla.getValueAt(fila, 0).toString();
    String sistema = modeloTabla.getValueAt(fila, 1).toString();
    String fecha = modeloTabla.getValueAt(fila, 2).toString();
    String conductividad = modeloTabla.getValueAt(fila, 3).toString();
    String temperatura = modeloTabla.getValueAt(fila, 4).toString();
    String ph = modeloTabla.getValueAt(fila, 5).toString();


    lblUltimaFecha.setText(fecha);
    lblValConductividad.setText(conductividad + " mS/cm");
    lblValTemperatura.setText(temperatura + " °C");
    lblValPh.setText(ph);
    lblValPreparada.setText(sistema);

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
