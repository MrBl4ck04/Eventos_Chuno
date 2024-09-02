package View;

import Model.NotificacionDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clase personalizada para panel con imagen de fondo
class PanelConFondo extends JPanel {
    private BufferedImage fondo;

    public PanelConFondo(String rutaImagen) {
        try {
            fondo = ImageIO.read(getClass().getResource(rutaImagen));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

public class ViewNotificacion extends JFrame {
    private static final long serialVersionUID = 1L;

    public ViewNotificacion() {
        setTitle("Notificaciones");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Usar el panel con fondo
        PanelConFondo panel = new PanelConFondo("/View/backNotificacion.png");
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Cargar las notificaciones desde la base de datos
        NotificacionDAO notificacionDAO = new NotificacionDAO();
        StringBuilder notificaciones = new StringBuilder("\u2713 "); // Añadir el símbolo de check sin salto de línea

        try {
            ResultSet rs = notificacionDAO.obtenerNotificaciones(); // Implementa este método en NotificacionDAO
            while (rs.next()) {
                String mensaje = rs.getString("mensaje");
                notificaciones.append(mensaje).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Crear un área de texto para mostrar las notificaciones
        JTextArea textArea = new JTextArea();
        textArea.setBounds(26, 75, 535, 260);
        panel.add(textArea);
        textArea.setEditable(false);
        
        // Configurar la fuente del área de texto
        textArea.setFont(new Font("Arial Unicode MS", Font.BOLD, 10));

        // Mostrar las notificaciones en el área de texto
        textArea.setText(notificaciones.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(24, 75, 537, 260);
        panel.add(scrollPane);
    }
}