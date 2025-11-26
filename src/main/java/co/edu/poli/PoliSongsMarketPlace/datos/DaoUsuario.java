package co.edu.poli.PoliSongsMarketPlace.datos;

import co.edu.poli.PoliSongsMarketPlace.modelo.Usuario;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {

    private Connection ConexionSupabase2;

    public DaoUsuario(ConexionSupabase2 connection) {
        this.ConexionSupabase2 = ConexionSupabase2;
    }

    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nombreusuario, correoelectronico, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexionSupabase2.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setString(3, usuario.getCorreoElectronico());
            stmt.setString(5, usuario.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean buscarPorNombreUsuario(String nombreUsuario) {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE nombreusuario = ?";
    
    try (PreparedStatement stmt = ConexionSupabase2.prepareStatement(sql)) {
        stmt.setString(1, nombreUsuario);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Retorna true si encuentra al menos un registro
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna false si no encuentra ningún usuario
    
   }
    public boolean ExisteNombre (String nombreUsuario) {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE nombreusuario = ?";
    
    try (PreparedStatement stmt = ConexionSupabase2.prepareStatement(sql)) {
        stmt.setString(1, nombreUsuario);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Retorna true si encuentra al menos un registro
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Retorna false si no encuentra ningún usuario
}
public boolean verificarCredenciales(String nombreUsuario, String password) {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE nombreusuario = ? AND password = ?";
    
    try (PreparedStatement stmt = ConexionSupabase2.prepareStatement(sql)) {
        stmt.setString(1, nombreUsuario);
        stmt.setString(2, password);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}
