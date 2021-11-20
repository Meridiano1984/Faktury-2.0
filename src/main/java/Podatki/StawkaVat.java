package Podatki;

public class StawkaVat {


    private double stawkaVAT;
    private StawkiVAT stawkaBruttoProcentowa;

    public StawkaVat(StawkiVAT stawkiVAT) {
        this.stawkaVAT = (double) stawkiVAT.getValue()/100;
    }

    public StawkaVat(int stawkaVAT) {
        this.stawkaVAT = (double) stawkaVAT;

        if(stawkaVAT==23){
            this.stawkaBruttoProcentowa = StawkiVAT.DWADZIESICIATRYPROCENT;
        } else if( stawkaVAT ==8){
            this.stawkaBruttoProcentowa = StawkiVAT.OSIEMPROCENT;
        } else if(stawkaVAT==5){
            this.stawkaBruttoProcentowa = StawkiVAT.PIECPROCENT;
        } else if(stawkaVAT==0){
            this.stawkaBruttoProcentowa =StawkiVAT.ZEROPROCENT;
        }

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
