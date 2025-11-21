/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.datos;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase;
import co.edu.poli.PoliSongsMarketPlace.negocio.UsuarioManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
/**
 *
 * @author Pardo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPago {

    // Insertar nuevo pago
    public boolean insertar(pago pago) {
        String sql = "INSERT INTO pago (idpago, idorden, metodopago, estadopago, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pago.getIdPago());
            stmt.setString(2, pago.getIdOrden());
            stmt.setString(3, pago.getMetodoPago());
            stmt.setString(4, pago.getEstadoPago());
            stmt.setString(5, pago.getFecha());

            stmt.executeUpdate();
            System.out.println("✅ Pago insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar pago: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los pagos
    public List<pago> listar() {
        List<pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pago p = new pago(
                    rs.getString("idpago"),
                    rs.getString("idorden"),
                    rs.getString("metodopago"),
                    rs.getString("estadopago"),
                    rs.getString("fecha")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pagos: " + e.getMessage());
        }

        return lista;
    }

    // Buscar pago por ID
    public pago buscarPorId(String id) {
        String sql = "SELECT * FROM pago WHERE idpago = ?";
        pago p = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                p = new pago(
                    rs.getString("idpago"),
                    rs.getString("idorden"),
                    rs.getString("metodopago"),
                    rs.getString("estadopago"),
                    rs.getString("fecha")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar pago: " + e.getMessage());
        }

        return p;
    }

    // Buscar pagos por orden
    public List<pago> buscarPorOrden(String idOrden) {
        List<pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago WHERE idorden = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idOrden);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pago p = new pago(
                    rs.getString("idpago"),
                    rs.getString("idorden"),
                    rs.getString("metodopago"),
                    rs.getString("estadopago"),
                    rs.getString("fecha")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar pagos por orden: " + e.getMessage());
        }

        return lista;
    }

    // Buscar pagos por estado
    public List<pago> buscarPorEstado(String estadoPago) {
        List<pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago WHERE estadopago = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estadoPago);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pago p = new pago(
                    rs.getString("idpago"),
                    rs.getString("idorden"),
                    rs.getString("metodopago"),
                    rs.getString("estadopago"),
                    rs.getString("fecha")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar pagos por estado: " + e.getMessage());
        }

        return lista;
    }

    // Buscar pagos por método de pago
    public List<pago> buscarPorMetodoPago(String metodoPago) {
        List<pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago WHERE metodopago = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, metodoPago);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pago p = new pago(
                    rs.getString("idpago"),
                    rs.getString("idorden"),
                    rs.getString("metodopago"),
                    rs.getString("estadopago"),
                    rs.getString("fecha")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar pagos por método: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar pago
    public boolean actualizar(pago pago) {
        String sql = "UPDATE pago SET idorden=?, metodopago=?, estadopago=?, fecha=? WHERE idpago=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pago.getIdOrden());
            stmt.setString(2, pago.getMetodoPago());
            stmt.setString(3, pago.getEstadoPago());
            stmt.setString(4, pago.getFecha());
            stmt.setString(5, pago.getIdPago());

            stmt.executeUpdate();
            System.out.println("✅ Pago actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar pago: " + e.getMessage());
            return false;
        }
    }

    // Actualizar solo el estado del pago
    public boolean actualizarEstado(String idPago, String nuevoEstado) {
        String sql = "UPDATE pago SET estadopago=? WHERE idpago=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setString(2, idPago);

            stmt.executeUpdate();
            System.out.println("✅ Estado de pago actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar estado de pago: " + e.getMessage());
            return false;
        }
    }

    // Eliminar pago
    public boolean eliminar(String id) {
        String sql = "DELETE FROM pago WHERE idpago = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Pago eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar pago: " + e.getMessage());
            return false;
        }
    }

    // Obtener estadísticas de pagos
    public int contarPagosPorEstado(String estadoPago) {
        String sql = "SELECT COUNT(*) as total FROM pago WHERE estadopago = ?";
        int total = 0;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estadoPago);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al contar pagos por estado: " + e.getMessage());
        }

        return total;
    }

    // Verificar si existe un pago para una orden
    public boolean existePagoParaOrden(String idOrden) {
        String sql = "SELECT COUNT(*) as count FROM pago WHERE idorden = ?";
        
        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idOrden);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al verificar pago para orden: " + e.getMessage());
        }

        return false;
    }
}