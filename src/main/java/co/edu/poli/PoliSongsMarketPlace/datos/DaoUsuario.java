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
public class DaoUsuario {
    
    // Insertar nuevo usuario
  public boolean insertar(UsuarioManager usuario) {
        String sql = "INSERT INTO usuario (idusuario, nombre, email, contrasena, rol) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getRol());

            stmt.executeUpdate();
            System.out.println(" Usuario insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los usuarios
    public List<UsuarioManager> listar() {
        List<UsuarioManager> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UsuarioManager u = new UsuarioManager(
                    rs.getString("idusuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    // Buscar usuario por ID
    public UsuarioManager buscarPorId(String id) {
        String sql = "SELECT * FROM usuario WHERE idusuario = ?";
        UsuarioManager u = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u = new UsuarioManager(
                    rs.getString("idusuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contrasena"),
                    rs.getString("rol")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar usuario: " + e.getMessage());
        }

        return u;
    }

    // Actualizar usuario
    public boolean actualizar(UsuarioManager usuario) {
        String sql = "UPDATE usuario SET nombre=?, email=?, contrasena=?, rol=? WHERE idusuario=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getContrasena());
            stmt.setString(4, usuario.getRol());
            stmt.setString(5, usuario.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("✅ Usuario actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // Eliminar usuario
    public boolean eliminar(String id) {
        String sql = "DELETE FROM usuario WHERE idusuario = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Usuario eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
    