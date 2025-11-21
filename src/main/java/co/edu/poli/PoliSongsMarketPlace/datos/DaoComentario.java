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

    import java.util.List;

public class DaoComentario {

    // Insertar nuevo comentario
    public boolean insertar(comentario comentario) {
        String sql = "INSERT INTO comentario (idcomentario, idproducto, idrelacion, texto, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comentario.getIdComentario());
            stmt.setString(2, comentario.getIdProducto());
            stmt.setString(3, comentario.getIdRelacion());
            stmt.setString(4, comentario.getTexto());
            stmt.setString(5, comentario.getFecha());

            stmt.executeUpdate();
            System.out.println("✅ Comentario insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar comentario: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los comentarios
    public List<comentario> listar() {
        List<comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentario";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                comentario c = new comentario(
                    rs.getString("idcomentario"),
                    rs.getString("idproducto"),
                    rs.getString("idrelacion"),
                    rs.getString("texto"),
                    rs.getString("fecha")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar comentarios: " + e.getMessage());
        }

        return lista;
    }

    // Buscar comentario por ID
    public comentario buscarPorId(String id) {
        String sql = "SELECT * FROM comentario WHERE idcomentario = ?";
        comentario c = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new comentario(
                    rs.getString("idcomentario"),
                    rs.getString("idproducto"),
                    rs.getString("idrelacion"),
                    rs.getString("texto"),
                    rs.getString("fecha")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar comentario: " + e.getMessage());
        }

        return c;
    }

    // Buscar comentarios por producto
    public List<comentario> buscarPorProducto(String idProducto) {
        List<comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentario WHERE idproducto = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comentario c = new comentario(
                    rs.getString("idcomentario"),
                    rs.getString("idproducto"),
                    rs.getString("idrelacion"),
                    rs.getString("texto"),
                    rs.getString("fecha")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar comentarios por producto: " + e.getMessage());
        }

        return lista;
    }

    // Buscar comentarios por relación
    public List<comentario> buscarPorRelacion(String idRelacion) {
        List<comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentario WHERE idrelacion = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idRelacion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comentario c = new comentario(
                    rs.getString("idcomentario"),
                    rs.getString("idproducto"),
                    rs.getString("idrelacion"),
                    rs.getString("texto"),
                    rs.getString("fecha")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar comentarios por relación: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar comentario
    public boolean actualizar(comentario comentario) {
        String sql = "UPDATE comentario SET idproducto=?, idrelacion=?, texto=?, fecha=? WHERE idcomentario=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comentario.getIdProducto());
            stmt.setString(2, comentario.getIdRelacion());
            stmt.setString(3, comentario.getTexto());
            stmt.setString(4, comentario.getFecha());
            stmt.setString(5, comentario.getIdComentario());

            stmt.executeUpdate();
            System.out.println("✅ Comentario actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar comentario: " + e.getMessage());
            return false;
        }
    }

    // Eliminar comentario
    public boolean eliminar(String id) {
        String sql = "DELETE FROM comentario WHERE idcomentario = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Comentario eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar comentario: " + e.getMessage());
            return false;
        }
    }
}

