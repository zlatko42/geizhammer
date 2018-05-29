package db;

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

    String url = String.format(
            "jdbc:sqlserver://geizhammer.database.windows.net:1433;database=geizhammerDB;user=Edmin@geizhammer;password=SQL16db_2018_req");


    public void createUserinDB(Benutzer b) {

        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String sql = "insert into tbl_Benutzer (BenID, Vorname, Nachname, Email, FKstand) values ('"+b.getBenID()+"', '"+b.getVorname()+"', '"+b.getNachname()+"', '"+b.getEmail()+"', '"+b.getFKstand()+"');";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {

                    System.out.println("User created!");

                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Benutzer getUserByEmail(String mail) {

        Connection connection = null;
        Benutzer b=null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM tbl_Benutzer where Email='"+mail+"'";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    int id = Integer.parseInt(resultSet.getString("BenID"));
                    String vorname = resultSet.getString("Vorname");
                    String nachname = resultSet.getString("Nachname");
                    String email = resultSet.getString("Email");
                    int fkstand = Integer.parseInt(resultSet.getString("FKstand"));


                    b = new Benutzer(id, vorname, nachname, email, fkstand);


                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }



}
