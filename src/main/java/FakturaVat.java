import DataBase.QueryExecutor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class FakturaVat implements DataBaseOperations, FakturaInterface {

    private static int liczbaFaktur=0;

    private LocalDate   dataWystawienia;
    private Kontrachent kontrachent;
    private String      nrFaktury;
    private double      cenaBrutto;


    public FakturaVat(LocalDate dataWystawienia, Kontrachent kontrachent) {
        this.dataWystawienia = dataWystawienia;
        this.cenaBrutto =0;
        this.nrFaktury = nadawanieNumeruFakturyWKonstruktorze();
        this.kontrachent = kontrachent;
        liczbaFaktur++;
    }

    private FakturaVat(LocalDate dataWystawienia, Kontrachent kontrachent, String nrFaktury, double cenaBrutto){
        this.dataWystawienia = dataWystawienia;
        this.kontrachent =kontrachent;
        this.nrFaktury =nrFaktury;
        this.cenaBrutto =cenaBrutto;
    }

    public String getNrFaktury() {
        return nrFaktury;
    }

    public void setNrFaktury(String nrFaktury) {
        this.nrFaktury = nrFaktury;
    }

    public Kontrachent getKontrachent() {
        return kontrachent;
    }

    public void setKontrachent(Kontrachent kontrachent) {
        this.kontrachent = kontrachent;
    }

    public static int getLiczbaFaktur() {
        return liczbaFaktur;
    }

    public static void setLiczbaFaktur(int liczbaFaktur) {
        FakturaVat.liczbaFaktur = liczbaFaktur;
    }

    public LocalDate getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(LocalDate dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public double getCenaBrutto() {
        return cenaBrutto;
    }

    public void setCenaBrutto(double cenaBrutto) {
        this.cenaBrutto = cenaBrutto;
    }




//    public static void wyswietlanieFaktur (ArrayList<FakturaVat> listaFaktur){
//        for (FakturaVat faktura: listaFaktur) {
//
//            System.out.println("FakturaVat nr:" + listaFaktur.indexOf(faktura));
//            System.out.println("Data wystawienia: " + faktura.getDataWystawienia());
//            System.out.println("Nabywca: " + faktura.getKontrachent().getNazwaKontrachenta() + " NIP: " + faktura.getKontrachent().getNIP() + " nr Faktury: " + faktura.getNrFaktury());
//            System.out.println("CenaBrutto: " + faktura.getCenaBrutto());
//            System.out.println();
//
//            int nrProduktu = 1;
//            System.out.println("PRODUKTY");
//            for (Produkt produkt: faktura.getProdukty()){
//                System.out.println(nrProduktu + ".Nazwa: " + produkt.getNazwaProduktu() + " cena brutto: " + produkt.getCenaProduktuBrutto());
//                nrProduktu++;
//            }
//
//            System.out.println();
//            System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
//            System.out.println();
//
//
//
//        }
//    }

//    public static void wyswietlanieKonkretnejFaktury(ArrayList<FakturaVat> listaFaktur){
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Podaj indeks faktury ktora chcesz wyswietlic: ");
//        int nrFaktury = scanner.nextInt();
//
//        FakturaVat faktura = listaFaktur.get(nrFaktury-1);
//
//        System.out.println("FakturaVat nr: " + nrFaktury);
//        System.out.println("Data wystawienia :" + faktura.getDataWystawienia());
//        System.out.println("Nabywca: " + faktura.getKontrachent().getNazwaKontrachenta() + " NIP: " + faktura.getKontrachent().getNIP());
//        System.out.println();
//        System.out.println("PRODUKTY");
//        int nrProduktu = 1;
//
//        for (Produkt produkt: faktura.getProdukty()){
//            System.out.println(nrProduktu + ".Nazwa: " + produkt.getNazwaProduktu() + " cena brutto: " + produkt.getCenaProduktuBrutto());
//            nrProduktu++;
//        }
//
//        System.out.println();
//        System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
//        System.out.println();
//
//
//    }

//    public static void dodanieNowejFaktury (ArrayList<FakturaVat> listaFaktur){
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("        Dodawanie nowej faktury");
//        System.out.println("1.Data wystawienia ");
//        System.out.print("      Rok:");
//        int rok = scanner.nextInt();
//        System.out.print("      Miesiąc:");
//        int miesiac = scanner.nextInt();
//        System.out.print("      Dzien:");
//        int dzien = scanner.nextInt();
//
//        LocalDate dataWystawiania = LocalDate.of(rok,miesiac,dzien);
//
//        Kontrachent kontrachent;
//
//        System.out.println("2.Dane Kontrachenta ");
//        System.out.print("      Nazwa Kontrachenta: ");
//        String nazwaKontrachenta = scanner.next();
//        System.out.print("      NIP: ");
//        String nip = scanner.next();
//
//        kontrachent = new Kontrachent(nazwaKontrachenta,nip);
//
//        FakturaVat faktura = new FakturaVat(dataWystawiania,kontrachent);
//        listaFaktur.add(faktura);
//
//        FakturaVat.dodawainieProduktowDoNowejFaktury(faktura);
//
//        System.out.println();
//        System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
//        System.out.println();
//
//
//    }

//    public static void dodawainieProduktowDoNowejFaktury(FakturaVat faktura){
//        System.out.println("                                                    Dodawanie Produktów");
//        System.out.println("FakturaVat nr: " + liczbaFaktur + " Wystawiona: " + faktura.getDataWystawienia() + " nabywca: " + faktura.getKontrachent().getNazwaKontrachenta() + " NIP: " + faktura.getKontrachent().getNIP());
//
//        System.out.println();
//
//
//        LinkedList<Produkt> produkty = new LinkedList<>();
//
//        boolean warunek = false;
//        int nrProduktu = 0;
//
//        do {
//            System.out.println("Produk nr: " + nrProduktu );
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Nazwa Produktu: ");
//            String nazwaProduktu = scanner.next();
//            System.out.print("Cena Produktu brutto: ");
//            float cenaBrutto = scanner.nextFloat();
//            faktura.setCenaBrutto(faktura.getCenaBrutto()+cenaBrutto);
//
//            produkty.add(new Produkt(nazwaProduktu,cenaBrutto));
//
//            System.out.println();
//            System.out.println();
//            System.out.println("1.Dodaj kolejny produkt");
//            System.out.println("2.Wystaw Fakture");
//
//            int wybor = scanner.nextInt();
//
//            if (wybor == 1) {
//                warunek = true;
//                nrProduktu++;
//            } else if (wybor != 1) {
//                warunek = false;
//            }
//
//        } while(warunek);
//
//        faktura.setProdukty(produkty);
//    }

    private String nadawanieNumeruFakturyWKonstruktorze(){

        String nowyNrFaktury;

        LocalDate dzisiejszaData = LocalDate.now();

        nowyNrFaktury = (getLiczbaFaktur() + "/" + dzisiejszaData.getMonthValue() + "/" + dzisiejszaData.getYear() );

        return nowyNrFaktury;
    }

    public static void dodawnaieNowejFaktury(){

        //POBIERANIE DATY
        Scanner scanner = new Scanner(System.in);

        System.out.println("        Dodawanie nowej faktury");
        System.out.println("1.Data wystawienia ");
        System.out.print("      Rok:");
        int rok = scanner.nextInt();
        System.out.print("      Miesiąc:");
        int miesiac = scanner.nextInt();
        System.out.print("      Dzien:");
        int dzien = scanner.nextInt();

        LocalDate dataWystawiania = LocalDate.of(rok,miesiac,dzien);



        //WYBRANIE/DODANIE KONTRACHENTA

        System.out.println("\n\n1.Wyswietl wszystkich kontrachentow\n2.Dodaj nowego");

        int wybor = scanner.nextInt();
        Kontrachent kontrachent =null;

        switch (wybor){
            case 1:
                Kontrachent.displayAllFromDataBase();
                System.out.print("Podaj numer kontrachenta ktorego chcesz dodac:");
                int wyborKontrachenta = scanner.nextInt();
                kontrachent = Kontrachent.getByIndexFromDataBase(wyborKontrachenta);
                break;
            case 2:
                kontrachent =Kontrachent.dodanieNowegoKontrachenta();
                break;

        }

        //TODO ZMIENIC ARCHITEKTURE BAZYDANYCH GDZIE FAKTURA-KONTRACHENT 1:1 A PRDUKTY TO IDK (TABICA/WIELE DO WIELU) BO TUTAJ DODOAJEMY DO FKATURY KONTRACHENTA A W BAZIE DANYCH TAK NIE MA

        FakturaVat fakturaVat = new FakturaVat(dataWystawiania,kontrachent);
        fakturaVat.addToDataBase();

        Produkt.dodawanieProduktowDoFaktury(fakturaVat);
        fakturaVat.setCenaBruttoWFakturWystawionych(fakturaVat);
        fakturaVat.setCenaBruttoFakturyWBazieDanych(fakturaVat);






    }

    public static FakturaVat getByIndexFromDataBase(int index){

        FakturaVat fakturaVat =null;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury WHERE faktura_id="+ index +";");
            result.next();



            String nr_faktury =  result.getString("nr_faktury");
            LocalDate dataWystawienia = result.getDate("data_wystawienia").toLocalDate();
            int kontrachent_id = result.getInt("kontrachent_id");
            Float cenaBrutto = result.getFloat("cenaBrutto");

            fakturaVat =  new FakturaVat(dataWystawienia,Kontrachent.getByIndexFromDataBase(kontrachent_id),nr_faktury,cenaBrutto);
//            kontrachent = new Kontrachent(kontrachent_name,nip);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return fakturaVat;
    }

    @Override
    public void addToDataBase(){

        try{
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_name='" + this.getKontrachent().getNazwaKontrachenta()+"';");

            result.next();
            int kontrachent_id = result.getInt("kontrachent_id");
            Date data_wystawienia = Date.valueOf(this.getDataWystawienia());
            QueryExecutor.executeQuery("INSERT INTO faktury (nr_faktury,data_wystawienia,kontrachent_id,cenaBrutto) VALUES ('" + this.getNrFaktury()+"','"+ data_wystawienia +"'," + kontrachent_id +",0);");
//            System.out.println("INSERT INTO faktury (nr_faktury,data_wystawienia,kontrachent_id,cenaBrutto) VALUES ('" + this.getNrFaktury()+"','"+ data_wystawienia +"'," + kontrachent_id +",0);");

        }catch (SQLException e){
            e.printStackTrace();
        }

//        QueryExecutor.executeQuery("INSERT INTO faktury (nr_faktury,data_wystawienia,kontrachent_id,cenaBrutto) VALUES('"+produkt.getNazwaProduktu()+"','"+produkt.getCenaProduktuBrutto()+"');");

    }

    public static void displayAllFromDataBase(){

        int fakturaID;
        String nrFaktury;
        int kontrachentID;
        LocalDate dataWystawienia;
        Float cenaBrutto;
        ResultSet resultProducts;
        String produktName;
        float cenaCałkowita =0;
        int produktID;

        try{
            //POBIERAMY FAKTURY
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury;");
            while (result.next()){
                cenaCałkowita = 0;
                fakturaID = result.getInt("faktura_id");
                nrFaktury = result.getString("nr_faktury");
                kontrachentID = result.getInt("kontrachent_id");
                dataWystawienia = result.getDate("data_wystawienia").toLocalDate();

                //POBIERAMY KONTRACHENTA
                Kontrachent kontrachent = Kontrachent.getByIndexFromDataBase(kontrachentID);

                String nazwaKontrachenta = kontrachent.getNazwaKontrachenta();
                String NIP = kontrachent.getNIP();

                System.out.println(fakturaID + ". Nr Faktury: " + nrFaktury + " Data wystawiania: " + dataWystawienia + " Kontrachent: " + nazwaKontrachenta + " NIP: " + NIP);
                System.out.println("TOWAR/USLUGI");

                //POBIERAMY PRODUKTY DO BAZYDANYCH
                ResultSet resultWystawioneFaktury = QueryExecutor.executeSelect("SELECT * FROM wystawione_faktury WHERE faktura_id=" + fakturaID + ";");

                while (resultWystawioneFaktury.next()) {
                    produktID = resultWystawioneFaktury.getInt("produkt_id");
                    resultProducts = QueryExecutor.executeSelect("SELECT * FROM produkty WHERE produkt_id=" + produktID + ";");
                    resultProducts.next();
                    produktName = resultProducts.getString("produkt_name");
                    cenaBrutto = resultProducts.getFloat("cena_brutto");
                    System.out.println(produktID + "." + produktName + "    " + cenaBrutto);

                    cenaCałkowita += cenaBrutto;

                }


                System.out.println("\nCENA CAŁKOWITA: " + cenaCałkowita+"\n\n\n");

            }
        } catch (SQLException e){
            e.printStackTrace();
        }



    }

    public static int getIndexFaktury(FakturaVat fakturaVat){
        int index=0;


        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury WHERE nr_faktury='"+ fakturaVat.getNrFaktury()+"';");
        System.out.println("Nr faktury: " + fakturaVat.getNrFaktury());

        try {
            result.next();
            index = result.getInt("faktura_id");

        } catch (SQLException e){
            e.printStackTrace();
        }

        return index;
    }

    public void setCenaBruttoWFakturWystawionych(FakturaVat fakturaVat){

        int indexFaktury = FakturaVat.getIndexFaktury(fakturaVat);

//        System.out.println("indeks faktury: " + FakturaVat.getIndexFaktury(fakturaVat));
        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM wystawione_faktury WHERE faktura_id="+ FakturaVat.getIndexFaktury(fakturaVat) + ";");
//        System.out.println("SELECT * FROM wystawione_faktury WHERE faktura_id="+ fakturaVat.getNrFaktury() + ";");

        int iloscProduktu,indexProduktu;
        Produkt produkt;
        float cena;
        try{

            while (result.next()){
                iloscProduktu = result.getInt("ilosc_produktow");
                indexProduktu = result.getInt("produkt_id");

                produkt = Produkt.getByIndexFromDataBase(indexProduktu);


                cena=produkt.getCenaProduktuBrutto()*iloscProduktu;

                QueryExecutor.executeQuery("UPDATE wystawione_faktury SET cena_Brutto=" +cena+ " WHERE produkt_id=" + indexProduktu + " AND faktura_id=" + indexFaktury + ";");
                System.out.println("UPDATE wystawione_faktury SET cena_Brutto=" +cena+ " WHERE produkt_id=" + indexProduktu + " AND faktura_id=" + indexFaktury + ";");


            }

        } catch (SQLException e){
            e.printStackTrace();
        }





    }

    private void setCenaBruttoFakturyWBazieDanych(FakturaVat fakturaVat){

        ResultSet result =  QueryExecutor.executeSelect("SELECT * FROM wystawione_faktury WHERE faktura_id="+ FakturaVat.getIndexFaktury(fakturaVat) + ";");
//        System.out.println("SELECT * FROM wystawione_faktury WHERE faktura_id="+ FakturaVat.getIndexFaktury(fakturaVat) + ";");
        float cenaFakturyBrutto=0;
        try {

            while (result.next()) {
                cenaFakturyBrutto = cenaFakturyBrutto +result.getInt("cena_Brutto");
            }

//            System.out.println("UPDATE faktury SET cenaBrutto=" + cenaFakturyBrutto +" WHERE faktura_id="+ FakturaVat.getIndexFaktury(fakturaVat) + ";");
            QueryExecutor.executeQuery("UPDATE faktury SET cenaBrutto=" + cenaFakturyBrutto +" WHERE faktura_id="+ FakturaVat.getIndexFaktury(fakturaVat) + ";" );
        } catch (SQLException e){
            e.printStackTrace();
        }


    }


}
