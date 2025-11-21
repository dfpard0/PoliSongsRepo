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
    
    private long idProductoVini;
    private String idProducto;
    private int stock;

    // Constructor
    public productovinilo(long idProductoVini, String idProducto, int stock) {
        this.idProductoVini = idProductoVini;
        this.idProducto = idProducto;
        this.stock = stock;
    }

    // Getters y Setters
    public long getIdProductoVini() {
        return idProductoVini;
    }

    public void setIdProductoVini(long idProductoVini) {
        this.idProductoVini = idProductoVini;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
