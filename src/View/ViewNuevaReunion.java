package View;

import controller.agregar;
import controller.marcas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Timestamp;

public class ViewNuevaReunion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTitulo;
    private JTextField txtTema;
    private JTextField txtSesion;
    private JTextField txtSesiones;
    private JComboBox<String> comboMarca;
    private String imagen;
    private String recursos;
    private int usuarioId;
    private Connection connection;

    public ViewNuevaReunion(int usuarioId) {
    	
    	this.usuarioId = usuarioId;
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 450);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS));
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setPreferredSize(new Dimension(80, 30));
        txtTitulo = new JTextField();
        panelTitulo.add(lblTitulo);
        panelTitulo.add(txtTitulo);
        contentPane.add(panelTitulo);
        
        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
        panelDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción"));
        JTextArea txtDescripcion = new JTextArea(3, 20);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panelDescripcion.add(scrollDescripcion);
        contentPane.add(panelDescripcion);
        
        JPanel panelFechas = new JPanel();
        panelFechas.setLayout(new BoxLayout(panelFechas, BoxLayout.Y_AXIS));
        panelFechas.setBorder(BorderFactory.createTitledBorder("Fechas"));
        
        JPanel panelFechaInicio = new JPanel();
        panelFechaInicio.setLayout(new BoxLayout(panelFechaInicio, BoxLayout.X_AXIS));
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setPreferredSize(new Dimension(80, 30));
        JSpinner spinnerDiaInicio = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        JComboBox<String> comboMesInicio = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                                                                        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        JSpinner spinnerAnoInicio = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panelFechaInicio.add(lblFechaInicio);
        panelFechaInicio.add(spinnerDiaInicio);
        panelFechaInicio.add(comboMesInicio);
        panelFechaInicio.add(spinnerAnoInicio);
        panelFechas.add(panelFechaInicio);
        
        JPanel panelFechaFin = new JPanel();
        panelFechaFin.setLayout(new BoxLayout(panelFechaFin, BoxLayout.X_AXIS));
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setPreferredSize(new Dimension(80, 30));
        JSpinner spinnerDiaFin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        JComboBox<String> comboMesFin = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                                                                     "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        JSpinner spinnerAnoFin = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panelFechaFin.add(lblFechaFin);
        panelFechaFin.add(spinnerDiaFin);
        panelFechaFin.add(comboMesFin);
        panelFechaFin.add(spinnerAnoFin);
        panelFechas.add(panelFechaFin);
        
        contentPane.add(panelFechas);
        
        JPanel panelTema = new JPanel();
        panelTema.setLayout(new BoxLayout(panelTema, BoxLayout.X_AXIS));
        JLabel lblTema = new JLabel("Tema:");
        lblTema.setPreferredSize(new Dimension(80, 30));
        txtTema = new JTextField();
        panelTema.add(lblTema);
        panelTema.add(txtTema);
        contentPane.add(panelTema);
        
        JPanel panelMarca = new JPanel();
        panelMarca.setLayout(new BoxLayout(panelMarca, BoxLayout.X_AXIS));
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setPreferredSize(new Dimension(80, 30));
        
        comboMarca = new JComboBox<>();
        cargarMarcasEnComboBox();
        
        panelMarca.add(lblMarca);
        panelMarca.add(comboMarca);
        contentPane.add(panelMarca);

        // Agregar un listener para el evento de selección de "Otros"
        comboMarca.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboMarca.getSelectedItem().equals("Otros")) {
                        comboMarca.setEditable(true);  // Permitir edición
                        comboMarca.getEditor().selectAll(); // Seleccionar texto para sobreescribir
                    } else {
                        comboMarca.setEditable(false); // Deshabilitar edición
                    }
                }
            }
        });
        
        JPanel panelSesiones = new JPanel();
        panelSesiones.setLayout(new BoxLayout(panelSesiones, BoxLayout.X_AXIS));
        JLabel lblSesiones = new JLabel("Sesiones:");
        lblSesiones.setPreferredSize(new Dimension(80, 30));
        txtSesiones = new JTextField("0");  
        panelSesiones.add(lblSesiones);
        panelSesiones.add(txtSesiones);
        contentPane.add(panelSesiones);

        // Restringir el campo txtSesiones para que solo acepte números
        txtSesiones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignora la entrada si no es un número
                }
            }
        });
        
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.X_AXIS));
        JButton btnImagen = new JButton("Subir Imagen");
        btnImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagen = fileChooser.getSelectedFile().getAbsolutePath();
            }
        });
        panelImagen.add(btnImagen);
        contentPane.add(panelImagen);

        JPanel panelRecursos = new JPanel();
        panelRecursos.setLayout(new BoxLayout(panelRecursos, BoxLayout.X_AXIS));
        JButton btnRecursos = new JButton("Subir Recursos");
        btnRecursos.addActionListener(e -> {
            JDialog recursosDialog = new JDialog(this, "Seleccionar Recursos", true);
            recursosDialog.setBounds(100, 100, 400, 150);
            recursosDialog.setLayout(new GridBagLayout());
            
            JLabel lblUrl = new JLabel("URL:");
            GridBagConstraints gbc_lblUrl = new GridBagConstraints();
            gbc_lblUrl.anchor = GridBagConstraints.EAST;
            gbc_lblUrl.insets = new Insets(10, 10, 5, 5);
            gbc_lblUrl.gridx = 0;
            gbc_lblUrl.gridy = 0;
            recursosDialog.add(lblUrl, gbc_lblUrl);
            
            JTextField txtUrl = new JTextField();
            GridBagConstraints gbc_txtUrl = new GridBagConstraints();
            gbc_txtUrl.insets = new Insets(10, 5, 5, 5);
            gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtUrl.gridx = 1;
            gbc_txtUrl.gridy = 0;
            recursosDialog.add(txtUrl, gbc_txtUrl);
            txtUrl.setColumns(20);
            
            JButton btnSubirArchivo = new JButton("Subir Archivo");
            btnSubirArchivo.addActionListener(ae -> {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(recursosDialog);
                if (result == JFileChooser.APPROVE_OPTION) {
                    recursos = fileChooser.getSelectedFile().getAbsolutePath();
                    txtUrl.setText(recursos);
                }
            });
            GridBagConstraints gbc_btnSubirArchivo = new GridBagConstraints();
            gbc_btnSubirArchivo.insets = new Insets(10, 5, 5, 5);
            gbc_btnSubirArchivo.gridx = 2;
            gbc_btnSubirArchivo.gridy = 0;
            recursosDialog.add(btnSubirArchivo, gbc_btnSubirArchivo);
            
            JButton btnAceptar = new JButton("Aceptar");
            btnAceptar.addActionListener(ae -> {
                if (!txtUrl.getText().trim().isEmpty()) {
                    recursos = txtUrl.getText().trim();
                }
                recursosDialog.dispose();
            });
            GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
            gbc_btnAceptar.insets = new Insets(10, 5, 5, 5);
            gbc_btnAceptar.gridx = 1;
            gbc_btnAceptar.gridy = 1;
            recursosDialog.add(btnAceptar, gbc_btnAceptar);
            
            recursosDialog.setVisible(true);
        });
        panelRecursos.add(btnRecursos);
        contentPane.add(panelRecursos);
        
        JPanel panelGuardar = new JPanel();
        panelGuardar.setLayout(new BoxLayout(panelGuardar, BoxLayout.X_AXIS));
        JButton btnGuardar = new JButton("Guardar Conferencia");
        btnGuardar.addActionListener(e -> guardarConferencia());
        panelGuardar.add(btnGuardar);
        contentPane.add(panelGuardar);
    }

    private void cargarMarcasEnComboBox() {
        marcas controllerMarcas = new marcas();
        List<String> listaMarcas = controllerMarcas.obtenerMarcas();
        for (String marca : listaMarcas) {
            comboMarca.addItem(marca);
        }
        comboMarca.addItem("Otros"); 
    }
    
    private void guardarConferencia() {
        try {
             String titulo = txtTitulo.getText().trim();
             String descripcion = ((JTextArea)((JScrollPane)((JPanel)contentPane.getComponent(1)).getComponent(0)).getViewport().getView()).getText().trim();
             int diaInicio = (Integer)((JSpinner)((JPanel)((JPanel)contentPane.getComponent(2)).getComponent(0)).getComponent(1)).getValue();
             int mesInicio = ((JComboBox<String>)((JPanel)((JPanel)contentPane.getComponent(2)).getComponent(0)).getComponent(2)).getSelectedIndex();
             int anoInicio = (Integer)((JSpinner)((JPanel)((JPanel)contentPane.getComponent(2)).getComponent(0)).getComponent(3)).getValue();
             int diaFin = (Integer)((JSpinner)((JPanel)((JPanel)contentPane.getComponent(2)).getComponent(1)).getComponent(1)).getValue();
             int mesFin = ((JComboBox<String>)((JPanel)((JPanel)contentPane.getComponent(2)).getComponent(1)).getComponent(2)).getSelectedIndex();
             int anoFin = (Integer)((JSpinner)((JPanel)((JPanel)contentPane.getComponent(2)).getComponent(1)).getComponent(3)).getValue();
             String tema = txtTema.getText().trim();
             
             // Obtener la marca
             String marca;
             if (comboMarca.isEditable()) {
                 marca = comboMarca.getEditor().getItem().toString().trim();
             } else {
                 marca = comboMarca.getSelectedItem().toString();
             }

             String sesiones = txtSesiones.getText().trim();
             
             // Validación de campos vacíos
             if (titulo.isEmpty() || descripcion.isEmpty() || tema.isEmpty() || marca.isEmpty() || 
                 imagen == null || imagen.isEmpty() || recursos == null || recursos.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios de llenar", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
             }

             Calendar calInicio = Calendar.getInstance();
             calInicio.set(anoInicio, mesInicio, diaInicio);
             Timestamp fechaInicio = new Timestamp(calInicio.getTimeInMillis());

             Calendar calFin = Calendar.getInstance();
             calFin.set(anoFin, mesFin, diaFin);
             Timestamp fechaFin = new Timestamp(calFin.getTimeInMillis());


             if (Integer.parseInt(sesiones) == 0 && !fechaInicio.equals(fechaFin)) {
                 int opcion = JOptionPane.showConfirmDialog(this, 
                     "La cantidad de sesiones es 0, pero la fecha de fin es distinta a la fecha de inicio. " +
                     "¿Desea cambiar la fecha de fin para que coincida con la fecha de inicio?",
                     "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                 
                 if (opcion == JOptionPane.OK_OPTION) {
                     fechaFin = fechaInicio;
                 } else {
                     JOptionPane.showMessageDialog(this, "Por favor, corrija las fechas para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
             }

             if (Integer.parseInt(sesiones) > 0) {
                 ViewSesiones viewSesiones = new ViewSesiones(connection, titulo, descripcion, fechaInicio, fechaFin, tema, marca, recursos, imagen, usuarioId);
                 viewSesiones.setVisible(true);
             } else {
                 agregar agregarConferencia = new agregar();
                 int idConferencia = agregarConferencia.agregarConferencia(titulo, descripcion, fechaInicio, fechaFin, tema, marca, recursos, imagen, usuarioId);

                 if (idConferencia > 0) {                     
                	 JOptionPane.showMessageDialog(this, "Conferencia guardada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                 } else {
                     JOptionPane.showMessageDialog(this, "Error al guardar la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}