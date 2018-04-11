package com.ahmed.aziz.salattracker;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Date;

public class MyListAdapter extends ArrayAdapter<Salat>{

    private Activity context;
    private Salat[] salat;
    private TextView salatName;
    private ToggleButton[] prayedButton = new ToggleButton[5];
    private ToggleButton[] prayedLateButton = new ToggleButton[5];
    private ToggleButton[] missedButton = new ToggleButton[5];
    int[] options;
    private final DB db;



    public MyListAdapter(@NonNull Context context, Salat[] salat) {
        super(context, R.layout.salat_listview, salat);
        this.context = (Activity) context;
        this.salat = salat;

        db = new DB(context);
        options = db.getRow(MainActivity.selectedDate);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.salat_listview, null, true);

        salatName = (TextView) listview.findViewById(R.id.salat_name);
        prayedButton[position] = (ToggleButton) listview.findViewById(R.id.prayed_button);
        prayedLateButton[position] = (ToggleButton) listview.findViewById(R.id.prayed_late_button);
        missedButton[position] = (ToggleButton) listview.findViewById(R.id.missed_button);

        salatName.setText(getItem(position).getName());

        salat[position].setOption(options[position]);
        //Set Button Options
        if(salat[position].getOption() == 1){
            prayedButton[position].setChecked(true);
        }
        else if(salat[position].getOption() == 2){
            missedButton[position].setChecked(true);
        }
        else if(salat[position].getOption() == 3){
            prayedLateButton[position].setChecked(true);
        }


        prayedButton[position].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    salat[position].setOption(1);
                    db.entry(salat[position], MainActivity.selectedDate);

                    prayedLateButton[position].setChecked(!b);
                    missedButton[position].setChecked(!b);
                }
                else{
                    salat[position].setOption(0);
                    db.entry(salat[position], MainActivity.selectedDate);
                }
            }
        });

        missedButton[position].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    salat[position].setOption(2);
                    db.entry(salat[position], MainActivity.selectedDate);

                    prayedButton[position].setChecked(!b);
                    prayedLateButton[position].setChecked(!b);
                }
                else{
                    salat[position].setOption(0);
                    db.entry(salat[position], MainActivity.selectedDate);
                }
            }
        });

        prayedLateButton[position].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    salat[position].setOption(3);
                    db.entry(salat[position], MainActivity.selectedDate);

                    prayedButton[position].setChecked(!b);
                    missedButton[position].setChecked(!b);
                }
                else{
                    salat[position].setOption(0);
                    db.entry(salat[position], MainActivity.selectedDate);
                }
            }
        });

        return listview;
    }

}
