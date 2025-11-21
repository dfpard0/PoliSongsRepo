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
import co.edu.poli.PoliSongsMarketPlace.Managers.*;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pardo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoOrden {

    // Insertar nueva orden
    public boolean insertar(Orden orden) {
        String sql = "INSERT INTO orden (idorden, idcarrito, fecha, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orden.getIdOrden());
            stmt.setString(2, orden.getIdCarrito());
            stmt.setString(3, orden.getFecha());
            stmt.setString(4, orden.getEstado());

            stmt.executeUpdate();
            System.out.println("✅ Orden insertada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar orden: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las órdenes
    public List<Orden> listar() {
        List<Orden> lista = new ArrayList<>();
        String sql = "SELECT * FROM orden";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Orden o = new Orden(
                    rs.getString("idorden"),
                    rs.getString("idcarrito"),
                    rs.getString("fecha"),
                    rs.getString("estado")
                );
                lista.add(o);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar órdenes: " + e.getMessage());
        }

        return lista;
    }

    // Buscar orden por ID
    public Orden buscarPorId(String id) {
        String sql = "SELECT * FROM orden WHERE idorden = ?";
        Orden o = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new Orden(
                    rs.getString("idorden"),
                    rs.getString("idcarrito"),
                    rs.getString("fecha"),
                    rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar orden: " + e.getMessage());
        }

        return o;
    }

    // Actualizar orden
    public boolean actualizar(Orden orden) {
        String sql = "UPDATE orden SET idcarrito=?, fecha=?, estado=? WHERE idorden=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orden.getIdCarrito());
            stmt.setString(2, orden.getFecha());
            stmt.setString(3, orden.getEstado());
            stmt.setString(4, orden.getIdOrden());

            stmt.executeUpdate();
            System.out.println("✅ Orden actualizada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar orden: " + e.getMessage());
            return false;
        }
    }

    // Eliminar orden
    public boolean eliminar(String id) {
        String sql = "DELETE FROM orden WHERE idorden = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Orden eliminada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar orden: " + e.getMessage());
            return false;
        }
    }
}