package com.example.cesaraugustocordonbalcarcel.registrosmedicos;

import android.content.Intent;
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
        leerqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, QRReader.class);
                startActivity(intent);
            }
        });
        nuevop = (Button) findViewById(R.id.nuevop);
        nuevop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, NuevoP.class);
                startActivity(intent);
            }
        });
        estad = (Button) findViewById(R.id.estad);
        estad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Estadisticas.class);
                startActivity(intent);
            }
        });
        registro = (Button) findViewById(R.id.registro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Grabar.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
