package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

    private ConexionBD conexion;
    private int idUsuario; // Variable para guardar el id_usuario

    public login() {
        conexion = new ConexionBD();
        idUsuario = -1; // Inicializa con -1 para indicar que no se ha autenticado
    }

    public int autenticar(String email, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = conexion.getConexion();
            String sql = "SELECT id_usuario FROM usuario WHERE email = ? AND contraseña = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt("id_usuario");
                return idUsuario; // Retorna el id_usuario si la autenticación es exitosa
            } else {
                idUsuario = -1; // Restablece a -1 si no se encuentra el usuario
                return -1;
            }

        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
            idUsuario = -1;
            return -1;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    // Método para obtener el idUsuario guardado
    public int getIdUsuario() {
        return idUsuario;
    }
}
