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

public class DaoNotificacion {

    // Insertar nueva notificación
    public boolean insertar(notificacion notificacion) {
        String sql = "INSERT INTO notification (idnotification, idrelacion, idproducto, mensaje, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, notificacion.getIdNotification());
            stmt.setString(2, notificacion.getIdRelacion());
            stmt.setString(3, notificacion.getIdProducto());
            stmt.setString(4, notificacion.getMensaje());
            stmt.setString(5, notificacion.getFecha());

            stmt.executeUpdate();
            System.out.println("✅ Notificación insertada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar notificación: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las notificaciones
    public List<notificacion> listar() {
        List<notificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM notification";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                notificacion n = new notificacion(
                    rs.getString("idnotification"),
                    rs.getString("idrelacion"),
                    rs.getString("idproducto"),
                    rs.getString("mensaje"),
                    rs.getString("fecha")
                );
                lista.add(n);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar notificaciones: " + e.getMessage());
        }

        return lista;
    }

    // Buscar notificación por ID
    public notificacion buscarPorId(String id) {
        String sql = "SELECT * FROM notification WHERE idnotification = ?";
        notificacion n = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                n = new notificacion(
                    rs.getString("idnotification"),
                    rs.getString("idrelacion"),
                    rs.getString("idproducto"),
                    rs.getString("mensaje"),
                    rs.getString("fecha")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar notificación: " + e.getMessage());
        }

        return n;
    }

    // Buscar notificaciones por relación
    public List<notificacion> buscarPorRelacion(String idRelacion) {
        List<notificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM notification WHERE idrelacion = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idRelacion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notificacion n = new notificacion(
                    rs.getString("idnotification"),
                    rs.getString("idrelacion"),
                    rs.getString("idproducto"),
                    rs.getString("mensaje"),
                    rs.getString("fecha")
                );
                lista.add(n);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar notificaciones por relación: " + e.getMessage());
        }

        return lista;
    }

    // Buscar notificaciones por producto
    public List<notificacion> buscarPorProducto(String idProducto) {
        List<notificacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM notification WHERE idproducto = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                notificacion n = new notificacion(
                    rs.getString("idnotification"),
                    rs.getString("idrelacion"),
                    rs.getString("idproducto"),
                    rs.getString("mensaje"),
                    rs.getString("fecha")
                );
                lista.add(n);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar notificaciones por producto: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar notificación
    public boolean actualizar(notificacion notificacion) {
        String sql = "UPDATE notification SET idrelacion=?, idproducto=?, mensaje=?, fecha=? WHERE idnotification=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, notificacion.getIdRelacion());
            stmt.setString(2, notificacion.getIdProducto());
            stmt.setString(3, notificacion.getMensaje());
            stmt.setString(4, notificacion.getFecha());
            stmt.setString(5, notificacion.getIdNotification());

            stmt.executeUpdate();
            System.out.println("✅ Notificación actualizada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar notificación: " + e.getMessage());
            return false;
        }
    }

    // Eliminar notificación
    public boolean eliminar(String id) {
        String sql = "DELETE FROM notification WHERE idnotification = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Notificación eliminada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar notificación: " + e.getMessage());
            return false;
        }
    }

    // Eliminar notificaciones por relación
    public boolean eliminarPorRelacion(String idRelacion) {
        String sql = "DELETE FROM notification WHERE idrelacion = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idRelacion);
            stmt.executeUpdate();
            System.out.println("✅ Notificaciones de relación eliminadas correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar notificaciones por relación: " + e.getMessage());
            return false;
        }
    }
}
