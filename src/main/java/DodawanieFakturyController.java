import Faktury.FakturyVat.FakturaVAT;
import Kontrachent.Firma;
import Kontrachent.FirmaKontrachenta;
import Towary.Towar;
import Towary.TowarNaFakturze;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DodawanieFakturyController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static LocalDate dataWystawieniaFaktury;
    private static FirmaKontrachenta wybranyKontrachent;
    private static Towar wybranyTowar;
    private static Integer ilosc;
    private static ArrayList<TowarNaFakturze> wybranyTowarNaFakturze = new ArrayList<>();

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
    private TableView<TowarNaFakturze> wybraneProduktyTabelView;
    @FXML
    private TableColumn<TowarNaFakturze, String> nazwaDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Integer> iloscDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double> cenaNettoZaSztukeDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double> cenBruttoZaSztukeDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double> stawkaVatDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double> calkowitaCenaNettoDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double> calkowitaCenaBruttoDodanychTowarowColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double> wartoscPodatkuDodanychTowarowColumn;
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
    @FXML
    private TextField iloscTextField;

    public DodawanieFakturyController() {
        System.out.println("został utworzony konstruktoer");
    }

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

    private ObservableList<TowarNaFakturze> getTowarNaFakturzeToTabel(){
        ObservableList<TowarNaFakturze> towarNaFakturzeObservableList = FXCollections.observableArrayList();
        for(TowarNaFakturze towarNaFakturze:wybranyTowarNaFakturze ){
            towarNaFakturzeObservableList.add(towarNaFakturze);
        }
        return towarNaFakturzeObservableList;
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

    public void getDateFromDatePicker(ActionEvent event){
        dataWystawieniaFaktury=wybierzDateDatePicker.getValue();
        System.out.println(dataWystawieniaFaktury);
    }

    public void getKontrachentFromTabelView(MouseEvent event){
        if (event.getClickCount() == 2)             //Checking double click
        {
            FirmaKontrachenta firmaKontrachenta = wybranieKontrachentaTabelView.getSelectionModel().getSelectedItem();
            System.out.println(firmaKontrachenta.toString());
        }
    }

    public void getTowarFromTabelView(MouseEvent event){
        if(event.getClickCount()==2){               //Checking double click
            wybranyTowar= wyborTowaruTabelView.getSelectionModel().getSelectedItem();
            System.out.println(wybranyTowar.toString());
            setWybranyTowar();
            setWybranyTowarToTabel();
        }
    }

    private void setWybranyTowar(){
        wybranyProduktTextField.setText("Nazwa: "+wybranyTowar.getNazwa()+"  cena netto:  " + wybranyTowar.getCenaSprzedazyNetto()+"  cena brutto:  "+ wybranyTowar.getCenaSprzedazyBrutto());
    }

    public void getIlosc(ActionEvent event){
        ilosc =Integer.parseInt(iloscTextField.getText());
        setWybranyTowarToTabel();
    }

    private void setWybranyTowarToTabel(){
        if(wybranyTowar!=null && ilosc!=null && !iloscTextField.getText().equals("")){
            wybranyTowarNaFakturze.add(new TowarNaFakturze(wybranyTowar,ilosc));
            setWybranieKontrachentaTabelView();
            iloscTextField.setText("");
        }
    }

    private void setWybranieKontrachentaTabelView(){

        nazwaDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TowarNaFakturze, String> towarNaFakturzeStringCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeStringCellDataFeatures.getValue().getTowar().getNazwa());
            }
        });

        iloscDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<TowarNaFakturze, Integer> towarNaFakturzeIntegerCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeIntegerCellDataFeatures.getValue().getIlosc());
            }
        });

        cenaNettoZaSztukeDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getTowar().getCenaSprzedazyNetto());
            }
        });

        cenBruttoZaSztukeDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getTowar().getCenaSprzedazyBrutto());
            }
        });

        stawkaVatDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getTowar().getStawkaVatSprzedazy().getStawkaVAT());
            }
        });

        calkowitaCenaNettoDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getWartoscNetto());
            }
        });

        calkowitaCenaBruttoDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getWartoscBrutto());
            }
        });

        wartoscPodatkuDodanychTowarowColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getWartoscVAT());
            }
        });

        wybraneProduktyTabelView.setItems(getTowarNaFakturzeToTabel());
    }

}
