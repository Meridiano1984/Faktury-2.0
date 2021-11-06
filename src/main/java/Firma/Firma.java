package Firma;

public abstract class Firma {

    protected String nazwa;
    protected String kraj;
    protected String adres;
    protected String NIP;

    public Firma(String nazwa, String kraj, String adres, String NIP) {
        this.nazwa = nazwa;
        this.kraj = kraj;
        this.adres = adres;
        this.NIP = NIP;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }
}
