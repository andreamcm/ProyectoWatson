package com.example.cesaraugustocordonbalcarcel.registrosmedicos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registros extends AppCompatActivity {

    Button nuevo, ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        nuevo = (Button)findViewById(R.id.btnNuevo);
        ingresar = (Button)findViewById(R.id.btnIngresar);

        nuevo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Registros.this, Nuevo.class);
                startActivity(intent);

            }
        });

        nuevo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Registros.this, Principal.class);
                startActivity(intent);

            }
        });
    }


}
