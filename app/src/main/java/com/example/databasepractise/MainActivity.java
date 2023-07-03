package com.example.databasepractise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText ETDescription, ETDate;
    Button btnInsert,btnGetTasks;
    TextView tvResults;
    ArrayList<String> Stringlist;
    ArrayAdapter<String> ArrayAdapter;
    ListView listv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETDescription = findViewById(R.id.ETDescription);
        ETDate = findViewById(R.id.ETDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        listv = findViewById(R.id.lv);
        Stringlist = new ArrayList<String>();
        ArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Stringlist);
        listv.setAdapter(ArrayAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String desc = ETDescription.getText().toString();
                String date = ETDate.getText().toString();
                String ArrayInfo = desc+"\n"+date;
                Stringlist.add((Stringlist.size()+1)+"\n"+ArrayInfo);
                ArrayAdapter.notifyDataSetChanged();

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(desc, date);

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
            }
        });

    }
}