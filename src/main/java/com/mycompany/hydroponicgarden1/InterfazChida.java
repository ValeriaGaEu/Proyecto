package com.mycompany.hydroponicgarden1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.mycompany.hydroponicgarden1.dao.MonitoringDAO;
import com.mycompany.hydroponicgarden1.model.Monitoring;

public class InterfazChida extends JFrame {
    
    private final Color BG = Color.decode("#F6F8F7");
    private final Color CARD = Color.WHITE;
    private final Color GREEN = Color.decode("#2E7D32");
    private final Color BLUE = Color.decode("#1565C0");
    private final Color RED = Color.decode("#D32F2F");
    private final Color TEXT = Color.decode("#37474F");
    private final Color MUTED = Color.decode("#6B7280");
    private final Color BORDER = Color.decode("#DDE3E8");
    private final Color BADGE_BG = Color.decode("#E8F5E9");
    private final Color BADGE_TEXT = Color.decode("#43A047");

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private final Font sectionFont = new Font("Segoe UI", Font.BOLD, 16);
    private final Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

    
    private JTextField txtIdMonitoring;
    private JTextField txtWaterTemperature;
    private JTextField txtAmbientTemperature;
    private JTextField txtAmbientHumidity;
    private JTextField txtEcMeasured;
    private JTextField txtPhMeasured;
    private JTextField txtDateTime;

    private JTable table;
    private DefaultTableModel tableModel;
    private MonitoringDAO monitoringDAO = new MonitoringDAO();

    public InterfazChida() {
    setTitle("Gestion de Monitoreo");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1300, 1000);
    setLocationRelativeTo(null);

    JPanel content = new JPanel(new BorderLayout(0, 20));
    content.setBackground(BG);
    content.setBorder(new EmptyBorder(20, 20, 20, 20));
    setContentPane(content);

    content.add(createHeader(), BorderLayout.NORTH);
    content.add(createSideMenu(), BorderLayout.WEST);
    content.add(createCenter(), BorderLayout.CENTER);

    configurarSeleccionTabla();

