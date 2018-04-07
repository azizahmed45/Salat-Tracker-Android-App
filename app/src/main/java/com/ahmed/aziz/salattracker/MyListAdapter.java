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
import android.widget.TextView;

import java.util.Date;

public class MyListAdapter extends ArrayAdapter<Salat>{

    private Activity context;
    private Salat[] salat;

    public MyListAdapter(@NonNull Context context, Salat[] salat) {
        super(context, R.layout.salat_listview, salat);
        this.context = (Activity) context;
        this.salat = salat;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.salat_listview, null, true);

        TextView salatName = (TextView) listview.findViewById(R.id.salat_name);
        Button prayedButton = (Button) listview.findViewById(R.id.prayed_button);
        Button prayedLate = (Button) listview.findViewById(R.id.prayed_late_button);
        Button missedButton = (Button) listview.findViewById(R.id.missed_button);

        salatName.setText(getItem(position).getName());

        final DB db = new DB(context);

        prayedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItem(position).setOption(1);
                db.entry(getItem(position), MainActivity.selectedDate);
            }
        });

        return listview;
    }
}
