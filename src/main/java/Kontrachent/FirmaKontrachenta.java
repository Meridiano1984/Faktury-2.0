package Kontrachent;

import DataBase.QueryExecutor;
import DataStructure.Adres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FirmaKontrachenta extends Firma {

    public FirmaKontrachenta(String nazwaFirmy, Adres adres, String NIP) throws IllegalArgumentException {
        super.nazwaFirmy = nazwaFirmy;
        super.adres = adres;
        if(Firma.sprawdzanieNIP(NIP)==false) throw new IllegalArgumentException();
        super.NIP = NIP;
    }


    public static FirmaKontrachenta getByIndex(int index){
        FirmaKontrachenta firmaKontrachenta=null;


        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_id="+ index +";");
            result.next();

            String kontrachent_name = result.getString("kontrachent_name")           ;
            String NIP = result.getString("nip");
            String adresKraj= result.getString("adres_kraj");
            String adresMiasto = result.getString("adres_miasto");
            String adresUlica = result.getString("adres_ulica");
            String adresNrBudynku = result.getString("adres_nr_budynku");


             firmaKontrachenta = new FirmaKontrachenta(kontrachent_name,new Adres(adresKraj,adresMiasto,adresUlica,adresNrBudynku),NIP);

        } catch (SQLException e){
            e.printStackTrace();
        }


        return firmaKontrachenta;
    }

    public static void wyswietlanieFirmyKontrachenta(){
        //TODO JAK PISAC METODY WYSWIETLAJACE W JAKI SPOSOB ZEBY DZIDZICZYC I NIE BYŁY STSTYCZNE
        FirmaKontrachenta.wyswietlanieFirmyKontrachenta();
    }

    protected void dodanieKontrachentaDoBazydanych(){
        QueryExecutor.executeQuery("INSERT INTO kontrachenci (typ_kontrachent,kontrachent_name,nip,adres_kraj,adres_miasto,adres_ulica,adres_nr_budynku) " +
                "VALUES('firma','"+this.nazwaFirmy+"','"+this.NIP+"','"+this.adres.getKraj()+"','"+this.adres.getMiasto()+"','"+this.adres.getUlica()+"','"+this.adres.getNrBudynku()+"');");
    }

    @Override
    public String toString(){
        return "Nazwa firmy: " + this.nazwaFirmy + " NIP: " + this.NIP + " " + this.adres.toString();
    }



}
