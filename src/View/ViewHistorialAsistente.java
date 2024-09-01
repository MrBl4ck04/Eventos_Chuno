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

        String[] columnNames = {"Título", "Descripción", "Fecha Inicio", "Fecha Fin", "Tema", "Marca", "Acciones"};
        String[][] data = new String[historial.size()][columnNames.length];

        for (int i = 0; i < historial.size(); i++) {
            System.arraycopy(historial.get(i), 0, data[i], 0, historial.get(i).length);
            data[i][columnNames.length - 1] = "Eliminar";
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
        TableColumn actionColumn = columnModel.getColumn(columnModel.getColumnCount() - 1);
        actionColumn.setCellRenderer(new ButtonRenderer());
        actionColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

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

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("Eliminar");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton button;
        private int row;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox) {
            button = new JButton("Eliminar");
            button.setOpaque(true);
            button.addActionListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"Sí", "No"};
            int confirm = JOptionPane.showOptionDialog(
                button,
                "¿Estás seguro de que quieres eliminar la conferencia?",
                "Confirmar Eliminación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[1]  
            );

            // 0 es sí
            if (confirm == 0) { 
                stopCellEditing();

                String tituloConferencia = (String) table.getValueAt(row, 0);

                HistorialAsistencias historialAsistencias = new HistorialAsistencias();
                historialAsistencias.eliminarConferencia(idUsuario, tituloConferencia);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(button, "No hay más conferencias en el historial.");
                } else {
                    JOptionPane.showMessageDialog(button, "Conferencia eliminada del historial.");
                }
            } else {
                stopCellEditing();
            }
        }
    }
}
