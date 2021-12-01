import Faktury.FakturyVat.FakturaVAT;
import Kontrachent.Firma;
import Kontrachent.FirmaKontrachenta;
import Towary.Towar;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DodawanieFakturyController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<FirmaKontrachenta> wybranieKontrachentaTabelView;
    @FXML
    private TableColumn<FirmaKontrachenta, String> nazwaKontrachentaColumn;
    @FXML
    private TableColumn<FirmaKontrachenta, String> nipColumn;
    @FXML
    private TableView<Towar> wyborTowaruTabelView;
    @FXML
    private TableColumn<Towar, String> nazwaWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, String> jednostkaMiaryWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, Double> cenaNettoZakupuWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, Double> stawkaVatZakupuWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, Double> cenaBruttoZakupuWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, Double> cenaSprzedazyNettoWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, Double> stawkaVatSprzedazyWybieranieProduktuColumn;
    @FXML
    private TableColumn<Towar, Double> cenaBruttoSprzedazyWybieranieProduktuColumn;
    @FXML
    private TableView<FakturaVAT> wybraneProduktyTabeView;
    @FXML
    private TableColumn<FakturaVAT, String> nazwaDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Integer> iloscDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Double> cenaNettoZaSztukeDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Double> cenBruttoZaSztukeDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Double> stawkaVatDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Double> calkowitaCenaNettoDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Double> calkowitaCenaBruttoDodanychTowarowColumn;
    @FXML
    private TableColumn<FakturaVAT, Double> wartoscPodatkuDodanychTowarowColumn;
    @FXML
    private DatePicker wybierzDateDatePicker;
    @FXML
    private Label wybranyProduktTextField;
    @FXML
    private Label wartoscCalkowitaNettoLabel;
    @FXML
    private Label wartoscCalkowitaBruttoLabel;
    @FXML
    private Label wartoscCalkowitaPodatkuLabel;
    @FXML
    private Button dodajNowaFaktureButton;
    @FXML
    private Button dodajNowegoKontrachentaButton;
    @FXML
    private Button dodajNowyTowarButton;

    public void noweOkno() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieFakturyWindow.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Tytul");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    private ObservableList<FirmaKontrachenta> getKontrachentToTable() {
        ObservableList<FirmaKontrachenta> ListaKontrachentow = FXCollections.observableArrayList();
        ArrayList<FirmaKontrachenta> kontracjentArrayList = FirmaKontrachenta.getFromDataBase();
        for (FirmaKontrachenta kontrachent : kontracjentArrayList) {
            ListaKontrachentow.add(kontrachent);
        }
        return ListaKontrachentow;
    }

    private ObservableList<Towar> getTowarToTabel() {
        ObservableList<Towar> ListaTowaru = FXCollections.observableArrayList();
        ArrayList<Towar> towarArrayList = Towar.getZBazydanych();
        for (Towar towar : towarArrayList) {
            ListaTowaru.add(towar);
        }
        return ListaTowaru;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setKontrachentToTabel();
        setTowarToTabel();

    }


    private void setKontrachentToTabel() {
        nazwaKontrachentaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FirmaKontrachenta, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FirmaKontrachenta, String> firmaKontrachentaStringCellDataFeatures) {
                return new SimpleObjectProperty<>(firmaKontrachentaStringCellDataFeatures.getValue().getNazwaFirmy());
            }
        });
        nipColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FirmaKontrachenta, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FirmaKontrachenta, String> firmaKontrachentaStringCellDataFeatures) {
                return new SimpleObjectProperty<>(firmaKontrachentaStringCellDataFeatures.getValue().getNIP());
            }
        });
        wybranieKontrachentaTabelView.setItems(getKontrachentToTable());
    }

    private void setTowarToTabel() {

        nazwaWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Towar, String> towarStringCellDataFeatures) {
                return new SimpleObjectProperty<>(towarStringCellDataFeatures.getValue().getNazwa());
            }
        });

        jednostkaMiaryWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Towar, String> towarStringCellDataFeatures) {
                return new SimpleObjectProperty<>(towarStringCellDataFeatures.getValue().getJednostkiMiary().getJednostkaMiary());
            }
        });

        cenaNettoZakupuWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getCenaZakupuNetto());
            }
        });

        stawkaVatZakupuWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getStawkaVatZakupu().getStawkaVAT());
            }
        });

        cenaBruttoZakupuWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getCenaZakupuBrutto());
            }
        });

        cenaSprzedazyNettoWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getCenaSprzedazyNetto());
            }
        });

        stawkaVatSprzedazyWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getStawkaVatSprzedazy().getStawkaVAT());
            }
        });

        cenaBruttoSprzedazyWybieranieProduktuColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Towar, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Towar, Double> towarDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarDoubleCellDataFeatures.getValue().getCenaSprzedazyBrutto());
            }
        });

        wyborTowaruTabelView.setItems(getTowarToTabel());
    }



}
