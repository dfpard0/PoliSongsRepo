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

public class DaoCarritoItem {

    // Insertar nuevo item al carrito
    public boolean insertar(carritoitem carritoItem) {
        String sql = "INSERT INTO carritoitem (idcarritoitem, idproducto, idcarrito, cantidad) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carritoItem.getIdCarritoItem());
            stmt.setString(2, carritoItem.getIdProducto());
            stmt.setString(3, carritoItem.getIdCarrito());
            stmt.setInt(4, carritoItem.getCantidad());

            stmt.executeUpdate();
            System.out.println("✅ Item de carrito insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar item de carrito: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los items del carrito
    public List<carritoitem> listar() {
        List<carritoitem> lista = new ArrayList<>();
        String sql = "SELECT * FROM carritoitem";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                carritoitem ci = new carritoitem(
                    rs.getString("idcarritoitem"),
                    rs.getString("idproducto"),
                    rs.getString("idcarrito"),
                    rs.getInt("cantidad")
                );
                lista.add(ci);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar items de carrito: " + e.getMessage());
        }

        return lista;
    }

    // Buscar item por ID
    public carritoitem buscarPorId(String id) {
        String sql = "SELECT * FROM carritoitem WHERE idcarritoitem = ?";
        carritoitem ci = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ci = new carritoitem(
                    rs.getString("idcarritoitem"),
                    rs.getString("idproducto"),
                    rs.getString("idcarrito"),
                    rs.getInt("cantidad")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar item de carrito: " + e.getMessage());
        }

        return ci;
    }

    // Buscar items por carrito
    public List<carritoitem> buscarPorCarrito(String idCarrito) {
        List<carritoitem> lista = new ArrayList<>();
        String sql = "SELECT * FROM carritoitem WHERE idcarrito = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCarrito);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                carritoitem ci = new carritoitem(
                    rs.getString("idcarritoitem"),
                    rs.getString("idproducto"),
                    rs.getString("idcarrito"),
                    rs.getInt("cantidad")
                );
                lista.add(ci);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar items por carrito: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar item del carrito
    public boolean actualizar(carritoitem carritoItem) {
        String sql = "UPDATE carritoitem SET idproducto=?, idcarrito=?, cantidad=? WHERE idcarritoitem=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carritoItem.getIdProducto());
            stmt.setString(2, carritoItem.getIdCarrito());
            stmt.setInt(3, carritoItem.getCantidad());
            stmt.setString(4, carritoItem.getIdCarritoItem());

            stmt.executeUpdate();
            System.out.println("✅ Item de carrito actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar item de carrito: " + e.getMessage());
            return false;
        }
    }

    // Eliminar item del carrito
    public boolean eliminar(String id) {
        String sql = "DELETE FROM carritoitem WHERE idcarritoitem = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Item de carrito eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar item de carrito: " + e.getMessage());
            return false;
        }
    }

    // Eliminar todos los items de un carrito
    public boolean eliminarPorCarrito(String idCarrito) {
        String sql = "DELETE FROM carritoitem WHERE idcarrito = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idCarrito);
            stmt.executeUpdate();
            System.out.println("✅ Todos los items del carrito eliminados correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar items del carrito: " + e.getMessage());
            return false;
        }
    }
}
