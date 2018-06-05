package at.geizhammer.geizhammer;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.LinkedList;

import model.ProdukteList;

public class Search extends AppCompatActivity {

    AutoCompleteTextView ac_text;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    // method for bt_search
    public void start_search(View v) {
        ac_text = (AutoCompleteTextView) findViewById(R.id.ac_text);
        lv = (ListView) findViewById(R.id.listView);

        String input = ac_text.getText().toString();
        System.out.println(input);

        ProdukteList list = new ProdukteList();
        LinkedList output = list.getProdukteByName(input);

        //list.printListTest();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, list.listToString());

        lv.setAdapter(arrayAdapter);

    }


}
