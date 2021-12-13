import Faktury.Faktura;
import Faktury.FakturyVat.FakturaVAT;
import Kontrachent.FirmaKontrachenta;
import Kontrachent.MojaFirma;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DodawanieFakturyController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static LocalDate dataWystawieniaFaktury;
    private static FirmaKontrachenta wybranyKontrachent;
    private static Towar wybranyTowar;
    private static Integer ilosc;
    private  ArrayList<TowarNaFakturze> wybranyTowarNaFakturzeArrayList=new ArrayList<>();

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
        System.out.println("KONSTRUKTOR KONSTRUKTOR KONSTRUKTOR");
    }

    public void noweOkno(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieFakturyWindow.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        for(TowarNaFakturze towarNaFakturze: wybranyTowarNaFakturzeArrayList){
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
    }

    public void getKontrachentFromTabelView(MouseEvent event){
        if (event.getClickCount() == 2)             //Checking double click
        {
            wybranyKontrachent = wybranieKontrachentaTabelView.getSelectionModel().getSelectedItem();
        }
    }

    public void getTowarFromTabelView(MouseEvent event){
        if(event.getClickCount()==2){               //Checking double click
            wybranyTowar= wyborTowaruTabelView.getSelectionModel().getSelectedItem();
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
            wybranyTowarNaFakturzeArrayList.add(new TowarNaFakturze(wybranyTowar,ilosc));
            setWybranieKontrachentaTabelView();
            iloscTextField.setText("");

            for(TowarNaFakturze towr : wybranyTowarNaFakturzeArrayList){
                System.out.println("Towr: " + towr.getTowar().getNazwa()+ "  ilosc: " + towr.getIlosc() );
            }
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

        setWartoscCalkowitaNettoLabel();
        setWartoscCalkowitaBruttoLabel();
        setWartoscPodatkuLabel();
    }

    public void setWartoscCalkowitaNettoLabel(){
        double suma=0;
        for(TowarNaFakturze towarNaFakturze: wybranyTowarNaFakturzeArrayList){
            suma=suma+towarNaFakturze.getWartoscNetto();
        }
        wartoscCalkowitaNettoLabel.setText("Wartość całkowita Netto: "+ BigDecimal.valueOf(suma).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public void setWartoscCalkowitaBruttoLabel(){
        double suma=0;
        for(TowarNaFakturze towarNaFakturze: wybranyTowarNaFakturzeArrayList){
            suma=suma+towarNaFakturze.getWartoscBrutto();
        }
        wartoscCalkowitaBruttoLabel.setText("Wartość całkowita Brutto: "+BigDecimal.valueOf(suma).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public void setWartoscPodatkuLabel(){
        double suma=0;
        for(TowarNaFakturze towarNaFakturze: wybranyTowarNaFakturzeArrayList){
            suma =suma+ towarNaFakturze.getWartoscVAT();
        }
        wartoscCalkowitaPodatkuLabel.setText("Wartość całkowita Podatku: "+BigDecimal.valueOf(suma).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public void dodawanieNowejFaktury(){
        Faktura fakturaVAT = new FakturaVAT(dataWystawieniaFaktury, MojaFirma.getInstance(),wybranyKontrachent,wybranyTowarNaFakturzeArrayList);
        fakturaVAT.dodanieFakturyDoBazydanych();
    }

    public void powrot(ActionEvent e) throws IOException {
        Controller controller = new Controller();
        controller.noweOkno(e);
    }

    public void odswiez(){
        setKontrachentToTabel();
        setTowarToTabel();
        setWybranieKontrachentaTabelView();
    }

    public void dodanieNowegoProduktu() throws  IOException{
        DodawanieProduktuController dodawanieProduktuController = new DodawanieProduktuController();
        dodawanieProduktuController.noweOkno();
    }

    public void dodanieNowegoKontrachenta() throws IOException{
        DodawanieKontrachentaController dodawanieKontrachentaController = new DodawanieKontrachentaController();
        dodawanieKontrachentaController.noweOkno();
    }

}
