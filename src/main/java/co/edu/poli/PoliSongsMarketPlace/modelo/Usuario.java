/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.modelo;



public class Usuario {

   private long id;
    private String nombreUsuario;
    private String numero;
    private String correoElectronico;
    private String telefono;
    private String password;
    private String documento;


    public Usuario(long id, String nombreUsuario, String numero, String correoElectronico,
                   String telefono, String password, String documento) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.numero = numero;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.password = password;
        this.documento = documento;
            }

    public Usuario() {}

    // Getters y Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

}

  


