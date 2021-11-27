package Faktury.FakturyVat;

import Faktury.Faktura;
import Kontrachent.Kontrachent;
import Kontrachent.MojaFirma;
import Towary.TowarNaFakturze;

import java.time.LocalDate;
import java.util.ArrayList;

public class FakturaVAT extends Faktura {

    public FakturaVAT(LocalDate dataWystawienia, MojaFirma mojaFirma, Kontrachent kontrachent, ArrayList<TowarNaFakturze> listaTowarow) {
        super(dataWystawienia, mojaFirma, kontrachent, listaTowarow);
    }
    public FakturaVAT(String numerFaktury, LocalDate dataWystawienia, MojaFirma mojaFirma, Kontrachent kontrachent, ArrayList<TowarNaFakturze> listaTowarow, double wartoscSprzedazyNetto, double wartoscSprzedazyBrutto, String uwaga,double wartoscPodatku){
        super(numerFaktury,dataWystawienia,mojaFirma,kontrachent,listaTowarow,wartoscSprzedazyNetto,wartoscSprzedazyBrutto,uwaga,wartoscPodatku);
    }

    public static void wyswietlanieZDB(){
        FakturaVATdataBaseOperator fakturaVATdataBaseOperator = new FakturaVATdataBaseOperator();
        fakturaVATdataBaseOperator.wyswietlanieFakturyVATzDB();
    }
}
