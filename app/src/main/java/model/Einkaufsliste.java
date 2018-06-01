package model;

import java.sql.Date;

public class Einkaufsliste {

    private int lid;
    private Date datum;
    private int fKben;

    public Einkaufsliste(int lid, Date datum, int fKben) {
        super();
        this.lid = lid;
        this.datum = datum;
        this.fKben = fKben;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getFKben() {
        return fKben;
    }

    public void setFKben(int fKben) {
        this.fKben = fKben;
    }

}
