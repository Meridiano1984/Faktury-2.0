package Kontrachent;

import DataStructure.Adres;

public abstract class Kontrachent {

    protected Adres adres;

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void wyswietlenieKontrachenta(Kontrachent kontrachent){
        System.out.println("wyswietolono kontrachenta");
    }

    //TODO ZMIENIC KONTRACHENTA W BAZIE DANYCH Z KRAJOWYCH NA OSOBE FIZYCZNA FIRME ITDI DAC TUTAJ FUNKCJE GETBYINDEX


}
