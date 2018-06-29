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

import java.util.ArrayList;

import model.Einkaufsliste;
import model.Produkt;
import model.ProdukteList;

public class Search extends AppCompatActivity {

    AutoCompleteTextView ac_text;
    ListView lv;
    Produkt currentP;
    public static Einkaufsliste einkaufsliste = new Einkaufsliste(1);
    public static ProdukteList searchlist = new ProdukteList();
    ProdukteList list = new ProdukteList();
    ArrayList output = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ac_text = (AutoCompleteTextView) findViewById(R.id.ac_text);


    }


    // method for bt_search
    public void start_search(final View v) {

        lv = (ListView) findViewById(R.id.listView);

        String input = ac_text.getText().toString();
        System.out.println("  ---  Textinput: "+input);


        if (input.length() > 2) {
            list.clear();
            list.getProdukteByName(input);

            output = list.listToStringUnique();

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1, output);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                    view.setSelected(true);
                    //currentP = list.getProduktAt(position);
                    currentP = list.getFirstProduktfromListbyBez(output.get(position).toString());

                    registerForContextMenu(lv);
                }

            });

            lv.setAdapter(arrayAdapter);


        }
        else{
            Toast.makeText(this, "Suchbegriff zu kurz!", Toast.LENGTH_LONG).show();
        }
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

            einkaufsliste.getProdukte().add(currentP);

            unregisterForContextMenu(lv);

            for(Produkt p:list.getProdukte())
            {
                if(currentP.getBezeichnung().equals(p.getBezeichnung()))
                {
                    searchlist.add(p);
                    System.out.println("Searchlist: "+p.toString());
                }
            }

        }


        return true;
    }
}
