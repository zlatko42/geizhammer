package db;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

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


    public ResultSet execQuery(String sql)
    {
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
    }


    public void createUserinDB(Benutzer b) {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String sql = "insert into tbl_Benutzer (BenID, Vorname, Nachname, Email, FKstand) values ('" + b.getBenID() + "', '" + b.getVorname() + "', '" + b.getNachname() + "', '" + b.getEmail() + "', '" + b.getFKstand() + "');";


                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {

                    System.out.println("User created!");

                }
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Benutzer getUserByEmail (String mail){

            Connection connection;
            Benutzer b = null;

            try {
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


                    b = new Benutzer(id, vorname, nachname, email, fkstand);


                }
                connection.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return b;

        }
    }