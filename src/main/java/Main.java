//import ApplicationInterface.ComendUserInterface;
//import ApplicationInterface.GuiControler;
//import DataBase.DatabaseInitialization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import java.time.LocalDate;
//import java.util.*;

//public class Main {
//
//
//    public static void main(String[] args) {
//        //KOMENDY DO INICJALIZACJI BAZY DANYCH MYSQL
//        //TODO dodac inicjalizacje schema w MYSQL a nie robic to recznie
//        //TODO CZY JESLI LACZYMY JAKIS PROPGRAM Z BAZ DANYCH TO OBIEKTY W NIM POWINNY BYC ODZWIERCIEDLENIM TYCH W DB? CZY NA ODWROT CZY INACZEJ?
//        //TODO CZY OBIEKTY POWINNY BYC PRZECHOWYWANE W PAMIECI UZYTKOWEJ RPGRAU CZY W DB JEST OK?
//
//        ComendUserInterface comendUserInterface = new ComendUserInterface();
//        comendUserInterface.dzialnieMenu();
//
//    }
//}
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
