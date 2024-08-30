package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class agregar {
    private ConexionBD conexion;

    public agregar() {
        conexion = new ConexionBD();
    }

    public int agregarConferencia(
        String titulo, String descripcion, java.sql.Timestamp fecha_inicio, 
        java.sql.Timestamp fecha_fin, String tema, String marca, 
        String recursos, String imagen, int id_usuario
    ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO conferencia (titulo, descripcion, fecha_inicio, fecha_fin, tema, marca, id_usuario, recursos, id_sala, disponibilidad, cupos, imagen) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int id_sala = 1;
            int dispo = 1;
            int cupos = 1;
            conn = conexion.getConexion();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Preparar para obtener claves generadas
            pstmt.setString(1, titulo);
            pstmt.setString(2, descripcion);
            pstmt.setTimestamp(3, fecha_inicio);
            pstmt.setTimestamp(4, fecha_fin);
            pstmt.setString(5, tema);
            pstmt.setString(6, marca);
            pstmt.setInt(7, id_usuario);
            pstmt.setString(8, recursos);
            pstmt.setInt(9, id_sala);
            pstmt.setInt(10, dispo);
            pstmt.setInt(11, cupos);
            pstmt.setString(12, imagen);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Retornar el ID de la conferencia generada
                }
            }
            return -1; // Indicar error si no se insertó la conferencia
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Indicar error en caso de excepción
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
}
