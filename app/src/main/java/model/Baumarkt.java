
package model;

public class Baumarkt {

    public Baumarkt(int id, String name, String zusatz, int fkstand) {
        super();
        this.id = id;
        this.name = name;
        this.zusatz = zusatz;
        this.fkstand = fkstand;
    }

    private int id;
    private String name;
    private String zusatz;
    private int fkstand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZusatz() {
        return zusatz;
    }

    public void setZusatz(String zusatz) {
        this.zusatz = zusatz;
    }

    public int getFkstand() {
        return fkstand;
    }

    public void setFkstand(int fkstand) {
        this.fkstand = fkstand;
    }

}
