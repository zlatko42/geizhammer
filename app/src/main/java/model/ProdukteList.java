package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class ProdukteList {

    LinkedList<Produkt> produkte = new LinkedList<Produkt>();

    /*String url = String.format(
            "jdbc:sqlserver://geizhammer.database.windows.net:1433;database=geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");*/
    String url = String.format(
            "jdbc:jtds:sqlserver://geizhammer.database.windows.net:1433/geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req");

    public void add(Produkt p) {
        produkte.add(p);
    }

    public void clear() {
        produkte.clear();
    }

    public LinkedList<Produkt> getProdukte() {
        return produkte;
    }

    public void printListTest() {
        System.out.println("PID  Bezeichnung  Packungsgr  PackungsEH  Kategorie  Preis  Baumarkt");
        for (Produkt p : produkte) {
            System.out.println(p.getPid() + "     " + p.getBezeichnung() + "       " + p.getPackungsgr() + "   " + p.getPackungsart() + "   " + p.getKategorie()
                    + "   " + p.getPreis() + "   " + p.getBaumarkt());
        }


    }

    public ArrayList listToString() {

        ArrayList list = new ArrayList();

        for (Produkt p : produkte) {

            list.add(p.toString());

        }

        return list;
    }

    public LinkedList<Produkt> getProdukteByKategorie(int kategorie) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * " + "FROM tbl_Produkte where FKCat=" + kategorie;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("PID"));
                    String bezeichnung = resultSet.getString("Bezeichnung");
                    int packungsgr = Integer.parseInt(resultSet.getString("Packungsgroeße"));
                    int packungsart = Integer.parseInt(resultSet.getString("FKPack"));
                    int kat = Integer.parseInt(resultSet.getString("FKCat"));
                    float preis = Float.parseFloat(resultSet.getString("Preis"));
                    int baumarkt = Integer.parseInt(resultSet.getString("FKbaum"));

                    Produkt p = new Produkt(id, bezeichnung, packungsgr, packungsart, kat, preis, baumarkt);
                    produkte.add(p);


                }
                connection.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
        return produkte;
    }

    public LinkedList<Produkt> getProdukteByName(String search) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM tbl_Produkte where Bezeichnung like '" + search + "%'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("PID"));
                    String bezeichnung = resultSet.getString("Bezeichnung");
                    int packungsgr = Integer.parseInt(resultSet.getString("Packungsgroeße"));
                    int packungsart = Integer.parseInt(resultSet.getString("FKPack"));
                    int kat = Integer.parseInt(resultSet.getString("FKCat"));
                    float preis = Float.parseFloat(resultSet.getString("Preis"));
                    int baumarkt = Integer.parseInt(resultSet.getString("FKbaum"));

                    Produkt p = new Produkt(id, bezeichnung, packungsgr, packungsart, kat, preis, baumarkt);
                    System.out.println("+++++++++++++++++++++++++++++++++++++ " + p.toString());
                    produkte.add(p);


                }
                connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produkte;
    }


    public void pushSQLStatement(String sql) {

    }


}
