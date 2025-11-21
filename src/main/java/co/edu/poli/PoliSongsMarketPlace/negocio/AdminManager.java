/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.negocio;
import co.edu.poli.PoliSongsMarketPlace.datos.*;
/**
 *
 * @author Pardo
 */
public class AdminManager {
    private DaoUsuario daoUsuario;
    private DaoProducto daoProducto;
    private DaoInfraccion daoInfraccion;
    private DaoPago daoPago;
    private DaoOrden daoOrden;

    public AdminManager() {
        this.daoUsuario = new DaoUsuario();
        this.daoProducto = new DaoProducto();
        this.daoInfraccion = new DaoInfraccion();
        this.daoPago = new DaoPago();
        this.daoOrden = new DaoOrden();
    }

    // Getters para acceder a los DAOs individualmente
    public DaoUsuario getDaoUsuario() {
        return daoUsuario;
    }

    public DaoProducto getDaoProducto() {
        return daoProducto;
    }

    public DaoInfraccion getDaoInfraccion() {
        return daoInfraccion;
    }

    public DaoPago getDaoPago() {
        return daoPago;
    }

    public DaoOrden getDaoOrden() {
        return daoOrden;
    }
}