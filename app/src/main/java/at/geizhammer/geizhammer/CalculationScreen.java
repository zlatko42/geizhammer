package at.geizhammer.geizhammer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import model.Produkt;

public class CalculationScreen extends AppCompatActivity {


    EditText ed_solution;

    ListView lv;
    Produkt currentP;
    int index = 0;
    ArrayAdapter<String> arrayAdapter;
    String solution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcalculation);
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        ed_solution = (EditText) findViewById(R.id.ed_solution);

        lv = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, Search.einkaufsliste.listToString());
    //Search.einkaufsliste.getProdukte().listToString()
        lv.setAdapter(arrayAdapter);

        solution = Search.searchlist.calculateBaumarkt();

        ed_solution.setText(solution);



    }


    // method for bt_saveScreen
    public void savescreen(final View v) {

        //System.out.println("--------- TODO -----------");
       // Toast.makeText(this, "No Connection - Server Maintenance ", Toast.LENGTH_LONG).show();

        // tackeAndSaveScreenShot(this);
        writeListToTxtFile();

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


    public void writeListToTxtFile()
    {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this, permissions, 110);
        }

        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            OutputStream fOut = null;

            String name = "GEIZHAMMER";
            File file = new File(path, name + ".txt");
            fOut = new FileOutputStream(file);

            fOut.write(" +++ GEIZHAMMER SHOPPING LIST +++ \n".getBytes());
            for (Object o : Search.einkaufsliste.listToString()) {
                fOut.write((o.toString() + "\n").getBytes());
            }
            fOut.write(" \n".getBytes());

            fOut.write(("Günstigster Baumarkt: " + solution).getBytes());
            fOut.flush();
            fOut.close();

            Toast.makeText(getBaseContext(), "File saved in: " + path,
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
