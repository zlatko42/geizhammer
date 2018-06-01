package model;

public class Packungseinheit {

    public Packungseinheit(int packID, String bezeichnung) {
        super();
        this.packID = packID;
        this.bezeichnung = bezeichnung;
    }

    private int packID;
    private String bezeichnung;

    public int getPackID() {
        return packID;
    }

    public void setPackID(int packID) {
        this.packID = packID;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

}
