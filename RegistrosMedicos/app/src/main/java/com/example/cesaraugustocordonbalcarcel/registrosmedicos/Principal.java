package com.example.cesaraugustocordonbalcarcel.registrosmedicos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    Button leerqr, nuevop, estad, registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        leerqr = (Button) findViewById(R.id.leerqr);
        leerqr.setOnClickListener(this);
        nuevop = (Button) findViewById(R.id.nuevop);
        nuevop.setOnClickListener(this);
        estad = (Button) findViewById(R.id.estad);
        estad.setOnClickListener(this);
        registro = (Button) findViewById(R.id.registro);
        registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}