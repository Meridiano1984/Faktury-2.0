import DataBase.DatabaseInitialization;

import java.time.LocalDate;
import java.util.*;

public class Main {


    public static void  main(String[] args){


        //KOMENDY DO INICJALIZACJI BAZY DANYCH MYSQL
        //TODO dodac inicjalizacje schema w MYSQL a nie robic to recznie
        //TODO CZY JESLI LACZYMY JAKIS PROPGRAM Z BAZ DANYCH TO OBIEKTY W NIM POWINNY BYC ODZWIERCIEDLENIM TYCH W DB? CZY NA ODWROT CZY INACZEJ?
        //TODO CZY OBIEKTY POWINNY BYC PRZECHOWYWANE W PAMIECI UZYTKOWEJ RPGRAU CZY W DB JEST OK?



        DatabaseInitialization databaseInitialization = new DatabaseInitialization();
//        databaseInitialization.usuwanieTabelv2();
        databaseInitialization.tworzenieBazydanychV2();
        databaseInitialization.dodaniePrzykładowychdanych();

//        DatabaseInitialization databaseInitialization = new DatabaseInitialization();
//
////        FakturaVat fakturaVat =FakturaVat.getFakturaPoZadanymIndex(1);
////        fakturaVat.setCenaBruttoWFakturWystawionych(fakturaVat);
//
//        //KOMENDA DO USOWANIA POPRZEDNIEJ BAZY DANYCH
//        databaseInitialization.usuwanieTabel();
//
//        //KOMENDY DO TWORZENIE I INICJALIZACJI BAZY DANYCH
//        databaseInitialization.tworzenieBazyDanychv2();
//        databaseInitialization.dodaniePrzykładowychdanych();
//
//        //USTWANIE CENN BRUTTO W WYSTAWIONYCH FAKTURACH
//        FakturaVat fakturaVat = FakturaVat.getByIndexFromDataBase(1);
//        fakturaVat.setCenaBruttoWFakturWystawionych(fakturaVat);
////        fakturaVat.setCenaBruttoFakturyWBazieDanych(fakturaVat);
//
//        fakturaVat = FakturaVat.getByIndexFromDataBase(2);
//        fakturaVat.setCenaBruttoWFakturWystawionych(fakturaVat);
////        fakturaVat.setCenaBruttoFakturyWBazieDanych(fakturaVat);
//
//        fakturaVat = FakturaVat.getByIndexFromDataBase(3);
//        fakturaVat.setCenaBruttoWFakturWystawionych(fakturaVat);
////        fakturaVat.setCenaBruttoFakturyWBazieDanych(fakturaVat);
//
//
//
//
//        // DODANIE PARU KONTRACHENTOW
//
//        LinkedList<Kontrachent> listaKontrachentow = new LinkedList<>();
//        listaKontrachentow.add(new Kontrachent("MIRBUDEX","1234567890"));
//        listaKontrachentow.add(new Kontrachent("Biedronka", "334512967"));
//        listaKontrachentow.add(new Kontrachent("Oracle", "815462967"));
//
//
//        // DODANIE PARU PRODUKTOW
//        LinkedList<Produkt> listaProduktow = new LinkedList<>();
//        listaProduktow.add(new Produkt("Mleko", 2.50f));
//        listaProduktow.add(new Produkt("Czekolada", 10.00f));
//        listaProduktow.add(new Produkt("Samochód", 10000.00f));
//
//        //DODANIE 10 NOWYCH FAKTUR
//        int liczbaFakturDoWprowadzenia = 10;
//        LocalDate dataWystawienia = LocalDate.of(2021,8,11);
//        String  nabywca =  new String("Mirbux");
//        int     cenaBrutto = 0;
//
//        ArrayList<FakturaVat> listaFaktur = new ArrayList<>(liczbaFakturDoWprowadzenia);
//        for(int i  =0; i< liczbaFakturDoWprowadzenia; i++) {
//            listaFaktur.add(new FakturaVat(dataWystawienia, listaKontrachentow.get(1)));
//        }
//
//
//        do{
//            Menu.wyswietlanieMenu(listaFaktur);
//            FakturaVat.setLiczbaFaktur(listaFaktur.size());
//        }while (true);

    }
}
