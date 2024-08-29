package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConferenciaDAO {

    private ConexionBD conexionBD;

    public ConferenciaDAO() {
        this.conexionBD = new ConexionBD();
    }

    // Método para obtener las conferencias desde la base de datos
    public List<Conferencia> obtenerConferencias() {
        List<Conferencia> conferencias = new ArrayList<>();
        String query = "SELECT * FROM conferencia";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Conferencia conferencia = new Conferencia(
                        rs.getInt("id_conferencia"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha_inicio"),
                        rs.getString("fecha_fin"),
                        rs.getString("tema"),
                        rs.getString("marca"),
                        rs.getInt("id_usuario"),
                        rs.getString("recursos"),
                        rs.getInt("id_sala"),
                        rs.getInt("disponibilidad"),
                        rs.getInt("cupos")
                );
                conferencias.add(conferencia);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las conferencias: " + e.getMessage());
        }

        return conferencias;
    }

    // Método para registrar la asistencia a una conferencia
    public boolean registrarAsistencia(int idUsuario, int idConferencia) {
        String query = "INSERT INTO asistencia (id_usuario, id_conferencia, estado) VALUES (?, ?, 'Registrado')";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idConferencia);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar la asistencia: " + e.getMessage());
            return false;
        }
    }
}
