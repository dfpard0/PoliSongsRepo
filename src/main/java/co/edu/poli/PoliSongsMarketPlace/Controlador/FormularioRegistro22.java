package co.edu.poli.PoliSongsMarketPlace.Controlador;

import co.edu.poli.PoliSongsMarketPlace.Managers.ManagerUsuario;
import co.edu.poli.PoliSongsMarketPlace.modelo.Usuario;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase2;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormularioRegistro22 {

    @FXML
    private ComboBox<String> ComboBoxRol;

    @FXML
    private TextField CorreoElectronico;

    @FXML
    private Button Iniciar;
    
    @FXML
    private Button btnVolver;

    @FXML
    private TextField NombreUsuario;

    @FXML
    private TextField Numerocel;

    @FXML
    private TextField Password1;

    @FXML
    private TextField Password2;

    @FXML
    private TextField documento;

    private ManagerUsuario usuarioManager;

    public void setConnection(ConexionSupabase2 ConexionSupabase2) {
        this.usuarioManager = new ManagerUsuario(ConexionSupabase2);
    }

    @FXML
    void ClickGuardarUsuario(ActionEvent event) {
        String pass1 = Password1.getText();
        String pass2 = Password2.getText();

        if (pass1 == null || pass1.isEmpty() || pass2 == null || pass2.isEmpty()) {
            mostrarAlerta("Error", "Las contraseñas no pueden estar vacías.");
            return;
        }

        if (!pass1.equals(pass2)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden.");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(NombreUsuario.getText());
        usuario.setCorreoElectronico(CorreoElectronico.getText());
        usuario.setNumero(Numerocel.getText());
        usuario.setDocumento(documento.getText());
        usuario.setTelefono(Numerocel.getText());

        boolean exito = usuarioManager.registrarUsuario(usuario);
        if (exito) {
            mostrarAlerta("Éxito", "Usuario registrado correctamente.");
        } else {
            mostrarAlerta("Error", "No se pudo registrar el usuario.");
        }
    }
    
    @FXML
    void ClickVolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/PoliSongsMarketPlace/Vista/MenuInicio1.fxml"));

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
