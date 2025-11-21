/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class usuariovendedor {

    private String idVendedor;
    private String idUsuario;

    // Constructor
    public usuariovendedor(String idVendedor, String idUsuario) {
        this.idVendedor = idVendedor;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }    
}
