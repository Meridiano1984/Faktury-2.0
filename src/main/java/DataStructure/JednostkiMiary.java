package DataStructure;


/**
Typ wyliczeniowy określający możliwe jednostki miar określających ilość produktów
 */

public enum JednostkiMiary {

    SZTUKA("szt"),
    KILOGRAM("kg"),
    TONA("T"),
    LITR("l"),
    MINILITR("ml"),
    METR("m"),
    CENTYMETR("cm"),
    METRKWADRATOWY("m2"),
    METRSZESCIENNY("m3");


    String jednostkaMiary;

    private JednostkiMiary(String jednostka_miary){
        jednostkaMiary = jednostka_miary;
    }

}
