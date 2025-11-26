/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase2;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoUsuario;
import co.edu.poli.PoliSongsMarketPlace.modelo.Usuario;
import java.sql.Connection;

public class ManagerUsuario {
    private DaoUsuario DaoUsuario;

    public ManagerUsuario(ConexionSupabase2 ConexionSupabase2) {
        this.DaoUsuario = new DaoUsuario(ConexionSupabase2);
    }

    public boolean registrarUsuario(Usuario usuario) {
        return DaoUsuario.insertarUsuario(usuario);
    }
}
