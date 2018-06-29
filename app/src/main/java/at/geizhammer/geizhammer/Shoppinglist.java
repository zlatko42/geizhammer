package at.geizhammer.geizhammer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import model.Produkt;

public class Shoppinglist extends AppCompatActivity {

    ListView lv;
    Produkt currentP;
    int index = 0;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showshopping);
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        lv = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, Search.einkaufsliste.getProdukte().listToString());

        lv.setAdapter(arrayAdapter);

        //registerForContextMenu(lv);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);
                index = position;
                currentP = Search.einkaufsliste.getProdukte().getProduktAt(position);
                registerForContextMenu(lv);

            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Artikel von der Einkaufsliste entfernen");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        if (item.getTitle() == "Artikel von der Einkaufsliste entfernen") {
            Toast.makeText(this, "Removed: " + currentP.toString(), Toast.LENGTH_LONG).show();

            Search.einkaufsliste.getProdukte().removeProduktAt(index);
            Search.searchlist.removeProduktByBez(currentP.getBezeichnung());


            arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1, Search.einkaufsliste.getProdukte().listToString());
            lv.setAdapter(arrayAdapter);
            unregisterForContextMenu(lv);

        }

        return true;
    }
    // method for bt_calculate
    public void calculate(final View v) {

        Intent intent = new Intent(this, CalculationScreen.class);
        startActivity(intent);


    }

    // method for bt_saveScreen
    public void savescreen(final View v) {

               tackeAndSaveScreenShot(this);

    }

    public void tackeAndSaveScreenShot(Activity mActivity) {

        View MainView = mActivity.getWindow().getDecorView();
        MainView.setDrawingCacheEnabled(true);
        MainView.buildDrawingCache();
        Bitmap MainBitmap = MainView.getDrawingCache();
        Rect frame = new Rect();

        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //to remove statusBar from the taken sc
        int statusBarHeight = frame.top;
        //using screen size to create bitmap
        int width = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        int height = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap OutBitmap = Bitmap.createBitmap(MainBitmap, 0, statusBarHeight, width, height - statusBarHeight);
        MainView.destroyDrawingCache();
        try {

            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};


            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted

                ActivityCompat.requestPermissions(this,permissions, 110);


            }

            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            //you can also using current time to generate name
            String name="YourName";
            File file = new File(path, name + ".png");
            fOut = new FileOutputStream(file);

            OutBitmap.compress(Bitmap.CompressFormat.PNG, 90, fOut);
            fOut.flush();
            fOut.close();

            //this line will add the saved picture to gallery
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());

            Toast.makeText(this, "Saved in Gallery! ", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
