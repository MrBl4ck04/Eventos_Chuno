package View;

import controller.HistorialAsistencias;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ViewHistorialAsistente extends JFrame {

    public ViewHistorialAsistente(int idUsuario) {
        setTitle("Historial de Asistencias");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        HistorialAsistencias historialAsistencias = new HistorialAsistencias();
        List<String[]> historial = historialAsistencias.obtenerHistorial(idUsuario);

        String[] columnNames = {"Título", "Descripción", "Fecha Inicio", "Fecha Fin", "Tema", "Marca"};
        String[][] data = historial.toArray(new String[0][]);

        // Crear la tabla con los datos y las columnas
        JTable table = new JTable(data, columnNames) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setForeground(new Color(64, 0, 128)); // Color de la fuente
                c.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15)); // Fuente
                return c;
            }
        };
        table.setForeground(new Color(64, 0, 128)); // Color de la fuente
        table.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15)); // Fuente

        // Establecer el encabezado de la tabla con la misma fuente y color
        JTableHeader header = table.getTableHeader();
        header.setForeground(new Color(64, 0, 128)); // Color de la fuente del encabezado
        header.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15)); // Fuente del encabezado

        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un panel con borde superior y fondo con imagen
        JPanel panelWithPadding = new BackgroundPanel("/View/backHistorialA.png");
        panelWithPadding.setLayout(new BorderLayout());
        panelWithPadding.setBorder(BorderFactory.createEmptyBorder(80, 15, 15, 15)); // Espacio: superior, izquierda, inferior, derecha
        panelWithPadding.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panelWithPadding, BorderLayout.CENTER);
    }

    // Clase interna para manejar un panel con imagen de fondo
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            backgroundImage = new ImageIcon(ViewHistorialAsistente.class.getResource(fileName)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
