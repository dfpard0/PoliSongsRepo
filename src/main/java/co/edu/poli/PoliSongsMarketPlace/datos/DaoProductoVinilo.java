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
    public String insertar(productovinilo productoVinilo) {
        String sql = "INSERT INTO productovinilo (idproductovini, titulo, autor, cantidad, precio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productoVinilo.getIdProductoVini());
            stmt.setString(2, productoVinilo.getTitulo());
            stmt.setString(3, productoVinilo.getAutor());            
            stmt.setInt(4, productoVinilo.getCantidad());
            stmt.setInt(5, productoVinilo.getPrecio());
            

            stmt.executeUpdate();
            return("✅ Producto vinilo insertado correctamente");
            

        } catch (SQLException e) {
            return("❌ Error al insertar producto vinilo: " + e.getMessage());
            
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
                    rs.getString("idproductovini"),
                    rs.getString("titulo"),
                    rs.getString("autor"),  
                    rs.getInt("cantidad"),
                    rs.getInt("precio")
                );
                lista.add(pv);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar productos vinilo: " + e.getMessage());
        }

        return lista;
    }

    // Buscar producto vinilo por ID
    public productovinilo buscarPorId(String id) {
        String sql = "SELECT * FROM productovinilo WHERE idproductovini = ?";
        productovinilo pv = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pv = new productovinilo(
                    rs.getString("idproductovini"),
                    rs.getString("titulo"),
                    rs.getString("autor"),  
                    rs.getInt("cantidad"),
                    rs.getInt("precio")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto vinilo: " + e.getMessage());
        }

        return pv;
    }

    // Buscar producto vinilo por ID de producto
    public productovinilo buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM productovinilo WHERE titulo = ?";
        productovinilo pv = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pv = new productovinilo(
                    rs.getString("idproductovini"),
                    rs.getString("titulo"),
                    rs.getString("autor"),  
                    rs.getInt("cantidad"),
                    rs.getInt("precio")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto vinilo por idproducto: " + e.getMessage());
        }

        return pv;
    }

    // Actualizar producto vinilo
    public String actualizar(productovinilo productoVinilo) {
        String sql = "UPDATE productovinilo SET titulo=?, autor=?, cantidad=?, precio=? WHERE idproductovini=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productoVinilo.getTitulo());
            stmt.setString(2, productoVinilo.getAutor());
            stmt.setInt(3, productoVinilo.getCantidad());
            stmt.setInt(4, productoVinilo.getPrecio());
            stmt.setString(5, productoVinilo.getIdProductoVini());

            stmt.executeUpdate();
            return("✅ Producto vinilo actualizado correctamente");
            

        } catch (SQLException e) {
            return("❌ Error al actualizar producto vinilo: " + e.getMessage());
            
        }
    }

    // Actualizar solo el stock
    public String actualizarStock(String idProductoVini, int nuevoStock) {
        String sql = "UPDATE productovinilo SET cantidad=? WHERE idproductovini=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevoStock);
            stmt.setString(2, idProductoVini);

            stmt.executeUpdate();
            return("✅ Stock de producto vinilo actualizado correctamente");
            

        } catch (SQLException e) {
            return("❌ Error al actualizar stock de producto vinilo: " + e.getMessage());
            
        }
    }

    // Eliminar producto vinilo
    public String eliminar(String id) {
        String sql = "DELETE FROM productovinilo WHERE idproductovini = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            return("✅ Producto vinilo eliminado correctamente");
            

        } catch (SQLException e) {
            return("❌ Error al eliminar producto vinilo: " + e.getMessage());
            
        }
    }

    // Verificar stock disponible
    public boolean verificarStock(String idProductoVini, int cantidadRequerida) {
        String sql = "SELECT cantidad FROM productovinilo WHERE idproductovini = ?";
        
        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProductoVini);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stockActual = rs.getInt("cantidad");
                return stockActual >= cantidadRequerida;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al verificar stock: " + e.getMessage());
        }

        return false;
    }
}
