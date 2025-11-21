/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class comentario {
    private String idComentario;
    private String idProducto;
    private String idRelacion;
    private String texto;
    private String fecha;

    // Constructor
    public comentario(String idComentario, String idProducto, String idRelacion, String texto, String fecha) {
        this.idComentario = idComentario;
        this.idProducto = idProducto;
        this.idRelacion = idRelacion;
        this.texto = texto;
        this.fecha = fecha;
    }

    // Getters y Setters
    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(String idRelacion) {
        this.idRelacion = idRelacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
