import ApplicationInterface.ComendUserInterface;
import DataBase.DatabaseInitialization;

import java.time.LocalDate;
import java.util.*;

public class Main {


    public static void  main(String[] args){
        //KOMENDY DO INICJALIZACJI BAZY DANYCH MYSQL
        //TODO dodac inicjalizacje schema w MYSQL a nie robic to recznie
        //TODO CZY JESLI LACZYMY JAKIS PROPGRAM Z BAZ DANYCH TO OBIEKTY W NIM POWINNY BYC ODZWIERCIEDLENIM TYCH W DB? CZY NA ODWROT CZY INACZEJ?
        //TODO CZY OBIEKTY POWINNY BYC PRZECHOWYWANE W PAMIECI UZYTKOWEJ RPGRAU CZY W DB JEST OK?


        ComendUserInterface comendUserInterface = new ComendUserInterface();
        comendUserInterface.dzialnieMenu();
    }
}
