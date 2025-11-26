package co.edu.poli.PoliSongsMarketPlace.Controlador;

import co.edu.poli.PoliSongsMarketPlace.Managers.carritoitem;
import co.edu.poli.PoliSongsMarketPlace.Managers.productovinilo;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoCarritoItem;
import co.edu.poli.PoliSongsMarketPlace.datos.DaoProductoVinilo;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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

public class controladorCatalogo implements Initializable {

    @FXML
    private Button btnAñadirCarrito;

    @FXML
    private Button btnVerCarrito;

    @FXML
    private Button btnirapagar;

    @FXML
    private Button btnSalir;

    @FXML
    private TextField cantCarrito;

    @FXML
    private TableView<productovinilo> catalogo;

    @FXML
    private TableColumn<productovinilo, String> columAutor;

    @FXML
    private TableColumn<productovinilo, Integer> columCant;

    @FXML
    private TableColumn<productovinilo, String> columCodigo;

    @FXML
    private TableColumn<productovinilo, Integer> columPrecio;

    @FXML
    private TableColumn<productovinilo, String> columTitulo;

    final private ObservableList<productovinilo> listaVinilos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarDatosDesdeBD();

    }

    private void configurarColumnas() {
        columCodigo.setCellValueFactory(new PropertyValueFactory<>("idproductovini"));
        columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }

    private void cargarDatosDesdeBD() {

        try {
            DaoProductoVinilo dao = new DaoProductoVinilo();
            List<productovinilo> lista = dao.listar();

            // Limpiar lista antes de usarla
            listaVinilos.clear();

            // Recorrer lista que viene del DAO
            for (productovinilo pv : lista) {

                listaVinilos.add(new productovinilo(
                        pv.getIdProductoVini(),
                        pv.getTitulo(),
                        pv.getAutor(),
                        pv.getCantidad(),
                        pv.getPrecio()
                ));
            }

            // Cargar datos en tabla
            catalogo.setItems(listaVinilos);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los datos: " + e.getMessage());
        }
    }

    @FXML
    void clickAñadirCarrito(ActionEvent event) {
        try {
            DaoCarritoItem dao = new DaoCarritoItem();
            productovinilo seleccionado = catalogo.getSelectionModel().getSelectedItem();

            carritoitem carritoItem = new carritoitem("1", seleccionado.getIdProductoVini(), 1, seleccionado.getPrecio());

            if (seleccionado == null) {
                mostrarAlerta("Sin selección", "Primero debes seleccionar un vinilo.");
                return;
            }
            if (cantCarrito.getText().isBlank()) {
                cantCarrito.setText("1");
            } else {
                int cantTotal = Integer.parseInt(cantCarrito.getText()) + 1;
                cantCarrito.setText(String.valueOf(cantTotal));
            }
            dao.insertar(carritoItem);

            mostrarAlerta("Añadido", "El vinilo fue añadido al carrito.");

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los datos: " + e.getMessage());
        }

    }

    @FXML
    void clickIrAPagar(ActionEvent event) {

    }

    @FXML
    void clickVerCarrito(ActionEvent event) {

    }

    @FXML
    void clickSalir(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuInicio1.fxml"));

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
