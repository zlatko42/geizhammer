package at.geizhammer.geizhammer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    // method for login button
    public void buttonLogin(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    // method for next button
    public void buttonNext(View view) {
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }


}
