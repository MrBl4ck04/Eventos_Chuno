package controller;

import View.Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registro {

    private ConexionBD conexion;

    public Registro() {
        conexion = new ConexionBD();
    }

    public boolean registrarUsuario(String nombre, String apellido, String email, String password, String telefono) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Obtener la conexión a la base de datos
            conn = conexion.getConexion(); 
            
            // Sentencia SQL para insertar un nuevo usuario en la base de datos, asignando un valor booleano por defecto a 'rol'
            String sql = "INSERT INTO usuario (nombre, apellido, email, rol, contraseña, telefono) VALUES (?, ?, ?, ?, ?, ?)";
            
            // Preparar la sentencia
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setString(3, email);
            pst.setBoolean(4, false); // Asigna 'false' como valor predeterminado para el rol
            pst.setString(5, password);
            pst.setString(6, telefono);

            // Ejecutar la sentencia SQL
            int result = pst.executeUpdate();

            // Retornar verdadero si la inserción fue exitosa (es decir, si se afectó al menos una fila)
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        } finally {
            try {
                // Cerrar los recursos
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
