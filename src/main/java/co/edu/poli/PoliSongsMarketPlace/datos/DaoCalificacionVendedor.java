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

public class DaoCalificacionVendedor {
 
    // Constructor vacío
    public DaoCalificacionVendedor() {
    }

    // Insertar nueva calificación
    public boolean insertar(calificacionvendedor calificacion) {
        String sql = "INSERT INTO calificacionvendedor (idealification, idrelacion, puntuacion, comentario, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, calificacion.getIdealification());
            stmt.setString(2, calificacion.getIdrelacion());
            stmt.setShort(3, calificacion.getPuntuacion());
            stmt.setString(4, calificacion.getComentario());
            stmt.setString(5, calificacion.getFecha());

            stmt.executeUpdate();
            System.out.println("✅ Calificación de vendedor insertada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar calificación de vendedor: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las calificaciones
    public List<calificacionvendedor> listar() {
        List<calificacionvendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM calificacionvendedor";

        try (Connection conn = ConexionSupabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                calificacionvendedor c = new calificacionvendedor(
                    rs.getInt("idealification"),
                    rs.getString("idrelacion"),
                    rs.getShort("puntuacion"),
                    rs.getString("comentario"),
                    rs.getString("fecha")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar calificaciones de vendedor: " + e.getMessage());
        }

        return lista;
    }

    // Buscar calificación por ID
    public calificacionvendedor buscarPorId(int id) {
        String sql = "SELECT * FROM calificacionvendedor WHERE idealification = ?";
        calificacionvendedor c = null;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new calificacionvendedor(
                    rs.getInt("idealification"),
                    rs.getString("idrelacion"),
                    rs.getShort("puntuacion"),
                    rs.getString("comentario"),
                    rs.getString("fecha")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar calificación de vendedor: " + e.getMessage());
        }

        return c;
    }

    // Buscar calificaciones por relación
    public List<calificacionvendedor> buscarPorRelacion(String idRelacion) {
        List<calificacionvendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM calificacionvendedor WHERE idrelacion = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idRelacion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                calificacionvendedor c = new calificacionvendedor(
                    rs.getInt("idealification"),
                    rs.getString("idrelacion"),
                    rs.getShort("puntuacion"),
                    rs.getString("comentario"),
                    rs.getString("fecha")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar calificaciones por relación: " + e.getMessage());
        }

        return lista;
    }

    // Actualizar calificación
    public boolean actualizar(calificacionvendedor calificacion) {
        String sql = "UPDATE calificacionvendedor SET idrelacion=?, puntuacion=?, comentario=?, fecha=? WHERE idealification=?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, calificacion.getIdrelacion());
            stmt.setShort(2, calificacion.getPuntuacion());
            stmt.setString(3, calificacion.getComentario());
            stmt.setString(4, calificacion.getFecha());
            stmt.setInt(5, calificacion.getIdealification());

            stmt.executeUpdate();
            System.out.println("✅ Calificación de vendedor actualizada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar calificación de vendedor: " + e.getMessage());
            return false;
        }
    }

    // Eliminar calificación
    public boolean eliminar(int id) {
        String sql = "DELETE FROM calificacionvendedor WHERE idealification = ?";

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Calificación de vendedor eliminada correctamente");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar calificación de vendedor: " + e.getMessage());
            return false;
        }
    }

    // Obtener promedio de puntuaciones por vendedor (a través de relación)
    public double obtenerPromedioPorVendedor(String idVendedor) {
        String sql = "SELECT AVG(cv.puntuacion) as promedio " +
                    "FROM calificacionvendedor cv " +
                    "JOIN relacion r ON cv.idrelacion = r.idrelacion " +
                    "WHERE r.idvendedor = ?";
        double promedio = 0.0;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idVendedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                promedio = rs.getDouble("promedio");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener promedio de vendedor: " + e.getMessage());
        }

        return promedio;
    }

    // Contar calificaciones por vendedor
    public int contarCalificacionesPorVendedor(String idVendedor) {
        String sql = "SELECT COUNT(*) as total " +
                    "FROM calificacionvendedor cv " +
                    "JOIN relacion r ON cv.idrelacion = r.idrelacion " +
                    "WHERE r.idvendedor = ?";
        int total = 0;

        try (Connection conn = ConexionSupabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idVendedor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al contar calificaciones de vendedor: " + e.getMessage());
        }

        return total;
    }
}