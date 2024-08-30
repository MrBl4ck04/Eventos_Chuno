package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class sesiones {
    private ConexionBD conexion;

    public sesiones() {
        conexion = new ConexionBD();
    }

    public boolean guardarSesiones(int idConferencia, ArrayList<Object[]> sesionesList) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO sesion (id_conferencia, fecha, hora_inicio, hora_fin,id_sala) VALUES (?, ?, ?, ?,?)";
        
        try {
            conn = conexion.getConexion();
            pstmt = conn.prepareStatement(sql);
            int id_sala=1;
            for (Object[] sesion : sesionesList) {
                pstmt.setInt(1, idConferencia);
                pstmt.setTimestamp(2, (Timestamp) sesion[0]); 
                pstmt.setTime(3, (Time) sesion[1]);  
                pstmt.setTime(4, (Time) sesion[2]); 
                pstmt.setInt(5, id_sala);
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
}
