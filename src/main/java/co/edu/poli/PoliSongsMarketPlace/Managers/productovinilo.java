/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class productovinilo {
    
    private String idProductoVini;
    private String titulo;
    private int cantidad;
    private int precio;

    public productovinilo(String idProductoVini, String titulo, int cantidad, int precio) {
        this.idProductoVini = idProductoVini;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getIdProductoVini() {
        return idProductoVini;
    }

    public void setIdProductoVini(String idProductoVini) {
        this.idProductoVini = idProductoVini;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
}

