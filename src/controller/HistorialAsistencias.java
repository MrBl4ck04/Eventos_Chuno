package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialAsistencias {

    private ConexionBD conexion;

    public HistorialAsistencias() {
        conexion = new ConexionBD();
    }

    public List<String[]> obtenerHistorial(int idUsuario) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String[]> historial = new ArrayList<>();

        try {
            conn = conexion.getConexion();
            String sql = "SELECT c.titulo, c.descripcion, c.fecha_inicio, c.fecha_fin, c.tema, c.marca "
                       + "FROM asistencia a "
                       + "JOIN conferencia c ON a.id_conferencia = c.id_conferencia "
                       + "WHERE a.id_usuario = ? AND c.fecha_fin < CURRENT_TIMESTAMP;";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();

            while (rs.next()) {
                String[] conferencia = new String[6];
                conferencia[0] = rs.getString("titulo");
                conferencia[1] = rs.getString("descripcion");
                conferencia[2] = rs.getString("fecha_inicio").toString();
                conferencia[3] = rs.getString("fecha_fin").toString();
                conferencia[4] = rs.getString("tema");
                conferencia[5] = rs.getString("marca");
                historial.add(conferencia);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener historial de asistencias: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return historial;
    }

}
