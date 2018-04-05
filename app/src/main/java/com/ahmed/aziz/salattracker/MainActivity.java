package com.ahmed.aziz.salattracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public String[] salatNames;
    private ListView salatListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        salatNames = getResources().getStringArray(R.array.salat_names);
        salatListView = (ListView) findViewById(R.id.salat_listView);

        MyListAdapter adapter = new MyListAdapter(this, salatNames);
        salatListView.setAdapter(adapter);
    }
}
