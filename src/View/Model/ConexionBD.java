package View.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private final String url = "jdbc:postgresql://localhost:5432/chuno";
    private final String user = "postgres";
    private final String password = "admin";

    // Método para obtener la conexión a la base de datos
    public Connection getConexion() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a la base de datos!");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conn;
    }
}
