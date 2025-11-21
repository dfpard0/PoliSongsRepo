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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
/**
 *
 * @author Pardo
 */
public class DaoUsuarioVendedor {
   



    // Insertar nuevo vendedor
    public boolean insertar(usuariovendedor vendedor) {
        String sql = "INSERT INTO vendedor (idvendedor, idusuario) VALUES (?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vendedor.getIdVendedor());
            stmt.setString(2, vendedor.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("✅ Vendedor insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar vendedor: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los vendedores
    public List<usuariovendedor> listar() {
        List<usuariovendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuariovendedor v = new usuariovendedor(
                    rs.getString("idvendedor"),
                    rs.getString("idusuario")
                );
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar vendedores: " + e.getMessage());
        }

        return lista;
    }

    // Buscar vendedor por ID
    public usuariovendedor buscarPorId(String id) {
        String sql = "SELECT * FROM vendedor WHERE idvendedor = ?";
        usuariovendedor v = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new usuariovendedor(
                    rs.getString("idvendedor"),
                    rs.getString("idusuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar vendedor: " + e.getMessage());
        }

        return v;
    }

    // Buscar vendedor por ID de usuario
    public usuariovendedor buscarPorUsuario(String idUsuario) {
        String sql = "SELECT * FROM vendedor WHERE idusuario = ?";
        usuariovendedor v = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new usuariovendedor(
                    rs.getString("idvendedor"),
                    rs.getString("idusuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar vendedor por usuario: " + e.getMessage());
        }

        return v;
    }

    // Actualizar vendedor
    public boolean actualizar(usuariovendedor vendedor) {
        String sql = "UPDATE vendedor SET idusuario=? WHERE idvendedor=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vendedor.getIdUsuario());
            stmt.setString(2, vendedor.getIdVendedor());

            stmt.executeUpdate();
            System.out.println("✅ Vendedor actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar vendedor: " + e.getMessage());
            return false;
        }
    }

    // Eliminar vendedor
    public boolean eliminar(String id) {
        String sql = "DELETE FROM vendedor WHERE idvendedor = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Vendedor eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar vendedor: " + e.getMessage());
            return false;
        }
    }
}

