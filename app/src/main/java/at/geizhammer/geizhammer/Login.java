package at.geizhammer.geizhammer;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import db.Queries;
import model.Benutzer;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // method for login button
    public void login(View v){


            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Queries q = new Queries();
            Benutzer b = null;
            b = q.getUserByEmail("test@testitest.test");    //++++WORKS
            System.out.println(b.toString());


    }

    // method for register button
    public void register(View v){

    }
}
