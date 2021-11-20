package Kontrachent;

import DataStructure.Adres;

//KLASA SINGLETON
public final class MojaFirma extends Firma{

    private static final MojaFirma instance = new MojaFirma("Michalex",new Adres("Polska","Radomsko","targowa","12c/31"),"1111111111");


    private MojaFirma(String nazwaFirmy, Adres adres, String NIP) throws IllegalArgumentException {
        super.nazwaFirmy = nazwaFirmy;
        super.adres = adres;
        if(Firma.sprawdzanieNIP(NIP)==false) throw new IllegalArgumentException();
        super.NIP = NIP;
    }

    public static MojaFirma getInstance(){
        return instance;
    }

    @Override
    public String toString(){
        return "Nazwa mojej firmy: " + this.nazwaFirmy + " NIP: " + this.NIP + " " + this.adres.toString();
    }



}
