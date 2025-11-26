package co.edu.poli.PoliSongsMarketPlace.datos;

import co.edu.poli.PoliSongsMarketPlace.modelo.Usuario;
import java.sql.*;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase2;

public class DaoUsuario {

    private Connection connection;

    public DaoUsuario(Connection connection) {
        this.connection = connection;
    }

    // INSERTAR USUARIO
   public boolean insertarUsuario(Usuario usuario) {
    // Quitamos el id de la sentencia
    String sql = "INSERT INTO usuario (nombreusuario, correoelectronico, password) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, usuario.getNombreUsuario());
        stmt.setString(2, usuario.getCorreoElectronico());
        stmt.setString(3, usuario.getPassword());

        int filas = stmt.executeUpdate();
        if (filas == 0) return false;

        // Obtener ID generado automáticamente
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1));
            }
        }

        return true;

    } catch (SQLException e) {
        System.out.println("❌ Error al insertar usuario: " + e.getMessage());
        return false;
    }
}


    // BUSCAR POR NOMBRE DE USUARIO
    public boolean buscarPorNombreUsuario(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nombreusuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error en buscarPorNombreUsuario: " + e.getMessage());
            return false;
        }
    }

    public boolean ExisteNombre(String nombreUsuario) {
        return buscarPorNombreUsuario(nombreUsuario);
    }


    // VERIFICAR LOGIN
    public boolean verificarCredenciales(String nombreUsuario, String password) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nombreusuario = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error en verificarCredenciales: " + e.getMessage());
            return false;
        }
    }
    public int obtenerIdMaximo() {
    String sql = "SELECT MAX(id) FROM usuarios";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(0)+2; // Retorna el valor máximo
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Retorna 0 si no hay registros o hay error
}
}