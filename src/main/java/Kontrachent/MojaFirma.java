package Kontrachent;

import DataStructure.Adres;


public final class MojaFirma extends Firma{


    public MojaFirma(String nazwaFirmy, Adres adres, String NIP) throws IllegalArgumentException {
        super.nazwaFirmy = nazwaFirmy;
        super.adres = adres;
        if(Firma.sprawdzanieNIP(NIP)==false) throw new IllegalArgumentException();
        super.NIP = NIP;
    }

    @Override
    public String toString(){
        return "Nazwa mojej firmy: " + this.nazwaFirmy + " NIP: " + this.NIP + " " + this.adres.toString();
    }



}
