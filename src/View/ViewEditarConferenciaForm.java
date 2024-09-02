package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Conferencia;
import Model.ConferenciaDAO;
import Model.NotificacionDAO;

public class ViewEditarConferenciaForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtTitulo;
    private JTextField txtDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtTema;
    private JTextField txtMarca;
    private Conferencia conferencia;

    public ViewEditarConferenciaForm(Conferencia conferencia) {
        this.conferencia = conferencia;
        
        setTitle("Editar Conferencia");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);
        
        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField(conferencia.getTitulo());
        panel.add(txtTitulo);
        
        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField(conferencia.getDescripcion());
        panel.add(txtDescripcion);
        
        panel.add(new JLabel("Fecha Inicio:"));
        txtFechaInicio = new JTextField(conferencia.getFechaInicio());
        panel.add(txtFechaInicio);
        
        panel.add(new JLabel("Fecha Fin:"));
        txtFechaFin = new JTextField(conferencia.getFechaFin());
        panel.add(txtFechaFin);
        
        panel.add(new JLabel("Tema:"));
        txtTema = new JTextField(conferencia.getTema());
        panel.add(txtTema);
        
        panel.add(new JLabel("Marca:"));
        txtMarca = new JTextField(conferencia.getMarca());
        panel.add(txtMarca);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        panel.add(btnGuardar);
    }

    private void guardarCambios() {
        if (conferencia != null) {  // Verifica que el objeto no sea null
            // Guardar los valores originales
            String oldTitulo = conferencia.getTitulo();
            String oldDescripcion = conferencia.getDescripcion();
            String oldFechaInicio = conferencia.getFechaInicio();
            String oldFechaFin = conferencia.getFechaFin();
            String oldTema = conferencia.getTema();
            String oldMarca = conferencia.getMarca();

            // Actualizar los valores de la conferencia
            conferencia.setTitulo(txtTitulo.getText());
            conferencia.setDescripcion(txtDescripcion.getText());
            conferencia.setFechaInicio(txtFechaInicio.getText());
            conferencia.setFechaFin(txtFechaFin.getText());
            conferencia.setTema(txtTema.getText());
            conferencia.setMarca(txtMarca.getText());

            ConferenciaDAO conferenciaDAO = new ConferenciaDAO();
            if (conferenciaDAO.actualizarConferencia(conferencia)) {
                // Construir el mensaje de notificación basado en las diferencias
                StringBuilder mensajeBuilder = new StringBuilder("La conferencia '");
                mensajeBuilder.append(conferencia.getTitulo()).append("' ");

                if (!oldTitulo.equals(conferencia.getTitulo())) {
                    mensajeBuilder.append("cambió el título de '").append(oldTitulo).append("' a '").append(conferencia.getTitulo()).append("'. ");
                }
                if (!oldDescripcion.equals(conferencia.getDescripcion())) {
                    mensajeBuilder.append("cambió la descripción. ");
                }
                if (!oldFechaInicio.equals(conferencia.getFechaInicio())) {
                    mensajeBuilder.append("cambió la fecha de inicio de '").append(oldFechaInicio).append("' a '").append(conferencia.getFechaInicio()).append("'. ");
                }
                if (!oldFechaFin.equals(conferencia.getFechaFin())) {
                    mensajeBuilder.append("cambió la fecha de fin de '").append(oldFechaFin).append("' a '").append(conferencia.getFechaFin()).append("'. ");
                }
                if (!oldTema.equals(conferencia.getTema())) {
                    mensajeBuilder.append("cambió el tema de '").append(oldTema).append("' a '").append(conferencia.getTema()).append("'. ");
                }
                if (!oldMarca.equals(conferencia.getMarca())) {
                    mensajeBuilder.append("cambió la marca de '").append(oldMarca).append("' a '").append(conferencia.getMarca()).append("'. ");
                }

                // Crear el mensaje final
                String mensaje = mensajeBuilder.toString().trim();
                if (mensaje.endsWith(".")) {
                    mensaje = mensaje.substring(0, mensaje.length() - 1); // Eliminar el punto final si es necesario
                }
                
                // Inserta la notificación
                NotificacionDAO notificacionDAO = new NotificacionDAO();
                Integer idAsistencia = null; // Asignar null si se debe guardar como NULL en la base de datos
                int idConferencia = conferencia.getIdConferencia(); // Asume que tienes un método para obtener el ID de conferencia
                int idSesion = 1; // Asigna el ID de sesión apropiado

                if (notificacionDAO.insertarNotificacion(idAsistencia, idConferencia, idSesion, mensaje, "Actualización")) {
                    JOptionPane.showMessageDialog(this, "Conferencia actualizada y notificación registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Conferencia actualizada, pero no se pudo registrar la notificación.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la conferencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: La conferencia no está inicializada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




}