/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class carritoitem {
     private String idCarritoItem;
    private String idProducto;
    private String idCarrito;
    private int cantidad;

    // Constructor
    public carritoitem(String idCarritoItem, String idProducto, String idCarrito, int cantidad) {
        this.idCarritoItem = idCarritoItem;
        this.idProducto = idProducto;
        this.idCarrito = idCarrito;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getIdCarritoItem() {
        return idCarritoItem;
    }

    public void setIdCarritoItem(String idCarritoItem) {
        this.idCarritoItem = idCarritoItem;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(String idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
