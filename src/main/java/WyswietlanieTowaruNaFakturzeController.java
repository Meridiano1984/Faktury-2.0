import Faktury.FakturyVat.FakturaVAT;
import Faktury.FakturyVat.FakturaVATdataBaseOperator;
import Towary.TowarNaFakturze;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WyswietlanieTowaruNaFakturzeController implements Initializable {

    //tutaj dostajemy tylko z listy
    private static Stage stage;
    private Scene scene;
    private Parent root;
    private FakturaVAT fakturaVAT;

    @FXML
    private TableView<TowarNaFakturze>towaryNaFakturzeTabelView;
    @FXML
    private TableColumn<TowarNaFakturze, String>nazwaColumn;
    @FXML
    private TableColumn<TowarNaFakturze, String>jednostkaMirayColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double>cenaNettoColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double>stawkaVatColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double>cenaBruttoColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Integer>iloscColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double>cenaCalkowitaColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double>cenaCalkowitaBruttoColumn;
    @FXML
    private TableColumn<TowarNaFakturze, Double>wartoscPodatkuColumn;

    public WyswietlanieTowaruNaFakturzeController()  {
        System.out.println("jebac bydgoszcz");
    }

    public void noweOkno() throws IOException {      ;
        ObservableList<TowarNaFakturze> listaTowarowNaFakturze = getTowarToTabel();
        this.root = FXMLLoader.load(getClass().getResource("wyswietlanieTowaruNaFakturzeWindow.fxml"));
        this.stage = new Stage();
        stage.setTitle("Tytul");
        this.scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if(this.stage==null){
            System.out.println("to jebany null  1");
        }
    }

    public void powrotDoMainWindow(ActionEvent e) throws IOException {


        //TODO ZMIENIC FUNKCJE TEGO PRZYCISKU DODAJE NOWE MAIN OKNA bo dalem static i to jest zle jak dziala wywolywanie argumentow z Fxml
        if(this.stage==null){
            System.out.println("to jebany null");
        }
        this.stage.hide();
    }

    private ObservableList<TowarNaFakturze> getTowarToTabel(){
        fakturaVAT= Controller.wybranaFaktura;
        ObservableList<TowarNaFakturze> listaTowarowNaFakturze = FXCollections.observableArrayList();
        int fakturId = fakturaVAT.getIndexFromDataBase(fakturaVAT);
        System.out.println("Faktur id "+ fakturId);
        ArrayList<TowarNaFakturze> towarNaFakturzeArrayList = FakturaVATdataBaseOperator.getTowarNaFakturze(fakturId);
        for(TowarNaFakturze towar : towarNaFakturzeArrayList){
            listaTowarowNaFakturze.add(towar);
        }

        return listaTowarowNaFakturze;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        nazwaColumn.setCellValueFactory(cell-> Bindings.selectString(cell.getValue().getTowar(),"nazwa"));
        jednostkaMirayColumn.setCellValueFactory(cell->Bindings.selectString(cell.getValue().getTowar(),"jednostkiMiary"));
        iloscColumn.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        cenaCalkowitaColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscNetto"));
        cenaCalkowitaBruttoColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscVAT"));
        wartoscPodatkuColumn.setCellValueFactory(new PropertyValueFactory<>("wartoscBrutto"));

        cenaNettoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getTowar().getCenaSprzedazyBrutto());
            }
        });
        stawkaVatColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getTowar().getStawkaVatSprzedazy().getStawkaVAT());
                }
            });
        cenaBruttoColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TowarNaFakturze, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TowarNaFakturze, Double> towarNaFakturzeDoubleCellDataFeatures) {
                return new SimpleObjectProperty<>(towarNaFakturzeDoubleCellDataFeatures.getValue().getTowar().getCenaSprzedazyBrutto());
            }
        });
                towaryNaFakturzeTabelView.setItems(getTowarToTabel());

    }
}
