import DataStructure.Adres;
import Kontrachent.FirmaKontrachenta;
import Towary.Towar;
import javafx.beans.binding.Bindings;
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

public class WyswietlanieKontrachentowController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<FirmaKontrachenta> kontrachentTabelView;
    @FXML
    private TableColumn<String,String> nazwaColumn;
    @FXML
    private TableColumn<FirmaKontrachenta,String> nipColumn;
    @FXML
    private TableColumn<FirmaKontrachenta, String> krajColumn;
    @FXML
    private TableColumn<FirmaKontrachenta,String> miastoColumn;
    @FXML
    private TableColumn<FirmaKontrachenta,String> ulicaColumn;
    @FXML
    private TableColumn<FirmaKontrachenta,String> budynekColumn;



    public void noweOkno(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("wyswietlanieKontrachentowWindow.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<FirmaKontrachenta> getKontrachentToTable(){
        ObservableList<FirmaKontrachenta> ListaKontrachentow= FXCollections.observableArrayList();
        ArrayList<FirmaKontrachenta> kontracjentArrayList = FirmaKontrachenta.getFromDataBase();
        for(FirmaKontrachenta kontrachent : kontracjentArrayList){
            ListaKontrachentow.add(kontrachent);
        }
        return ListaKontrachentow;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //zamien typy kolumn u gory
        nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaFirmy"));
        nipColumn.setCellValueFactory(new PropertyValueFactory<>("NIP"));
        krajColumn.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getAdres(),"Kraj"));
        miastoColumn.setCellValueFactory(cell->Bindings.selectString(cell.getValue().getAdres(),"Miasto"));
        ulicaColumn.setCellValueFactory(cell->Bindings.selectString(cell.getValue().getAdres(),"Ulica"));
        budynekColumn.setCellValueFactory(cell->Bindings.selectString(cell.getValue().getAdres(),"nrBudynku"));
        kontrachentTabelView.setItems(getKontrachentToTable());


    }

    public void powrotDoMainWindow(ActionEvent e) throws IOException {
        Controller controller = new Controller();
        controller.noweOkno(e);
    }


}
