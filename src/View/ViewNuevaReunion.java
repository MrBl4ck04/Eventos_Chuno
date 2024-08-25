package View;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.Calendar;
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

public class ViewNuevaReunion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTitulo;
    private JTextField txtTema;
    private JTextField txtMarca;
    private JTextField txtSesion;
    private String imagenPath;
    private String recursosPathOrUrl;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewNuevaReunion frame = new ViewNuevaReunion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewNuevaReunion() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 400); // Hacer la ventana más compacta
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Añadir margen al contenido
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Usar BoxLayout para organizar los elementos
        setContentPane(contentPane);
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS));
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setPreferredSize(new Dimension(80, 30)); // Tamaño fijo para etiquetas
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
        txtMarca = new JTextField();
        panelMarca.add(lblMarca);
        panelMarca.add(txtMarca);
        contentPane.add(panelMarca);

        JPanel panelSesion = new JPanel();
        panelSesion.setLayout(new BoxLayout(panelSesion, BoxLayout.X_AXIS));
        JLabel lblSesion = new JLabel("Sesión:");
        lblSesion.setPreferredSize(new Dimension(80, 30));
        txtSesion = new JTextField();
        panelSesion.add(lblSesion);
        panelSesion.add(txtSesion);
        contentPane.add(panelSesion);
        
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.X_AXIS));
        JButton btnImagen = new JButton("Subir Imagen");
        btnImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                imagenPath = fileChooser.getSelectedFile().getAbsolutePath();
                // Aquí podrías hacer algo con la imagen, como mostrarla en la interfaz, etc.
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
                    recursosPathOrUrl = fileChooser.getSelectedFile().getAbsolutePath();
                    txtUrl.setText(recursosPathOrUrl);
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
                    recursosPathOrUrl = txtUrl.getText().trim();
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
        panelGuardar.add(btnGuardar);
        contentPane.add(panelGuardar);
    }
}
