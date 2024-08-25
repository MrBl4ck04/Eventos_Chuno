package View;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ViewNuevaReunion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTitulo;
    private JTextField txtTema;
    private JTextField txtMarca;

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
        setBounds(100, 100, 516, 386);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        JLabel lblTitulo = new JLabel("Título:");
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.anchor = GridBagConstraints.EAST;
        gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
        gbc_lblTitulo.gridx = 0;
        gbc_lblTitulo.gridy = 0;
        contentPane.add(lblTitulo, gbc_lblTitulo);
        
        txtTitulo = new JTextField();
        GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
        gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
        gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTitulo.gridx = 1;
        gbc_txtTitulo.gridy = 0;
        contentPane.add(txtTitulo, gbc_txtTitulo);
        txtTitulo.setColumns(10);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.NORTH;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 1;
        contentPane.add(lblDescripcion, gbc_lblDescripcion);
        
        JTextArea txtDescripcion = new JTextArea();
        GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
        gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
        gbc_txtDescripcion.gridx = 1;
        gbc_txtDescripcion.gridy = 1;
        gbc_txtDescripcion.gridwidth = 2;
        gbc_txtDescripcion.gridheight = 3;
        contentPane.add(txtDescripcion, gbc_txtDescripcion);
        
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
        gbc_lblFechaInicio.anchor = GridBagConstraints.EAST;
        gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaInicio.gridx = 0;
        gbc_lblFechaInicio.gridy = 4;
        contentPane.add(lblFechaInicio, gbc_lblFechaInicio);
        
        JPanel panelFechaInicio = new JPanel();
        GridBagConstraints gbc_panelFechaInicio = new GridBagConstraints();
        gbc_panelFechaInicio.insets = new Insets(0, 0, 5, 5);
        gbc_panelFechaInicio.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelFechaInicio.gridx = 1;
        gbc_panelFechaInicio.gridy = 4;
        contentPane.add(panelFechaInicio, gbc_panelFechaInicio);
        
        JSpinner spinnerDiaInicio = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        panelFechaInicio.add(spinnerDiaInicio);
        
        JComboBox<String> comboMesInicio = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                                                                        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        panelFechaInicio.add(comboMesInicio);
        
        JSpinner spinnerAnoInicio = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panelFechaInicio.add(spinnerAnoInicio);
        
        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        GridBagConstraints gbc_lblFechaFin = new GridBagConstraints();
        gbc_lblFechaFin.anchor = GridBagConstraints.EAST;
        gbc_lblFechaFin.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaFin.gridx = 0;
        gbc_lblFechaFin.gridy = 5;
        contentPane.add(lblFechaFin, gbc_lblFechaFin);
        
        JPanel panelFechaFin = new JPanel();
        GridBagConstraints gbc_panelFechaFin = new GridBagConstraints();
        gbc_panelFechaFin.insets = new Insets(0, 0, 5, 5);
        gbc_panelFechaFin.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelFechaFin.gridx = 1;
        gbc_panelFechaFin.gridy = 5;
        contentPane.add(panelFechaFin, gbc_panelFechaFin);
        
        JSpinner spinnerDiaFin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        panelFechaFin.add(spinnerDiaFin);
        
        JComboBox<String> comboMesFin = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                                                                     "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        panelFechaFin.add(comboMesFin);
        
        JSpinner spinnerAnoFin = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
        panelFechaFin.add(spinnerAnoFin);
        
        JLabel lblTema = new JLabel("Tema:");
        GridBagConstraints gbc_lblTema = new GridBagConstraints();
        gbc_lblTema.anchor = GridBagConstraints.EAST;
        gbc_lblTema.insets = new Insets(0, 0, 5, 5);
        gbc_lblTema.gridx = 0;
        gbc_lblTema.gridy = 6;
        contentPane.add(lblTema, gbc_lblTema);
        
        txtTema = new JTextField();
        GridBagConstraints gbc_txtTema = new GridBagConstraints();
        gbc_txtTema.insets = new Insets(0, 0, 5, 5);
        gbc_txtTema.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTema.gridx = 1;
        gbc_txtTema.gridy = 6;
        contentPane.add(txtTema, gbc_txtTema);
        txtTema.setColumns(10);
        
        JLabel lblMarca = new JLabel("Marca:");
        GridBagConstraints gbc_lblMarca = new GridBagConstraints();
        gbc_lblMarca.anchor = GridBagConstraints.EAST;
        gbc_lblMarca.insets = new Insets(0, 0, 0, 5);
        gbc_lblMarca.gridx = 0;
        gbc_lblMarca.gridy = 7;
        contentPane.add(lblMarca, gbc_lblMarca);
        
        txtMarca = new JTextField();
        GridBagConstraints gbc_txtMarca = new GridBagConstraints();
        gbc_txtMarca.insets = new Insets(0, 0, 0, 5);
        gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMarca.gridx = 1;
        gbc_txtMarca.gridy = 7;
        contentPane.add(txtMarca, gbc_txtMarca);
        txtMarca.setColumns(10);
        
        JButton btnGuardar = new JButton("Guardar Conferencia");
        GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
        gbc_btnGuardar.gridx = 2;
        gbc_btnGuardar.gridy = 8;
        contentPane.add(btnGuardar, gbc_btnGuardar);
    }
}
