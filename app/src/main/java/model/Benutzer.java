package model;

public class Benutzer {

    String url = String.format(
            "jdbc:sqlserver://geizhammer.database.windows.net:1433;database=geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");


    public Benutzer(int benID, String vorname, String nachname, String email, int FKstand) {
        super();
        this.benID = benID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.FKstand = FKstand;
    }

    private int benID;
    private String vorname;
    private String nachname;
    private String email;
    private String password;
    private int FKstand;

    public Benutzer(String vorname, String nachname, String email, int FKstand) {
        super();
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.FKstand = FKstand;
    }

    public int getBenID() {
        return benID;
    }

    public void setBinID(int benID) {
        this.benID = benID;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFKstand() {
        return FKstand;
    }

    public void setFKstand(int FKstand) {
        this.FKstand = FKstand;
    }

    public String toString() {
        return benID + "  " + vorname + "  " + nachname + "  " + email + "  " + FKstand;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
