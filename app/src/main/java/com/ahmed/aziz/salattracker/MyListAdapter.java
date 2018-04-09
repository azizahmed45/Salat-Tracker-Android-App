package com.ahmed.aziz.salattracker;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    TextView salatName;
    ToggleButton prayedButton;
    ToggleButton prayedLateButton;
    ToggleButton missedButton;
    final DB db = new DB(context);



    public MyListAdapter(@NonNull Context context, Salat[] salat) {
        super(context, R.layout.salat_listview, salat);
        this.context = (Activity) context;
        this.salat = salat;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.salat_listview, null, true);

        salatName = (TextView) listview.findViewById(R.id.salat_name);
        prayedButton = (ToggleButton) listview.findViewById(R.id.prayed_button);
        prayedLateButton = (ToggleButton) listview.findViewById(R.id.prayed_late_button);
        missedButton = (ToggleButton) listview.findViewById(R.id.missed_button);

        salatName.setText(getItem(position).getName());


        prayedButton.setOnCheckedChangeListener(buttonListener);
        prayedLateButton.setOnCheckedChangeListener(buttonListener);
        missedButton.setOnCheckedChangeListener(buttonListener);



        prayedButton.setTag(position);
        prayedLateButton.setTag(position);
        missedButton.setTag(position);


        return listview;
    }

    CompoundButton.OnCheckedChangeListener buttonListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(compoundButton.getId() == prayedButton.getId()){
                compoundButton.setChecked(b);
                prayedLateButton.setChecked(!b);
                missedButton.setChecked(!b);
            }
            else if(compoundButton.getId() == prayedLateButton.getId()){
                compoundButton.setChecked(b);
                prayedButton.setChecked(!b);
                missedButton.setChecked(!b);
            }
            else if(compoundButton.getId() == missedButton.getId()){
                compoundButton.setChecked(b);
                prayedButton.setChecked(!b);
                prayedLateButton.setChecked(!b);
            }
        }
    };
}
