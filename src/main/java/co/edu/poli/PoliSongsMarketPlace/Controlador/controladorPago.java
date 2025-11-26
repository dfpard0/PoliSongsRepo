package co.edu.poli.PoliSongsMarketPlace.controlador;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorPago {

    @FXML
    private ComboBox<?> TipoTarjeta;

    @FXML
    private Button btnPagar;

    @FXML
    private Button btnVerCarrito;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnirapagar;

    @FXML
    private TextField correo;

    @FXML
    private ComboBox<?> redPago;

    @FXML
    private TextField total;

    @FXML
    void clickPagar(ActionEvent event) {

    }

    @FXML
    void clickVerCarrito(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("carritodecompras.fxml"));

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void clickVolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("carritodecompras.fxml"));

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
