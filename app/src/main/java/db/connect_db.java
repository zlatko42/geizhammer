package db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Benutzer;

public class connect_db {

    public static void main(String[] args) throws SQLException {

        //Benutzer b = new Benutzer(17, "Testmongo 1", "McTestFace", "test@testitest.test", 1);

        String selectSql = "select * from tbl_Benutzer where Email='test@testitest.test'";


        Queries q = new Queries();
        ResultSet resultSet = q.execQuery(selectSql);
        //System.out.println("resultset defined");
        //       b = q.getUserByEmail("test@testitest.test");	//++++WORKS
        //  System.out.println(b.toString());

        Benutzer b = null;


        while (resultSet.next()) {

            int id = Integer.parseInt(resultSet.getString("BenID"));
            String vorname = resultSet.getString("Vorname");
            String nachname = resultSet.getString("Nachname");
            String email = resultSet.getString("Email");
            int fkstand = Integer.parseInt(resultSet.getString("FKstand"));


            b = new Benutzer(id, vorname, nachname, email, fkstand);
        }

        //System.out.println(b.toString()+"      ------>    rs correct!");

        //ProdukteList produkte = new ProdukteList();

        // produkte.getProdukteByKategorie(1); ++++WORKS
        //produkte.getProdukteByName("Schraub"); // ++++WORKS

        //produkte.printListTest();

        //q.createUserinDB(b); ++++WORKS
    }

}

//--------------- old Test Code --------------------------
// Connect to database
/*
 * String url = String.format(
 * "jdbc:sqlserver://geizhammer.database.windows.net:1433;database=geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
 * );
 *
 *
 * Connection connection = null;
 *
 * try { connection = DriverManager.getConnection(url); String schema =
 * connection.getSchema(); System.out.println("Successful connection - Schema: "
 * + schema);
 *
 * System.out.println("Query data example:");
 * System.out.println("=========================================");
 *
 * // Create and execute a SELECT SQL statement. String selectSql = "SELECT * "
 * + "FROM tbl_Standorte";
 *
 * try (Statement statement = connection.createStatement(); ResultSet resultSet
 * = statement.executeQuery(selectSql)) {
 *
 * // Print results from select statement
 * System.out.println("Result of SQL Query:"); while (resultSet.next()) {
 * System.out.println(resultSet.getString(1) + " " + resultSet.getString(2)+
 * " "+ resultSet.getString(3) + " "+ resultSet.getString(4) + " "+
 * resultSet.getString(5)); } connection.close(); } } catch (Exception e) {
 * e.printStackTrace(); }
 */