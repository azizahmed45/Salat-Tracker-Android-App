package com.ahmed.aziz.salattracker;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String>{

    private String[] salatNames;
    private Activity context;
    public MyListAdapter(@NonNull Context context, String[] salatNames) {
        super(context, R.layout.salat_listview, salatNames);

        this.salatNames = salatNames;
        this.context = (Activity) context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.salat_listview, null, true);

        TextView salatName = (TextView) listview.findViewById(R.id.salat_name);
        Button prayedButton = (Button) listview.findViewById(R.id.prayed_button);
        Button prayedLate = (Button) listview.findViewById(R.id.prayed_late_button);
        Button missedButton = (Button) listview.findViewById(R.id.missed_button);

        salatName.setText(salatNames[position]);

        return listview;
    }
}
