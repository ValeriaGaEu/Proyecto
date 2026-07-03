package com.mycompany.hydroponicgarden1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import com.mycompany.hydroponicgarden1.dao.BatchDAO;
import com.mycompany.hydroponicgarden1.dao.HydroponicSystemDAO;
import com.mycompany.hydroponicgarden1.dao.PlantDAO;

import com.mycompany.hydroponicgarden1.model.Batch;
import com.mycompany.hydroponicgarden1.model.HydroponicSystem;
import com.mycompany.hydroponicgarden1.model.Plant;
import com.mycompany.hydroponicgarden1.model.User;

import com.mycompany.hydroponicgarden1.session.session;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestionLotes extends JFrame {

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

    // --- NUEVOS ATRIBUTOS DE CLASE PARA MANEJAR LOS DATOS ---
    private JTextField txtIdLote;
    private JComboBox<String> cmbSistema;
    private JComboBox<String> cmbPlanta;
    private JTextField txtCantidad;
    private JComboBox<String> cmbEstado;
    private JTextField txtFechaSiembra;
    private JLabel lblId;
    private JLabel lblSistema;
    private JLabel lblPlanta;
    private JLabel lblCantidad;
    private JLabel lblEstado;
    private JLabel lblFecha;
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtBuscar;
    private ArrayList<HydroponicSystem> listaSistemas = new ArrayList<>();
    private ArrayList<Plant> listaPlantas = new ArrayList<>();

    public GestionLotes() {
        setTitle("Gestion de Lotes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1360, 760);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(BG);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(content);

        content.add(createHeader(), BorderLayout.NORTH);
        content.add(createSideMenu(), BorderLayout.WEST);
        content.add(createCenter(), BorderLayout.CENTER);
        
        // Configurar el evento de selección de la tabla al iniciar
        configurarSeleccionTabla();

    cargarSistemas();

    cmbSistema.addActionListener(e -> {

        cargarPlantas();
        cargarTabla();

    });
    }
    private void navegar(String opcion) {

    switch (opcion) {

        case "Inicio":
            JOptionPane.showMessageDialog(this, "Ir a Inicio");
            break;

        case "Sistema Huerto":
            new VentanaPrincipal().setVisible(true);
            this.dispose();
            break;

        case "Plantas":
            new planta().setVisible(true);
            this.dispose();
            break;

        case "Lotes":
            new GestionLotes().setVisible(true);
            this.dispose();
            break;

        case "Monitoreo":
            new InterfazChida().setVisible(true);
            this.dispose();
            break;

        case "Solucion Nutritiva":
            new SolucionNutritivaPanel().setVisible(true);
            this.dispose();
            break;

        case "Mi perfil":
            new Profile().setVisible(true);
            this.dispose();
            break;

        case "Cerrar Sesion":
            session.setCurrentUser(null);
            new Login().setVisible(true);
            this.dispose();
            break;
    }
}
    private void cargarSistemas() {

    User user = session.getCurrentUser();

    if (user == null)
        return;

    HydroponicSystemDAO dao = new HydroponicSystemDAO();

    listaSistemas = dao.getSystemsByUser(user.getUserId());

    cmbSistema.removeAllItems();

    for (HydroponicSystem system : listaSistemas) {

        cmbSistema.addItem(
                system.getSystemId() + " - " +
                system.getTypeSystem());

    }

    if (!listaSistemas.isEmpty()) {
        cargarPlantas();
    }
}
    private void cargarPlantas() {

    if (cmbSistema.getSelectedIndex() == -1)
        return;

    HydroponicSystem system = listaSistemas.get(cmbSistema.getSelectedIndex());

    PlantDAO dao = new PlantDAO();

    listaPlantas = dao.getPlantsBySystem(system.getSystemId());

    cmbPlanta.removeAllItems();

    for (Plant plant : listaPlantas) {

        cmbPlanta.addItem(
                plant.getPlantId() + " - " +
                plant.getNamePlant());

    }
}
    private void cargarTabla() {

    if (cmbSistema.getSelectedIndex() == -1)
        return;

    HydroponicSystem system = listaSistemas.get(cmbSistema.getSelectedIndex());

    BatchDAO dao = new BatchDAO();

    ArrayList<Batch> lista = dao.getBatchBySystem(system.getSystemId());

    tableModel.setRowCount(0);

    for (Batch batch : lista) {

        String nombreSistema = "";
        String nombrePlanta = "";

        // Buscar nombre del sistema
        for (HydroponicSystem s : listaSistemas) {

            if (s.getSystemId() == batch.getSystemId()) {
                nombreSistema = s.getTypeSystem();
                break;
            }
        }

        // Buscar nombre de la planta
        PlantDAO plantDAO = new PlantDAO();

        ArrayList<Plant> plantas = plantDAO.getPlantsBySystem(batch.getSystemId());

        for (Plant p : plantas) {

            if (p.getPlantId() == batch.getPlantId()) {
                nombrePlanta = p.getNamePlant();
                break;
            }
        }

        tableModel.addRow(new Object[]{
            batch.getBatchId(),
            nombreSistema,
            nombrePlanta,
            batch.getQuantity(),
            batch.getState(),
            batch.getPlantingDate(),
            "",
            "",
            ""
        });
    }
}
    private void buscarLotes() {

    if (cmbSistema.getSelectedIndex() == -1)
        return;

    HydroponicSystem system =
            listaSistemas.get(cmbSistema.getSelectedIndex());

    BatchDAO dao = new BatchDAO();

    ArrayList<Batch> lista =
            dao.searchBatch(
                    system.getSystemId(),
                    txtBuscar.getText().trim());

    tableModel.setRowCount(0);

    for (Batch batch : lista) {

        String nombreSistema = "";
        String nombrePlanta = "";

        for (HydroponicSystem s : listaSistemas) {

            if (s.getSystemId() == batch.getSystemId()) {

                nombreSistema = s.getTypeSystem();
                break;
            }
        }

        for (Plant p : listaPlantas) {

            if (p.getPlantId() == batch.getPlantId()) {

                nombrePlanta = p.getNamePlant();
                break;
            }
        }

        tableModel.addRow(new Object[]{
                batch.getBatchId(),
                nombreSistema,
                nombrePlanta,
                batch.getQuantity(),
                batch.getState(),
                batch.getPlantingDate(),
                "",
                "",
                ""
        });

    }

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
            JButton btn = crearBotonMenu(opcion, "Lotes".equals(opcion));
            menu.add(btn);
            menu.add(Box.createVerticalStrut(10));
        }

        menu.add(Box.createVerticalGlue());

        JButton btnSalir = crearBotonMenu("Cerrar Sesion", false);
        menu.add(btnSalir);

        return menu;
    }


    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(5, 5, 10, 5));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Gestion de Lotes");
        title.setFont(titleFont);
        title.setForeground(GREEN);

        JLabel subtitle = new JLabel("Administra los lotes de cultivo asociados a tus plantas y sistemas de huerto.");
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

        JLabel section = new JLabel("Registro de Lote");
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
                "ID Lote:", "Sistema de huerto:", "Planta:",
                "Cantidad plantas:", "Estado:", "Fecha de siembra:"
        };

        // Inicializamos los componentes asignándolos a los atributos globales de la clase
        txtIdLote = styledTextField("");
        cmbSistema = styledComboBox(new String[]{"Invernadero Secundario (ID: 3)", "Invernadero Principal (ID: 1)"});
        cmbPlanta = styledComboBox(new String[]{"Lechuga - Batavia (ID: 1)", "Tomate - Cherry (ID: 2)"});
        txtCantidad = styledTextField("");
        cmbEstado = styledComboBox(new String[]{"En crecimiento", "Cosechado", "Inactivo"});
        txtFechaSiembra = styledTextField("23/05/2025");

        JComponent[] fields = { txtIdLote, cmbSistema, cmbPlanta, txtCantidad, cmbEstado, txtFechaSiembra };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.25;

            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(labelFont);
            lbl.setForeground(TEXT);
            form.add(lbl, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.75;
            form.add(fields[i], gbc);
        }

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 15));
        buttons.setOpaque(false);

        RoundedButton btnRegistrar = new RoundedButton("Registrar", GREEN);
        RoundedButton btnModificar = new RoundedButton("Modificar", BLUE);
        RoundedButton btnEliminar = new RoundedButton("Eliminar", RED);

        // --- ASIGNACIÓN DE ACCIONES A LOS BOTONES ---
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
    card.setLayout(new BorderLayout(15,10));
    card.setBorder(new EmptyBorder(18,18,18,18));

    JLabel section = new JLabel("Información del lote seleccionado");
    section.setFont(sectionFont);
    section.setForeground(GREEN);

    JPanel body = new JPanel(new GridLayout(1,2,15,0));
    body.setOpaque(false);

    JLabel imagePlaceholder = new JLabel("Imagen", SwingConstants.CENTER);
    imagePlaceholder.setFont(new Font("Segoe UI", Font.BOLD,18));
    imagePlaceholder.setOpaque(true);
    imagePlaceholder.setBackground(new Color(230,240,230));
    imagePlaceholder.setBorder(new LineBorder(BORDER,1,true));

    JPanel details = new JPanel(new GridBagLayout());
    details.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(6,0,6,10);
    gbc.anchor = GridBagConstraints.WEST;

    lblId = new JLabel("-");
    lblSistema = new JLabel("-");
    lblPlanta = new JLabel("-");
    lblCantidad = new JLabel("-");
    lblEstado = new JLabel("-");
    lblFecha = new JLabel("-");

    gbc.gridx=0;
    gbc.gridy=0;
    details.add(new JLabel("ID Lote:"),gbc);
    gbc.gridx=1;
    details.add(lblId,gbc);

    gbc.gridx=0;
    gbc.gridy=1;
    details.add(new JLabel("Sistema:"),gbc);
    gbc.gridx=1;
    details.add(lblSistema,gbc);

    gbc.gridx=0;
    gbc.gridy=2;
    details.add(new JLabel("Planta:"),gbc);
    gbc.gridx=1;
    details.add(lblPlanta,gbc);

    gbc.gridx=0;
    gbc.gridy=3;
    details.add(new JLabel("Cantidad:"),gbc);
    gbc.gridx=1;
    details.add(lblCantidad,gbc);

    gbc.gridx=0;
    gbc.gridy=4;
    details.add(new JLabel("Estado:"),gbc);
    gbc.gridx=1;
    details.add(lblEstado,gbc);

    gbc.gridx=0;
    gbc.gridy=5;
    details.add(new JLabel("Fecha de siembra:"),gbc);
    gbc.gridx=1;
    details.add(lblFecha,gbc);

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

        JLabel section = new JLabel("Listado de Lotes Registrados");
        section.setFont(sectionFont);
        section.setForeground(GREEN);

        JPanel search = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        search.setOpaque(false);

        JLabel buscarLbl = new JLabel("Buscar:");
        buscarLbl.setFont(labelFont);
        buscarLbl.setForeground(TEXT);

        txtBuscar = styledTextField("");
        txtBuscar.setPreferredSize(new Dimension(180,34));

        search.add(buscarLbl);
        search.add(txtBuscar);
        txtBuscar.addActionListener(e -> buscarLotes());

        top.add(section, BorderLayout.WEST);
        top.add(search, BorderLayout.EAST);

        String[] columns = {
                "ID Lote", "Sistema de Huerto", "Planta", "Cantidad",
                "Estado", "Fecha de Siembra", "Dias Transcurridos",
                "Fecha Estimada de Cosecha", "Acciones"
        };

        Object[][] rows = {
                {"8", "Invernadero Secundario (ID: 3)", "Lechuga - Batavia (ID: 1)", "120",
                        "En crecimiento", "23/05/2025", "2 dias", "07/07/2025", "Editar  Eliminar"}
        };

        // Asignamos el modelo y la tabla a los atributos globales de la clase
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

    // --- MÉTODOS DE LÓGICA DE CONTROL ---

    private void accionRegistrar() {

    if (cmbSistema.getSelectedIndex() == -1 ||
        cmbPlanta.getSelectedIndex() == -1) {

        JOptionPane.showMessageDialog(this,
                "Selecciona un sistema y una planta.");
        return;
    }

    if (txtCantidad.getText().trim().isEmpty()
            || txtFechaSiembra.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Completa todos los campos.");
        return;
    }

    HydroponicSystem system =
            listaSistemas.get(cmbSistema.getSelectedIndex());

    Plant plant =
            listaPlantas.get(cmbPlanta.getSelectedIndex());

    Batch batch = new Batch();

    batch.setSystemId(system.getSystemId());
    batch.setPlantId(plant.getPlantId());
    batch.setQuantity(Integer.parseInt(txtCantidad.getText()));
    batch.setState(cmbEstado.getSelectedItem().toString());

    try {

    java.time.format.DateTimeFormatter formato =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate fecha =
            LocalDate.parse(txtFechaSiembra.getText().trim(), formato);

    batch.setPlantingDate(Date.valueOf(fecha));

} catch (Exception e) {

    JOptionPane.showMessageDialog(this,
            "La fecha debe tener el formato dd/MM/yyyy\nEjemplo: 03/07/2026");

    return;
}

    BatchDAO dao = new BatchDAO();

    if (dao.insertBatch(batch)) {

        JOptionPane.showMessageDialog(this,
                "Lote registrado correctamente.");

        cargarTabla();

        txtCantidad.setText("");
        txtFechaSiembra.setText("");
        cmbEstado.setSelectedIndex(0);

    } else {

        JOptionPane.showMessageDialog(this,
                "No fue posible registrar el lote.");
    }
    }

    private void accionModificar() {

    int fila = table.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecciona un lote.");
        return;
    }

    if (cmbSistema.getSelectedIndex() == -1 ||
        cmbPlanta.getSelectedIndex() == -1) {
        return;
    }

    HydroponicSystem system =
            listaSistemas.get(cmbSistema.getSelectedIndex());

    Plant plant =
            listaPlantas.get(cmbPlanta.getSelectedIndex());

    Batch batch = new Batch();

    batch.setBatchId(
            Integer.parseInt(
                    table.getValueAt(fila, 0).toString()));

    batch.setSystemId(system.getSystemId());

    batch.setPlantId(plant.getPlantId());

    batch.setQuantity(
            Integer.parseInt(txtCantidad.getText()));

    batch.setState(
            cmbEstado.getSelectedItem().toString());

    try {

        java.time.format.DateTimeFormatter formato =
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate fecha =
                LocalDate.parse(txtFechaSiembra.getText(), formato);

        batch.setPlantingDate(Date.valueOf(fecha));

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Formato de fecha incorrecto.");

        return;
    }

    BatchDAO dao = new BatchDAO();

    if (dao.updateBatch(batch)) {

        JOptionPane.showMessageDialog(this,
                "Lote modificado correctamente.");

        cargarTabla();

    } else {

        JOptionPane.showMessageDialog(this,
                "No fue posible modificar el lote.");
    }

    }

    private void accionEliminar() {


    int fila = table.getSelectedRow();

    if (fila == -1) {

        JOptionPane.showMessageDialog(this,
                "Selecciona un lote.");

        return;
    }

    int confirmar = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas eliminar este lote?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);

    if (confirmar != JOptionPane.YES_OPTION) {
        return;
    }

    int id = Integer.parseInt(
            table.getValueAt(fila, 0).toString());

    BatchDAO dao = new BatchDAO();

    if (dao.deleteBatch(id)) {

        JOptionPane.showMessageDialog(this,
                "Lote eliminado correctamente.");

        cargarTabla();

        txtIdLote.setText("");
        txtCantidad.setText("");
        txtFechaSiembra.setText("");
        cmbEstado.setSelectedIndex(0);

        if (cmbSistema.getItemCount() > 0)
            cmbSistema.setSelectedIndex(0);

        if (cmbPlanta.getItemCount() > 0)
            cmbPlanta.setSelectedIndex(0);

    } else {

        JOptionPane.showMessageDialog(this,
                "No fue posible eliminar el lote.");
    }
    }

    private void configurarSeleccionTabla() {

    table.getSelectionModel().addListSelectionListener(e -> {

        if (!e.getValueIsAdjusting()) {

            int filaSeleccionada = table.getSelectedRow();

            if (filaSeleccionada != -1) {

                txtIdLote.setText(tableModel.getValueAt(filaSeleccionada, 0).toString());
                cmbSistema.setSelectedItem(tableModel.getValueAt(filaSeleccionada, 1).toString());
                cmbPlanta.setSelectedItem(tableModel.getValueAt(filaSeleccionada, 2).toString());
                txtCantidad.setText(tableModel.getValueAt(filaSeleccionada, 3).toString());
                cmbEstado.setSelectedItem(tableModel.getValueAt(filaSeleccionada, 4).toString());
                txtFechaSiembra.setText(tableModel.getValueAt(filaSeleccionada, 5).toString());

                int idBatch = Integer.parseInt(
        tableModel.getValueAt(filaSeleccionada, 0).toString());

BatchDAO dao = new BatchDAO();

Batch batch = dao.getBatchById(idBatch);

if (batch != null) {
    mostrarInformacion(batch);
}
            }
        }
    });
}
    private void mostrarInformacion(Batch batch) {

    lblId.setText(String.valueOf(batch.getBatchId()));

    // Sistema
    for (HydroponicSystem s : listaSistemas) {

        if (s.getSystemId() == batch.getSystemId()) {
            lblSistema.setText(s.getTypeSystem());
            break;
        }
    }

    // Planta
    PlantDAO plantDAO = new PlantDAO();

    ArrayList<Plant> plantas = plantDAO.getPlantsBySystem(batch.getSystemId());

    for (Plant p : plantas) {

        if (p.getPlantId() == batch.getPlantId()) {
            lblPlanta.setText(p.getNamePlant());
            break;
        }
    }

    lblCantidad.setText(String.valueOf(batch.getQuantity()));
    lblEstado.setText(batch.getState());
    lblFecha.setText(batch.getPlantingDate().toString());
}

    private void limpiarFormulario() {
        txtIdLote.setText("");
        txtCantidad.setText("");
        txtFechaSiembra.setText("23/05/2025");
        cmbSistema.setSelectedIndex(0);
        cmbPlanta.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        table.clearSelection();
    }

    // --- MÉTODOS DE ESTILIZACIÓN ORIGINALES ---

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

    private JComboBox<String> styledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(fieldFont);
        combo.setForeground(TEXT);
        combo.setBackground(Color.WHITE);
        combo.setBorder(new LineBorder(BORDER, 1, true));
        combo.setPreferredSize(new Dimension(220, 36));
        return combo;
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
 private JButton crearBotonMenu(String texto, boolean activo) {

    JButton btn = new JButton(texto);

    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new GestionLotes().setVisible(true);
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