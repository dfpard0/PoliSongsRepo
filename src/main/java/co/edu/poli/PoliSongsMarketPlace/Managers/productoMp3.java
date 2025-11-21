/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class productoMp3 {

    private String idProductoMp3;
    private String idProducto;
    private int stock;

    // Constructor
    public productoMp3(String idProductoMp3, String idProducto, int stock) {
        this.idProductoMp3 = idProductoMp3;
        this.idProducto = idProducto;
        this.stock = stock;
    }

    // Getters y Setters
    public String getIdProductoMp3() {
        return idProductoMp3;
    }

    public void setIdProductoMp3(String idProductoMp3) {
        this.idProductoMp3 = idProductoMp3;
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
