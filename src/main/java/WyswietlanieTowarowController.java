import DataStructure.JednostkiMiary;
import Faktury.FakturyVat.FakturaVAT;
import Podatki.StawkaVat;
import Towary.Towar;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WyswietlanieTowarowController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Towar> towarTabelView;
    @FXML
    private TableColumn<Towar,String> nazwaColumn;
    @FXML
    private TableColumn<Towar, JednostkiMiary> jednostkaMiaryColumn;
    @FXML
    private TableColumn<Towar,Double> cenaNettoZakupuColumn;
    @FXML
    private TableColumn<Towar,Double> stawkaVatZakupuColumn;
    @FXML
    private TableColumn<Towar, StawkaVat> cenaBruttoZakupuColumn;
    @FXML
    private TableColumn<Towar,Double> cenaSprzedazyNettoColumn;
    @FXML
    private TableColumn<Towar,Double> stawkaVatSprzedazyColumn;
    @FXML
    private TableColumn<Towar,Integer> cenaBruttoSprzedazyColumn;

    public void noweOkno(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("wyswietlanieTowaruWindow.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Towar> getTowarToTabel(){
        ObservableList<Towar> ListaTowaru= FXCollections.observableArrayList();
        ArrayList<Towar> towarArrayList = Towar.getZBazydanych();
        for(Towar towar : towarArrayList){
            ListaTowaru.add(towar);
        }
        return ListaTowaru;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        jednostkaMiaryColumn.setCellValueFactory(new PropertyValueFactory<>("jednostkiMiary"));
        cenaNettoZakupuColumn.setCellValueFactory(new PropertyValueFactory<>("cenaZakupuNetto"));
//        stawkaVatZakupuColumn.setCellValueFactory(new PropertyValueFactory<>("stawkaVatZakupu"));
        stawkaVatZakupuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getStawkaVatZakupu().getStawkaVAT()) ;
            }
        });
        cenaBruttoZakupuColumn.setCellValueFactory(new PropertyValueFactory<>("cenaZakupuBrutto"));
        cenaSprzedazyNettoColumn.setCellValueFactory(new PropertyValueFactory<>("cenaSprzedazyNetto"));
        stawkaVatSprzedazyColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getStawkaVatSprzedazy().getStawkaVAT());
            }
        });
        cenaBruttoSprzedazyColumn.setCellValueFactory(new PropertyValueFactory<>("cenaSprzedazyBrutto"));
        towarTabelView.setItems(getTowarToTabel());
    }

    public void powrotDoMainWindow(ActionEvent e) throws IOException {
        Controller controller = new Controller();
        controller.noweOkno(e);
    }
}
