package ApplicationInterface;

import Faktury.Faktura;
import Faktury.FakturyVat.FakturaVAT;
import Kontrachent.Firma;
import Kontrachent.FirmaKontrachenta;
import Kontrachent.Kontrachent;
import Towary.Towar;

import java.util.Scanner;

public class ComendUserInterface {

    public final String mainMenu =
            """
            ....////    MENU    ////....
            1-> WYSWIETLANIE WSZYSTKICH FAKTUR VAT
            2-> WYSWIETLANIE KONTRACHENTOW
            3-> WYSWIETLANIE TOWAROW
            4-> DODANIE NOWEJ FAKTURY VAT
            5-> DODANIE NOWEGO KONTRACHENTA
            6-> DODANIE NOWEGO TOWARU
            7-> ZMIANA DANYCH TWOJEJ FIRMY
            8-> WYJSCIE
            TWOJ WYBOR:
            """;

    public void dzialnieMenu(){

        int liczbaSterujaca=0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(mainMenu);
            liczbaSterujaca=scanner.nextInt();

            switch (liczbaSterujaca){
                case 1:
                    FakturaVAT.wyswietlanieZDB();
                    break;
                case 2:
                    Firma.wyswietlenieKontrachenta();
                    break;
                case 3:
                    Towar.wyswietlanieTowaru();
                    break;
                case 4:
                    Faktura.tworzenieNowejFaktury();
                    break;
                case 5:
                    Firma.dodanieNowegoKontrachenta();
                    break;
                case 6:
                    Towar.dodawanieNowegoTowaru();
                    break;
                case 8:
                    System.exit(0);
                    break;
            }



        } while (true);

    }

}
