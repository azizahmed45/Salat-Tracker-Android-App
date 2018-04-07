package com.ahmed.aziz.salattracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public String[] salatNames;
    private ListView salatListView;
    private LinearLayout datePickerLayout;
    private DatePickerDialog.OnDateSetListener setDateListener;
    private TextView dayView;
    private TextView dateView;
    private TextView monthYearView;
    public static Date selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Date views
        dayView = (TextView) findViewById(R.id.day_view);
        dateView = (TextView) findViewById(R.id.date_view);
        monthYearView = (TextView) findViewById(R.id.mounth_year_view);

        //Time
        selectedDate = Calendar.getInstance().getTime();
        setDate();


        //date set listener
        setDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                selectedDate = new Date(year-1900, month, day);
                setDate();
            }
        };

        //Date picker
        datePickerLayout = (LinearLayout) findViewById(R.id.date_picker_layout);
        datePickerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = selectedDate.getDate();
                int month = selectedDate.getMonth();
                int year = selectedDate.getYear()+1900;

                Log.d("Test: ", " " + year + " " +  month + " "  + day);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, setDateListener, year, month, day);
                datePickerDialog.show();
            }
        });




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



    private void setDate(){
        SimpleDateFormat dayName = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthName = new SimpleDateFormat("MMMM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        dayView.setText(dayName.format(selectedDate));
        dateView.setText(dateFormat.format(selectedDate));
        monthYearView.setText(monthName.format(selectedDate) + ", " + yearFormat.format(selectedDate));
    }
}
