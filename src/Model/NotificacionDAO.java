package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificacionDAO {
    private ConexionBD conexionBD;

    public NotificacionDAO() {
        this.conexionBD = new ConexionBD(); // Crear instancia de ConexionBD
    }

    public boolean insertarNotificacion(Integer idAsistencia, int idConferencia, int idSesion, String mensaje, String tipoNotificacion) {
        String query = "INSERT INTO notificaciones (id_asistencia, id_conferencia, id_sesion, mensaje, tipo_notificacion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexionBD.getConexion(); // Obtener conexión
             PreparedStatement stmt = conn.prepareStatement(query)) { // Preparar la sentencia
            if (idAsistencia != null) {
                stmt.setInt(1, idAsistencia);
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER); // Manejar null
            }
            stmt.setInt(2, idConferencia);
            stmt.setInt(3, idSesion);
            stmt.setString(4, mensaje);
            stmt.setString(5, tipoNotificacion);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet obtenerNotificaciones() throws SQLException {
        String query = "SELECT mensaje FROM notificaciones";
        Connection conn = conexionBD.getConexion();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }
}
