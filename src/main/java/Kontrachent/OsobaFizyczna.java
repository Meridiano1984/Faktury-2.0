package Kontrachent;

import DataStructure.Adres;

public final class OsobaFizyczna extends Kontrachent{

    private String imie;
    private String nazwisko;
    private String PESEL;

    public OsobaFizyczna(String PESEL, String imie, String nazwisko, Adres adres) {
        this.PESEL = PESEL;
        this.imie = imie;
        this.nazwisko = nazwisko;
        super.adres = adres;
    }


    @Override
    public String toString(){
        return "Imie: " + this.imie + " nazwisko: " + this.nazwisko + " PESEL: " + this.PESEL + "Adres : " + this.adres.toString();
    }
}
