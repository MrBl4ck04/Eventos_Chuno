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

