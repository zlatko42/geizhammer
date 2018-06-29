package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Benutzer;

public class Queries {


    /*String url = String.format(
            "jdbc:sqlserver://geizhammer.database.windows.net:1433;database=geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30");
 */
    //Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
    String url = String.format(
            "jdbc:jtds:sqlserver://geizhammer.database.windows.net:1433/geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req");


   /* public ResultSet execQuery(String sql) {
        Connection connection = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection(url);
            //  System.out.println("con ok");

            Statement statement = connection.createStatement();
            // System.out.println("statement ok");
            rs = statement.executeQuery(sql);

            //System.out.println("result set received");
            // connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }*/

    public void deleteUserinDB(Benutzer b) {

        Connection connection = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            // Create and execute a SELECT SQL statement.
            String sql = "delete from tbl_Benutzer where Email = '" + b.getEmail() + "'";
            //String sql = "insert into tbl_Benutzer (Vorname, Nachname, Email, FKstand) values ('" + b.getVorname() + "', '" + b.getNachname() + "', '" + b.getEmail() + "', '" + b.getFKstand() + "')";
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sql) > 0) {
                System.out.println("User deleted!");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createUserinDB(Benutzer b) {

        Connection connection = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            // Create and execute a SELECT SQL statement.
            String sql = "insert into tbl_Benutzer (Vorname, Nachname, Email, FKstand, Passwort) values ('" + b.getVorname() + "', '" + b.getNachname() + "', '" + b.getEmail() + "', '" + b.getFKstand() + "', '" + b.getPassword()+"')";
            //String sql = "insert into tbl_Benutzer (Vorname, Nachname, Email, FKstand) values ('" + b.getVorname() + "', '" + b.getNachname() + "', '" + b.getEmail() + "', '" + b.getFKstand() + "')";
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sql) > 0) {
                System.out.println("User created!");
            }
            else{
                System.out.println("### Benutzername bereits vergeben ###");
                //return false;
            }
            connection.close();
            //return true;

        } catch (Exception e) {
            e.printStackTrace();
            //return false;
        }

    }

    public Benutzer getUserByEmail(String mail) {

        Connection connection;
        Benutzer b = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(url);
            // Create and execute a SELECT SQL statement.
            String selectSql = "select * from tbl_Benutzer where Email='" + mail + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("BenID"));
                String vorname = resultSet.getString("Vorname");
                String nachname = resultSet.getString("Nachname");
                String email = resultSet.getString("Email");
                int fkstand = Integer.parseInt(resultSet.getString("FKstand"));
                String password = resultSet.getString("Passwort");
                b = new Benutzer(id, vorname, nachname, email, fkstand, password);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}