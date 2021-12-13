package Towary;

import DataBase.QueryExecutor;
import Faktury.FakturyVat.FakturaVAT;
import Kontrachent.FirmaKontrachenta;
import Kontrachent.Kontrachent;
import Kontrachent.Firma;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TowarNaFakturze {

    private Towar towar;
    private int ilosc;
    private double wartoscNetto;
    private double wartoscVAT;
    private double wartoscBrutto;

    public TowarNaFakturze(Towar towar, int ilosc) {
        this.towar = towar;
        this.ilosc = ilosc;
        this.wartoscNetto = obliczanieWartosciNettoTowaru(towar,ilosc);
        this.wartoscBrutto = obliczanieWartosciBrutto(towar,ilosc);
        this.wartoscVAT = obliczenieStawkiVAT();
    }

    public double getWartoscNetto() {
        return wartoscNetto;
    }

    public void setWartoscNetto(double wartoscNetto) {
        this.wartoscNetto = wartoscNetto;
    }

    public double getWartoscVAT() {
        return BigDecimal.valueOf(wartoscVAT).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setWartoscVAT(double wartoscVAT) {
        this.wartoscVAT = wartoscVAT;
    }

    public double getWartoscBrutto() {
        return wartoscBrutto;
    }

    public void setWartoscBrutto(double wartoscBrutto) {
        this.wartoscBrutto = wartoscBrutto;
    }

    public Towar getTowar() {
        return towar;
    }

    public void setTowar(Towar towar) {
        this.towar = towar;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    private double obliczanieWartosciNettoTowaru(Towar towar, int ilosc){
        return towar.getCenaSprzedazyNetto()*ilosc;
    }

    private double obliczanieWartosciVAT(Towar towar, int ilosc){
        return towar.getStawkaVatSprzedazy().getStawkaVAT()*ilosc;
    }

    private double obliczanieWartosciNetto(){
        return this.getIlosc()*this.getTowar().getCenaSprzedazyNetto();
    }

    private double obliczanieWartosciBrutto(){
        return this.getIlosc()*this.getTowar().getCenaSprzedazyBrutto();
    }

    private double obliczenieStawkiVAT(){
        return obliczanieWartosciBrutto() -obliczanieWartosciNetto();
    }

    private double obliczanieWartosciBrutto(Towar towar, int ilosc){
        return towar.getCenaSprzedazyBrutto()*ilosc;
    }

    public ArrayList<TowarNaFakturze> getFromDataBase(FakturaVAT fakturaVAT, FirmaKontrachenta firmaKontrachenta){

//        int kontrachentId = firmaKontrachenta.getIndexFromDataBase(firmaKontrachenta);
        int fakturId = fakturaVAT.getIndexFromDataBase(fakturaVAT);


        ArrayList<TowarNaFakturze> towarNaFakturzeList = new ArrayList<>();

        String QuerryText = """
                SELECT * FROM produkty_na_fakturach_vat  
                WHERE faktura_id="""+fakturId+";";

        System.out.println(QuerryText);


        ResultSet resultSet = QueryExecutor.executeSelect(QuerryText);
        try{
            while ((resultSet.next())){

            }

        } catch (SQLException e){
            e.printStackTrace();
        }


        return towarNaFakturzeList;
    }
}
