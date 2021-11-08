package DataStructure;

public class Adres {

    private String Kraj;
    private String Miasto;
    private String Ulica;
    private String nrBudynku;


    public Adres(String kraj, String miasto, String ulica, String nrBudynku) {
        Kraj = kraj;
        Miasto = miasto;
        Ulica = ulica;
        this.nrBudynku = nrBudynku;
    }

    public String getKraj() {
        return Kraj;
    }

    public void setKraj(String kraj) {
        Kraj = kraj;
    }

    public String getMiasto() {
        return Miasto;
    }

    public void setMiasto(String miasto) {
        Miasto = miasto;
    }

    public String getUlica() {
        return Ulica;
    }

    public void setUlica(String ulica) {
        Ulica = ulica;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    @Override
    public String toString(){
        return "Adres: " + this.Kraj + "/" + this.Miasto + "/" + this.Ulica + "/" + this.nrBudynku;
    }

}
