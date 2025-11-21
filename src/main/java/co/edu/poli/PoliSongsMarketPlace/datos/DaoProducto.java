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
/**
 *
 * @author Pardo
 */
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoProducto {

    // Insertar nuevo producto
    public boolean insertar(producto producto) {
        String sql = "INSERT INTO producto (idproducto, idtipo, idvendedor, nombre, descripcion, precio, disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getIdProducto());
            stmt.setString(2, producto.getIdTipo());
            stmt.setString(3, producto.getIdVendedor());
            stmt.setString(4, producto.getNombre());
            stmt.setString(5, producto.getDescripcion());
            stmt.setInt(6, producto.getPrecio());
            stmt.setInt(7, producto.getDisponible());

            stmt.executeUpdate();
            System.out.println("✅ Producto insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los productos
    public List<producto> listar() {
        List<producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                producto p = new producto(
                    rs.getString("idproducto"),
                    rs.getString("idtipo"),
                    rs.getString("idvendedor"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("precio"),
                    rs.getInt("disponible")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar productos: " + e.getMessage());
        }

        return lista;
    }

    // Buscar producto por ID
    public producto buscarPorId(String id) {
        String sql = "SELECT * FROM producto WHERE idproducto = ?";
        producto p = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                p = new producto(
                    rs.getString("idproducto"),
                    rs.getString("idtipo"),
                    rs.getString("idvendedor"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("precio"),
                    rs.getInt("disponible")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto: " + e.getMessage());
        }

        return p;
    }

    // Actualizar producto
    public boolean actualizar(producto producto) {
        String sql = "UPDATE producto SET idtipo=?, idvendedor=?, nombre=?, descripcion=?, precio=?, disponible=? WHERE idproducto=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getIdTipo());
            stmt.setString(2, producto.getIdVendedor());
            stmt.setString(3, producto.getNombre());
            stmt.setString(4, producto.getDescripcion());
            stmt.setInt(5, producto.getPrecio());
            stmt.setInt(6, producto.getDisponible());
            stmt.setString(7, producto.getIdProducto());

            stmt.executeUpdate();
            System.out.println("✅ Producto actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // Eliminar producto
    public boolean eliminar(String id) {
        String sql = "DELETE FROM producto WHERE idproducto = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Producto eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
