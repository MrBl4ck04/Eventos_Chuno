package View;

import controller.HistorialAsistencias;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewHistorialAsistente extends JFrame {
    private JTable table;
    private int idUsuario;

    public ViewHistorialAsistente(int idUsuario) {
        this.idUsuario = idUsuario;
        setTitle("Historial de Asistencias");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        HistorialAsistencias historialAsistencias = new HistorialAsistencias();
        List<String[]> historial = historialAsistencias.obtenerHistorial(idUsuario);

        String[] columnNames = {"Título", "Descripción", "Fecha Inicio", "Fecha Fin", "Tema", "Marca", "Eliminar", "Votar"};
        String[][] data = new String[historial.size()][columnNames.length];

        for (int i = 0; i < historial.size(); i++) {
            System.arraycopy(historial.get(i), 0, data[i], 0, historial.get(i).length);
            data[i][columnNames.length - 2] = "Eliminar"; // Para el botón de eliminar
            data[i][columnNames.length - 1] = "Votar"; // Para el botón de votar
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setForeground(new Color(64, 0, 128));
                c.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
                return c;
            }
        };
        table.setForeground(new Color(64, 0, 128));
        table.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
        table.setBackground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setForeground(new Color(64, 0, 128));
        header.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
        header.setBackground(Color.WHITE);

        TableColumnModel columnModel = table.getColumnModel();

     // Configurar la columna "Eliminar"
     TableColumn eliminarColumn = columnModel.getColumn(columnModel.getColumnCount() - 2);
     eliminarColumn.setCellRenderer(new ButtonRenderer("Eliminar"));
     eliminarColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

     // Configurar la columna "Votar"
     TableColumn votarColumn = columnModel.getColumn(columnModel.getColumnCount() - 1);
     votarColumn.setCellRenderer(new ButtonRenderer("Votar"));
     votarColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelWithPadding = new BackgroundPanel("/View/backHistorialA.png");
        panelWithPadding.setLayout(new BorderLayout());
        panelWithPadding.setBorder(BorderFactory.createEmptyBorder(50, 15, 30, 15));
        panelWithPadding.add(scrollPane, BorderLayout.CENTER);

        JButton volverButton = new JButton("Volver");
        volverButton.setForeground(new Color(64, 0, 128));
        volverButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewPaginaPrincipalAsistente(idUsuario).setVisible(true);
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(volverButton, BorderLayout.EAST);

        panelWithPadding.add(topPanel, BorderLayout.NORTH);

        getContentPane().add(panelWithPadding, BorderLayout.CENTER);
    }

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

 // Modificar el ButtonRenderer para manejar ambos botones
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String label) {
            setOpaque(true);
            setText(label);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (column == table.getColumnCount() - 1) { // Última columna para votar
                setText("Votar");
            } else if (column == table.getColumnCount() - 2) { // Penúltima columna para eliminar
                setText("Eliminar");
            }
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton button;
        private int row;
        private int column;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox) {
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            this.column = column;
            if (column == table.getColumnCount() - 1) { // Última columna para votar
                button.setText("Votar");
            } else if (column == table.getColumnCount() - 2) { // Penúltima columna para eliminar
                button.setText("Eliminar");
            }
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (column == table.getColumnCount() - 1) { // Acción de votar
                stopCellEditing();
                JComboBox<Integer> comboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5});
                int voto = JOptionPane.showConfirmDialog(button, comboBox, "Selecciona tu voto", JOptionPane.OK_CANCEL_OPTION);
                if (voto == JOptionPane.OK_OPTION) {
                    int valorVoto = (int) comboBox.getSelectedItem();
                    String tituloConferencia = (String) table.getValueAt(row, 0);
                    HistorialAsistencias historialAsistencias = new HistorialAsistencias();
                    historialAsistencias.guardarVoto(idUsuario, tituloConferencia, valorVoto);
                    JOptionPane.showMessageDialog(button, "Voto guardado.");
                }
            } else if (column == table.getColumnCount() - 2) { // Acción de eliminar
                // Código existente para eliminar
            }
        }
    }
}
