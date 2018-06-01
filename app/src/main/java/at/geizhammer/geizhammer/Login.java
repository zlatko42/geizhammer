package at.geizhammer.geizhammer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;

import db.Queries;
import model.Benutzer;

public class Login extends AppCompatActivity {



    EditText ed_username;
    EditText ed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    // method for login button
    public void login(View v){

        ed_username = (EditText) findViewById(R.id.username);
        // ed_password = (EditText) findViewById(R.id.password);

        final String usernameT = ed_username.getText().toString();
        System.out.println(" -------- Username Field: "+usernameT);

        Benutzer b;

        Queries q = new Queries();
        b = q.getUserByEmail(usernameT);

        if(b != null)
        {
            String text = "Login successfull: "+b.getEmail();

            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();


        }
        else{

            String text = "Login failed!!";

            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();

        }




    }

    // method for register button
    public void register(View v) {

    }



}
