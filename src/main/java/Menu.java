import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void wyswietlanieMenu(ArrayList<FakturaVat> listaFaktur){

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                        Program do wystawiania faktur               Całkowita lIczba faktur wynosi:" + FakturaVat.getLiczbaFaktur());
        System.out.println("1.Wyswietl wszystkie faktury");
        System.out.println("2.Dodaj nowa Fakture");
        System.out.println("3.Wyswietl wszystkich kontrachentow z MYSQL");
        System.out.println("4.Wyswietlanie produktów z bazy danych");
        System.out.println("Twój wybór: ");

        Scanner scanner = new Scanner(System.in);
        int liczbaSterujaca =scanner.nextInt();

        switch (liczbaSterujaca){
            case 1:
//                FakturaVat.wyswietlanieFaktur(listaFaktur);
                FakturaVat.displayAllFromDataBase();

                break;
            case 2:
//                FakturaVat.dodanieNowejFaktury(listaFaktur);
                FakturaVat.dodawnaieNowejFaktury();

                break;
            case 3:
//                FakturaVat.wyswietlanieKonkretnejFaktury(listaFaktur);
                Kontrachent.displayAllFromDataBase();
                break;
            case 4:
                Produkt.displayAllFromDataBase();
                break;

            case 21:
                System.out.println("Wyjscie z programu");
                System.exit(0);
                break;
        }
    }


    public static void wyswietlanieMenuDodawaniaProduktowDoFaktury(){

    }




}
