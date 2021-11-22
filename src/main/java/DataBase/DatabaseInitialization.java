package DataBase;

import static DataBase.QueryExecutor.executeQuery;

public class DatabaseInitialization {

    public DatabaseInitialization() {
    }

    public void tworzenieBazydanych(){

        String Query;

        //TODO PRZY ROBIENIU Z TEGO APLIKACJI WEBOWEJ TRZEBA DODOAC DO BAZY DANYCH 2 KONTRACHENT ID. NIE ZAWSZE WYSTAWIASZ NA SIEBIE

        /**
         * MAKSYMALNA WARTOSC SPRZEDAZY DO WYSTAWIANIA NA FAKTURZE TO (10 000 000 000 - 1)
         */


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
                    CREATE TABLE faktury (\s
                     faktura_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     typ_faktury VARCHAR(15),
                     nr_faktury VARCHAR(15),
                     data_wystawienia DATE,                    
                     kontrachent_id INT,
                     wartosc_sprzedazy_netto DOUBLE(9,2),
                     wartosc_sprzedazy_brutto DOUBLE(9,2),
                     wartosc_calkowita_podatku DOUBLE(9,2),
                     uwagi TINYTEXT,
                     FOREIGN KEY(kontrachent_id) REFERENCES kontrachenci(kontrachent_id) ON DELETE SET NULL                     
                     );
                            """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("TABELA FAKTURY JUZ ISTNIEJE");
        }

        try {
            Query = """ 
                     CREATE TABLE towary ( 
                     produkt_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     produkt_name VARCHAR(40),
                     jedostka_miary VARCHAR(5),
                     cena_zakupu_netto  DOUBLE(9,2),
                     cena_zakupu_brutto  DOUBLE(9,2),
                     stawka_VAT_zakupu INT(2),
                     cena_sprzedazy_netto DOUBLE(9,2),
                     cena_sprzedazy_brutto DOUBLE(9,2),
                     stawka_VAT_sprzedazy INT(2)                  
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
                     produkt_id INT,
                     ilosc_produktow INT,
                     wartosc_netto DOUBLE(9,2),
                     wartosc_brutto DOUBLE(9,2),
                     wartosc_vat DOUBLE(9,2),
                     PRIMARY KEY(faktura_id, produkt_id),
                     FOREIGN KEY(faktura_id) REFERENCES faktury(faktura_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(produkt_id) REFERENCES towary(produkt_id) ON DELETE CASCADE ON UPDATE CASCADE
                     );
                    """;
        executeQuery(Query);


    }

    public void dodaniePrzykładowychdanych(){

        try {
            executeQuery("INSERT INTO kontrachenci VALUES(1,'KRAJOWY','Biedronka',1234567890,'Polska','Wrocław','Swidnicka','12c/32');");
            executeQuery("INSERT INTO kontrachenci VALUES(2,'KRAJOWY','Autocentrum',2134567890,'Polska','Wrocław','Swidnicka','12c/32');");
            executeQuery("INSERT INTO kontrachenci VALUES(3,'KRAJOWY','PGE-Belchatow',3354618291,'Polska','Wrocław','Swidnicka','12c/32');");
            executeQuery("INSERT INTO kontrachenci VALUES(4,'KRAJOWY','Kamilex',3466789008,'Polska','Wrocław','Swidnicka','12c/32');");
            executeQuery("INSERT INTO kontrachenci VALUES(5,'KRAJOWY','II LO Radomsko',1111144444,'Polska','Wrocław','Swidnicka','12c/32');");
            executeQuery("INSERT INTO kontrachenci VALUES(6,'KRAJOWY','Fitnes Centrum',3334445559,'Polska','Wrocław','Swidnicka','12c/32');");
            executeQuery("INSERT INTO kontrachenci VALUES(7,'KRAJOWY','Riff',1231231234,'Polska','Wrocław','Swidnicka','12c/32');");

            System.out.println("Dodano juz kontrachentow");
        }catch (RuntimeException e ){
            e.printStackTrace();
        }

        try {
            executeQuery("INSERT INTO towary VALUES(1,'Mleko','szt',2.50,3.50,23,4.50,5.60,23);");
            executeQuery("INSERT INTO towary VALUES(2,'Laptop','szt',2500,3000,23,3300,3600,23)");
            executeQuery("INSERT INTO towary VALUES(3,'Jeansy','szt',250,300,23,320,360,34);");
            executeQuery("INSERT INTO towary VALUES(4,'Windows','szt',4000,5000,23,5500,6000,20);");
            executeQuery("INSERT INTO towary VALUES(5,'Bulka','szt',0.39,0.60,23,1.20,1.80,23);");
            executeQuery("INSERT INTO towary VALUES(6,'Margherita','szt',12.50,15,23,18,21,23);");

            System.out.println("Dodano produkty");
        }catch (RuntimeException e ){
            e.printStackTrace();
        }

            executeQuery("INSERT INTO faktury VALUES(1,'VAT','1/10/2021','2021-12-23',1,0,0,0,'pierwsza faktura');");
            executeQuery("INSERT INTO faktury VALUES(2,'VAT','2/10/2021','2022-01-01',2,0,0,0,'pierwsza faktura');");
            executeQuery("INSERT INTO faktury VALUES(3,'VAT','3/10/2021','2022-01-02',3,0,0,0,'pierwsza faktura');");


            System.out.println("Dodano faktury");


            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(1,2,112,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(1,3,22,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(1,4,53,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(1,1,31,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(2,1,33,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(2,2,44,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(2,4,55,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(3,1,11,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(3,2,22,0,1,1);");
            executeQuery("INSERT INTO produkty_na_fakturach_vat VALUES(3,3,33,0,1,1);");

            System.out.println("Dodano wystawione Faktury");


    }

    public void usuwanieTabel(){


        executeQuery("DROP TABLE produkty_na_fakturach_vat;");
        executeQuery("DROP TABLE faktury;");
        executeQuery("DROP TABLE towary;");
        executeQuery("DROP TABLE kontrachenci;");
//            executeQuery("DROP TABLE faktury;");

    }

}
