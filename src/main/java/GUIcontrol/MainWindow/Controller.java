package GUIcontrol.MainWindow;

import Faktury.Faktura;
import Faktury.FakturyVat.FakturaVAT;
import Kontrachent.Kontrachent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private TableColumn<String,String> fakturaIDColumn;
    @FXML
    private TableColumn<String,String> typFakturyColumn;
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

    public static void tabelSeetings(){

    }

//    public Controller() {
////        fakturyTabelView.setItems(getFakturyToTabel());
////        fakturyTabelView.setEditable(true);
//
//        nrFakturyIDColumn.setCellValueFactory(new PropertyValueFactory<>("numerFaktury"));
//        kontrachentNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaKontrachenta"));
//        dataWystawianiaColumn.setCellValueFactory(new PropertyValueFactory<>("dataWystawienia"));
//        wartoscNettoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscSprzedazyNetto"));
//        wartoscBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscSprzedazyBrutto"));
//        wartoscPodatkuColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscPodatku"));
//        uwagiColumn.setCellValueFactory(new PropertyValueFactory<>("uwaga"));
//
//        fakturyTabelView.setItems(getFakturyToTabel());
////        fakturyTabelView.getColumns().addAll(nrFakturyIDColumn,kontrachentNameColumn,dataWystawianiaColumn,wartoscNettoColumn,wartoscBruttoColumn,wartoscPodatkuColumn,uwagiColumn);
//
//    }

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
//        fakturaIDColumn.
        nrFakturyColumn.setCellValueFactory(new PropertyValueFactory<>("numerFaktury"));
        kontrachentNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaKontrachenta"));
        dataWystawianiaColumn.setCellValueFactory(new PropertyValueFactory<>("dataWystawienia"));
        wartoscNettoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscSprzedazyNetto"));
        wartoscBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscSprzedazyBrutto"));
        wartoscPodatkuColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscPodatku"));
        uwagiColumn.setCellValueFactory(new PropertyValueFactory<>("uwaga"));

        fakturyTabelView.setItems(getFakturyToTabel());
    }

//    public void dodajFakture(ActionEvent event){
//        try {
////            Parent root = FXMLLoader.load(getClass().getResource("dodawanieFakturyWindow.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("resources/dodawanieFakturyWindow.fxml"));
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
////        System.out.println("Dodanie fkatury");
//
//
//    }

    public void dodajFakture(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieFakturyWindow.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
