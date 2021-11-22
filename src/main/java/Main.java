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

public class Main extends Application {


//    public static void  main(String[] args){
//        //KOMENDY DO INICJALIZACJI BAZY DANYCH MYSQL
//        //TODO dodac inicjalizacje schema w MYSQL a nie robic to recznie
//        //TODO CZY JESLI LACZYMY JAKIS PROPGRAM Z BAZ DANYCH TO OBIEKTY W NIM POWINNY BYC ODZWIERCIEDLENIM TYCH W DB? CZY NA ODWROT CZY INACZEJ?
//        //TODO CZY OBIEKTY POWINNY BYC PRZECHOWYWANE W PAMIECI UZYTKOWEJ RPGRAU CZY W DB JEST OK?
//
//        GuiControler.startGui(args);
//        ComendUserInterface comendUserInterface = new ComendUserInterface();
//        comendUserInterface.dzialnieMenu();
//
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
