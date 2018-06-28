package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        ArrayList<String> list = new ArrayList<>();

        for (Produkt p : produkte) {list.add(p.toString());}

        Set<String> diffList = new HashSet<>(list);
        list.clear();
        list.addAll(diffList);

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
        float sum = 0;
        float tempsum = 0;
        int tempc = 0;
        int count = 0;
        int currentBmkt = 0;
        int bestBmkt = 0;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(url);

            List<Integer> baumarktList = new ArrayList<>();
            for (Produkt p : Search.searchlist.getProdukte()){baumarktList.add(p.getBaumarkt()); }
            System.out.println("######   Searchlistsize: "+baumarktList.size());

            Set<Integer> diffBaumkt = new HashSet<>(baumarktList);
            baumarktList.clear();
            baumarktList.addAll(diffBaumkt);
            Collections.sort(baumarktList);
            System.out.println("#####   Sorted List.");

            for(Integer i:baumarktList)
            {
                for(Produkt p:Search.searchlist.getProdukte())
                {

                    if(p.getBaumarkt()==i)
                    {
                        currentBmkt = i;
                        count++;
                        sum+=p.getPreis();
                        System.out.println("#####  Loop run:" + i + "   :"+p.getBezeichnung());
                    }
                }
                if(tempc<count)
                {
                        tempc = count;
                        tempsum = sum;
                        bestBmkt = currentBmkt;
                }
                if(tempc==count)
                {
                    if(tempsum>sum)
                    {
                        tempc = count;
                        tempsum = sum;
                        bestBmkt = currentBmkt;
                    }
                }

                count = 0;
                sum=0;
            }

            // Create and execute a SELECT SQL statement.
            System.out.println("#####   Bester Baumarkt: "+bestBmkt);
            String selectSql = "SELECT Name FROM tbl_Baumaerkte where BaumID = "+bestBmkt;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {

                solution = resultSet.getString("Name"); // + " " + resultSet.getString("FKstand");
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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



