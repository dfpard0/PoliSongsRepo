/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.modelo;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String correoElectronico;
    private String password;

    public Usuario (){}
    public Usuario(long id, String nombreUsuario, String correoElectronico, String password) {
        this.id = (int) id;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(long id) {
        this.id = (int) id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}
