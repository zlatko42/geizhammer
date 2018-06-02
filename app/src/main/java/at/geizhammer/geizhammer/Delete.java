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

public class Delete extends AppCompatActivity {


    EditText ed_username;
    EditText ed_password;
    EditText ed_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    // method for delete button
    public void delete(View v) {

        ed_username = (EditText) findViewById(R.id.username);
        ed_password = (EditText) findViewById(R.id.password);
        ed_password2 = (EditText) findViewById(R.id.password2);

        String usernameT = ed_username.getText().toString();
        String passwordT = ed_password.getText().toString();
        String password2T = ed_password2.getText().toString();

        Queries q = new Queries();
        Benutzer b = q.getUserByEmail(usernameT);

        if (b != null && passwordT.equals(password2T)) {
            String text = "Account removed: " + usernameT;

            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();

            q.deleteUserinDB(b);

            Intent intent = new Intent(this, Start.class);
            startActivity(intent);


        } else {

            String text = "Removal failed, check Passwords";

            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();

        }

    }


}
