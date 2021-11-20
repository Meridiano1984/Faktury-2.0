package Towary;

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
        this.wartoscVAT = obliczanieWartosciVAT(towar,ilosc);
        this.wartoscBrutto = obliczanieWartosciBrutto(towar,ilosc);
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
}
