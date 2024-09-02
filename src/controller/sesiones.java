package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.ResultSet; // Asegúrate de importar esta clase
import java.util.ArrayList;

public class sesiones {
    private ConexionBD conexion;

    public sesiones() {
        conexion = new ConexionBD();
    }

    public boolean guardarSesiones(int idConferencia, ArrayList<Object[]> sesionesList) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO sesion (id_conferencia, fecha, hora_inicio, hora_fin, id_sala) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = conexion.getConexion();
            pstmt = conn.prepareStatement(sql);

            // Obtener el cupo actual de la conferencia
            int cuposActuales = obtenerCuposConferencia(idConferencia);

            for (Object[] sesion : sesionesList) {
                int idSala = encontrarSalaDisponibleParaSesion(cuposActuales, (Timestamp) sesion[0], (Time) sesion[1], (Time) sesion[2]);
                if (idSala == -1) {
                    System.err.println("No hay salas disponibles con la capacidad necesaria para la sesión en la fecha y hora especificadas.");
                    return false; // No continuar si no hay una sala disponible
                }
                pstmt.setInt(1, idConferencia);
                pstmt.setTimestamp(2, (Timestamp) sesion[0]);
                pstmt.setTime(3, (Time) sesion[1]);
                pstmt.setTime(4, (Time) sesion[2]);
                pstmt.setInt(5, idSala);
                pstmt.addBatch();
            }

            int[] rowsAffected = pstmt.executeBatch();
            return rowsAffected.length == sesionesList.size();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int encontrarSalaDisponibleParaSesion(int cuposActuales, Timestamp fecha, Time horaInicio, Time horaFin) {
        String query = "SELECT id_sala FROM sala WHERE capacidad >= ? " +
                       "AND id_sala NOT IN (SELECT id_sala FROM sesion WHERE fecha = ? AND " +
                       "((hora_inicio <= ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin >= ?) OR (hora_inicio >= ? AND hora_fin <= ?))) " +
                       "ORDER BY capacidad ASC LIMIT 1";

        try (Connection conn = conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, cuposActuales);
            pstmt.setTimestamp(2, fecha);
            pstmt.setTime(3, horaInicio);
            pstmt.setTime(4, horaInicio);
            pstmt.setTime(5, horaFin);
            pstmt.setTime(6, horaFin);
            pstmt.setTime(7, horaInicio);
            pstmt.setTime(8, horaFin);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_sala");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no encuentra una sala adecuada
    }

    private int obtenerCuposConferencia(int idConferencia) {
        String query = "SELECT cupos FROM conferencia WHERE id_conferencia = ?";
        try (Connection conn = conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idConferencia);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cupos");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorna 0 si no se encuentra el cupo
    }

    private String obtenerNumeroSala(int idSala) {
        if (idSala == 0) {
            return null;
        }
        String numeroSala = null;
        String query = "SELECT numero FROM sala WHERE id_sala = ?";
        try (Connection conn = conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1, idSala); // Establecer el valor del parámetro antes de ejecutar la consulta

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    numeroSala = rs.getString("numero"); // Obtener el número de la sala
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el número de la sala: " + e.getMessage());
        }
        return numeroSala;
    }
}