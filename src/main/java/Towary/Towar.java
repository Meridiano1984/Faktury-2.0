package Towary;
import DataStructure.JednostkiMiary;
import Podatki.StawkaVat;

public class Towar {

    private String nazwa;
    private JednostkiMiary jednostkiMiary;
    private Double cenaZakupuNetto;
    private Double cenaZakupuBrutto;
    private StawkaVat stawkaVatZakupu;
    private Double cenaSprzedazyNetto;
    private Double cenaSprzedazyBrutto;
    private StawkaVat stawkaVatSprzedazy;


    public Towar(String nazwa, JednostkiMiary jednostkiMiary, Double cenaZakupuNetto, StawkaVat stawkaVatZakupu, Double cenaSprzedazyNetto, StawkaVat stawkaVatSprzedazy) {
        this.nazwa = nazwa;
        this.jednostkiMiary = jednostkiMiary;
        this.cenaZakupuNetto = cenaZakupuNetto;
        this.stawkaVatZakupu = stawkaVatZakupu;
        this.cenaZakupuBrutto = StawkaVat.obliczanieCenyBrutto(cenaZakupuNetto, stawkaVatZakupu);
        this.cenaSprzedazyNetto = cenaSprzedazyNetto;
        this.stawkaVatSprzedazy = stawkaVatSprzedazy;
        this.cenaSprzedazyBrutto = StawkaVat.obliczanieCenyBrutto(cenaSprzedazyNetto, stawkaVatSprzedazy);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public JednostkiMiary getJednostkiMiary() {
        return jednostkiMiary;
    }

    public void setJednostkiMiary(JednostkiMiary jednostkiMiary) {
        this.jednostkiMiary = jednostkiMiary;
    }

    public Double getCenaZakupuNetto() {
        return cenaZakupuNetto;
    }

    public void setCenaZakupuNetto(Double cenaZakupuNetto) {
        this.cenaZakupuNetto = cenaZakupuNetto;
    }

    public Double getCenaZakupuBrutto() {
        return cenaZakupuBrutto;
    }

    public void setCenaZakupuBrutto(Double cenaZakupuBrutto) {
        this.cenaZakupuBrutto = cenaZakupuBrutto;
    }

    public StawkaVat getStawkaVatZakupu() {
        return stawkaVatZakupu;
    }

    public void setStawkaVatZakupu(StawkaVat stawkaVatZakupu) {
        this.stawkaVatZakupu = stawkaVatZakupu;
    }

    public Double getCenaSprzedazyNetto() {
        return cenaSprzedazyNetto;
    }

    public void setCenaSprzedazyNetto(Double cenaSprzedazyNetto) {
        this.cenaSprzedazyNetto = cenaSprzedazyNetto;
    }

    public Double getCenaSprzedazyBrutto() {
        return cenaSprzedazyBrutto;
    }

    public void setCenaSprzedazyBrutto(Double cenaSprzedazyBrutto) {
        this.cenaSprzedazyBrutto = cenaSprzedazyBrutto;
    }

    public StawkaVat getStawkaVatSprzedazy() {
        return stawkaVatSprzedazy;
    }

    public void setStawkaVatSprzedazy(StawkaVat stawkaVatSprzedazy) {
        this.stawkaVatSprzedazy = stawkaVatSprzedazy;
    }

    @Override
    public String toString(){
        return "TOWAR nazwa: " + this.nazwa + " jednostka miary: " + this.jednostkiMiary + " cena zakupu netto: " + this.cenaZakupuNetto
                + " stawka VAT zakupu: " + this.stawkaVatZakupu.getStawkaVAT() + " cena brutto zakupu: " + this.cenaZakupuBrutto + "cena sprzedaży netto: " + this.cenaSprzedazyNetto
                + " stawka VAT sprzedaży: " + this.stawkaVatSprzedazy.getStawkaVAT() + " cena brutto sprzedaży: " + this.cenaSprzedazyBrutto;
    }
}
