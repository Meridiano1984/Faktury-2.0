package DataStructure;


import java.awt.*;

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
    METRSZESCIENNY("m3"),
    BRAKJEDNOSTKI("brak");


    String jednostkaMiary;

    private JednostkiMiary(String jednostka_miary){
        jednostkaMiary = jednostka_miary;
    }

    public String getJednostkaMiary() {
        return jednostkaMiary;
    }

    public void setJednostkaMiary(String jednostkaMiary) {
        this.jednostkaMiary = jednostkaMiary;
    }

    public static JednostkiMiary sprawdzanieJednostekMiary(String jednostkaMiary){
        return switch (jednostkaMiary) {
            case "szt" -> SZTUKA;
            case "kg" -> KILOGRAM;
            case "T" -> TONA;
            case "l" -> LITR;
            case "ml" -> MINILITR;
            case "m" -> METR;
            case "cm" -> CENTYMETR;
            case "m2" -> METRKWADRATOWY;
            case "m3" -> METRSZESCIENNY;
            default -> BRAKJEDNOSTKI;
        };

    }
}
