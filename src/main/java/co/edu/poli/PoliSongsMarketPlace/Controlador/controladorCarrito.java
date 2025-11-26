package co.edu.poli.PoliSongsMarketPlace.controlador;

import co.edu.poli.PoliSongsMarketPlace.Managers.carritoitem;
import co.edu.poli.PoliSongsMarketPlace.Managers.productovinilo;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoCarritoItem;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoProductoVinilo;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controladorCarrito implements Initializable {

    @FXML
    private TextField TotalCarrito;

    @FXML
    private Button btnVerCarrito;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnirapagar;

    @FXML
    private Button btneliminaritem;

    @FXML
    private TextField cantTotal;

    @FXML
    private TableView<carritoitem> carrito;

    @FXML
    private TableColumn<carritoitem, Integer> columCant;

    @FXML
    private TableColumn<carritoitem, String> columIdproducto;

    @FXML
    private TableColumn<carritoitem, Integer> columItem;

    @FXML
    private TableColumn<carritoitem, Integer> columPrecio;

    final private ObservableList<carritoitem> carritoLista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            configurarColumnas();
            cargarDatosDesdeBD();
            calcularCantidad();
            calcularTotal();
        });
    }

    private void configurarColumnas() {
        columItem.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        columIdproducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        columCant.setCellValueFactory(new PropertyValueFactory<>("cant"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void cargarDatosDesdeBD() {

        try {
            DaoCarritoItem dao = new DaoCarritoItem();
            List<carritoitem> lista = dao.listar();

            // Limpiar lista antes de usarla
            carritoLista.clear();

            // Recorrer lista que viene del DAO
            for (carritoitem pv : lista) {

                carritoLista.add(new carritoitem(
                        pv.getIdItem(),
                        pv.getIdProducto(),
                        pv.getCant(),
                        pv.getTotal()
                ));
            }

            // Cargar datos en tabla
            carrito.setItems(carritoLista);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los datos: " + e.getMessage());
        }
    }

    @FXML
    void clickIrAPagar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/PoliSongsMarketPlace/Vista/procesoPago.fxml"));

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void clickVolver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/PoliSongsMarketPlace/Vista/Catalogo.fxml"));

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void clickEliminarItem(ActionEvent event) {
        try {
            carritoitem seleccionado = carrito.getSelectionModel().getSelectedItem();
            DaoCarritoItem dao = new DaoCarritoItem();

            dao.eliminar(seleccionado.getIdProducto());
            mostrarAlerta("Estado", "El item fue eliminado correctamente.");

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los datos: " + e.getMessage());
        }

    }

    void calcularCantidad() {
        int totalCantidades = 0;

        for (carritoitem v : carrito.getItems()) {
            totalCantidades += v.getCant();
        }
        cantTotal.setText(String.valueOf(totalCantidades));

    }

    void calcularTotal() {

        int total = 0;

        for (carritoitem v : carrito.getItems()) {
            total += v.getTotal();
        }
        TotalCarrito.setText(String.valueOf(total));

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
