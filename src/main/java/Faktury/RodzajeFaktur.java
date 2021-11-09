package Faktury;

public enum RodzajeFaktur {

    FAKTURAVAT("vat");


    String rodzajFaktury;
    int liczba;

    RodzajeFaktur(String rodzajFaktury) {
        this.rodzajFaktury = rodzajFaktury;
    }
}
