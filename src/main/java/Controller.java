import Faktury.Faktura;
import Faktury.FakturyVat.FakturaVAT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

//TODO PRZEROB ARCHITEKTURE PROGRAMU MASZ 3 ROZNE KONCEPCJE OBIEKTOWA BAZE ANYCH I DO WYSWIETLANIA ROZMYWAJA SIE
//TODO ZROB TAK ZE OBIEKTY ODWZOROWUJA OBIEKTY W BAZIE DANYCH I ZMIEN KLUCZ NP DLA TABLE FAKTUR KONTRACHT NA KONTRACHENT NAME W STRING I W TABLI KONTRACHENT PRIMARY KEY KONTRACHENT NAME STRING

public class Controller implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<String,String> nrFakturyColumn;
    @FXML
    private TableColumn<String, String> kontrachentNameColumn;
    @FXML
    private TableColumn<String, Date> dataWystawianiaColumn;
    @FXML
    private TableColumn<String,Double> wartoscNettoColumn;
    @FXML
    private TableColumn<String,Double> wartoscBruttoColumn;
    @FXML
    private TableColumn<String,Double> wartoscPodatkuColumn;
    @FXML
    private TableColumn<String,String> uwagiColumn;
    @FXML
    private TableView<FakturaVAT> fakturyTabelView;
    @FXML
    private Button dodajFaktureButton;

    public Controller() {
        System.out.println("Cotroller utworzony");
    }


    public ObservableList<FakturaVAT> getFakturyToTabel(){

        ObservableList<FakturaVAT> faktury= FXCollections.observableArrayList();
        ArrayList<FakturaVAT> listaFaktur = Faktura.getAllFakturFromDBToTabel();
        for(FakturaVAT faktura : listaFaktur){
            faktury.add(faktura);
        }
        return faktury;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                //TODO JAK ZROBIC BY TO NIE WLACZAO SIE PODCZAS ZMIANY OKNA
                nrFakturyColumn.setCellValueFactory(new PropertyValueFactory<>("numerFaktury"));
                kontrachentNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaKontrachenta"));
                dataWystawianiaColumn.setCellValueFactory(new PropertyValueFactory<>("dataWystawienia"));
                wartoscNettoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscSprzedazyNetto"));
                wartoscBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscSprzedazyBrutto"));
                wartoscPodatkuColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscPodatku"));
                uwagiColumn.setCellValueFactory(new PropertyValueFactory<>("uwaga"));
                fakturyTabelView.setItems(getFakturyToTabel());
    }

    //TODO ZALAPAC TE WYJATKI


    public void dodajFakture(ActionEvent e) throws IOException {

        DodawanieFakturyController controller2 = new DodawanieFakturyController();
        controller2.noweOkno();
    }

    public void dodajProdukt(ActionEvent e) throws IOException{

        DodawanieProduktuController dodawanieProduktuController = new DodawanieProduktuController();
        dodawanieProduktuController.noweOkno();

    }

    public void wyswietlTowar(ActionEvent e) throws IOException{
        WyswietlanieTowarowController wyswietlanieTowarowController = new WyswietlanieTowarowController();
        wyswietlanieTowarowController.noweOkno(e);
    }

}
