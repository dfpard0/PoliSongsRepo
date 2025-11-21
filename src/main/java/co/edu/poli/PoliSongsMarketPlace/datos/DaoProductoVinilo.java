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
public class DaoProductoVinilo {
   
    // Insertar nuevo producto vinilo
    public boolean insertar(productovinilo productoVinilo) {
        String sql = "INSERT INTO productovinilo (idproductovini, idproducto, stock) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, productoVinilo.getIdProductoVini());
            stmt.setString(2, productoVinilo.getIdProducto());
            stmt.setInt(3, productoVinilo.getStock());

            stmt.executeUpdate();
            System.out.println("✅ Producto vinilo insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto vinilo: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los productos vinilo
    public List<productovinilo> listar() {
        List<productovinilo> lista = new ArrayList<>();
        String sql = "SELECT * FROM productovinilo";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productovinilo pv = new productovinilo(
                    rs.getLong("idproductovini"),
                    rs.getString("idproducto"),
                    rs.getInt("stock")
                );
                lista.add(pv);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar productos vinilo: " + e.getMessage());
        }

        return lista;
    }

    // Buscar producto vinilo por ID
    public productovinilo buscarPorId(long id) {
        String sql = "SELECT * FROM productovinilo WHERE idproductovini = ?";
        productovinilo pv = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pv = new productovinilo(
                    rs.getLong("idproductovini"),
                    rs.getString("idproducto"),
                    rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto vinilo: " + e.getMessage());
        }

        return pv;
    }

    // Buscar producto vinilo por ID de producto
    public productovinilo buscarPorIdProducto(String idProducto) {
        String sql = "SELECT * FROM productovinilo WHERE idproducto = ?";
        productovinilo pv = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pv = new productovinilo(
                    rs.getLong("idproductovini"),
                    rs.getString("idproducto"),
                    rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto vinilo por idproducto: " + e.getMessage());
        }

        return pv;
    }

    // Actualizar producto vinilo
    public boolean actualizar(productovinilo productoVinilo) {
        String sql = "UPDATE productovinilo SET idproducto=?, stock=? WHERE idproductovini=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productoVinilo.getIdProducto());
            stmt.setInt(2, productoVinilo.getStock());
            stmt.setLong(3, productoVinilo.getIdProductoVini());

            stmt.executeUpdate();
            System.out.println("✅ Producto vinilo actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto vinilo: " + e.getMessage());
            return false;
        }
    }

    // Actualizar solo el stock
    public boolean actualizarStock(long idProductoVini, int nuevoStock) {
        String sql = "UPDATE productovinilo SET stock=? WHERE idproductovini=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevoStock);
            stmt.setLong(2, idProductoVini);

            stmt.executeUpdate();
            System.out.println("✅ Stock de producto vinilo actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar stock de producto vinilo: " + e.getMessage());
            return false;
        }
    }

    // Eliminar producto vinilo
    public boolean eliminar(long id) {
        String sql = "DELETE FROM productovinilo WHERE idproductovini = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Producto vinilo eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto vinilo: " + e.getMessage());
            return false;
        }
    }

    // Verificar stock disponible
    public boolean verificarStock(long idProductoVini, int cantidadRequerida) {
        String sql = "SELECT stock FROM productovinilo WHERE idproductovini = ?";
        
        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idProductoVini);
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
