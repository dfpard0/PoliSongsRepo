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
 *productomedia
 * @author Pardo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoProductoMedia {

    // Insertar nuevo media
    public boolean insertar(productomedia media) {
        String sql = "INSERT INTO media (idmedia, idproducto, url, portada) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, media.getIdMedia());
            stmt.setString(2, media.getIdProducto());
            stmt.setString(3, media.getUrl());
            stmt.setString(4, media.getPortada());

            stmt.executeUpdate();
            System.out.println("✅ Media insertado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar media: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los media
    public List<productomedia> listar() {
        List<productomedia> lista = new ArrayList<>();
        String sql = "SELECT * FROM media";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productomedia m = new productomedia(
                    rs.getString("idmedia"),
                    rs.getString("idproducto"),
                    rs.getString("url"),
                    rs.getString("portada")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar media: " + e.getMessage());
        }

        return lista;
    }

    // Buscar media por ID
    public productomedia buscarPorId(String id) {
        String sql = "SELECT * FROM media WHERE idmedia = ?";
        productomedia m = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                m = new productomedia(
                    rs.getString("idmedia"),
                    rs.getString("idproducto"),
                    rs.getString("url"),
                    rs.getString("portada")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar media: " + e.getMessage());
        }

        return m;
    }

    // Buscar media por producto
    public List<productomedia> buscarPorProducto(String idProducto) {
        List<productomedia> lista = new ArrayList<>();
        String sql = "SELECT * FROM media WHERE idproducto = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productomedia m = new productomedia(
                    rs.getString("idmedia"),
                    rs.getString("idproducto"),
                    rs.getString("url"),
                    rs.getString("portada")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar media por producto: " + e.getMessage());
        }

        return lista;
    }

    // Buscar portada de un producto
    public productomedia buscarPortadaPorProducto(String idProducto) {
        String sql = "SELECT * FROM media WHERE idproducto = ? AND portada = ?";
        productomedia m = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            stmt.setString(2, "1"); // Asumiendo que "1" indica que es portada
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                m = new productomedia(
                    rs.getString("idmedia"),
                    rs.getString("idproducto"),
                    rs.getString("url"),
                    rs.getString("portada")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar portada por producto: " + e.getMessage());
        }

        return m;
    }

    // Buscar imágenes de un producto (excluyendo portada)
    public List<productomedia> buscarImagenesPorProducto(String idProducto) {
        List<productomedia> lista = new ArrayList<>();
        String sql = "SELECT * FROM media WHERE idproducto = ? AND (portada IS NULL OR portada != ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            stmt.setString(2, "1");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productomedia m = new productomedia(
                    rs.getString("idmedia"),
                    rs.getString("idproducto"),
                    rs.getString("url"),
                    rs.getString("portada")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar imágenes por producto: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar media
    public boolean actualizar(productomedia media) {
        String sql = "UPDATE media SET idproducto=?, url=?, portada=? WHERE idmedia=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, media.getIdProducto());
            stmt.setString(2, media.getUrl());
            stmt.setString(3, media.getPortada());
            stmt.setString(4, media.getIdMedia());

            stmt.executeUpdate();
            System.out.println("✅ Media actualizado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar media: " + e.getMessage());
            return false;
        }
    }

    // Establecer como portada
    public boolean establecerPortada(String idMedia, String idProducto) {
        // Primero quitar cualquier portada existente para este producto
        String sqlQuitarPortada = "UPDATE media SET portada = NULL WHERE idproducto = ? AND portada = ?";
        String sqlEstablecerPortada = "UPDATE media SET portada = ? WHERE idmedia = ?";

        try (Connection conn = ConexionSupabase.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(sqlQuitarPortada);
                 PreparedStatement stmt2 = conn.prepareStatement(sqlEstablecerPortada)) {

                // Quitar portada existente
                stmt1.setString(1, idProducto);
                stmt1.setString(2, "1");
                stmt1.executeUpdate();

                // Establecer nueva portada
                stmt2.setString(1, "1");
                stmt2.setString(2, idMedia);
                stmt2.executeUpdate();

                conn.commit();
                System.out.println("✅ Portada establecida correctamente");
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al establecer portada: " + e.getMessage());
            return false;
        }
    }

    // Eliminar media
    public boolean eliminar(String id) {
        String sql = "DELETE FROM media WHERE idmedia = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Media eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar media: " + e.getMessage());
            return false;
        }
    }

    // Eliminar media por producto
    public boolean eliminarPorProducto(String idProducto) {
        String sql = "DELETE FROM media WHERE idproducto = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            stmt.executeUpdate();
            System.out.println("✅ Media del producto eliminado correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar media por producto: " + e.getMessage());
            return false;
        }
    }

    // Contar media por producto
    public int contarPorProducto(String idProducto) {
        String sql = "SELECT COUNT(*) as total FROM media WHERE idproducto = ?";
        int total = 0;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al contar media por producto: " + e.getMessage());
        }

        return total;
    }
}
