import DataStructure.JednostkiMiary;
import Faktury.FakturyVat.FakturaVAT;
import Podatki.StawkaVat;
import Towary.Towar;
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
    private TableColumn<String,String> nazwaColumn;
    @FXML
    private TableColumn<String, JednostkiMiary> jednostkaMiaryColumn;
    @FXML
    private TableColumn<String,Double> cenaNettoZakupuColumn;
    @FXML
    private TableColumn<String,Double> stawkaVatZakupuColumn;
    @FXML
    private TableColumn<String, StawkaVat> cenaBruttoZakupuColumn;
    @FXML
    private TableColumn<String,Double> cenaSprzedazyNettoColumn;
    @FXML
    private TableColumn<String,Double> stawkaVatSprzedazyColumn;
    @FXML
    private TableColumn<String,Integer> cenaBruttoSprzedazyColumn;

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
        stawkaVatZakupuColumn.setCellValueFactory(new PropertyValueFactory<>("stawkaVatZakupu"));
        cenaBruttoZakupuColumn.setCellValueFactory(new PropertyValueFactory<>("cenaZakupuBrutto"));
        cenaSprzedazyNettoColumn.setCellValueFactory(new PropertyValueFactory<>("cenaSprzedazyNetto"));
        stawkaVatSprzedazyColumn.setCellValueFactory(new PropertyValueFactory<>("stawkaVatSprzedazy"));
        cenaBruttoSprzedazyColumn.setCellValueFactory(new PropertyValueFactory<>("cenaSprzedazyBrutto"));
        towarTabelView.setItems(getTowarToTabel());
//        towarTabelView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }
}
