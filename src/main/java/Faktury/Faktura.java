package Faktury;

import Faktury.FakturyVat.FakturaVAT;
import Faktury.FakturyVat.FakturaVATdataBaseOperator;
import Kontrachent.Firma;
import Kontrachent.Kontrachent;
import Kontrachent.MojaFirma;
import Towary.TowarNaFakturze;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Faktura {

    private static int liczbaFaktur = 0;

    protected String numerFaktury;
    protected LocalDate dataWystawienia;
    protected Firma mojaFirma;
    protected Kontrachent kontrachent;
    protected ArrayList<TowarNaFakturze> listaTowarow;
    protected double wartoscSprzedazyNetto;
    protected double wartoscSprzedazyBrutto;
    protected String uwaga;



    public Faktura(LocalDate dataWystawienia, MojaFirma mojaFirma, Kontrachent kontrachent, ArrayList<TowarNaFakturze> listaTowarow) {
        this.numerFaktury =nadawanieNumeruWKonstruktorze();
        this.dataWystawienia = dataWystawienia;
        this.mojaFirma = mojaFirma;
        this.kontrachent = kontrachent;
        this.listaTowarow = listaTowarow;
        this.wartoscSprzedazyNetto = this.obliczanieWartosciSprzedazyNetto(listaTowarow);
        this.wartoscSprzedazyBrutto = this.obliczanieWartosciSprzedazyBrutto(listaTowarow);
        liczbaFaktur++;
    }

    public static int getLiczbaFaktur() {
        return liczbaFaktur;
    }

    private double obliczanieWartosciSprzedazyNetto(ArrayList<TowarNaFakturze> listaTowarow){
        double wartoscSprzedazyNetto=0;
        for(TowarNaFakturze towarNaFakturze : listaTowarow){
                wartoscSprzedazyNetto += towarNaFakturze.getIlosc()*towarNaFakturze.getTowar().getCenaSprzedazyNetto();
        }
        return wartoscSprzedazyNetto;
    }

    private double obliczanieWartosciSprzedazyBrutto(ArrayList<TowarNaFakturze> listaTowarow){
        double wartoscSprzedazyBrutto=0;
        for(TowarNaFakturze towarNaFakturze : listaTowarow){
            wartoscSprzedazyBrutto += towarNaFakturze.getIlosc()*towarNaFakturze.getTowar().getCenaSprzedazyBrutto();
        }
        return wartoscSprzedazyBrutto;
    }

    private String nadawanieNumeruWKonstruktorze(){
        String nrFaktury;
        LocalDate dzisiejszaData = LocalDate.now();
        nrFaktury = (getLiczbaFaktur() + "/" + dzisiejszaData.getMonthValue() + "/" + dzisiejszaData.getYear() );
        return nrFaktury;
    }

    @Override
    public String toString(){
        String naglowek ="FAKTURA\nnr faktury: " + this.numerFaktury + " data wystawienia: " + this.dataWystawienia.toString() +
                "\nSPRZEDAWCA: " +this.mojaFirma.toString()+
                "\nKUPUJACY: " + this.kontrachent.toString();

        String produkty;
        produkty ="\n\n";
        if(this.listaTowarow!=null) {
            for (TowarNaFakturze towarNaFakturze : this.listaTowarow) {
                produkty = produkty + towarNaFakturze.getTowar().toString() + " ILOSC: " + towarNaFakturze.getIlosc() + "\n";
            }
        }
        String podsumowanie = "\n\nNETTO W SUMIE: " + this.wartoscSprzedazyNetto + " \nBRUTTO W SUMIE: " + this.wartoscSprzedazyBrutto;

        return naglowek+produkty+podsumowanie;
    }
}
