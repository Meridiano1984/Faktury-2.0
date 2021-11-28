import DataStructure.JednostkiMiary;
import Podatki.StawkaVat;
import Towary.Towar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DodawanieProduktuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //TODO zmienic stavkiVatTextField na choicePickery


    @FXML
    private Button dodajProduktButton;
    @FXML
    private TextField nazwaTowaruTextField;
    @FXML
    private TextField jedonstkaMiaryTextField;
    @FXML
    private TextField cenaZakupuNettoTextField;
    @FXML
    private TextField stawkaVatZakupuTextField;
    @FXML
    private TextField cenaSprzedazyNettoTextField;
    @FXML
    private TextField stawkaVatSprzedazyTextField;
    @FXML
    private Label cenaZakupuBruttoLabel;
    @FXML
    private Label cenaSprzedazyBruttoLabel;


    public void noweOkno() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieProduktuWindow.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Tytul");
        newStage.setScene(new Scene(root));
        newStage.show();
    }



    public void dodajProdukt(){

        Alert alert;


        if(this.produktValidation(nazwaTowaruTextField.getText(),jedonstkaMiaryTextField.getText(),cenaZakupuNettoTextField.getText(),stawkaVatZakupuTextField.getText(),cenaSprzedazyNettoTextField.getText(),stawkaVatSprzedazyTextField.getText())){

            Towar towar = new Towar(nazwaTowaruTextField.getText(),JednostkiMiary.sprawdzanieJednostekMiary(jedonstkaMiaryTextField.getText()),Double.parseDouble(cenaZakupuNettoTextField.getText()),new StawkaVat(Integer.parseInt(stawkaVatZakupuTextField.getText())),Double.parseDouble(cenaSprzedazyNettoTextField.getText()),new StawkaVat(Integer.parseInt(stawkaVatSprzedazyTextField.getText())));
            towar.dodawnieDoBazyDanychZGUI();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText(nazwaTowaruTextField.getText()+" został(a) dodany do bazy danych");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(nazwaTowaruTextField.getText()+" nie został(a) dodany do bazy danych");
            alert.showAndWait();
        }


    }

    private boolean produktValidation(String nazwa, String jednostkiMiary, String cenaZakupuNetto, String stawkaVatZakupu, String cenaSprzedazyNetto, String stawkaVatSprzedazy){

        try {
            JednostkiMiary jednostkiMiaryCheck = JednostkiMiary.sprawdzanieJednostekMiary(jednostkiMiary);
            double cenaZakupuNettoDouble = Double.parseDouble(cenaZakupuNetto);
            int stawkaVatZakupuInt = Integer.parseInt(stawkaVatZakupu);
            double cenaSprzedazyNettoDouble = Double.parseDouble(cenaSprzedazyNetto);
            int stawkaVatSprzedazyInt = Integer.parseInt(stawkaVatSprzedazy);
            StawkaVat stawkaVatZakupuStavkaVat = new StawkaVat(stawkaVatZakupuInt);
            StawkaVat stawkaVatSprzedazyStavkaVat = new StawkaVat(stawkaVatSprzedazyInt);
            return true;

        } catch (Exception e){
            System.out.println("Nieprawidłowe argumenty");
            return false;
        }
    }

    public void ustawCeneBruttoSprzedazy(){
            if(cenaSprzedazyNettoTextField.getText()!=null && stawkaVatSprzedazyTextField.getText()!=null && !cenaSprzedazyNettoTextField.getText().equals("") && !stawkaVatSprzedazyTextField.getText().equals("")){
                StawkaVat stawkaVatSprzedazy = new StawkaVat(Integer.parseInt(stawkaVatSprzedazyTextField.getText()));
                double cenaSprzedazybrutto = Double.parseDouble(cenaSprzedazyNettoTextField.getText())*stawkaVatSprzedazy.getStawkaVAT();
                cenaSprzedazyBruttoLabel.setText("Cena Brutto sprzedazy: " + BigDecimal.valueOf(cenaSprzedazybrutto).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
    }

    public void ustawCeneBruttoZakupu(){
        if(cenaZakupuNettoTextField.getText()!=null && stawkaVatZakupuTextField.getText()!=null && !cenaZakupuNettoTextField.getText().equals("") && !stawkaVatZakupuTextField.getText().equals("")){
            StawkaVat stawkaVatZakupu = new StawkaVat(Integer.parseInt(stawkaVatZakupuTextField.getText()));
            double cenaZakupubrutto = Double.parseDouble(cenaZakupuNettoTextField.getText())*stawkaVatZakupu.getStawkaVAT();
            cenaZakupuBruttoLabel.setText("Cena Brutto zakupu: " + BigDecimal.valueOf(cenaZakupubrutto).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
    }




}
