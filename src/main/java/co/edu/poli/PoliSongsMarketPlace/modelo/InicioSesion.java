
package co.edu.poli.PoliSongsMarketPlace.modelo;


public class InicioSesion {
        private String usuario;
    private String contrasena;

    public InicioSesion(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "InicioSesion{" + "usuario=" + usuario + ", contrasena=" + contrasena + '}';
    }
    
}
