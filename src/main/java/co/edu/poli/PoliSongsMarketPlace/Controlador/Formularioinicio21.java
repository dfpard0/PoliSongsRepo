package co.edu.poli.PoliSongsMarketPlace.Controlador;

import co.edu.poli.PoliSongsMarketPlace.Managers.ManagerUsuario;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import co.edu.poli.PoliSongsMarketPlace.repositorio.ConexionSupabase2;
import javafx.scene.control.Alert;

public class Formularioinicio21 {
 
    @FXML
    private TextField Contraseña;

    @FXML
    private Button Iniciar1;

    @FXML
    private Button IniciarSesion;

    @FXML
    private TextField Usuario;
        String nombreUsuario = Usuario.getText().trim();
        String password = Contraseña.getText().trim();
    @FXML
 void ClickIniciarSesion(ActionEvent event) {
        ManagerUsuario managerUsuario = new ManagerUsuario();
        String nombreUsuario = Usuario.getText().trim();
        String password = Contraseña.getText().trim();
        
        // Validar que los campos no estén vacíos
        if (nombreUsuario.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos");
            return;
        }
        
        // Verificar credenciales usando la INSTANCIA de ManagerUsuario
        boolean credencialesCorrectas=false;
        
        credencialesCorrectas = managerUsuario.verificarLogin(nombreUsuario, password);
        
        if (credencialesCorrectas) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso");
            // Aquí puedes redirigir a la pantalla principal
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos");
            // Limpiar solo el campo de contraseña para nuevo intento
            Contraseña.clear();
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

    private void limpiarCampos() {
        Usuario.clear();
        Contraseña.clear();
    }

}
