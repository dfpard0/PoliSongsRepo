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
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
import java.util.List;
/**
 *
 * @author Pardo
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoInfraccion {

    // Insertar nueva infracción
    public boolean insertar(infraccion infraccion) {
        String sql = "INSERT INTO infraccion (idinfraccion, IdusuarioRepo, tipoinf, motivo, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, infraccion.getIdInfraccion());
            stmt.setString(2, infraccion.getIdUsuarioRepo());
            stmt.setString(3, infraccion.getTipoInf());
            stmt.setString(4, infraccion.getMotivo());
            stmt.setString(5, infraccion.getFecha());

            stmt.executeUpdate();
            System.out.println("✅ Infracción insertada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar infracción: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las infracciones
    public List<infraccion> listar() {
        List<infraccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM infraccion";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                infraccion i = new infraccion(
                    rs.getString("idinfraccion"),
                    rs.getString("IdusuarioRepo"),
                    rs.getString("tipoinf"),
                    rs.getString("motivo"),
                    rs.getString("fecha")
                );
                lista.add(i);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar infracciones: " + e.getMessage());
        }

        return lista;
    }

    // Buscar infracción por ID
    public infraccion buscarPorId(String id) {
        String sql = "SELECT * FROM infraccion WHERE idinfraccion = ?";
        infraccion i = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                i = new infraccion(
                    rs.getString("idinfraccion"),
                    rs.getString("IdusuarioRepo"),
                    rs.getString("tipoinf"),
                    rs.getString("motivo"),
                    rs.getString("fecha")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar infracción: " + e.getMessage());
        }

        return i;
    }

    // Eliminar infracción
    public boolean eliminar(String id) {
        String sql = "DELETE FROM infraccion WHERE idinfraccion = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Infracción eliminada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar infracción: " + e.getMessage());
            return false;
        }
    }
}
