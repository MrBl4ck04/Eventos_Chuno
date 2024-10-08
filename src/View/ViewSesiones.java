package View;

import controller.agregar;
import controller.sesiones;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Box;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Time;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class ViewSesiones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelSesiones;
    private String titulo;
    private String descripcion;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private String tema;
    private String marca;
    private String recursos;
    private String imagen;
    private int usuarioId;

    // Lista para almacenar los paneles de sesiones
    private ArrayList<JPanel> listaSesiones = new ArrayList<>();

    // Conexión a la base de datos
    private Connection connection;

    public ViewSesiones(Connection connection, String titulo, String descripcion, Timestamp fechaInicio, Timestamp fechaFin, 
                        String tema, String marca, String recursos, String imagen, int usuarioId, int numSesiones) {
        this.connection = connection;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tema = tema;
        this.marca = marca;
        this.recursos = recursos;
        this.imagen = imagen;
        this.usuarioId = usuarioId;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new BackgroundPanel("/View/backnewreu.jpeg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Panel contenedor de sesiones con scroll
        JScrollPane scrollPane = new JScrollPane();
        panelSesiones = new JPanel();
        panelSesiones.setLayout(new BoxLayout(panelSesiones, BoxLayout.Y_AXIS));
        panelSesiones.setOpaque(false);
        scrollPane.setViewportView(panelSesiones);
        contentPane.add(scrollPane);

        // Panel para los botones en una sola fila
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        buttonPanel.setOpaque(false);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Botón para guardar las sesiones
        JButton btnGuardarSesiones = new JButton("Guardar Sesiones");
        btnGuardarSesiones.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardarSesiones.setForeground(new Color(250, 252, 206));
        btnGuardarSesiones.setBackground(new Color(64,68,92));
        btnGuardarSesiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarConferenciaYSesiones();
            }
        });
        buttonPanel.add(btnGuardarSesiones);

        // Botón para cancelar la operación
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancelar.setForeground(new Color(250, 252, 206));
        btnCancelar.setBackground(new Color(64,68,92));
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        buttonPanel.add(btnCancelar);

        // Automatically add the specified number of sessions
        for (int i = 0; i < numSesiones; i++) {
            agregarSesion();
        }
    }

    /**
     * Método para agregar una nueva sesión (tarjeta).
     * Nota: No se incluye el botón de eliminar.
     */
    private void agregarSesion() {
        JPanel panelSesion = new JPanel();
        panelSesion.setLayout(new BoxLayout(panelSesion, BoxLayout.X_AXIS));
        panelSesion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel lblFecha = new JLabel("Fecha:");
        panelSesion.add(lblFecha);

        JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel());
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
        panelSesion.add(spinnerFecha);

        JLabel lblHoraInicio = new JLabel("Hora Inicio:");
        panelSesion.add(lblHoraInicio);

        JSpinner spinnerHoraInicio = new JSpinner(new SpinnerDateModel());
        spinnerHoraInicio.setEditor(new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm"));
        panelSesion.add(spinnerHoraInicio);

        JLabel lblHoraFin = new JLabel("Hora Fin:");
        panelSesion.add(lblHoraFin);

        JSpinner spinnerHoraFin = new JSpinner(new SpinnerDateModel());
        spinnerHoraFin.setEditor(new JSpinner.DateEditor(spinnerHoraFin, "HH:mm"));
        panelSesion.add(spinnerHoraFin);

        // Agregar el panel de la sesión a la lista y al contenedor principal
        listaSesiones.add(panelSesion);
        panelSesiones.add(panelSesion);
        panelSesiones.revalidate();
        panelSesiones.repaint();
    }


    private void guardarConferenciaYSesiones() {
        try {
        	ArrayList<Object[]> sesionesList = new ArrayList<>();

        	for (JPanel panelSesion : listaSesiones) {
                JSpinner spinnerFecha = (JSpinner) panelSesion.getComponent(1);
                JSpinner spinnerHoraInicio = (JSpinner) panelSesion.getComponent(3);
                JSpinner spinnerHoraFin = (JSpinner) panelSesion.getComponent(5);

                Date fecha = (Date) spinnerFecha.getValue();
                Date horaInicio = (Date) spinnerHoraInicio.getValue();
                Date horaFin = (Date) spinnerHoraFin.getValue();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                calendar.set(Calendar.HOUR_OF_DAY, horaInicio.getHours());
                calendar.set(Calendar.MINUTE, horaInicio.getMinutes());
                Timestamp timestampFecha = new Timestamp(calendar.getTimeInMillis());

                Time timeInicio = new Time(horaInicio.getTime());
                Time timeFin = new Time(horaFin.getTime());

                sesionesList.add(new Object[]{timestampFecha, timeInicio, timeFin});
            }

            agregar agregarConferencia = new agregar();
            int idConferencia = agregarConferencia.agregarConferencia(titulo, descripcion, fechaInicio, fechaFin, tema, marca, recursos, imagen, usuarioId);

            if (idConferencia > 0) {

                sesiones sesionController = new sesiones();
                boolean exitoSesiones = sesionController.guardarSesiones(idConferencia, sesionesList);

                if (exitoSesiones) {
                    JOptionPane.showMessageDialog(this, "Conferencia y sesiones guardadas con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cerrar ViewSesiones después de guardar
                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar las sesiones.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar la conferencia y las sesiones.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}