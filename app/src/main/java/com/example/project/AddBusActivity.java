package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddBusActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    private EditText busid, from, to, dt, seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbus);

        busid = findViewById(R.id.id);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        dt = findViewById(R.id.date);
        seats = findViewById(R.id.seats);
        Button addbus = findViewById(R.id.addbus);
        Button back1 = findViewById(R.id.button2);
        DBHelper DB = new DBHelper(this);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };


        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddBusActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = busid.getText().toString();
                String departure = from.getText().toString();
                String arrival = to.getText().toString();
                String date = dt.getText().toString();
                String total_seats = seats.getText().toString();

                if (id.equals("") || departure.equals("") || arrival.equals("") || date.equals("") || total_seats.equals("")) {
                    Toast.makeText(AddBusActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkid = DB.checkid(id);
                    if (checkid == false) {
                        Boolean insert = DB.insertBus(id, departure, arrival, date, total_seats);
                        if (insert == true) {
                            Toast.makeText(AddBusActivity.this, "Bus has been added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), adminpanel.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(AddBusActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddBusActivity.this, "ID already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adminpanel.class);
                startActivity(intent);
            }
        });

    }

    private void updateDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dt.setText(sdf.format(myCalendar.getTime()));
    }
}