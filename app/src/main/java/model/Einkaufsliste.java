package model;

import java.sql.Date;
import java.util.ArrayList;

public class Einkaufsliste {

    private int lid;
    private Date datum;
    private int fKben;
    public ProdukteList produkte = new ProdukteList();




    public ProdukteList getProdukte() {
        return produkte;
    }

    public void setProdukte(ProdukteList produkte) {
        this.produkte = produkte;
    }



    public Einkaufsliste(int fKben) {
        super();
        this.fKben=fKben;
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

    public ArrayList listToString() {

        return produkte.listToString();
    }


}
