package model;

public class Produkt {

    public Produkt(int id, String bezeichnung, int packungsgr, int packungsart, int kategorie, float preis,
                   int baumarkt) {
        super();
        this.pid = id;
        this.bezeichnung = bezeichnung;
        this.packungsgr = packungsgr;
        this.packungsart = packungsart;
        this.kategorie = kategorie;
        this.preis = preis;
        this.baumarkt = baumarkt;
    }

    private int pid;
    private String bezeichnung;
    private int packungsgr;
    private int packungsart;
    private int kategorie;
    private float preis;
    private int baumarkt;

    public int getPid() {
        return pid;
    }

    public void setPid(int id) {
        this.pid = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getPackungsgr() {
        return packungsgr;
    }

    public void setPackungsgr(int packungsgr) {
        this.packungsgr = packungsgr;
    }

    public int getPackungsart() {
        return packungsart;
    }

    public void setPackungsart(int packungsart) {
        this.packungsart = packungsart;
    }

    public int getKategorie() {
        return kategorie;
    }

    public void setKategorie(int kategorie) {
        this.kategorie = kategorie;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public int getBaumarkt() {
        return baumarkt;
    }

    public void setBaumarkt(int baumarkt) {
        this.baumarkt = baumarkt;
    }

    @Override
    public String toString() {
        return bezeichnung;
    }
}
