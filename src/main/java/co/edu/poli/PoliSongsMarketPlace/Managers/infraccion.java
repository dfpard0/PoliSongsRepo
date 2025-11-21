/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Managers;

/**
 *
 * @author Pardo
 */
public class infraccion {
    private String idInfraccion;
    private String idUsuarioRepo;
    private String tipoInf;
    private String motivo;
    private String fecha;

    // Constructor
    public infraccion(String idInfraccion, String idUsuarioRepo, String tipoInf, String motivo, String fecha) {
        this.idInfraccion = idInfraccion;
        this.idUsuarioRepo = idUsuarioRepo;
        this.tipoInf = tipoInf;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    // Getters y Setters
    public String getIdInfraccion() {
        return idInfraccion;
    }

    public void setIdInfraccion(String idInfraccion) {
        this.idInfraccion = idInfraccion;
    }

    public String getIdUsuarioRepo() {
        return idUsuarioRepo;
    }

    public void setIdUsuarioRepo(String idUsuarioRepo) {
        this.idUsuarioRepo = idUsuarioRepo;
    }

    public String getTipoInf() {
        return tipoInf;
    }

    public void setTipoInf(String tipoInf) {
        this.tipoInf = tipoInf;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
