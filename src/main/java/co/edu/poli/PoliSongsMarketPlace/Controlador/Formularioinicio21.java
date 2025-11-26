package co.edu.poli.PoliSongsMarketPlace.Controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Formularioinicio21 {

    @FXML
    private TextField Contraseña;

    @FXML
    private Button Iniciar1;

    @FXML
    private Button IniciarSesion;

    @FXML
    private TextField Usuario;

    @FXML
    void ClickIniciarSesion(ActionEvent event) {

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

}
