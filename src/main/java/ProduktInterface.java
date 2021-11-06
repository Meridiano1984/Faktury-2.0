public interface ProduktInterface {

    public void dodanieProduktuDoTabeliWystawinychFaktur(FakturaVat fakturaVat, Produkt produkt, int ilosc);

    public void sprawdzenieCzyProduktZanjdujeSieJuzWBazieDanych(Produkt produkt, FakturaVat fakturaVat, int ilosc);
}
