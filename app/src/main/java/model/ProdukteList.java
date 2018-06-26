package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import at.geizhammer.geizhammer.Search;

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

    public Produkt getProduktAt(int index) {
        return produkte.get(index);
    }

    public void removeProduktAt(int index) {
        produkte.remove(index);
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
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
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
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
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

    public String calculateBaumarkt()
    {
        String solution = "nix";
        Connection connection = null;
        int max = 0;
        int temp = 0;

        //try {
           // Class.forName("net.sourceforge.jtds.jdbc.Driver");
           // connection = DriverManager.getConnection(url);
            List<Integer> baumarktList = new ArrayList<>();
            for (Produkt p : Search.searchlist.getProdukte()){baumarktList.add(p.getBaumarkt()); }


        System.out.println("Highest occured Baumarkt: "+mostCommon(baumarktList));


            // Create and execute a SELECT SQL statement.
            /*String selectSql = "SELECT * FROM tbl_Produkte where Bezeichnung like '" + search + "%'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                solution = resultSet.getString(0);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return solution;
    }


    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }

    }



