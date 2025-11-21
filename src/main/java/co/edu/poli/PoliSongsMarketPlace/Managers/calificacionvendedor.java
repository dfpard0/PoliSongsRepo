/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */public class calificacionvendedor {
    private int idealification;
    private String idrelacion;
    private short puntuacion;
    private String comentario;
    private String fecha;

    // Constructor
    public calificacionvendedor(int idealification, String idrelacion, short puntuacion, String comentario, String fecha) {
        this.idealification = idealification;
        this.idrelacion = idrelacion;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdealification() {
        return idealification;
    }

    public void setIdealification(int idealification) {
        this.idealification = idealification;
    }

    public String getIdrelacion() {
        return idrelacion;
    }

    public void setIdrelacion(String idrelacion) {
        this.idrelacion = idrelacion;
    }

    public short getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(short puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
