/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.datos;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase;
import co.edu.poli.PoliSongsMarketPlace.negocio.UsuarioManager;
import java.sql.Connection;
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCarrito {

    // Insertar nuevo carrito
    public boolean insertar(carrito carrito) {
        String sql = "INSERT INTO carrito (idcarrito, idusuario) VALUES (?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carrito.getIdCarrito());
            stmt.setString(2, carrito.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("✅ Carrito insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar carrito: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los carritos
    public List<carrito> listar() {
        List<carrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM carrito";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                carrito c = new carrito(
                    rs.getString("idcarrito"),
                    rs.getString("idusuario")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar carritos: " + e.getMessage());
        }

        return lista;
    }

    // Buscar carrito por ID
    public carrito buscarPorId(String id) {
        String sql = "SELECT * FROM carrito WHERE idcarrito = ?";
        carrito c = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new carrito(
                    rs.getString("idcarrito"),
                    rs.getString("idusuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar carrito: " + e.getMessage());
        }

        return c;
    }

    // Buscar carrito por ID de usuario
    public carrito buscarPorUsuario(String idUsuario) {
        String sql = "SELECT * FROM carrito WHERE idusuario = ?";
        carrito c = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new carrito(
                    rs.getString("idcarrito"),
                    rs.getString("idusuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar carrito por usuario: " + e.getMessage());
        }

        return c;
    }

    // Actualizar carrito
    public boolean actualizar(carrito carrito) {
        String sql = "UPDATE carrito SET idusuario=? WHERE idcarrito=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carrito.getIdUsuario());
            stmt.setString(2, carrito.getIdCarrito());

            stmt.executeUpdate();
            System.out.println("✅ Carrito actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar carrito: " + e.getMessage());
            return false;
        }
    }

    // Eliminar carrito
    public boolean eliminar(String id) {
        String sql = "DELETE FROM carrito WHERE idcarrito = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Carrito eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar carrito: " + e.getMessage());
            return false;
        }
    }

    // Verificar si un usuario ya tiene un carrito
    public boolean existeCarritoPorUsuario(String idUsuario) {
        String sql = "SELECT COUNT(*) as count FROM carrito WHERE idusuario = ?";
        
        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al verificar carrito: " + e.getMessage());
        }

        return false;
    }
}
