package Kontrachent;

import DataStructure.Adres;

public class FirmaKontrachenta extends Firma {

    public FirmaKontrachenta(String nazwaFirmy, Adres adres, String NIP) throws IllegalArgumentException {
        super.nazwaFirmy = nazwaFirmy;
        super.adres = adres;
        if(Firma.sprawdzanieNIP(NIP)==false) throw new IllegalArgumentException();
        super.NIP = NIP;
    }

    @Override
    public String toString(){
        return "Nazwa firmy: " + this.nazwaFirmy + " NIP: " + this.NIP + " " + this.adres.toString();
    }



}
