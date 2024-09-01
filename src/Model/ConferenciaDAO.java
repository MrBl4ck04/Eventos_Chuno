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
    
    //Método para eliminar conferencia de la bdd (ORADOR)
    public boolean eliminarConferencia(int idConferencia) {
        String query = "DELETE FROM conferencia WHERE id_conferencia = ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idConferencia);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la conferencia: " + e.getMessage());
            return false;
        }
    }
    
    public List<Sesion> obtenerSesionesPorConferencia(int idConferencia) {
        List<Sesion> sesiones = new ArrayList<>();
        try (Connection con = conexionBD.getConexion()) {
            String sql = "SELECT * FROM sesion WHERE id_conferencia = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idConferencia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sesion sesion = new Sesion();
                sesion.setIdSesion(rs.getInt("id_sesion"));
                sesion.setIdConferencia(rs.getInt("id_conferencia"));
                sesion.setFecha(rs.getDate("fecha"));
                sesion.setHoraInicio(rs.getTime("hora_inicio"));
                sesion.setHoraFin(rs.getTime("hora_fin"));
                sesion.setIdSala(rs.getInt("id_sala"));
                sesiones.add(sesion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sesiones;
    }
    
 // Método para obtener las próximas conferencias de un orador específico
    public List<Conferencia> obtenerProximasConferenciasPorOrador(int idUsuario) {
        List<Conferencia> conferencias = new ArrayList<>();
        String query = "SELECT * FROM conferencia WHERE id_usuario = ? AND fecha_inicio > CURRENT_TIMESTAMP";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();

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
            System.err.println("Error al obtener las próximas conferencias para el orador: " + e.getMessage());
        }

        return conferencias;
    }
    
 // Método para obtener las próximas conferencias a las que un asistente está registrado
    public List<Conferencia> obtenerProximasConferenciasPorAsistente(int idUsuario) {
        List<Conferencia> conferencias = new ArrayList<>();
        String query = "SELECT c.* FROM conferencia c "
                + "JOIN asistencia a ON c.id_conferencia = a.id_conferencia "
                + "WHERE a.id_usuario = ? AND c.fecha_inicio > CURRENT_TIMESTAMP "
                + "ORDER BY c.fecha_inicio ASC";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();

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
            System.err.println("Error al obtener las próximas conferencias para el asistente: " + e.getMessage());
        }

        return conferencias;
    }
    
    public List<Conferencia> obtenerConferenciasPorOrador(int idUsuario) {
        List<Conferencia> conferencias = new ArrayList<>();
        String query = "SELECT * FROM conferencia WHERE id_usuario = ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();

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
            System.err.println("Error al obtener las conferencias para el orador: " + e.getMessage());
        }

        return conferencias;
    }
}

