package View;

import controller.agregar;
import controller.marcas;

import java.awt.EventQueue;
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
import javax.swing.Box;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Timestamp;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

public class ViewNuevaReunion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTitulo;
    private JTextField txtTema;
    private JTextField txtSesiones;
    private JComboBox<String> comboMarca;
    private String imagen;
    private String recursos;
    private int usuarioId;
    private Connection connection;

    public ViewNuevaReunion(int usuarioId) {
        this.usuarioId = usuarioId;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 470, 452);  // Tamaño reducido

        contentPane = new BackgroundPanel("/View/backnewreu.jpeg");
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Configurar el fondo transparente para los paneles
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS));
        panelTitulo.setOpaque(false);  // Hacer el panel transparente
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setPreferredSize(new Dimension(80, 30));
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Monospaced", Font.BOLD, 13));
        txtTitulo.setPreferredSize(new Dimension(100, 30));  
        panelTitulo.add(lblTitulo);
        panelTitulo.add(txtTitulo);
        contentPane.add(panelTitulo);

        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new BoxLayout(panelDescripcion, BoxLayout.Y_AXIS));
        panelDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción"));
        panelDescripcion.setOpaque(false);  // Hacer el panel transparente
        JTextArea txtDescripcion = new JTextArea(3, 30);
        txtDescripcion.setFont(new Font("Monospaced", Font.BOLD, 13));
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panelDescripcion.add(scrollDescripcion);
        contentPane.add(panelDescripcion);

        JPanel panelFechas = new JPanel();
        panelFechas.setLayout(new BoxLayout(panelFechas, BoxLayout.Y_AXIS));
        panelFechas.setBorder(BorderFactory.createTitledBorder("Fechas"));
        panelFechas.setOpaque(false);  // Hacer el panel transparente

        JPanel panelFechaInicio = new JPanel();
        panelFechaInicio.setLayout(new BoxLayout(panelFechaInicio, BoxLayout.X_AXIS));
        panelFechaInicio.setOpaque(false);  
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFechaInicio.setPreferredSize(new Dimension(100, 30));
        JSpinner spinnerDiaInicio = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        JComboBox<String> comboMesInicio = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        comboMesInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
        JSpinner spinnerAnoInicio = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panelFechaInicio.add(lblFechaInicio);
        panelFechaInicio.add(spinnerDiaInicio);
        panelFechaInicio.add(comboMesInicio);
        panelFechaInicio.add(spinnerAnoInicio);
        panelFechas.add(panelFechaInicio);

        JPanel panelFechaFin = new JPanel();
        panelFechaFin.setLayout(new BoxLayout(panelFechaFin, BoxLayout.X_AXIS));
        panelFechaFin.setOpaque(false);  // Hacer el panel transparente
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFechaFin.setPreferredSize(new Dimension(100, 30));
        JSpinner spinnerDiaFin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        JComboBox<String> comboMesFin = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        comboMesFin.setFont(new Font("Tahoma", Font.BOLD, 12));
        JSpinner spinnerAnoFin = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panelFechaFin.add(lblFechaFin);
        panelFechaFin.add(spinnerDiaFin);
        panelFechaFin.add(comboMesFin);
        panelFechaFin.add(spinnerAnoFin);
        panelFechas.add(panelFechaFin);

        contentPane.add(panelFechas);

        JPanel panelTema = new JPanel();
        panelTema.setLayout(new BoxLayout(panelTema, BoxLayout.X_AXIS));
        panelTema.setOpaque(false);  // Hacer el panel transparente
        JLabel lblTema = new JLabel("Tema:");
        lblTema.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTema.setPreferredSize(new Dimension(100, 30));
        txtTema = new JTextField();
        txtTema.setFont(new Font("Monospaced", Font.BOLD, 13));
        txtTema.setPreferredSize(new Dimension(50, 30));  // Reducir el tamaño del JTextField
        panelTema.add(lblTema);
        panelTema.add(txtTema);
        contentPane.add(panelTema);

        JPanel panelMarca = new JPanel();
        panelMarca.setLayout(new BoxLayout(panelMarca, BoxLayout.X_AXIS));
        panelMarca.setOpaque(false);  // Hacer el panel transparente
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMarca.setPreferredSize(new Dimension(100, 30));

        comboMarca = new JComboBox<>();
        comboMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
        cargarMarcasEnComboBox();

        panelMarca.add(lblMarca);
        panelMarca.add(comboMarca);
        contentPane.add(panelMarca);

        comboMarca.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (comboMarca.getSelectedItem().equals("Otros")) {
                    comboMarca.setEditable(true);
                    comboMarca.getEditor().selectAll();
                } else {
                    comboMarca.setEditable(false);
                }
            }
        });

        JPanel panelSesiones = new JPanel();
        panelSesiones.setLayout(new BoxLayout(panelSesiones, BoxLayout.X_AXIS));
        panelSesiones.setOpaque(false);  // Hacer el panel transparente
        JLabel lblSesiones = new JLabel("Sesiones:");
        lblSesiones.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSesiones.setPreferredSize(new Dimension(100, 30));
        txtSesiones = new JTextField("0");
        txtSesiones.setFont(new Font("Monospaced", Font.BOLD, 13));
        txtSesiones.setPreferredSize(new Dimension(20, 30));  // Reducir el tamaño del JTextField
        panelSesiones.add(lblSesiones);
        panelSesiones.add(txtSesiones);
        contentPane.add(panelSesiones);

        txtSesiones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        // Agregar un espacio entre los JTextFields y los botones
        contentPane.add(Box.createVerticalStrut(18));

        // Panel para los botones en una sola fila
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setOpaque(false);  // Hacer el panel transparente

        JButton btnImagen = new JButton("Subir Imagen");
        btnImagen.setForeground(new Color(250, 252, 206));
        btnImagen.setBackground(new Color(64,68,92));
        btnImagen.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagen = fileChooser.getSelectedFile().getAbsolutePath();
            }
        });
        panelBotones.add(btnImagen);

        // Agregar un margen entre los botones
        panelBotones.add(Box.createHorizontalStrut(10));

        JButton btnRecursos = new JButton("Subir Recursos");
        btnRecursos.setForeground(new Color(250, 252, 206));
        btnRecursos.setBackground(new Color(64,68,92));
        btnRecursos.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRecursos.addActionListener(e -> {
        	 JDialog recursosDialog = new JDialog(this, "Seleccionar Recursos", true);
        	    recursosDialog.setBounds(100, 100, 400, 150);
        	    recursosDialog.getContentPane().setLayout(new GridBagLayout());

        	    JLabel lblUrl = new JLabel("URL:");
        	    GridBagConstraints gbc_lblUrl = new GridBagConstraints();
        	    gbc_lblUrl.anchor = GridBagConstraints.EAST;
        	    gbc_lblUrl.insets = new Insets(10, 10, 5, 5);
        	    gbc_lblUrl.gridx = 0;
        	    gbc_lblUrl.gridy = 0;
        	    recursosDialog.getContentPane().add(lblUrl, gbc_lblUrl);

        	    JTextField txtUrl = new JTextField();
        	    GridBagConstraints gbc_txtUrl = new GridBagConstraints();
        	    gbc_txtUrl.insets = new Insets(10, 5, 5, 5);
        	    gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
        	    gbc_txtUrl.gridx = 1;
        	    gbc_txtUrl.gridy = 0;
        	    recursosDialog.getContentPane().add(txtUrl, gbc_txtUrl);
        	    txtUrl.setColumns(20);

        	    JButton btnSubirArchivo = new JButton("Subir Archivo");
        	    btnSubirArchivo.addActionListener(ae -> {
        	        JFileChooser fileChooser = new JFileChooser();
        	        int result = fileChooser.showOpenDialog(recursosDialog);
        	        if (result == JFileChooser.APPROVE_OPTION) {
        	            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
        	            // Guardar el archivo en la carpeta View/resources y obtener la ruta relativa
        	            String rutaGuardada = guardarArchivoEnView(rutaArchivo);
        	            if (rutaGuardada != null) {
        	                recursos = rutaGuardada;
        	                txtUrl.setText(recursos);
        	            }
        	        }
        	    });
        	    GridBagConstraints gbc_btnSubirArchivo = new GridBagConstraints();
        	    gbc_btnSubirArchivo.insets = new Insets(10, 5, 5, 5);
        	    gbc_btnSubirArchivo.gridx = 2;
        	    gbc_btnSubirArchivo.gridy = 0;
        	    recursosDialog.getContentPane().add(btnSubirArchivo, gbc_btnSubirArchivo);

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
        	    recursosDialog.getContentPane().add(btnAceptar, gbc_btnAceptar);

        	    recursosDialog.setVisible(true);
        	});
        panelBotones.add(btnRecursos);

        // Agregar un margen entre los botones
        panelBotones.add(Box.createHorizontalStrut(10));

        JButton btnGuardar = new JButton("Guardar Conferencia");
        btnGuardar.setForeground(new Color(250, 252, 206));
        btnGuardar.setBackground(new Color(64,68,92));
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGuardar.addActionListener(e -> guardarConferencia());
        panelBotones.add(btnGuardar);

        contentPane.add(panelBotones);
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
            String descripcion = ((JTextArea) ((JScrollPane) ((JPanel) contentPane.getComponent(1)).getComponent(0)).getViewport().getView()).getText().trim();
            int diaInicio = (Integer) ((JSpinner) ((JPanel) ((JPanel) contentPane.getComponent(2)).getComponent(0)).getComponent(1)).getValue();
            int mesInicio = ((JComboBox<String>) ((JPanel) ((JPanel) contentPane.getComponent(2)).getComponent(0)).getComponent(2)).getSelectedIndex();
            int anoInicio = (Integer) ((JSpinner) ((JPanel) ((JPanel) contentPane.getComponent(2)).getComponent(0)).getComponent(3)).getValue();
            int diaFin = (Integer) ((JSpinner) ((JPanel) ((JPanel) contentPane.getComponent(2)).getComponent(1)).getComponent(1)).getValue();
            int mesFin = ((JComboBox<String>) ((JPanel) ((JPanel) contentPane.getComponent(2)).getComponent(1)).getComponent(2)).getSelectedIndex();
            int anoFin = (Integer) ((JSpinner) ((JPanel) ((JPanel) contentPane.getComponent(2)).getComponent(1)).getComponent(3)).getValue();
            String tema = txtTema.getText().trim();

            String marca;
            if (comboMarca.isEditable()) {
                marca = comboMarca.getEditor().getItem().toString().trim();
            } else {
                marca = comboMarca.getSelectedItem().toString();
            }

            String sesionesStr = txtSesiones.getText().trim();
            int numSesiones = Integer.parseInt(sesionesStr);

            // Verificar si campos obligatorios están vacíos
            if (titulo.isEmpty() || descripcion.isEmpty() || tema.isEmpty() || marca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título, descripción, tema, y marca son campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (imagen == null || imagen.isEmpty()) {
                imagen = "/View/imagenconferencia.jpeg";
            }
            if (recursos == null || recursos.isEmpty()) {
                recursos = "No disponible";
            }

            Calendar calInicio = Calendar.getInstance();
            calInicio.set(anoInicio, mesInicio, diaInicio);
            Timestamp fechaInicio = new Timestamp(calInicio.getTimeInMillis());

            Calendar calFin = Calendar.getInstance();
            calFin.set(anoFin, mesFin, diaFin);
            Timestamp fechaFin = new Timestamp(calFin.getTimeInMillis());

            if (numSesiones == 0 && !fechaInicio.equals(fechaFin)) {
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

            if (numSesiones > 0) {
                ViewSesiones viewSesiones = new ViewSesiones(connection, titulo, descripcion, fechaInicio, fechaFin, tema, marca, recursos, imagen, usuarioId, numSesiones);
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
    private String guardarArchivoEnView(String rutaArchivo) {
        try {
            // Especificar el directorio dentro de la carpeta View
            String directorioDestino = "src/resources";
            java.io.File dir = new java.io.File(directorioDestino);
            if (!dir.exists()) {
                dir.mkdirs(); // Crear el directorio si no existe
            }

            // Obtener el nombre del archivo
            java.io.File archivoOriginal = new java.io.File(rutaArchivo);
            String nombreArchivo = archivoOriginal.getName();

            // Crear el archivo de destino
            java.io.File archivoDestino = new java.io.File(dir, nombreArchivo);

            // Copiar el archivo al nuevo destino
            java.nio.file.Files.copy(archivoOriginal.toPath(), archivoDestino.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // Retornar la ruta relativa del archivo guardado
            return "src/resources/" + nombreArchivo;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}