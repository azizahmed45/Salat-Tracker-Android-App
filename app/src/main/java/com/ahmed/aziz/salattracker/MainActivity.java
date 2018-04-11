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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public String[] salatNames;
    private ListView salatListView;
    private LinearLayout datePickerLayout;
    private DatePickerDialog.OnDateSetListener setDateListener;
    private TextView dayView;
    private TextView dateView;
    private TextView monthYearView;
    public static Date selectedDate;
    private Salat[] salat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salat = new Salat[5];

        //Date views
        dayView = (TextView) findViewById(R.id.day_view);
        dateView = (TextView) findViewById(R.id.date_view);
        monthYearView = (TextView) findViewById(R.id.mounth_year_view);

        //Time
        GregorianCalendar calendar = new GregorianCalendar(Calendar.getInstance().getTime().getYear() + 1900,
                Calendar.getInstance().getTime().getMonth(), Calendar.getInstance().getTime().getDate());
        selectedDate = new Date(calendar.getTimeInMillis());
        setDate();

        //date set listener
        setDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                GregorianCalendar calendar = new GregorianCalendar(year, month, day);
                if(new Date(calendar.getTimeInMillis()).after(Calendar.getInstance().getTime())){
                    Toast.makeText(MainActivity.this, "You can't set Future date",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    selectedDate = new Date(calendar.getTimeInMillis());
                    setDate();
                    setList();
                }

            }
        };

        //Date picker
        datePickerLayout = (LinearLayout) findViewById(R.id.date_picker_layout);
        datePickerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = selectedDate.getDate();
                int month = selectedDate.getMonth();
                int year = selectedDate.getYear() + 1900;

                Log.d("Test: ", " " + year + " " +  month + " "  + day);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        setDateListener, year, month, day);
                datePickerDialog.show();
            }
        });




        //Salat list
        salatNames = getResources().getStringArray(R.array.salat_names);
        //Salat name set
        for(int i=0; i<5; i++){
            salat[i] = new Salat();
            salat[i].setName(salatNames[i]);
        }
        salatListView = (ListView) findViewById(R.id.salat_listView);
        setList();

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

    private void setList(){
        MyListAdapter adapter = new MyListAdapter(this, salat);
        salatListView.setAdapter(adapter);
    }
}
