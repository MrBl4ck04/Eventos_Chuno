package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class agregar {
    private ConexionBD conexion;

    public agregar() {
        conexion = new ConexionBD();
    }

    public boolean agregarConferencia(
        String titulo, String descripcion, java.sql.Timestamp fecha_inicio, 
        java.sql.Timestamp fecha_fin, String tema, String marca, 
        String recursos, String imagen
    ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO conferencia (titulo, descripcion, fecha_inicio, fecha_fin, tema, marca, recursos, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn = conexion.getConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, titulo);
            pstmt.setString(2, descripcion);
            pstmt.setTimestamp(3, fecha_inicio);
            pstmt.setTimestamp(4, fecha_fin);
            pstmt.setString(5, tema);
            pstmt.setString(6, marca);
            pstmt.setString(7, recursos);
            pstmt.setString(8, imagen);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; 
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
}
