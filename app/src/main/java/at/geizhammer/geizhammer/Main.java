package at.geizhammer.geizhammer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // navigate to search view
    public void to_search(View v) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
