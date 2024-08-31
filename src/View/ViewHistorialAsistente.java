package View;

import controller.HistorialAsistencias;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        table.setBackground(Color.WHITE); // Establecer el fondo de la tabla a blanco

        // Establecer el encabezado de la tabla con la misma fuente y color
        JTableHeader header = table.getTableHeader();
        header.setForeground(new Color(64, 0, 128)); // Color de la fuente del encabezado
        header.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15)); // Fuente del encabezado
        header.setBackground(Color.WHITE); // Establecer el fondo del encabezado a blanco

        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un panel con borde superior y fondo con imagen
        JPanel panelWithPadding = new BackgroundPanel("/View/backHistorialA.png");
        panelWithPadding.setLayout(new BorderLayout());
        panelWithPadding.setBorder(BorderFactory.createEmptyBorder(50, 15, 30, 15)); 
        panelWithPadding.add(scrollPane, BorderLayout.CENTER);

        // Crear el botón "Volver"
        JButton volverButton = new JButton("Volver");
        volverButton.setForeground(new Color(64, 0, 128)); 
        volverButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15)); 
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                dispose();
                // Abrir la vista principal (ViewPaginaPrincipalAsistente)
                new ViewPaginaPrincipalAsistente(idUsuario).setVisible(true);
            }
        });

        // Crear un panel superior para colocar el botón "Volver"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Hacer transparente el panel superior
        topPanel.add(volverButton, BorderLayout.EAST); // Colocar el botón en la parte superior derecha

        // Agregar el panel superior al panel principal
        panelWithPadding.add(topPanel, BorderLayout.NORTH);

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
