package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static DataBase.QueryExecutor.executeQuery;

public class DatabaseInitialization {

    public DatabaseInitialization() {
    }

    public void tworzenieBazydanych (){

        String Query;

        try {
             Query = """
                    CREATE TABLE faktury (\s
                     faktura_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     nr_faktury VARCHAR(15),
                     data_wystawienia DATE,
                     cenaBrutto FLOAT(2)
                     );
                            """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("TABELA FAKTURY JUZ ISTNIEJE");
        }


        try {
            Query = """
                     CREATE TABLE kontrachenci (\s
                     kontrachent_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     kontrachent_name VARCHAR(40),
                     nip VARCHAR(15)
                     );
                    """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("Tabela KONTRACHENCI JUZ ISTNIEJE");
        }

        try {
            Query = """ 
                     CREATE TABLE produkty ( 
                     produkt_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     produkt_name VARCHAR(40),
                     cena_brutto FLOAT(20)
                     );
                    """;

            executeQuery(Query);
        } catch (RuntimeException e){
            System.out.println("Tabela produkty juz istnieje");
        }


        //TODO ZAPROJEKTOWAC BAZEDANYCH TAK ZE KAZDA FAKTURA MA JEDNEGO KONTRACHENTA (faktura i kontrachent ot primary key) I WIELE PRODUKTOW COS JAKBY TABLICA PRODUKTOW


            Query = """
                    CREATE TABLE wystawione_faktury ( 
                     faktura_id INT,
                     kontrachent_id INT,
                     produkt_id INT,
                     ilosc_produktow INT,
                     PRIMARY KEY(faktura_id, produkt_id),
                     FOREIGN KEY(faktura_id) REFERENCES faktury(faktura_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(kontrachent_id) REFERENCES kontrachenci(kontrachent_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(produkt_id) REFERENCES produkty(produkt_id) ON DELETE CASCADE ON UPDATE CASCADE
                     );
                    """;
            executeQuery(Query);


    }

    public void tworzenieBazydanychV2 (){

        String Query;

        //TODO PRZY ROBIENIU Z TEGO APLIKACJI WEBOWEJ TRZEBA DODOAC DO BAZY DANYCH 2 KONTRACHENT ID. NIE ZAWSZE WYSTAWIASZ NA SIEBIE

        /**
         * MAKSYMALNA WARTOSC SPRZEDAZY DO WYSTAWIANIA NA FAKTURZE TO (10 000 000 000 - 1)
         */

        try {
            Query = """
                    CREATE TABLE faktury (\s
                     faktura_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     typ_faktury VARCHAR(15),
                     nr_faktury VARCHAR(15),
                     data_wystawienia DATE,                    
                     kontrachent_id INT,
                     wartosc_sprzedazy_netto DOUBLE(9,2),
                     wartosc_sprzedazy_brutto DOUBLE(9,2),
                     wartosc_calkowita_podatku DOUBLE(9,2),
                     uwagi TINYTEXT
                     );
                            """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("TABELA FAKTURY JUZ ISTNIEJE");
        }


        try {
            Query = """
                     CREATE TABLE kontrachenci (\s
                     kontrachent_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     typ_kontrachent VARCHAR(20),
                     kontrachent_name VARCHAR(40),                  
                     nip VARCHAR(15),
                     adres_kraj VARCHAR(25),
                     adres_miasto VARCHAR(25),
                     adres_ulica VARCHAR(25),
                     adres_nr_budynku VARCHAR(10)
                     );
                    """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("Tabela KONTRACHENCI JUZ ISTNIEJE");
        }

        try {
            Query = """ 
                     CREATE TABLE towary ( 
                     produkt_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     produkt_name VARCHAR(40),
                     jedostka_miary VARCHAR(3),
                     cena_zakupu_netto  DOUBLE(9,2),
                     cena_zakupu_brutto  DOUBLE(9,2),
                     stawka_VAT_zakupu INT(2),
                     cena_sprzedazy_netto DOUBLE(9,2),
                     cena_sprzedazy_brutto DOUBLE(9,2),
                     stawka_VAT_sprzedazy INT(2),                  
                     );
                    """;

            executeQuery(Query);
        } catch (RuntimeException e){
            System.out.println("Tabela produkty juz istnieje");
        }


        //TODO ZAPROJEKTOWAC BAZEDANYCH TAK ZE KAZDA FAKTURA MA JEDNEGO KONTRACHENTA (faktura i kontrachent ot primary key) I WIELE PRODUKTOW COS JAKBY TABLICA PRODUKTOW


        Query = """
                    CREATE TABLE produkty_na_fakturach_vat ( 
                     faktura_id INT,
                     kontrachent_id INT,
                     produkt_id INT,
                     ilosc_produktow INT,
                     wartosc_netto DOUBLE(9,2),
                     wartosc_brutto DOUBLE(9,2),
                     wartosc_vat DOUBLE(9,2),
                     PRIMARY KEY(faktura_id, produkt_id),
                     FOREIGN KEY(faktura_id) REFERENCES faktury(faktura_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(kontrachent_id) REFERENCES kontrachenci(kontrachent_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(produkt_id) REFERENCES produkty(produkt_id) ON DELETE CASCADE ON UPDATE CASCADE
                     );
                    """;
        executeQuery(Query);


    }


