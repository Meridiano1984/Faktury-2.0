package Kontrachent;

import DataBase.QueryExecutor;
import DataStructure.Adres;
import DataStructure.JednostkiMiary;
import Podatki.StawkaVat;
import Towary.Towar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Firma extends Kontrachent{

    protected String NIP;
    protected String nazwaFirmy;

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public static boolean sprawdzanieNIP (String NIP){
        if(NIP.matches("[0-9]+") && NIP.length()==10){
            return true;
        } else {
            return false;
        }
    }

    public static void wyswietlenieKontrachenta(){

        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM kontrachenci;");
        Kontrachent kontrachent;
        String NIP;
        String nazwaFirmy;
        Adres adres;
        int licznik=0;
        try {
            while (resultSet.next()) {

                NIP = resultSet.getString("nip");
                nazwaFirmy = resultSet.getString("kontrachent_name");
                adres = new Adres(resultSet.getString("adres_kraj"),resultSet.getString("adres_miasto"),resultSet.getString("adres_ulica"),resultSet.getString("adres_nr_budynku"));

                //TODO OGARNAC TYPY KONTRACHENTOW BO W BAZIE DNAYCH SA INNE A TUTAJ INNE dac tutaj if i w zaleznosci jaki typ to taki toString

                kontrachent = new FirmaKontrachenta(nazwaFirmy,adres,NIP);
                licznik++;
                System.out.println(licznik+"."+kontrachent.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static Kontrachent dodanieNowegoKontrachenta(){

        Scanner scanner;
        boolean warnuek = true;
        FirmaKontrachenta kontrachent = null;
        do {
            try {
                scanner = new Scanner(System.in);                                                   //TUTAJ TRZEBA WYCZYSCIC SKANER BO PRZY WPISANIU ZLYCH ARGUMENTOW ZLE WCZYTUJE
                System.out.println("...///DODANIE NOWEGO KONTRACHENTA");
                System.out.print("nazwa kontrachenta: ");
                String nazwaKontrachenta = scanner.nextLine();
                System.out.print("NIP: ");
                String NIP = scanner.next();
                System.out.println("ADRES");
                scanner = new Scanner(System.in);
                System.out.print("kraj: ");
                String kraj = scanner.nextLine();
                System.out.print("miasto: ");
                String miasto = scanner.nextLine();
                System.out.print("ulica: ");
                String ulica = scanner.nextLine();
                System.out.print("budynek: ");
                String budynek = scanner.nextLine();


                kontrachent = new FirmaKontrachenta(nazwaKontrachenta,new Adres(kraj,miasto,ulica,budynek),NIP);
                kontrachent.dodanieKontrachentaDoBazydanych();
                warnuek=false;

            } catch (InputMismatchException e) {
                System.out.println("\n\nZLE DANE SPROBUJ PONOWNIE\n\n");
            } catch (IllegalArgumentException e){
                System.out.println("\n\nPODANY NIP JEST NIEPRAWIDŁOWY\n\n");
            }
        }while (warnuek);

        return kontrachent;
    }

    public int getIndexFromDataBase(Firma kontrachent){
        int index=0;

        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_name='"+kontrachent.getNazwaFirmy()+"';");
        try {
            resultSet.next();
            index = resultSet.getInt("kontrachent_id");
        }catch (SQLException e){
            e.printStackTrace();
        }

        return index;
    }





}
