/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Controlador;

 
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuInicio1 {
    

    @FXML
    private Button BotonInicio;

    @FXML
    private Button BotonRegistro;

    @FXML
    void inicio(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Formularioinicio21.fxml"));

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void registro(ActionEvent event)throws Exception {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/PoliSongsMarketPlace/Vista/FormularioRegistro22.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
