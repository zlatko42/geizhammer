package model;

public class Kategorie {

    public Kategorie(int catID, String bezeichnung, int scatID) {
        super();
        this.catID = catID;
        this.bezeichnung = bezeichnung;
        this.scatID = scatID;
    }

    private int catID;
    private String bezeichnung;
    private int scatID;

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getScatID() {
        return scatID;
    }

    public void setScatID(int scatID) {
        this.scatID = scatID;
    }

}
