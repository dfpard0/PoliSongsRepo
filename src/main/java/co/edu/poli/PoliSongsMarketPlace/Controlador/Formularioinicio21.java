/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poli.PoliSongsMarketPlace.Controlador;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.print.DocFlavor.URL;

public class Formularioinicio21 {

    @FXML
    private TextField Contraseña;

    @FXML
    private Button Iniciar;

    @FXML
    private ComboBox<String> Rol;

    @FXML
    private TextField Usuario;

    @FXML
    void Click(ActionEvent event) {

    }
    
    public void initialize (URL url, ResourceBundle resourceBundle){
        Rol.setItems(FXCollections.observableArrayList("Comprador", "Vendedor", "Ambas"));
    }

}