    cargarTabla();
}
    private void abrirVentana(JFrame ventana) {
    ventana.setVisible(true);
    this.dispose(); // cierra la actual (opcional pero recomendado)
}

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(5, 5, 10, 5));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Monitoreo");
        title.setFont(titleFont);
        title.setForeground(GREEN);

        JLabel subtitle = new JLabel("Registra y consulta las lecturas de sensores de tu huerto hidroponico.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);

        left.add(title);
        left.add(Box.createVerticalStrut(5));
        left.add(subtitle);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        userPanel.setOpaque(false);

        JLabel avatar = new JLabel("\u25CF");
        avatar.setForeground(new Color(180, 220, 180));
        avatar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 36));

        JPanel userText = new JPanel();
        userText.setOpaque(false);
        userText.setLayout(new BoxLayout(userText, BoxLayout.Y_AXIS));

        JLabel user = new JLabel("Usuario");
        user.setFont(new Font("Segoe UI", Font.BOLD, 13));
        user.setForeground(TEXT);

        JLabel role = new JLabel("Administrador");
        role.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        role.setForeground(MUTED);

        userText.add(user);
        userText.add(role);

        userPanel.add(avatar);
        userPanel.add(userText);

        header.add(left, BorderLayout.WEST);
        header.add(userPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel createSideMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(GREEN);
        menu.setBorder(new EmptyBorder(20, 14, 20, 14));
        menu.setPreferredSize(new Dimension(210, 0));

        String[] opciones = {
                "Inicio",
                "Sistema Huerto",
                "Plantas",
                "Lotes",
                "Monitoreo",
                "Solucion Nutritiva",
                "Mi perfil"
        };

        for (String opcion : opciones) {
            JButton btn = crearBotonMenu(opcion, "Monitoreo".equals(opcion));
            menu.add(btn);
            menu.add(Box.createVerticalStrut(10));
        }

        menu.add(Box.createVerticalGlue());

        JButton btnSalir = crearBotonMenu("Cerrar Sesion", false);
        menu.add(btnSalir);

        return menu;
    }

    private JButton crearBotonMenu(String texto, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(activo ? Color.decode("#1B5E20") : Color.decode("#43A047"));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       btn.addActionListener(e -> navegar(texto));
        return btn;
    }
    private void navegar(String opcion) {

    switch (opcion) {

        case "Inicio":
            abrirVentana(new Inicio());
            break;

        case "Sistema Huerto":
            abrirVentana(new VentanaPrincipal());
            break;

        case "Plantas":
            abrirVentana(new planta());
            break;

        case "Lotes":
            abrirVentana(new GestionLotes());
            break;

        case "Monitoreo":
            JOptionPane.showMessageDialog(this, "Ya estás en Monitoreo");
            break;

        case "Solucion Nutritiva":
            abrirVentana(new SolucionNutritivaPanel());
            break;

        case "Mi perfil":
            abrirVentana(new Profile());
            break;

        case "Cerrar Sesion":
            int op = JOptionPane.showConfirmDialog(
                    this,
                    "¿Cerrar sesión?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {
                abrirVentana(new Login());
            }
            break;
    }
}

    private JPanel createCenter() {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JPanel top = new JPanel(new GridLayout(1, 2, 20, 0));
        top.setOpaque(false);
        top.add(createFormCard());
        top.add(createInfoCard());

        JPanel bottom = createTableCard();

        wrapper.add(top);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(bottom);

        return wrapper;
    }

    private JPanel createFormCard() {
        RoundedPanel card = new RoundedPanel(20, CARD);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel section = new JLabel("Registro de Monitoreo");
        section.setFont(sectionFont);
        section.setForeground(GREEN);
        section.setBorder(new EmptyBorder(0, 0, 15, 0));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {
                "ID Monitoreo:", "Temp. del agua (C):", "Temp. ambiente (C):",
                "Humedad ambiente (%):", "EC medida (mS/cm):", "pH medido:", "Fecha y hora:"
        };

        // id_monitoring es auto_increment, se deja como solo lectura
        txtIdMonitoring = styledTextField("");
        txtIdMonitoring.setEditable(false);
        txtIdMonitoring.setBackground(new Color(240, 240, 240));

        txtWaterTemperature = styledTextField("");
        txtAmbientTemperature = styledTextField("");
        txtAmbientHumidity = styledTextField("");
        txtEcMeasured = styledTextField("");
        txtPhMeasured = styledTextField("");

        
        txtDateTime = styledTextField(obtenerFechaHoraActual());

        JComponent[] fields = {
                txtIdMonitoring, txtWaterTemperature, txtAmbientTemperature,
                txtAmbientHumidity, txtEcMeasured, txtPhMeasured, txtDateTime
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.4;

            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(labelFont);
            lbl.setForeground(TEXT);
            form.add(lbl, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.6;
            form.add(fields[i], gbc);
        }

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 15));
        buttons.setOpaque(false);

        RoundedButton btnRegistrar = new RoundedButton("Registrar", GREEN);
        RoundedButton btnModificar = new RoundedButton("Modificar", BLUE);
        RoundedButton btnEliminar = new RoundedButton("Eliminar", RED);

        btnRegistrar.addActionListener(e -> accionRegistrar());
        btnModificar.addActionListener(e -> accionModificar());
        btnEliminar.addActionListener(e -> accionEliminar());

        buttons.add(btnRegistrar);
        buttons.add(btnModificar);
        buttons.add(btnEliminar);

        card.add(section, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);
        card.add(buttons, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createInfoCard() {
        RoundedPanel card = new RoundedPanel(20, CARD);
        card.setLayout(new BorderLayout(15, 10));
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel section = new JLabel("Informacion del monitoreo seleccionado");
        section.setFont(sectionFont);
        section.setForeground(GREEN);

        JPanel body = new JPanel(new GridLayout(1, 2, 15, 0));
        body.setOpaque(false);

        JLabel imagePlaceholder = new JLabel("Imagen", SwingConstants.CENTER);
        imagePlaceholder.setFont(new Font("Segoe UI", Font.BOLD, 18));
        imagePlaceholder.setForeground(MUTED);
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(new Color(230, 240, 230));
        imagePlaceholder.setBorder(new LineBorder(BORDER, 1, true));

        JPanel details = new JPanel(new GridBagLayout());
        details.setOpaque(false);
        details.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 0, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[][] data = {
                {"ID Monitoreo:", "1"},
                {"Temp. del agua:", "20.5 C"},
                {"Temp. ambiente:", "24.0 C"},
                {"Humedad ambiente:", "65.0 %"},
                {"EC medida:", "1.8 mS/cm"},
                {"pH medido:", "6.2"},
                {"Estado:", "Normal"},
                {"Fecha y hora:", obtenerFechaHoraActual()}
        };

        for (int i = 0; i < data.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.4;

            JLabel k = new JLabel(data[i][0]);
            k.setFont(labelFont);
            k.setForeground(MUTED);
            details.add(k, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.6;

            JLabel v;
            if ("Estado:".equals(data[i][0])) {
                v = createBadge(data[i][1]);
            } else {
                v = new JLabel(data[i][1]);
                v.setFont(new Font("Segoe UI", Font.BOLD, 13));
                v.setForeground(TEXT);
            }
            details.add(v, gbc);
        }

        body.add(imagePlaceholder);
        body.add(details);

        card.add(section, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);

        return card;
    }

    private JPanel createTableCard() {
        RoundedPanel card = new RoundedPanel(20, CARD);
        card.setLayout(new BorderLayout(0, 15));
        card.setBorder(new EmptyBorder(18, 18, 18, 18));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel section = new JLabel("Listado de Monitoreos Registrados");
        section.setFont(sectionFont);
        section.setForeground(GREEN);

        JPanel search = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        search.setOpaque(false);

        JLabel buscarLbl = new JLabel("Buscar:");
        buscarLbl.setFont(labelFont);
        buscarLbl.setForeground(TEXT);

        JTextField buscarField = styledTextField("Buscar monitoreo...");
        buscarField.setPreferredSize(new Dimension(180, 34));

        search.add(buscarLbl);
        search.add(buscarField);

        top.add(section, BorderLayout.WEST);
        top.add(search, BorderLayout.EAST);

        String[] columns = {
                "ID Monitoreo", "Temp. Agua (C)", "Temp. Ambiente (C)",
                "Humedad Ambiente (%)", "EC Medida (mS/cm)", "pH Medido",
                "Fecha y Hora", "Acciones"
        };

        Object[][] rows = {
                {"1", "20.5", "24.0", "65.0", "1.8", "6.2", obtenerFechaHoraActual(), "Editar  Eliminar"}
        };

        tableModel = new DefaultTableModel(rows, columns);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(TEXT);
        table.setGridColor(new Color(235, 239, 242));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(232, 245, 233));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(243, 247, 244));
        header.setForeground(TEXT);
        header.setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new LineBorder(BORDER, 1, true));
        scroll.getViewport().setBackground(Color.WHITE);

        card.add(top, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);

        return card;
    }

    // --- METODOS DE LOGICA DE CONTROL ---

    private void accionRegistrar() {

    try {

        if (txtWaterTemperature.getText().trim().isEmpty()
                || txtAmbientTemperature.getText().trim().isEmpty()
                || txtAmbientHumidity.getText().trim().isEmpty()
                || txtEcMeasured.getText().trim().isEmpty()
                || txtPhMeasured.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Complete todos los campos.");
            return;
        }

        Monitoring monitoring = new Monitoring();

        monitoring.setWaterTemperature(
                Double.parseDouble(txtWaterTemperature.getText().trim()));

        monitoring.setAmbientTemperature(
                Double.parseDouble(txtAmbientTemperature.getText().trim()));

        monitoring.setAmbientHumidity(
                Double.parseDouble(txtAmbientHumidity.getText().trim()));

        monitoring.setEcMeasured(
                Double.parseDouble(txtEcMeasured.getText().trim()));

        monitoring.setPhMeasured(
                Double.parseDouble(txtPhMeasured.getText().trim()));

        MonitoringDAO dao = new MonitoringDAO();

        if (dao.insertMonitoring(monitoring)) {

            JOptionPane.showMessageDialog(this,
                    "Monitoreo registrado correctamente.");

            limpiarFormulario();
            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(this,
                    "No fue posible registrar el monitoreo.");

        }

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,
                "Los valores deben ser numéricos.");

    }

}

    private void accionModificar() {

    int fila = table.getSelectedRow();

    if (fila == -1) {

        JOptionPane.showMessageDialog(this,
                "Seleccione un monitoreo de la tabla.");

        return;

    }

    try {

        Monitoring monitoring = new Monitoring();

        monitoring.setIdMonitoring(
                Integer.parseInt(txtIdMonitoring.getText()));

        monitoring.setWaterTemperature(
                Double.parseDouble(txtWaterTemperature.getText().trim()));

        monitoring.setAmbientTemperature(
                Double.parseDouble(txtAmbientTemperature.getText().trim()));

        monitoring.setAmbientHumidity(
                Double.parseDouble(txtAmbientHumidity.getText().trim()));

        monitoring.setEcMeasured(
                Double.parseDouble(txtEcMeasured.getText().trim()));

        monitoring.setPhMeasured(
                Double.parseDouble(txtPhMeasured.getText().trim()));

        MonitoringDAO dao = new MonitoringDAO();

        if (dao.updateMonitoring(monitoring)) {

            JOptionPane.showMessageDialog(this,
                    "Monitoreo actualizado correctamente.");

            cargarTabla();
            limpiarFormulario();

        } else {

            JOptionPane.showMessageDialog(this,
                    "No fue posible actualizar el monitoreo.");

        }

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,
                "Verifique los datos ingresados.");

    }

}

    private void accionEliminar() {

    int fila = table.getSelectedRow();

    if (fila == -1) {

        JOptionPane.showMessageDialog(this,
                "Seleccione un monitoreo de la tabla.");

        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Seguro que quieres eliminar este registro?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);

    if (confirmacion != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        int id = Integer.parseInt(
                tableModel.getValueAt(fila, 0).toString());

        MonitoringDAO dao = new MonitoringDAO();

        if (dao.deleteMonitoring(id)) {

            JOptionPane.showMessageDialog(this,
                    "Monitoreo eliminado correctamente.");

            cargarTabla();
            limpiarFormulario();

        } else {

            JOptionPane.showMessageDialog(this,
                    "No se pudo eliminar el monitoreo.");

        }

    } catch (Exception ex) {

        JOptionPane.showMessageDialog(this,
                "Error al eliminar: " + ex.getMessage());
    }
}
    private void cargarMonitoreos() {

    tableModel.setRowCount(0);

    for (Monitoring monitoring : monitoringDAO.getAllMonitoring()) {

        tableModel.addRow(new Object[]{
            monitoring.getIdMonitoring(),
            monitoring.getWaterTemperature(),
            monitoring.getAmbientTemperature(),
            monitoring.getAmbientHumidity(),
            monitoring.getEcMeasured(),
            monitoring.getPhMeasured(),
            monitoring.getDateTime(),
            "Editar  Eliminar"
        });

    }

}

    private boolean esDecimalValido(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private String obtenerFechaHoraActual() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.now().format(formato);
    }

    private void configurarSeleccionTabla() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    txtIdMonitoring.setText(tableModel.getValueAt(filaSeleccionada, 0).toString());
                    txtWaterTemperature.setText(tableModel.getValueAt(filaSeleccionada, 1).toString());
                    txtAmbientTemperature.setText(tableModel.getValueAt(filaSeleccionada, 2).toString());
                    txtAmbientHumidity.setText(tableModel.getValueAt(filaSeleccionada, 3).toString());
                    txtEcMeasured.setText(tableModel.getValueAt(filaSeleccionada, 4).toString());
                    txtPhMeasured.setText(tableModel.getValueAt(filaSeleccionada, 5).toString());
                    txtDateTime.setText(tableModel.getValueAt(filaSeleccionada, 6).toString());
                }
            }
        });
    }
