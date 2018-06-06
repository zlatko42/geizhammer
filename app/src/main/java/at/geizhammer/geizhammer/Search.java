package at.geizhammer.geizhammer;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import model.Produkt;
import model.ProdukteList;

public class Search extends AppCompatActivity {

    AutoCompleteTextView ac_text;
    ListView lv;
    Produkt currentP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ac_text = (AutoCompleteTextView) findViewById(R.id.ac_text);
        lv = (ListView) findViewById(R.id.listView);


    }

    // method for bt_search
    public void start_search(final View v) {


        String input = ac_text.getText().toString();
        System.out.println(input);

        final ProdukteList list = new ProdukteList();
        list.getProdukteByName(input);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, list.listToString());

        lv.setAdapter(arrayAdapter);
        registerForContextMenu(lv);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                currentP = list.getProduktAt(position);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Artikel auf die Einkaufsliste setzen");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        if (item.getTitle() == "Artikel auf die Einkaufsliste setzen") {
            Toast.makeText(this, "Added: " + currentP.toString(), Toast.LENGTH_LONG).show();

        }

        return true;
    }
}
