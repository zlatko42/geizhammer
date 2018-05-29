package model;

public class Standort {

    public Standort(int stid, String straße, int plz, String ort, String gps) {
        super();
        this.stid = stid;
        this.straße = straße;
        this.plz = plz;
        this.ort = ort;
        this.gps = gps;
    }

    private int stid;
    private String straße;
    private int plz;
    private String ort;
    private String gps;

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

}
