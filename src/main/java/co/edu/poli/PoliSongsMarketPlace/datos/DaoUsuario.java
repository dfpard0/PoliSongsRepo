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
        String sql = "INSERT INTO usuarios (nombreusuario, numero, correoelectronico, telefono, password, documento) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexionSupabase2.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getNumero());
            stmt.setString(3, usuario.getCorreoElectronico());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, usuario.getDocumento());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

    