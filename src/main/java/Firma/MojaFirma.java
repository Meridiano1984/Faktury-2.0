package Firma;

import Firma.Firma;

public class MojaFirma extends Firma {

    private  String nazwa   = "Fakturowo";
    private  String kraj    = "Polska";
    private  String adres   = "Radomsko/Targowa/31/21c";
    private  String NIP     = "1234567890";


    public MojaFirma(String nazwa, String kraj, String adres, String NIP, String nazwa1, String kraj1, String adres1, String NIP1) {
        super(nazwa, kraj, adres, NIP);
    }
}
