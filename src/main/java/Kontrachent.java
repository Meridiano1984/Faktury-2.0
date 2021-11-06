import DataBase.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Kontrachent implements DataBaseOperations, KontrachentInterface {

    static  private int liczbaKontrachentow=0;
    private String nazwaKontrachenta;
    private String NIP;

    public Kontrachent(String nazwaKontrachenta, String NIP) {
        this.nazwaKontrachenta = nazwaKontrachenta;
        this.NIP = NIP;
        liczbaKontrachentow++;
    }

    public static int getLiczbaKontrachentow() {
        return liczbaKontrachentow;
    }

    public static void setLiczbaKontrachentow(int liczbaKontrachentow) {
        Kontrachent.liczbaKontrachentow = liczbaKontrachentow;
    }

    public String getNazwaKontrachenta() {
        return nazwaKontrachenta;
    }

    public void setNazwaKontrachenta(String nazwaKontrachenta) {
        this.nazwaKontrachenta = nazwaKontrachenta;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public static Kontrachent getByIndexFromDataBase(int index){

        Kontrachent kontrachent =null;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_id="+ index +";");
            result.next();

            String kontrachent_name = result.getString("kontrachent_name")           ;
            String nip = result.getString("nip");

            kontrachent = new Kontrachent(kontrachent_name,nip);

        } catch (SQLException e){
            e.printStackTrace();
        }

        return kontrachent ;
    }

    @Override
    public void addToDataBase(){

        QueryExecutor.executeQuery("INSERT INTO kontrachenci (kontrachent_name,nip) VALUES('"+this.getNazwaKontrachenta()+"','"+this.getNIP()+"');");

    }

    public static void displayAllFromDataBase(){
        //POBIERANIE KONTRACHENTOW Z BAZY DANYCH

        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci;");

            result.next();
            int kontrachent_id = result.getInt("kontrachent_id");
            String kontrachent_name = result.getString("kontrachent_name");

            System.out.println(kontrachent_id +"."+ kontrachent_name);


            while(result.next()){
                kontrachent_id = result.getInt("kontrachent_id");
                kontrachent_name = result.getString("kontrachent_name");
                System.out.println(kontrachent_id +"."+ kontrachent_name);
//                System.out.println( kontrachent_name);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Kontrachent dodanieNowegoKontrachenta(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("2.Dane nowego Kontrachenta ");
        System.out.print("      Nazwa Kontrachenta: ");
        String nazwaKontrachenta = scanner.next();
        System.out.print("      NIP: ");
        String nip = scanner.next();

        Kontrachent kontrachent = new Kontrachent(nazwaKontrachenta,nip);
        kontrachent.addToDataBase();


        return  kontrachent;
    }

    public static int getIdexKontrachenta(Kontrachent kontrachent){
        int index = 0;

        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci WHERE kontrachent_name='" + kontrachent.getNazwaKontrachenta() + "';");
        try {
            result.next();
            index = result.getInt("kontrachent_id");

        } catch (SQLException e){
            e.printStackTrace();
        }

        return index;
    }

}


