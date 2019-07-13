package com.example.flowater1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private static final String TAGLOG = "firebase-db";

    TextView tvconsumo;
    Switch simpleSwitch;
    Button historial;
    Button factura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvconsumo = (TextView) findViewById(R.id.tv_Consumo);
        simpleSwitch = (Switch) findViewById(R.id.switch1);
        historial = (Button) findViewById(R.id.button1);
        factura = (Button) findViewById(R.id.button2);


        DatabaseReference dbFlowater =
                FirebaseDatabase.getInstance().getReference()
                        .child("ConsumoTotal");

        // Read from the database
        dbFlowater.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                String value =  dataSnapshot.getValue().toString();
                Log.d(TAGLOG, "Value is: " + value);
                tvconsumo.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAGLOG, "Failed to read value.", error.toException());
                tvconsumo.setText("Error");
            }
        });

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DatabaseReference dbvalvula =
                        FirebaseDatabase.getInstance().getReference()
                                .child("ControlValvula");
                if ( isChecked )
                {
                    dbvalvula.setValue(1);
                }
                else
                {
                    dbvalvula.setValue(0);
                }
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hist = new Intent(getApplicationContext(), History_Activity.class);
                //pes.putExtra(EXTRA_Pesos, (Serializable) pesos);
                //pes.putExtra(EXTRA_Pesosq, (Serializable) pesosq);
                //pes.putExtra(EXTRA_Fechas, (Serializable) fechas);
                startActivity(hist);
            }
        });

        factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fact = new Intent(getApplicationContext(), Bill_Activity.class);
                //pes.putExtra(EXTRA_Pesos, (Serializable) pesos);
                //pes.putExtra(EXTRA_Pesosq, (Serializable) pesosq);
                //pes.putExtra(EXTRA_Fechas, (Serializable) fechas);
                startActivity(fact);
            }
        });





    }


}


