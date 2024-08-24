package View.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static void main(String[] args) {
        
        String url = "jdbc:postgresql://localhost:5432/chuno";
        String user = "postgres";
        String password = "admin";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Conectado a la base de datos!");
            } else {
                System.out.println("Fallo al conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
