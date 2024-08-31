package View;

import controller.HistorialAsistencias;
import javax.swing.*;
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

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }
}
