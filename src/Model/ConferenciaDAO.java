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

    // Método para obtener todas las conferencias desde la base de datos
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

    // Método para buscar conferencias por título
    public List<Conferencia> buscarConferenciasPorTitulo(String titulo) {
        List<Conferencia> conferencias = new ArrayList<>();
        String query = "SELECT * FROM conferencia WHERE titulo ILIKE ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + titulo + "%"); // Coincidencias parciales

            try (ResultSet rs = pstmt.executeQuery()) {
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
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar las conferencias: " + e.getMessage());
        }

        return conferencias;
    }

    // Método para verificar si un usuario ya está registrado en una conferencia
    public boolean isUsuarioRegistrado(int idUsuario, int idConferencia) {
        String query = "SELECT COUNT(*) FROM asistencia WHERE id_usuario = ? AND id_conferencia = ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idConferencia);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;  // Si el conteo es mayor a 0, el usuario ya está registrado
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar registro del usuario: " + e.getMessage());
        }

        return false;  // Por defecto, consideramos que no está registrado si ocurre un error
    }

    // Método para registrar la asistencia a una conferencia y actualizar los cupos
    public boolean registrarAsistencia(int idUsuario, int idConferencia, int voto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exito = false;

        try {
            conn = conexionBD.getConexion();
            conn.setAutoCommit(false); // Iniciar transacción

            // 1. Registrar la asistencia
            String queryAsistencia = "INSERT INTO asistencia (id_usuario, id_conferencia, voto, estado) VALUES (?, ?, ?, 'Registrado')";
            pstmt = conn.prepareStatement(queryAsistencia);
            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idConferencia);
            pstmt.setInt(3, voto);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // 2. Incrementar el número de cupos en la conferencia
                String queryActualizarCupos = "UPDATE conferencia SET cupos = cupos + 1 WHERE id_conferencia = ?";
                pstmt = conn.prepareStatement(queryActualizarCupos);
                pstmt.setInt(1, idConferencia);
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    conn.commit(); // Confirmar transacción si todo va bien
                    exito = true;
                } else {
                    conn.rollback(); // Revertir cambios si no se pudo actualizar los cupos
                }
            } else {
                conn.rollback(); // Revertir cambios si no se pudo registrar la asistencia
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revertir cambios si ocurre algún error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error al registrar la asistencia: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exito;
    }
}