private void cargarTabla() {

    tableModel.setRowCount(0);

    MonitoringDAO dao = new MonitoringDAO();

    for (Monitoring monitoring : dao.getAllMonitoring()) {

        tableModel.addRow(new Object[]{
            monitoring.getIdMonitoring(),
            monitoring.getWaterTemperature(),
            monitoring.getAmbientTemperature(),
            monitoring.getAmbientHumidity(),
            monitoring.getEcMeasured(),
            monitoring.getPhMeasured(),
            monitoring.getDateTime(),
            "Editar  Eliminar"
        });

    }

}
    private void limpiarFormulario() {
        txtIdMonitoring.setText("");
        txtWaterTemperature.setText("");
        txtAmbientTemperature.setText("");
        txtAmbientHumidity.setText("");
        txtEcMeasured.setText("");
        txtPhMeasured.setText("");
        txtDateTime.setText(obtenerFechaHoraActual());
        table.clearSelection();
    }

    // --- METODOS DE ESTILIZACION ---

    private JTextField styledTextField(String text) {
        JTextField field = new JTextField(text);
        field.setFont(fieldFont);
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
        field.setPreferredSize(new Dimension(220, 36));
        return field;
    }

    private JLabel createBadge(String text) {
        JLabel badge = new JLabel(text);
        badge.setOpaque(true);
        badge.setBackground(BADGE_BG);
        badge.setForeground(BADGE_TEXT);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 12));
        badge.setBorder(new EmptyBorder(4, 10, 4, 10));
        return badge;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new InterfazChida().setVisible(true);
        });
    }

    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color backgroundColor;

        public RoundedPanel(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(0, 0, 0, 15));
            g2.fillRoundRect(4, 4, getWidth() - 4, getHeight() - 4, radius, radius);

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, radius, radius);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedButton extends JButton {
        private final Color bgColor;

        public RoundedButton(String text, Color bgColor) {
            super(text);
            this.bgColor = bgColor;
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(120, 38));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);

            super.paintComponent(g);
            g2.dispose();
        }
    }
}