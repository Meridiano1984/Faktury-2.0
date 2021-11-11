package Faktury.FakturyVat;

import DataBase.QueryExecutor;
import DataStructure.JednostkiMiary;
import Kontrachent.Firma;
import Kontrachent.Kontrachent;
import Podatki.StawkaVat;
import Podatki.StawkiVAT;
import Towary.Towar;
import Towary.TowarNaFakturze;
import Kontrachent.FirmaKontrachenta;
import Kontrachent.MojaFirma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FakturaVATdataBaseOperator {


    protected void wyswietlanieFakturyVATzDB(){
        int fakturaID;
        int kontrachentID;
        String numerFaktury;
        LocalDate dataWystawienia;
        ArrayList<TowarNaFakturze> towaryNaFakturze;

        try{
            //POBIERAMY FAKTURY
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury;");
            while (result.next()){
                fakturaID = result.getInt("faktura_id");
                numerFaktury = result.getString("nr_faktury");
                kontrachentID = result.getInt("kontrachent_id");
                dataWystawienia = result.getDate("data_wystawienia").toLocalDate();

                //POBIERAMY KONTRACHENTA
                FirmaKontrachenta firmaKontrachenta = FirmaKontrachenta.getByIndex(kontrachentID);
                //POBIERANIE TOWARU Z DANEJ FAKTURY
                towaryNaFakturze = this.getTowarNaFakturze(fakturaID);
                FakturaVAT fakturaVAT = new FakturaVAT(dataWystawienia,MojaFirma.getInstance(),firmaKontrachenta,towaryNaFakturze);

                System.out.print(numerFaktury + ".");
                System.out.println(fakturaVAT.toString());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private ArrayList<TowarNaFakturze> getTowarNaFakturze(int fakturaID){

        ArrayList<TowarNaFakturze> towarNaFakturze =  new ArrayList<>();





//        POBIERAMY PRODUKTY DO BAZYDANYCH
            ResultSet resultTowaryNaFakturze = QueryExecutor.executeSelect("SELECT * FROM produkty_na_fakturach_vat WHERE faktura_id=" + fakturaID + ";");

            try{
                int     produktID;
                ResultSet resultProducts;
                int     ilosc;
                String  nazwa;
                String  jednostkaMiary1;
                Double  cenaZakupuNetto;
                int     stawkaVat;
                Double  cenaSprzedazyNetto;
                Double  cenaSprzedazyBrutto;


                while (resultTowaryNaFakturze.next()) {

                    ilosc = resultTowaryNaFakturze.getInt("ilosc_produktow");
                    produktID = resultTowaryNaFakturze.getInt("produkt_id");

                    resultProducts = QueryExecutor.executeSelect("SELECT * FROM towary WHERE produkt_id=" + produktID + ";");
                    resultProducts.next();

                    nazwa = resultProducts.getString("produkt_name");
                    jednostkaMiary1 = resultProducts.getString("jedostka_miary");
                    JednostkiMiary jednostkiMiary = JednostkiMiary.sprawdzanieJednostekMiary(jednostkaMiary1);
                    cenaZakupuNetto = resultProducts.getDouble("cena_zakupu_netto");

                    stawkaVat = resultProducts.getInt("stawka_VAT_zakupu");
                    StawkaVat stawkaVatZakupu11 = new StawkaVat(stawkaVat);
                    stawkaVat = resultProducts.getInt("stawka_VAT_sprzedazy");
                    StawkaVat stawkaVatSprzedazy11 = new StawkaVat(stawkaVat);

                    cenaSprzedazyNetto = resultProducts.getDouble("cena_sprzedazy_netto");

                    towarNaFakturze.add(new TowarNaFakturze(new Towar(nazwa,jednostkiMiary,cenaZakupuNetto,stawkaVatZakupu11,cenaSprzedazyNetto,stawkaVatSprzedazy11),ilosc));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        return towarNaFakturze;
    }



}
