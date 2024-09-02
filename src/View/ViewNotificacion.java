package View;

import Model.NotificacionDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewNotificacion extends JFrame {
    private static final long serialVersionUID = 1L;

    public ViewNotificacion() {
        setTitle("Notificaciones");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel para mostrar las notificaciones
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        // Crear un área de texto para mostrar las notificaciones
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Cargar las notificaciones desde la base de datos
        NotificacionDAO notificacionDAO = new NotificacionDAO();
        StringBuilder notificaciones = new StringBuilder();

        try {
            ResultSet rs = notificacionDAO.obtenerNotificaciones(); // Implementa este método en NotificacionDAO
            while (rs.next()) {
                String mensaje = rs.getString("mensaje");
                notificaciones.append(mensaje).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mostrar las notificaciones en el área de texto
        textArea.setText(notificaciones.toString());
    }
}