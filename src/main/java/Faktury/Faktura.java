package Faktury;

import DataBase.QueryExecutor;
import Faktury.FakturyVat.FakturaVAT;
import Faktury.FakturyVat.FakturaVATdataBaseOperator;
import Kontrachent.Firma;
import Kontrachent.Kontrachent;
import Kontrachent.MojaFirma;
import Towary.Towar;
import Towary.TowarNaFakturze;
import Kontrachent.FirmaKontrachenta;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Faktura {

    private static int liczbaFaktur = 0;

    protected String numerFaktury;
    protected LocalDate dataWystawienia;
    protected Firma mojaFirma;
    protected Kontrachent kontrachent;
    protected String nazwaKontrachenta;
    protected ArrayList<TowarNaFakturze> listaTowarow;
    protected double wartoscSprzedazyNetto;
    protected double wartoscSprzedazyBrutto;
    protected double wartoscPodatku;
    protected String uwaga;



    public Faktura(LocalDate dataWystawienia, MojaFirma mojaFirma, Kontrachent kontrachent, ArrayList<TowarNaFakturze> listaTowarow) {
        liczbaFaktur = setLiczbaFaktur();
        this.numerFaktury =nadawanieNumeruWKonstruktorze();
        this.dataWystawienia = dataWystawienia;
        this.mojaFirma = mojaFirma;
        this.kontrachent = kontrachent;
        this.listaTowarow = listaTowarow;
        this.wartoscSprzedazyNetto = this.obliczanieWartosciSprzedazyNetto(listaTowarow);
        this.wartoscSprzedazyBrutto = this.obliczanieWartosciSprzedazyBrutto(listaTowarow);
        liczbaFaktur++;
    }

    public Faktura(String numerFaktury, LocalDate dataWystawienia, MojaFirma mojaFirma, Kontrachent kontrachent, ArrayList<TowarNaFakturze> listaTowarow, double wartoscSprzedazyNetto, double wartoscSprzedazyBrutto, String uwaga,double wartoscPodatku) {
        this.numerFaktury = numerFaktury;
        this.dataWystawienia = dataWystawienia;
        this.mojaFirma = mojaFirma;
        this.kontrachent = kontrachent;
        this.nazwaKontrachenta = ((Firma) kontrachent).getNazwaFirmy();
        this.listaTowarow = listaTowarow;
        this.wartoscSprzedazyNetto = wartoscSprzedazyNetto;
        this.wartoscSprzedazyBrutto = wartoscSprzedazyBrutto;
        this.wartoscPodatku =wartoscPodatku;
        this.uwaga = uwaga;
    }

    public String getNazwaKontrachenta() {
        return nazwaKontrachenta;
    }

    public void setNazwaKontrachenta(String nazwaKontrachenta) {
        this.nazwaKontrachenta = nazwaKontrachenta;
    }

    public double getWartoscPodatku() {
        return wartoscPodatku;
    }

    public void setWartoscPodatku(double wartoscPodatku) {
        this.wartoscPodatku = wartoscPodatku;
    }

    private static int setLiczbaFaktur() {
        int liczbaFaktur = 0;
        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM faktury;");
        try{
            while (resultSet.next()){
                liczbaFaktur++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return liczbaFaktur;
    }

    public String getNumerFaktury() {
        return numerFaktury;
    }

    public void setNumerFaktury(String numerFaktury) {
        this.numerFaktury = numerFaktury;
    }

    public LocalDate getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(LocalDate dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public Kontrachent getMojaFirma() {
        return mojaFirma;
    }

    public void setMojaFirma(Firma mojaFirma) {
        this.mojaFirma = mojaFirma;
    }

    public Kontrachent getKontrachent() {
        return kontrachent;
    }

    public void setKontrachent(Kontrachent kontrachent) {
        this.kontrachent = kontrachent;
    }

    public ArrayList<TowarNaFakturze> getListaTowarow() {
        return listaTowarow;
    }

    public void setListaTowarow(ArrayList<TowarNaFakturze> listaTowarow) {
        this.listaTowarow = listaTowarow;
    }

    public double getWartoscSprzedazyNetto() {
        return wartoscSprzedazyNetto;
    }

    public void setWartoscSprzedazyNetto(double wartoscSprzedazyNetto) {
        this.wartoscSprzedazyNetto = wartoscSprzedazyNetto;
    }

    public double getWartoscSprzedazyBrutto() {
        return wartoscSprzedazyBrutto;
    }

    public void setWartoscSprzedazyBrutto(double wartoscSprzedazyBrutto) {
        this.wartoscSprzedazyBrutto = wartoscSprzedazyBrutto;
    }

    public String getUwaga() {
        return uwaga;
    }

    public void setUwaga(String uwaga) {
        this.uwaga = uwaga;
    }

    public void setUwaga() {
        Scanner scanner = new Scanner(System.in);
        String nowaUwaga;
        System.out.println("\n\bTREŚĆ UWAGI(256 ZNAKOW):");
//        nowaUwaga = scanner.next(".{256}");
        nowaUwaga = scanner.nextLine();
        System.out.println("UWAGA W SKANERZE:" + nowaUwaga);

        this.uwaga = nowaUwaga;
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

    public static void tworzenieNowejFaktury(){

        boolean warunek = true;
        Scanner scanner = new Scanner(System.in);
        Kontrachent kontrachent = null;
        ArrayList<TowarNaFakturze> listaTowarow=null;
        LocalDate localDate;
        int wybor;

        do {
            try {

                System.out.println(".../// TWORZENIE NOWEJ FAKTURY");
                System.out.println("""
                        1 -> Dodaj nowego kontrachenta
                        2 -> Wybierz kontrachenta z listy Kontrachentow
                        """);
                wybor = scanner.nextInt();

                switch (wybor){
                    case 1:
                         kontrachent = Firma.dodanieNowegoKontrachenta();
                        break;
                    case 2:
//                        Firma.wyswietlenieKontrachenta();
                        kontrachent =Kontrachent.dodanieKontrachentaDoFaktury();
                        break;
                }
                localDate =Faktura.dodanieDatyDoFaktury();
                listaTowarow = Faktura.dodanieTowaruDoFaktury();
                Faktura faktura = new FakturaVAT(localDate,MojaFirma.getInstance(),kontrachent,listaTowarow);
                faktura.setUwaga();
                faktura.dodanieFakturyDoBazydanych();


                warunek = false;
            }catch (InputMismatchException e){
                System.out.println("PODALES ZLE DANE");
            }
        }while ((warunek));
    }

    private static ArrayList<TowarNaFakturze> dodanieTowaruDoFaktury(){

        ArrayList<TowarNaFakturze> towaryNaFakturze = new ArrayList<>();
        Scanner scanner;
        Towar towar = null;
        int wybor;
        boolean warunek = true;
        String menu = """
                1 -> dodaj towar
                2 -> wyjdz
                Twój wybór:
                """;

        do{
            try {
                scanner = new Scanner(System.in);
                System.out.print(menu);
                wybor = scanner.nextInt();

                switch (wybor) {
                    case 1:
                        Towar.wyswietlanieTowaru();
                        System.out.print("Wybierz nr towaru do dodania: ");
                        int nrTowaru = scanner.nextInt();
                        towar = Towar.getByIndexFromDataBase(nrTowaru);
                        System.out.print("PODAJ ILOSC: ");
                        int ilosc = scanner.nextInt();
                        towaryNaFakturze.add(new TowarNaFakturze(towar, ilosc));

                        break;
                    case 2:
                        warunek = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("\n\nNIEPRAWIDŁOWE ARGUMENTY\n\n");
            }

        }while (warunek);

        return towaryNaFakturze;
    }

    private void dodanieFakturyDoBazydanych(){
        Date date = Date.valueOf(this.dataWystawienia);
        Firma kontrachent = (Firma) this.kontrachent;
        QueryExecutor.executeQuery("INSERT INTO faktury (typ_faktury,nr_faktury,data_wystawienia,kontrachent_id,wartosc_sprzedazy_netto,wartosc_sprzedazy_brutto,wartosc_calkowita_podatku,uwagi) " +
                "VALUES('VAT','"+this.numerFaktury+"','"+date+"',"+ this.kontrachent.getIndexFromDataBase(kontrachent)+","+this.wartoscSprzedazyNetto+","+this.wartoscSprzedazyBrutto+","+(this.wartoscSprzedazyBrutto-this.wartoscSprzedazyNetto)+",'"+this.uwaga+"');");
        this.dodanieListyTowarow();


    }

    private void dodanieListyTowarow(){
            int i = 0;
            for (TowarNaFakturze towarNaFakturze : this.listaTowarow) {
                try {
                    QueryExecutor.executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES("+this.getIndexFromDataBase(Faktura.this)+","+towarNaFakturze.getTowar().getIndexFromDataBase(towarNaFakturze.getTowar())+","+towarNaFakturze.getIlosc()+",0,0,0);");
                    System.out.println("INSERT INTO produkty_na_fakturach_vat VALUES(" + this.getIndexFromDataBase(Faktura.this) + "," + towarNaFakturze.getTowar().getIndexFromDataBase(towarNaFakturze.getTowar()) + "," + towarNaFakturze.getIlosc() + "," + this.obliczanieWartosciNetto(this.listaTowarow.get(i)) + "," + this.obliczanieWartosciBrutto(this.listaTowarow.get(i)) + "," + this.obliczenieStawkiVAT(this.listaTowarow.get(i)) + ");");
                    QueryExecutor.executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(" + this.getIndexFromDataBase(Faktura.this) + "," + towarNaFakturze.getTowar().getIndexFromDataBase(towarNaFakturze.getTowar()) + "," + towarNaFakturze.getIlosc() + "," + this.obliczanieWartosciNetto(this.listaTowarow.get(i)) + "," + this.obliczanieWartosciBrutto(this.listaTowarow.get(i)) + "," + this.obliczenieStawkiVAT(this.listaTowarow.get(i)) + ");");
                    i++;
                }catch (RuntimeException e){
                    System.out.println("PRODUKTY SIE NIE DODALY NAPISZ TU FUNKCJE");
                    this.sprawdzenieCzyproduktznajdujeSieWTowarachWystawionych(this.getListaTowarow().get(i));
                }
            }
        }

    private static LocalDate dodanieDatyDoFaktury(){
        LocalDate nowaData=null;
        Scanner scanner = new Scanner(System.in);
        int wybor;
        boolean warunek = true;

        do {
            try {
                System.out.println("DATA WYSTAWIANIA");
                System.out.println("""
                        1 -> Dzisiejsza data
                        2 -> Ustaw date
                        """);
                wybor = scanner.nextInt();

                switch (wybor){
                    case 1:
                        nowaData = LocalDate.now();

                        break;
                    case 2:
                        System.out.println("Rok: ");
                        int rok = scanner.nextInt();
                        System.out.println("Miesiac: ");
                        int miesiac = scanner.nextInt();
                        System.out.println("Dzien: ");
                        int dzien = scanner.nextInt();
                        nowaData = LocalDate.of(rok,miesiac,dzien);
                        break;
                }
                warunek=false;

            }catch (InputMismatchException e){
                System.out.println("\n\nNIEPRAWDIŁOWY ARGUMENT");
            }
        }while (warunek);
        return nowaData;
    }

    public int getIndexFromDataBase(Faktura faktura){
        int index=0;

        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM faktury WHERE nr_faktury='"+faktura.getNumerFaktury()+"';");
        try {
            resultSet.next();
            index = resultSet.getInt("faktura_id");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    private double obliczanieWartosciNetto(TowarNaFakturze towarNaFakturze){
        return towarNaFakturze.getIlosc()*towarNaFakturze.getTowar().getCenaSprzedazyNetto();
    }

    private double obliczanieWartosciBrutto(TowarNaFakturze towarNaFakturze){
        return towarNaFakturze.getIlosc()*towarNaFakturze.getTowar().getCenaSprzedazyBrutto();
    }

    private double obliczenieStawkiVAT(TowarNaFakturze towarNaFakturze){
        return obliczanieWartosciBrutto(towarNaFakturze) -obliczanieWartosciNetto(towarNaFakturze);
    }

    private void sprawdzenieCzyproduktznajdujeSieWTowarachWystawionych(TowarNaFakturze towarNaFakturze){

        int indexTowarDoSprawdzenia = towarNaFakturze.getTowar().getIndexFromDataBase(towarNaFakturze.getTowar());
        int indexFakturyDoSprawdzenia = this.getIndexFromDataBase(Faktura.this);
        System.out.println("SELECT * FROM produkty_na_fakturach_vat WHERE faktura_id=" + indexFakturyDoSprawdzenia + " AND produkt_id=" + indexTowarDoSprawdzenia + ";");
        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty_na_fakturach_vat WHERE faktura_id=" + indexFakturyDoSprawdzenia + " AND produkt_id=" + indexTowarDoSprawdzenia + ";");
        try {
            if(result.next()){
                QueryExecutor.executeQuery("UPDATE produkty_na_fakturach_vat SET ilosc_produktow = ilosc_produktow+" + towarNaFakturze.getIlosc() + " WHERE faktura_id=" + indexFakturyDoSprawdzenia + " AND produkt_id=" + indexTowarDoSprawdzenia + ";" );
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<FakturaVAT> getAllFakturFromDBToTabel(){
        //TODO ZMIENIC TA FUNKCJE BO JEST W TABELI WYSWIETLANE JUZ INACZEJ I USUNAC POLE U KONTRACHENTA
        ArrayList<FakturaVAT> listaFaktur = new ArrayList();

        int kontrachentID;
        String numerFaktury;
        String uwaga;
        LocalDate dataWystawienia;
        double wartoscSprzedazyNetto;
        double wartoscSprzedazyBrutto;
        double wartoscPodatku;

        try{
            //POBIERAMY FAKTURY
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury;");
            while (result.next()){
                numerFaktury = result.getString("nr_faktury");
                kontrachentID = result.getInt("kontrachent_id");
                dataWystawienia = result.getDate("data_wystawienia").toLocalDate();
                wartoscSprzedazyNetto = result.getDouble("wartosc_sprzedazy_netto");
                wartoscSprzedazyBrutto =result.getDouble("wartosc_sprzedazy_brutto");
                wartoscPodatku = result.getDouble("wartosc_calkowita_podatku");
                uwaga = result.getString("uwagi");
                //POBIERAMY KONTRACHENTA
                FirmaKontrachenta firmaKontrachenta = FirmaKontrachenta.getByIndex(kontrachentID);

                FakturaVAT fakturka = new FakturaVAT(numerFaktury,dataWystawienia,MojaFirma.getInstance(),firmaKontrachenta,null,wartoscSprzedazyNetto,wartoscSprzedazyBrutto,uwaga,wartoscPodatku);
//                System.out.println(fakturka.toString());
//                System.out.println("kontrachent: " + fakturka.nazwaKontrachenta);
//                System.out.println("wartosc podatku: " + fakturka.wartoscPodatku);
                listaFaktur.add(fakturka);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listaFaktur;
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

        System.out.println("\n---UWAGA---");
        System.out.println(this.getUwaga());
        System.out.println("-----------");

        String podsumowanie = "\n\nNETTO W SUMIE: " + this.wartoscSprzedazyNetto + " \nBRUTTO W SUMIE: " + this.wartoscSprzedazyBrutto;

        return naglowek+produkty+podsumowanie;
    }




}
