package Podatki;

public class StawkaVat {


    private double stawkaVAT;
    private StawkiVAT stawkaBruttoProcentowa;

    public StawkaVat(StawkiVAT stawkiVAT) {
        this.stawkaVAT = (double) stawkiVAT.getValue()/100;
    }

    public static Double obliczanieCenyBrutto(Double cenaNetto, StawkaVat stawkaVat){
        return cenaNetto+(cenaNetto* stawkaVat.stawkaVAT);
    }


    public double getStawkaVAT() {
        return stawkaVAT;
    }

    public void setStawkaVAT(double stawkaVAT) {
        this.stawkaVAT = stawkaVAT;
    }

    public StawkiVAT getStawkaBruttoProcentowa() {
        return stawkaBruttoProcentowa;
    }

    public void setStawkaBruttoProcentowa(StawkiVAT stawkaBruttoProcentowa) {
        this.stawkaBruttoProcentowa = stawkaBruttoProcentowa;
    }
}
