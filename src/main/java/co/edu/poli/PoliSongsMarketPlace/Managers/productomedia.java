/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *productomedia
 * @author Pardo
 */
public class productomedia {
    private String idMedia;
    private String idProducto;
    private String url;
    private String portada;

    // Constructor
    public productomedia(String idMedia, String idProducto, String url, String portada) {
        this.idMedia = idMedia;
        this.idProducto = idProducto;
        this.url = url;
        this.portada = portada;
    }

    // Getters y Setters
    public String getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}