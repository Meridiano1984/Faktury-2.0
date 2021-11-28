package Towary;
import DataBase.QueryExecutor;
import DataStructure.JednostkiMiary;
import Kontrachent.Firma;
import Podatki.StawkaVat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Towar {

    private String nazwa;
    private JednostkiMiary jednostkiMiary;
    private Double cenaZakupuNetto;
    private Double cenaZakupuBrutto;
    private StawkaVat stawkaVatZakupu;
    private Double cenaSprzedazyNetto;
    private Double cenaSprzedazyBrutto;
    private StawkaVat stawkaVatSprzedazy;


    public Towar(String nazwa, JednostkiMiary jednostkiMiary, Double cenaZakupuNetto, StawkaVat stawkaVatZakupu, Double cenaSprzedazyNetto, StawkaVat stawkaVatSprzedazy) {
        this.nazwa = nazwa;
        this.jednostkiMiary = jednostkiMiary;
        this.cenaZakupuNetto = cenaZakupuNetto;
        this.stawkaVatZakupu = stawkaVatZakupu;
        this.cenaZakupuBrutto = StawkaVat.obliczanieCenyBrutto(cenaZakupuNetto, stawkaVatZakupu);
        this.cenaSprzedazyNetto = cenaSprzedazyNetto;
        this.stawkaVatSprzedazy = stawkaVatSprzedazy;
        this.cenaSprzedazyBrutto = StawkaVat.obliczanieCenyBrutto(cenaSprzedazyNetto, stawkaVatSprzedazy);
    }

    public Towar(){}



    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public JednostkiMiary getJednostkiMiary() {
        return jednostkiMiary;
    }

    public void setJednostkiMiary(JednostkiMiary jednostkiMiary) {
        this.jednostkiMiary = jednostkiMiary;
    }

    public Double getCenaZakupuNetto() {
        return cenaZakupuNetto;
    }

    public void setCenaZakupuNetto(Double cenaZakupuNetto) {
        this.cenaZakupuNetto = cenaZakupuNetto;
    }

    public Double getCenaZakupuBrutto() {
        return cenaZakupuBrutto;
    }

    public void setCenaZakupuBrutto(Double cenaZakupuBrutto) {
        this.cenaZakupuBrutto = cenaZakupuBrutto;
    }

    public StawkaVat getStawkaVatZakupu() {
        return stawkaVatZakupu;
    }

    public void setStawkaVatZakupu(StawkaVat stawkaVatZakupu) {
        this.stawkaVatZakupu = stawkaVatZakupu;
    }

    public Double getCenaSprzedazyNetto() {
        return cenaSprzedazyNetto;
    }

    public void setCenaSprzedazyNetto(Double cenaSprzedazyNetto) {
        this.cenaSprzedazyNetto = cenaSprzedazyNetto;
    }

    public Double getCenaSprzedazyBrutto() {
        return cenaSprzedazyBrutto;
    }

    public void setCenaSprzedazyBrutto(Double cenaSprzedazyBrutto) {
        this.cenaSprzedazyBrutto = cenaSprzedazyBrutto;
    }

    public StawkaVat getStawkaVatSprzedazy() {
        return stawkaVatSprzedazy;
    }

    public void setStawkaVatSprzedazy(StawkaVat stawkaVatSprzedazy) {
        this.stawkaVatSprzedazy = stawkaVatSprzedazy;
    }

    public static void wyswietlanieTowaru(){
        int licznik=0;
        ArrayList<Towar> listaTowaru =Towar.getZBazydanych();

        for(Towar towar: listaTowaru){
            licznik++;
            System.out.println(licznik + "." + towar);
        }
    }

    public static void dodawanieNowegoTowaru(){

        Scanner scanner;
        boolean warnuek = true;
        do {
            try {
                scanner = new Scanner(System.in);                                                   //TUTAJ TRZEBA WYCZYSCIC SKANER BO PRZY WPISANIU ZLYCH ARGUMENTOW ZLE WCZYTUJE
                System.out.println("...///DODANIE NOWEGO TOWARU");
                System.out.print("nazwa towaru: ");
                String nazwaTowaru = scanner.nextLine();
                System.out.println("dostepne jednostki(" + JednostkiMiary.dostepneJednostki() + ")");
                System.out.print("jednostka: ");
                String jednostkaMiary = scanner.next();
                System.out.print("cena zakupu netto: ");
                Double cenaZakupuNetto = scanner.nextDouble();
                System.out.print("Stawka VAT zakupu: ");
                int stawkaZakupuVat = scanner.nextInt();
                System.out.print("cena sprzedezy netto:");
                Double cenaSprzedazyNetto = scanner.nextDouble();
                System.out.print("stawka VAT sprzedazy: ");
                int stawkaSoprzedazyVat = scanner.nextInt();
                warnuek=false;

                Towar towar = new Towar(nazwaTowaru,JednostkiMiary.sprawdzanieJednostekMiary(jednostkaMiary),cenaZakupuNetto,new StawkaVat(stawkaZakupuVat),cenaSprzedazyNetto,new StawkaVat(stawkaSoprzedazyVat));
                towar.dodanieTowaruDoBazydanych();
                System.out.println("\nNOWY TOWAR\n");
                System.out.println(towar.toString());
            } catch (InputMismatchException e) {
//                e.printStackTrace();
                System.out.println("\n\nZLE DANE SPROBUJ PONOWNIE\n\n");
            }
        }while (warnuek);


    }

    private void dodanieTowaruDoBazydanych(){
        System.out.println("INSERT INTO towary (produkt_name,jednostka_miary,cena_zakupu_netto,cena_zakupu_brutto,stawka_VAT_zakupu,cena_sprzedazy_netto,cena_sprzedazy_brutto,stawka_VAT_sprzedazy)" +
                " VALUES('"+this.nazwa+"','"+this.jednostkiMiary.getJednostkaMiary()+"',"+this.cenaZakupuNetto+","+this.cenaZakupuBrutto+",'"+this.stawkaVatZakupu.getStawkaVAT()+"',"+this.cenaSprzedazyNetto+","+this.cenaSprzedazyBrutto+",'"+this.stawkaVatSprzedazy.getStawkaVAT()+"');");
        QueryExecutor.executeQuery("INSERT INTO towary (produkt_name,jedostka_miary,cena_zakupu_netto,cena_zakupu_brutto,stawka_VAT_zakupu,cena_sprzedazy_netto,cena_sprzedazy_brutto,stawka_VAT_sprzedazy)" +
                " VALUES('"+this.nazwa+"','"+this.jednostkiMiary.getJednostkaMiary()+"',"+this.cenaZakupuNetto+","+this.cenaZakupuBrutto+",'"+this.stawkaVatZakupu.getStawkaVAT()+"',"+this.cenaSprzedazyNetto+","+this.cenaSprzedazyBrutto+",'"+this.stawkaVatSprzedazy.getStawkaVAT()+"');");
    }

    public static ArrayList<Towar> getZBazydanych(){
        //TODO
        //TODO ZROB JAKAS KLASE ABSTRKACYJNA ZEBY TA FUNKCJA MOGLA BYC WYKORZYSTANA ROWNIEZ DLA TOWARU NA FAKTURZE
        //TODO
        ResultSet resultProducts = QueryExecutor.executeSelect("SELECT * FROM towary;");

        ArrayList<Towar> listaTowaru = new ArrayList<>();
        Towar towar;
        String nazwaProduktu;
        JednostkiMiary jednostkiMiary;
        String jednostkiMiaryString;
        double cenaZakupuNetto;
        StawkaVat stawkaVatZakupu;
        int stawkaVatZakupuInt;
        double cenaSprzedazyNetto;
        StawkaVat stawkaVatSprzedazy;
        int stawkaVatSprzedazyInt;

        try {
           while (resultProducts.next()) {
                nazwaProduktu = resultProducts.getString("produkt_name");
                jednostkiMiaryString = resultProducts.getString("jedostka_miary");
                jednostkiMiary = JednostkiMiary.sprawdzanieJednostekMiary(jednostkiMiaryString);
                cenaZakupuNetto = resultProducts.getDouble("cena_zakupu_netto");

                stawkaVatZakupuInt = resultProducts.getInt("stawka_VAT_zakupu");
                stawkaVatZakupu = new StawkaVat(stawkaVatZakupuInt);
                stawkaVatSprzedazyInt = resultProducts.getInt("stawka_VAT_sprzedazy");
                stawkaVatSprzedazy = new StawkaVat(stawkaVatSprzedazyInt);
                cenaSprzedazyNetto = resultProducts.getDouble("cena_sprzedazy_netto");

                towar = new Towar(nazwaProduktu,jednostkiMiary,cenaZakupuNetto,stawkaVatZakupu,cenaSprzedazyNetto,stawkaVatSprzedazy);
                listaTowaru.add(towar);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaTowaru;
    }

    public static Towar getByIndexFromDataBase(int index){

        Towar towar =null;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM towary WHERE produkt_id="+ index +";");
            result.next();

            String produktName = result.getString("produkt_name");
            String jedostkaMiary = result.getString("jedostka_miary");
            Double cenaZakupuNetto = result.getDouble("cena_zakupu_netto");
            String stawkaVATZakupu = result.getString("stawka_VAT_zakupu");
            Double cenaSprzedazyNetto = result.getDouble("cena_sprzedazy_netto");
            String stawkaVATSprzedazy = result.getString("stawka_VAT_sprzedazy");

            towar = new Towar(produktName,JednostkiMiary.sprawdzanieJednostekMiary(jedostkaMiary),cenaZakupuNetto,new StawkaVat(Integer.parseInt(stawkaVATZakupu)),cenaSprzedazyNetto,new StawkaVat(Integer.parseInt(stawkaVATSprzedazy)));
        } catch (SQLException e){
            e.printStackTrace();
        }

        return towar;
    }

    public int getIndexFromDataBase(Towar towar){
        int index=0;

        ResultSet resultSet = QueryExecutor.executeSelect("SELECT * FROM towary WHERE produkt_name='"+towar.getNazwa()+"';");
        try {
            resultSet.next();
            index = resultSet.getInt("produkt_id");
        }catch (SQLException e){
            e.printStackTrace();
        }

        return index;
    }

    public void dodawnieDoBazyDanychZGUI(){
        this.dodanieTowaruDoBazydanych();
    }

    @Override
    public String toString(){
        return "TOWAR nazwa: " + this.nazwa + " jednostka miary: " + this.jednostkiMiary + " cena zakupu netto: " + this.cenaZakupuNetto
                + " stawka VAT zakupu: " + this.stawkaVatZakupu.getStawkaVAT() + " cena brutto zakupu: " + this.cenaZakupuBrutto + "cena sprzedaży netto: " + this.cenaSprzedazyNetto
                + " stawka VAT sprzedaży: " + this.stawkaVatSprzedazy.getStawkaVAT() + " cena brutto sprzedaży: " + this.cenaSprzedazyBrutto;
    }
}
