package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class adminpanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);

        Button add = findViewById(R.id.add);
        Button update=findViewById(R.id.update);
        Button delete=findViewById(R.id.delete);
        Button view = findViewById(R.id.view);
        Button end = findViewById(R.id.logout);
        DBHelper DB = new DBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddBusActivity.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), updatebus.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), deletebus.class);
                startActivity(intent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = DB.viewbuses();
                if (result.getCount()==0){
                    Toast.makeText(adminpanel.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("ID :"+result.getString(0)+"\n");
                    buffer.append("Departure :"+result.getString(1)+"\n");
                    buffer.append("Arrival :"+result.getString(2)+"\n");
                    buffer.append("Date :"+result.getString(3)+"\n");
                    buffer.append("Total Seats :"+result.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(adminpanel.this);
                builder.setCancelable(true);
                builder.setTitle("ALL BUSES DETAILS");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

            }
        }

