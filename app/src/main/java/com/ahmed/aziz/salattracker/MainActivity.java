package com.ahmed.aziz.salattracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public String[] salatNames;
    private ListView salatListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Time





        //Salat list
        salatNames = getResources().getStringArray(R.array.salat_names);
        //Salat name set
        Salat[] salat = new Salat[5];
        for(int i=0; i<5; i++){
            salat[i] = new Salat();
            salat[i].setName(salatNames[i]);
        }

        salatListView = (ListView) findViewById(R.id.salat_listView);
        MyListAdapter adapter = new MyListAdapter(this, salat);
        salatListView.setAdapter(adapter);
    }
}
