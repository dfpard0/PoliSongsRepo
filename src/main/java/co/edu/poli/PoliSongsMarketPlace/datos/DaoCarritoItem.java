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
    public String insertar(carritoitem carritoItem) {
        String sql = "INSERT INTO carrito ( idproducto, cant, total) VALUES ( ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            
            stmt.setString(1, carritoItem.getIdProducto());
            stmt.setInt(2, carritoItem.getCant());
            stmt.setInt(3, carritoItem.getTotal());

            stmt.executeUpdate();
            return("✅ Item de carrito insertado correctamente");
           

        } catch (SQLException e) {
            return("❌ Error al insertar item de carrito: " + e.getMessage());
           
        }
    }

    // Listar todos los items del carrito
    public List<carritoitem> listar() {
        List<carritoitem> lista = new ArrayList<>();
        String sql = "SELECT * FROM carrito";

        try (Connection conn = ConexionSupabase.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                carritoitem ci = new carritoitem(
                        rs.getString("iditem"),
                        rs.getString("idproducto"),
                        rs.getInt("cant"),
                        rs.getInt("total")
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
        String sql = "SELECT * FROM carrito WHERE iditem = ?";
        carritoitem ci = null;

        try (Connection conn = ConexionSupabase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ci = new carritoitem(
                        rs.getString("iditem"),
                        rs.getString("idproducto"),
                        rs.getInt("cant"),
                        rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar item de carrito: " + e.getMessage());
        }

        return ci;
    }

    // Actualizar item del carrito
    public String actualizar(carritoitem carritoItem) {
        String sql = "UPDATE carrito SET idproducto=?, cant=?, total=? WHERE iditem=?";

        try (Connection conn = ConexionSupabase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carritoItem.getIdProducto());
            stmt.setInt(2, carritoItem.getCant());
            stmt.setInt(3, carritoItem.getTotal());
            stmt.setString(4, carritoItem.getIdItem());

            stmt.executeUpdate();
            return("✅ Item de carrito actualizado correctamente");
            

        } catch (SQLException e) {
            return("❌ Error al actualizar item de carrito: " + e.getMessage());
            
        }
    }

    // Eliminar item del carrito
    public String eliminar(String id) {
        String sql = "DELETE FROM carrito WHERE iditem = ?";

        try (Connection conn = ConexionSupabase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            return("✅ Item de carrito eliminado correctamente");
            

        } catch (SQLException e) {
            return("❌ Error al eliminar item de carrito: " + e.getMessage());
            
        }
    }

}
