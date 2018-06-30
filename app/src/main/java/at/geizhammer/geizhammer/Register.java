package at.geizhammer.geizhammer;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import db.Queries;
import model.Benutzer;

public class Register extends AppCompatActivity {


    EditText ed_username;
    EditText ed_password;
    EditText ed_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    // method for register button
    public void register(View v) {

        ed_username = (EditText) findViewById(R.id.username);
        ed_password = (EditText) findViewById(R.id.password);
        ed_password2 = (EditText) findViewById(R.id.password2);

        String usernameT = ed_username.getText().toString();
        String passwordT = ed_password.getText().toString();
        String password2T = ed_password2.getText().toString();

        Queries q = new Queries();
        Benutzer b = q.getUserByEmail(usernameT);

        if (b == null) {

            if (passwordT.equals(password2T)) {
                String text = "Register successfull: " + usernameT;

                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();

                q.createUserinDB(new Benutzer((int) (Math.random() * 100), "~active", "~active", usernameT, 1, passwordT));

                Intent intent = new Intent(this, Start.class);
                startActivity(intent);

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {

            String text = "Register failed: Username already in use!";

            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();

        }

    }


}