    public void dodaniePrzykładowychdanych(){

        try {
            executeQuery("INSERT INTO kontrachenci VALUES(1,'Biedronka',1234567890);");
            executeQuery("INSERT INTO kontrachenci VALUES(2,'Autocentrum',2134567890);");
            executeQuery("INSERT INTO kontrachenci VALUES(3,'PGE-Belchatow',3354618291);");
            executeQuery("INSERT INTO kontrachenci VALUES(4,'Kamilex',3466789008);");
            executeQuery("INSERT INTO kontrachenci VALUES(5,'II LO Radomsko',1111144444);");
            executeQuery("INSERT INTO kontrachenci VALUES(6,'Fitnes Centrum',3334445559);");
            executeQuery("INSERT INTO kontrachenci VALUES(7,'Riff',1231231234);");

            System.out.println("Dodano juz kontrachentow");
        }catch (RuntimeException e ){
            e.printStackTrace();
        }

        try {
            executeQuery("INSERT INTO produkty VALUES(1,'Mleko',2.50);");
            executeQuery("INSERT INTO produkty VALUES(2,'Laptop',2500)");
            executeQuery("INSERT INTO produkty VALUES(3,'Jeansy',250);");
            executeQuery("INSERT INTO produkty VALUES(4,'Windows',4000);");
            executeQuery("INSERT INTO produkty VALUES(5,'Bulka',0.39);");
            executeQuery("INSERT INTO produkty VALUES(6,'Margherita',12.50);");

            System.out.println("Dodano produkty");
        }catch (RuntimeException e ){
            e.printStackTrace();
        }

            executeQuery("INSERT INTO faktury VALUES(1,'1/10/2021','2021-12-23',1,0);");
            executeQuery("INSERT INTO faktury VALUES(2,'2/10/2021','2022-01-01',2,0);");
            executeQuery("INSERT INTO faktury VALUES(3,'3/10/2021','2022-01-02',3,0);");


            System.out.println("Dodano faktury");


            executeQuery("INSERT INTO wystawione_faktury VALUES(1,2,112,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(1,3,22,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(1,4,53,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(1,1,31,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(2,1,33,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(2,2,44,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(2,4,55,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(3,1,11,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(3,2,22,0);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(3,3,33,0);");

            System.out.println("Dodano wystawione Faktury");


    }


    public void usuwanieTabel(){


            executeQuery("DROP TABLE wystawione_faktury;");
            executeQuery("DROP TABLE faktury;");
            executeQuery("DROP TABLE produkty;");
            executeQuery("DROP TABLE kontrachenci;");
//            executeQuery("DROP TABLE faktury;");

    }

    public void tworzenieBazyDanychv2(){

        String Query;


        try{
            Query = """
                     CREATE TABLE kontrachenci (\s
                     kontrachent_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     kontrachent_name VARCHAR(40),
                     nip VARCHAR(15)
                     );
                    """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("Tabela KONTRACHENCI JUZ ISTNIEJE");
        }

        try {
            Query = """
                    CREATE TABLE faktury (\s
                     faktura_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     nr_faktury VARCHAR(15),
                     data_wystawienia DATE,
                     kontrachent_id INT,
                     cenaBrutto FLOAT(2),
                      FOREIGN KEY (kontrachent_id) REFERENCES kontrachenci(kontrachent_id) ON DELETE SET NULL                     
                     );
                            """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("TABELA FAKTURY JUZ ISTNIEJE");
        }




        try {
            Query = """ 
                     CREATE TABLE produkty ( 
                     produkt_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     produkt_name VARCHAR(40),
                     cena_brutto FLOAT(4)
                     );
                    """;

            executeQuery(Query);
        } catch (RuntimeException e){
            System.out.println("Tabela produkty juz istnieje");
        }


        //TODO ZAPROJEKTOWAC BAZEDANYCH TAK ZE KAZDA FAKTURA MA JEDNEGO KONTRACHENTA (faktura i kontrachent ot primary key) I WIELE PRODUKTOW COS JAKBY TABLICA PRODUKTOW



        try {
            Query = """
                    CREATE TABLE wystawione_faktury ( 
                     faktura_id INT,
                     produkt_id INT,
                     ilosc_produktow INT,
                     cena_Brutto FLOAT(4),
                     PRIMARY KEY(faktura_id, produkt_id),
                     FOREIGN KEY(faktura_id) REFERENCES faktury(faktura_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(produkt_id) REFERENCES produkty(produkt_id) ON DELETE CASCADE ON UPDATE CASCADE
                     );
                    """;
            executeQuery(Query);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }



}
