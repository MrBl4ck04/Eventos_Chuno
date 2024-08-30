package controller;

import Model.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class marcas {

    private ConexionBD conexion;

    public marcas() {
        conexion = new ConexionBD();
    }

    public List<String> obtenerMarcas() {
        List<String> listaMarcas = new ArrayList<>();
        String sql = "SELECT DISTINCT LOWER(marca) AS marca FROM conferencia";

        try (Connection conn = conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String marca = rs.getString("marca");
                String marcaFormateada = marca.substring(0, 1).toUpperCase() + marca.substring(1).toLowerCase();
                if (!listaMarcas.contains(marcaFormateada)) {
                    listaMarcas.add(marcaFormateada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaMarcas;
    }
}
