/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.datos;

import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase;
import co.edu.poli.PoliSongsMarketPlace.negocio.UsuarioManager;
import java.sql.Connection;
import co.edu.poli.PoliSongsMarketPlace.Managers.*;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase2;
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

public class DaoColeccionCarritos {

    // Insertar nuevo carrito
    public String insertar(coleccionCarritos carrito) {
        String sql = "INSERT INTO coleccioncarrito (cant, total) VALUES (?, ?)";

        try (Connection conn = ConexionSupabase2.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carrito.getCantTotal());
            stmt.setInt(2, carrito.getTotal());

            stmt.executeUpdate();
            return ("✅ Carrito insertado correctamente");

        } catch (SQLException e) {
            return ("❌ Error al insertar carrito: " + e.getMessage());
        }
    }

    // Listar todos los carritos
    public List<coleccionCarritos> listar() {
        List<coleccionCarritos> lista = new ArrayList<>();
        String sql = "SELECT * FROM coleccioncarritos";

        try (Connection conn = ConexionSupabase2.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                coleccionCarritos c = new coleccionCarritos(
                        rs.getInt("idcarrito"),
                        rs.getInt("cant"),
                        rs.getInt("total")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar carritos: " + e.getMessage());
        }

        return lista;
    }

    // Buscar carrito por ID
    public coleccionCarritos buscarPorId(String id) {
        String sql = "SELECT * FROM coleccioncarritos WHERE idcarrito = ?";
        coleccionCarritos c = null;

        try (Connection conn = ConexionSupabase2.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new coleccionCarritos(
                        rs.getInt("idcarrito"),
                        rs.getInt("cant"),
                        rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar carrito: " + e.getMessage());
        }

        return c;
    }

    // Actualizar carrito
    public String actualizar(coleccionCarritos carrito) {
        String sql = "UPDATE coleccioncarritos SET cant=?, precio=? WHERE idcarrito=?";

        try (Connection conn = ConexionSupabase2.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carrito.getCantTotal());
            stmt.setInt(2, carrito.getTotal());

            stmt.executeUpdate();

            return "✅ Carrito actualizado correctamente";

        } catch (SQLException e) {
            return ("❌ Error al actualizar carrito: " + e.getMessage());

        }
    }

    // Eliminar carrito
    public String eliminar() {
        String sql = "DELETE FROM coleccioncarritos";

        try (Connection conn = ConexionSupabase2.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
            return ("✅ Carrito eliminado correctamente");

        } catch (SQLException e) {
            return ("❌ Error al eliminar carrito: " + e.getMessage());

        }
    }

    public coleccionCarritos obtenerUltimoRegistro() {
        String sql = "SELECT * FROM coleccioncarritos ORDER BY idcarrito DESC LIMIT 1";
        coleccionCarritos car = null;

        try (Connection conn = ConexionSupabase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                car = new coleccionCarritos(
                        rs.getInt("idcarrto"),
                        rs.getInt("cant"),
                        rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener último registro: " + e.getMessage());
        }

        return car;
    }
}

