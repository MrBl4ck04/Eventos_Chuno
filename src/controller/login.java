package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

    private ConexionBD conexion;

    public login() {
        conexion = new ConexionBD();
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
                return rs.getInt("id_usuario");
            } else {
                return -1; // Retorna -1 si no se encuentra el usuario
            }

        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
            return -1; // Retorna -1 en caso de error
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
}
