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
import javax.print.DocFlavor;

public class FormularioRegistro22 {

    @FXML
    private ComboBox<String> ComboBoxRol;

    @FXML
    private TextField CorreoElectronico;

    @FXML
    private Button Iniciar;

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

    @FXML
    void Click(ActionEvent event) {

    }
       
    public void initialize (DocFlavor.URL url, ResourceBundle resourceBundle){
        ComboBoxRol.setItems(FXCollections.observableArrayList("Comprador", "Vendedor"));
    }

}
