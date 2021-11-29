import DataStructure.Adres;
import Kontrachent.Firma;
import Kontrachent.FirmaKontrachenta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DodawanieKontrachentaController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nazwaTextField;
    @FXML
    private TextField nipTextField;
    @FXML
    private TextField krajTextField;
    @FXML
    private TextField miastoTextField;
    @FXML
    private TextField budynekTextField;
    @FXML
    private TextField ulicaTextField;
    @FXML
    private Button dodajKontrachentaButton;


    public void dodajKontrachenta(){
        Alert alert;
                if(this.kontrachentValidation(nazwaTextField.getText(),nipTextField.getText(),krajTextField.getText(),miastoTextField.getText(),budynekTextField.getText(),ulicaTextField.getText())){
                    FirmaKontrachenta nowaFirma = new FirmaKontrachenta(nazwaTextField.getText(),new Adres(krajTextField.getText(),miastoTextField.getText(),ulicaTextField.getText(),budynekTextField.getText()),nipTextField.getText());
                    nowaFirma.dodawnieDoBazyDanychZGUI();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacja");
                    alert.setHeaderText(null);
                    alert.setContentText(nazwaTextField.getText()+" został(a) dodany do bazy danych");
                    alert.showAndWait();
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(nazwaTextField.getText()+" nie został(a) dodany do bazy danych");
                    alert.showAndWait();
                }
    }

    private boolean kontrachentValidation(String nazwa, String nip, String kraj, String miasto, String budynek, String ulica){
        try{
            //TODO SPRAWDZAC ROZMIAR NAZW ZGODNIE Z BAZA DANYCH
            Exception exception = new Exception();
            if(!(nazwa.equals("") || nip.equals("") || kraj.equals("") || miasto.equals("") || budynek.equals("") || ulica.equals("")))throw exception;
            if(!Firma.sprawdzanieNIP(nip)) throw exception;
            return  true;
        }catch (Exception e){
            return false;
        }
    }



    public void noweOkno() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieKontrachenta.fxml"));
        Stage newStage = new Stage();
        newStage.setTitle("Tytul");
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}
