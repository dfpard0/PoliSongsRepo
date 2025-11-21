/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.datos;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
/**usuariocomprador
 *
 * @author Pardo
 */
public class DaoUsuarioComprador {



    // Insertar nuevo comprador
    public boolean insertar(usuariocomprador comprador) {
        String sql = "INSERT INTO comprador (idcomprador, idusuario) VALUES (?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comprador.getIdComprador());
            stmt.setString(2, comprador.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("✅ Comprador insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar comprador: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los compradores
    public List<usuariocomprador> listar() {
        List<usuariocomprador> lista = new ArrayList<>();
        String sql = "SELECT * FROM comprador";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuariocomprador c = new usuariocomprador(
                    rs.getString("idcomprador"),
                    rs.getString("idusuario")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar compradores: " + e.getMessage());
        }

        return lista;
    }

    // Buscar comprador por ID
    public usuariocomprador buscarPorId(String id) {
        String sql = "SELECT * FROM comprador WHERE idcomprador = ?";
        usuariocomprador c = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new usuariocomprador(
                    rs.getString("idcomprador"),
                    rs.getString("idusuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar comprador: " + e.getMessage());
        }

        return c;
    }

    // Buscar comprador por ID de usuario
    public usuariocomprador buscarPorUsuario(String idUsuario) {
        String sql = "SELECT * FROM comprador WHERE idusuario = ?";
        usuariocomprador c = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new usuariocomprador(
                    rs.getString("idcomprador"),
                    rs.getString("idusuario")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar comprador por usuario: " + e.getMessage());
        }

        return c;
    }

    // Actualizar comprador
    public boolean actualizar(usuariocomprador comprador) {
        String sql = "UPDATE comprador SET idusuario=? WHERE idcomprador=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comprador.getIdUsuario());
            stmt.setString(2, comprador.getIdComprador());

            stmt.executeUpdate();
            System.out.println("✅ Comprador actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar comprador: " + e.getMessage());
            return false;
        }
    }

    // Eliminar comprador
    public boolean eliminar(String id) {
        String sql = "DELETE FROM comprador WHERE idcomprador = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Comprador eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar comprador: " + e.getMessage());
            return false;
        }
    }

}
