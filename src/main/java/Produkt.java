import DataBase.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Produkt implements DataBaseOperations,ProduktInterface {

    //TODO ZDECYDUJ SIE CZY CEN PRODUKTU PRUTTO CZY NETTO

    public static int iloscvProduktow = 0;

    private String  nazwaProduktu;
    private float cenaProduktuBrutto;

    public Produkt(String nazwaProduktu, float cenaProduktuNetto) {
        this.nazwaProduktu = nazwaProduktu;
        this.cenaProduktuBrutto = cenaProduktuNetto;
        iloscvProduktow++;
    }

    public static int getIloscvProduktow() {
        return iloscvProduktow;
    }

    public static void setIloscvProduktow(int iloscvProduktow) {
        Produkt.iloscvProduktow = iloscvProduktow;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public float getCenaProduktuBrutto() {
        return cenaProduktuBrutto;
    }

    public void setCenaProduktuBrutto(int cenaProduktuNetto) {
        this.cenaProduktuBrutto = cenaProduktuNetto;
    }

    public static void dodawanieProduktowDoFaktury(FakturaVat fakturaVat){

        Scanner scanner = new Scanner(System.in);
        boolean warunek = true;
        Produkt produkt = null;
        int ilosc_produktow = 0;

        System.out.println("DODAWANIE PRODUKTOW DO FAKTURY");

        do {
            System.out.println("1.Dodaj produkt z listy");
            System.out.println("2.Dodaj nowy produkt");
            System.out.println("3.Wyjdz");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    Produkt.displayAllFromDataBase();
                    System.out.print("Podaj indeks produktu któy chcesz dodoac do faktury:");
                    int wybranyProdukt = scanner.nextInt();
                    System.out.print("Ilość: ");
                    ilosc_produktow = scanner.nextInt();
//                    Produkt.getProoduktPoZadanymIndex(wybranyProdukt);
                    produkt =Produkt.getByIndexFromDataBase(wybranyProdukt);
                    produkt.dodanieProduktuDoTabeliWystawinychFaktur(fakturaVat,produkt,ilosc_produktow);
                    break;
                case 2:
                    produkt = Produkt.dodanieNowegoProduktu();
                    System.out.print("Ilość: ");
                    ilosc_produktow = scanner.nextInt();
                    produkt.dodanieProduktuDoTabeliWystawinychFaktur(fakturaVat,produkt,ilosc_produktow);
                    break;
                case 3:
                    warunek = false;
                    break;
            }

        }while(warunek);
    }

    public static Produkt getByIndexFromDataBase(int index){

        Produkt produkt =null;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty WHERE produkt_id="+ index +";");
            result.next();

            String produkt_name = result.getString("produkt_name");
            Float cenaBrutto = result.getFloat("cena_brutto");

            produkt = new Produkt(produkt_name,cenaBrutto);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return produkt ;
    }

    @Override
    public void addToDataBase(){
        QueryExecutor.executeQuery("INSERT INTO produkty (produkt_name,cena_brutto) VALUES('"+this.getNazwaProduktu()+"','"+this.getCenaProduktuBrutto()+"');");

    }

    public static void displayAllFromDataBase(){
        //POBIERANIE KONTRACHENTOW Z BAZY DANYCH

        int produkt_id;
        String produkt_name;
        float cena_brutto;

        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty;");

//            result.next();
//            int produkt_id = result.getInt("produkt_id");
//            String produkt_name = result.getString("produkt_name");
//            float cena_brutto = result.getFloat("cena_brutto");
//
//            System.out.println(produkt_id +"."+ produkt_name + "  cena: " + cena_brutto);


            while(result.next()){
                produkt_id = result.getInt("produkt_id");
                cena_brutto = result.getFloat("cena_brutto");
                produkt_name = result.getString("produkt_name");
                System.out.println(produkt_id +"."+ produkt_name + "  cena: " + cena_brutto);
//                System.out.println( produkt_name);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Produkt dodanieNowegoProduktu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dodoanie nowego produktu:");
        System.out.print("Nazwa: ");
        String nazwaProduktu = scanner.nextLine();
        System.out.print("Cena: ");
        float cenaBruutProduktu = scanner.nextFloat();

        Produkt produkt = new Produkt(nazwaProduktu,cenaBruutProduktu);
        produkt.addToDataBase();

        return produkt;

    }

    @Override
    public void dodanieProduktuDoTabeliWystawinychFaktur(FakturaVat fakturaVat, Produkt produkt, int ilosc){

        //TODO CZY JA NIE POWINIEM TWEGO ROZDZILIC NA OSBNE FUNKCJE? JAK POWINNY WYGLADAC FUNKCJE OBSLUGUJCE CRUDY?
        //TODO CZY MOZAN ROZDZIELIC WYSTAPIENIE EXEPTIONA OD BREKAEPOINTA?
        try {

            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury WHERE nr_faktury='"+ fakturaVat.getNrFaktury()+"';");
            System.out.println(fakturaVat.getNrFaktury());
            System.out.println("SELECT * FROM faktury WHERE nr_faktury='"+ fakturaVat.getNrFaktury()+"';");
            result.next();



            int faktura_id = result.getInt("faktura_id");

            result = QueryExecutor.executeSelect("SELECT * FROM produkty WHERE produkt_name='"+ produkt.getNazwaProduktu()+"';");
            result.next();
//            if(result.next()){
//                System.out.println("Sa Dane ");
//            } else {
//                System.out.println("PUSTY");
//            }

            int produkt_id = result.getInt("produkt_id");


//            System.out.println("INSERT INTO wystawione_faktury VALUES (" + faktura_id + "," + produkt_id + "," +ilosc+",0);");

            try {
                QueryExecutor.executeQuery("INSERT INTO wystawione_faktury VALUES (" + faktura_id + "," + produkt_id + "," + ilosc + ",0);");
            } catch (RuntimeException f) {
                produkt.sprawdzenieCzyProduktZanjdujeSieJuzWBazieDanych(produkt, fakturaVat, ilosc);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void sprawdzenieCzyProduktZanjdujeSieJuzWBazieDanych(Produkt produkt, FakturaVat fakturaVat, int ilosc){
        //todo gdzie przezucac logike wykonywania funkcji do programu czy do bazy SQL?

        int indexProduktuDoSprawdzenia = Produkt.getIndexProduktu(produkt);
        int indexFakturyDoSprawdzenia = FakturaVat.getIndexFaktury(fakturaVat);
        System.out.println("SELECT * FROM wystawione_faktury WHERE faktura_id=" + indexFakturyDoSprawdzenia + " AND produkt_id=" + indexProduktuDoSprawdzenia + ";");
        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM wystawione_faktury WHERE faktura_id=" + indexFakturyDoSprawdzenia + " AND produkt_id=" + indexProduktuDoSprawdzenia + ";");
        try {
            if(result.next()){
                QueryExecutor.executeQuery("UPDATE wystawione_faktury SET ilosc_produktow = ilosc_produktow+" + ilosc + " WHERE faktura_id=" + indexFakturyDoSprawdzenia + " AND produkt_id=" + indexProduktuDoSprawdzenia + ";" );
            }

        } catch (SQLException e){
            e.printStackTrace();
        }



    }

    public static int getIndexProduktu(Produkt produkt){
        int index = 0;

        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty WHERE produkt_name='"+ produkt.getNazwaProduktu()+"';");

        try {
            result.next();
            index = result.getInt("produkt_id");

        } catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }
}
