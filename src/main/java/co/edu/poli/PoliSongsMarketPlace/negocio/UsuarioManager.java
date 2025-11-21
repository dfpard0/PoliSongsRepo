
package co.edu.poli.PoliSongsMarketPlace.negocio;

public class UsuarioManager {
    private String idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;

    // Constructor vacío
    public UsuarioManager() {}

    // Constructor completo
    public UsuarioManager(String idUsuario, String nombre, String email, String contrasena, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + 
               ", email=" + email + ", rol=" + rol + "]";
    }
}
