package co.edu.poli.PoliSongsMarketPlace.controlador;

import co.edu.poli.PoliSongsMarketPlace.Managers.carritoitem;
import co.edu.poli.PoliSongsMarketPlace.Managers.coleccionCarritos;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoCarritoItem;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoColeccionCarritos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class controladorPago implements Initializable {

    @FXML
    private ComboBox<String> TipoTarjeta;

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
    private TextField numTarjeta;
    
    

    @FXML
    private ComboBox<String> redPago;

    @FXML
    private TextField total;
    
   DaoColeccionCarritos dao = new DaoColeccionCarritos();
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        redPago.getItems().addAll(
                "MASTERCARD",
                "MAESTRO",
                "VISA"
        );

        TipoTarjeta.getItems().addAll(
                "CREDITO",
                "DEBITO"
        );
        
        coleccionCarritos car = dao.obtenerUltimoRegistro();
        total.setText(String.valueOf(car.getTotal()));
        
        
    }

    @FXML
    void clickPagar(ActionEvent event) {
        String tarjeta = numTarjeta.getText();
        int dosdigitos = Integer.parseInt(tarjeta.substring(tarjeta.length() - 2));
        
        if (dosdigitos%2 == 0){
            DaoColeccionCarritos colec = new DaoColeccionCarritos();
            DaoCarritoItem carr = new DaoCarritoItem();
            colec.eliminar();
            carr.eliminarTodo();
            
            mostrarAlerta("Mensaje", "El pago ha sido procesado correctamente");
            
        }else{
            mostrarAlerta("Mensaje", "La tarjeta no es valida. Por favor intente una nueva.");
        }
        

    }

    @FXML
    void clickVerCarrito(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/PoliSongsMarketPlace/Vista/carritodecompras.fxml"));

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void clickVolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/PoliSongsMarketPlace/Vista/carritodecompras.fxml"));

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
