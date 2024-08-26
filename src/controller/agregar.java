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
        String recursos, String imagen,int id_usuario
    ) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO conferencia (titulo, descripcion, fecha_inicio, fecha_fin, tema, marca, id_usuario, recursos, id_sala, disponibilidad, cupos, imagen) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	int id_sala=1;
        	int dispo=1;
        	int cupos=1;
        	System.out.println("Título: " + titulo);
        	System.out.println("Descripción: " + descripcion);
        	System.out.println("Fecha Inicio: " + fecha_inicio);
        	System.out.println("Fecha Fin: " + fecha_fin);
        	System.out.println("Tema: " + tema);
        	System.out.println("Marca: " + marca);
        	System.out.println("ID Usuario: " + id_usuario);
        	System.out.println("Recursos: " + recursos);
        	System.out.println("ID Sala: " + id_sala);
        	System.out.println("Disponibilidad: " + dispo);
        	System.out.println("Cupos: " + cupos);
        	System.out.println("Imagen: " + imagen);
            conn = conexion.getConexion();
            pstmt = conn.prepareStatement(sql);
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
