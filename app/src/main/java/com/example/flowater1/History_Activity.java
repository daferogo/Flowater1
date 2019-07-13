package com.example.flowater1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class History_Activity extends AppCompatActivity {

    private adaptadorConsumo adapter;
    private List<Prediccion> listaConsumo;

    DatabaseReference dbconsumo;

    String date_time = "";
    String date_time_print = "";
    String srtfecha = "";
    String srtfecha2 = "m3";

    String anio = "";
    String mes = "";
    String dia = "";
    String hora = "";
    String min = "";

    String aniof = "";
    String mesf = "";
    String diaf = "";
    String horaf = "";
    String minf = "";

    String init_Date = "";
    String final_Date = "";

    long fechaCompIni = 0;
    long fechaCompFin = 0;

    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int mMinute;
    int dateFlag;

    EditText tv_show_date_time;
    EditText final_date_time;
    Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_);


        tv_show_date_time = (EditText) findViewById(R.id.tv_show_date_time);
        final_date_time = (EditText) findViewById(R.id.final_date_time);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.lstPredicciones);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listaConsumo = new ArrayList<>();;
        adapter = new adaptadorConsumo(this,listaConsumo);
        recycler.setAdapter(adapter);

        dbconsumo = FirebaseDatabase.getInstance().getReference()
                        .child("Consumo2");

        // fecha inicial
        tv_show_date_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker();
                dateFlag = 1;
            }
        });

        // fecha final
        final_date_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker();
                dateFlag = 2;
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query query1 = FirebaseDatabase.getInstance().getReference()
                        .child("Consumo2")
                        .orderByChild("fecha")
                        //.orderByKey()
                        .startAt(init_Date)
                        .endAt(final_Date);

                query1.addListenerForSingleValueEvent(valueEventListener);

              /*  final FirebaseRecyclerAdapter mAdapter =
                       new FirebaseRecyclerAdapter<Prediccion, PrediccionHolder>(
                                Prediccion.class, R.layout.item_lista, PrediccionHolder.class, dbconsumo) {

                            @Override
                            public void populateViewHolder(PrediccionHolder predViewHolder, Prediccion pred, int position) {

                                //2019-06-18-03:40
                                srtfecha = pred.getFecha();
                                anio = srtfecha.substring(0,4);
                                mes = srtfecha.substring(5,7);
                                dia = srtfecha.substring(8,10);
                                hora = srtfecha.substring(11,13);
                                min = srtfecha.substring(14,16);
                                fechaCompIni = Long.parseLong(anio+mes+dia+hora+min);

                                if ((fechaCompIni >= init_Date)  && (fechaCompIni <= final_Date)) {

                                    predViewHolder.setFecha(pred.getFecha());
                                    predViewHolder.setConsumo(pred.getConsumo());
                                }
                            }
                        };
                recycler.setAdapter(mAdapter);
                */

            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            listaConsumo.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Prediccion dato = snapshot.getValue(Prediccion.class);
                    listaConsumo.add(dato);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void datePicker() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        //date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        date_time = Integer.toString(year)+String.format("%02d",(monthOfYear+1))+String.format("%02d",dayOfMonth);
                        date_time_print = Integer.toString(year)+ "-" +String.format("%02d",(monthOfYear+1))+ "-" +String.format("%02d",dayOfMonth);
                        //*************Call Time Picker Here ********************
                        //tv_show_date_time.setText(date_time);
                        tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        //tv_show_date_time.setText(date_time+" "+hourOfDay + ":" + minute);
                        date_time = date_time+String.format("%02d",hourOfDay)+String.format("%02d",minute);
                        date_time_print = date_time_print+"-"+String.format("%02d",hourOfDay) + ":" + String.format("%02d",minute);
                        if(dateFlag == 1)
                        {
                        tv_show_date_time.setText(date_time_print);
                        //init_Date = Long.parseLong(date_time);
                            init_Date = date_time_print;
                        }
                        if(dateFlag == 2)
                        {
                            final_date_time.setText(date_time_print);
                            //final_Date = Long.parseLong(date_time);
                            final_Date = date_time_print;
                        }

                    }
                }, mHour, mMinute,true);
        timePickerDialog.show();
    }

}
