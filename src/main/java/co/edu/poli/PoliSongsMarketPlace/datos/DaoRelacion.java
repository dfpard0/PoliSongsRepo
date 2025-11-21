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
 *relacion
 * @author Pardo
 */
public class DaoRelacion {



    // Insertar nueva relación
    public boolean insertar(relacion relacion) {
        String sql = "INSERT INTO relacion (idrelacion, idvendedor, idcomprador) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, relacion.getIdRelacion());
            stmt.setString(2, relacion.getIdVendedor());
            stmt.setString(3, relacion.getIdComprador());

            stmt.executeUpdate();
            System.out.println("✅ Relación insertada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar relación: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las relaciones
    public List<relacion> listar() {
        List<relacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM relacion";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                relacion r = new relacion(
                    rs.getString("idrelacion"),
                    rs.getString("idvendedor"),
                    rs.getString("idcomprador")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar relaciones: " + e.getMessage());
        }

        return lista;
    }

    // Buscar relación por ID
    public relacion buscarPorId(String id) {
        String sql = "SELECT * FROM relacion WHERE idrelacion = ?";
        relacion r = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                r = new relacion(
                    rs.getString("idrelacion"),
                    rs.getString("idvendedor"),
                    rs.getString("idcomprador")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar relación: " + e.getMessage());
        }

        return r;
    }

    // Buscar relaciones por vendedor
    public List<relacion> buscarPorVendedor(String idVendedor) {
        List<relacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM relacion WHERE idvendedor = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idVendedor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                relacion r = new relacion(
                    rs.getString("idrelacion"),
                    rs.getString("idvendedor"),
                    rs.getString("idcomprador")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar relaciones por vendedor: " + e.getMessage());
        }

        return lista;
    }

    // Buscar relaciones por comprador
    public List<relacion> buscarPorComprador(String idComprador) {
        List<relacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM relacion WHERE idcomprador = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idComprador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                relacion r = new relacion(
                    rs.getString("idrelacion"),
                    rs.getString("idvendedor"),
                    rs.getString("idcomprador")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar relaciones por comprador: " + e.getMessage());
        }

        return lista;
    }

    // Buscar relación específica entre vendedor y comprador
    public relacion buscarPorVendedorYComprador(String idVendedor, String idComprador) {
        String sql = "SELECT * FROM relacion WHERE idvendedor = ? AND idcomprador = ?";
        relacion r = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idVendedor);
            stmt.setString(2, idComprador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                r = new relacion(
                    rs.getString("idrelacion"),
                    rs.getString("idvendedor"),
                    rs.getString("idcomprador")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar relación por vendedor y comprador: " + e.getMessage());
        }

        return r;
    }

    // Actualizar relación
    public boolean actualizar(relacion relacion) {
        String sql = "UPDATE relacion SET idvendedor=?, idcomprador=? WHERE idrelacion=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, relacion.getIdVendedor());
            stmt.setString(2, relacion.getIdComprador());
            stmt.setString(3, relacion.getIdRelacion());

            stmt.executeUpdate();
            System.out.println("✅ Relación actualizada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar relación: " + e.getMessage());
            return false;
        }
    }

    // Eliminar relación
    public boolean eliminar(String id) {
        String sql = "DELETE FROM relacion WHERE idrelacion = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Relación eliminada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar relación: " + e.getMessage());
            return false;
        }
    }
}
