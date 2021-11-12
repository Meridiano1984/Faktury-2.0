package Kontrachent;

import DataBase.QueryExecutor;
import DataStructure.Adres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Kontrachent {

    protected Adres adres;

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public abstract int getIndexFromDataBase(Firma kontrachent);


    //TODO ZMIENIC KONTRACHENTA W BAZIE DANYCH Z KRAJOWYCH NA OSOBE FIZYCZNA FIRME ITDI DAC TUTAJ FUNKCJE GETBYINDEX

    public static Kontrachent getByIndexFromDataBase(int index){

        Kontrachent kontrachent =null;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_id="+ index +";");
            result.next();

            String kontrachent_name = result.getString("kontrachent_name");
            String NIP = result.getString("nip");
            String adres_kraj= result.getString("adres_kraj");
            String adres_miasto = result.getString("adres_miasto");
            String adres_ulica = result.getString("adres_ulica");
            String adres_nr_budynku = result.getString("adres_nr_budynku");
            kontrachent = new FirmaKontrachenta(kontrachent_name,new Adres(adres_kraj,adres_miasto,adres_ulica,adres_nr_budynku), NIP);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return kontrachent ;
    }

    public static Kontrachent dodanieKontrachentaDoFaktury(){
        boolean warunek = true;
        do {
            try {
                Firma.wyswietlenieKontrachenta();
                System.out.print("Wybierz nr kontrachenta ktorego chcesz dodac: ");
                Scanner scanner = new Scanner(System.in);
                int nrKontrachenta = scanner.nextInt();
                Kontrachent kontrachent = Kontrachent.getByIndexFromDataBase(nrKontrachenta);
                warunek = false;
                return kontrachent;
            }catch (InputMismatchException e){
                System.out.println("\n\nZŁY ARGUMENT\n\n");
            }
        }while (warunek);
        return null;
    }

//    public int getIndexFromDataBase2(Firma kontrachent){
//        int index=0;
//
//        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_name='"+kontrachent.getNazwaFirmy()+"');");
//        try {
//            resultSet.next();
//            index = resultSet.getInt("kontrachent_id");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return index;
//    }




}
