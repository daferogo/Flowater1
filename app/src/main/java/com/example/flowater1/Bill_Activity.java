package com.example.flowater1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill_Activity extends AppCompatActivity {

    Spinner spinner1;
    final String[] datos =
            new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_);

        spinner1 = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);

        spinner1.setAdapter(adaptador);
        Date d = new Date();
        CharSequence s = DateFormat.format("MMMM d, yyyy ", d.getTime());

    }
}
