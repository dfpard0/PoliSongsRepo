/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class notificacion {
    private String idNotification;
    private String idRelacion;
    private String idProducto;
    private String mensaje;
    private String fecha;

    // Constructor
    public notificacion(String idNotification, String idRelacion, String idProducto, String mensaje, String fecha) {
        this.idNotification = idNotification;
        this.idRelacion = idRelacion;
        this.idProducto = idProducto;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    // Getters y Setters
    public String getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
    }

    public String getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(String idRelacion) {
        this.idRelacion = idRelacion;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
