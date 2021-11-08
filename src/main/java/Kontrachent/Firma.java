package Kontrachent;

public abstract class Firma extends Kontrachent{

    protected String NIP;
    protected String nazwaFirmy;

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    protected static boolean sprawdzanieNIP (String NIP){
        if(NIP.matches("[0-9]+") && NIP.length()==10){
            return true;
        } else {
            return false;
        }
    }
}
