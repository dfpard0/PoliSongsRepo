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

public class DaoProductoMP3 {

    // Insertar nuevo producto mp3
    public boolean insertar(productoMp3 productoMp3) {
        String sql = "INSERT INTO productomp3 (idproductomp3, idproducto, stock) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productoMp3.getIdProductoMp3());
            stmt.setString(2, productoMp3.getIdProducto());
            stmt.setInt(3, productoMp3.getStock());

            stmt.executeUpdate();
            System.out.println("✅ Producto mp3 insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto mp3: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los productos mp3
    public List<productoMp3> listar() {
        List<productoMp3> lista = new ArrayList<>();
        String sql = "SELECT * FROM productomp3";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productoMp3 pm = new productoMp3(
                    rs.getString("idproductomp3"),
                    rs.getString("idproducto"),
                    rs.getInt("stock")
                );
                lista.add(pm);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar productos mp3: " + e.getMessage());
        }

        return lista;
    }

    // Buscar producto mp3 por ID
    public productoMp3 buscarPorId(String id) {
        String sql = "SELECT * FROM productomp3 WHERE idproductomp3 = ?";
        productoMp3 pm = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pm = new productoMp3(
                    rs.getString("idproductomp3"),
                    rs.getString("idproducto"),
                    rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto mp3: " + e.getMessage());
        }

        return pm;
    }

    // Buscar producto mp3 por ID de producto
    public productoMp3 buscarPorIdProducto(String idProducto) {
        String sql = "SELECT * FROM productomp3 WHERE idproducto = ?";
        productoMp3 pm = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pm = new productoMp3(
                    rs.getString("idproductomp3"),
                    rs.getString("idproducto"),
                    rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto mp3 por idproducto: " + e.getMessage());
        }

        return pm;
    }

    // Actualizar producto mp3
    public boolean actualizar(productoMp3 productoMp3) {
        String sql = "UPDATE productomp3 SET idproducto=?, stock=? WHERE idproductomp3=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productoMp3.getIdProducto());
            stmt.setInt(2, productoMp3.getStock());
            stmt.setString(3, productoMp3.getIdProductoMp3());

            stmt.executeUpdate();
            System.out.println("✅ Producto mp3 actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto mp3: " + e.getMessage());
            return false;
        }
    }

    // Actualizar solo el stock
    public boolean actualizarStock(String idProductoMp3, int nuevoStock) {
        String sql = "UPDATE productomp3 SET stock=? WHERE idproductomp3=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevoStock);
            stmt.setString(2, idProductoMp3);

            stmt.executeUpdate();
            System.out.println("✅ Stock de producto mp3 actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar stock de producto mp3: " + e.getMessage());
            return false;
        }
    }

    // Eliminar producto mp3
    public boolean eliminar(String id) {
        String sql = "DELETE FROM productomp3 WHERE idproductomp3 = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Producto mp3 eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto mp3: " + e.getMessage());
            return false;
        }
    }

    // Verificar stock disponible
    public boolean verificarStock(String idProductoMp3, int cantidadRequerida) {
        String sql = "SELECT stock FROM productomp3 WHERE idproductomp3 = ?";
        
        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProductoMp3);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                return stockActual >= cantidadRequerida;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al verificar stock: " + e.getMessage());
        }

        return false;
    }
}
