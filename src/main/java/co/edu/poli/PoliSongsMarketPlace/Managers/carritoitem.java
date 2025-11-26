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
    private String idItem;
    private String idProducto;
    private int cant;
    private int total;

    public carritoitem(String idItem, String idProducto, int cant, int total) {
        this.idItem = idItem;
        this.idProducto = idProducto;
        this.cant = cant;
        this.total = total;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
}
