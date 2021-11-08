package Podatki;

public enum StawkiVAT {

    DWADZIESICIATRYPROCENT(23),
    OSIEMPROCENT(8),
    PIECPROCENT(5),
    ZEROPROCENT(0),
    ;

    private int value;

    StawkiVAT(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
