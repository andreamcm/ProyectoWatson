package com.example.cesaraugustocordonbalcarcel.registrosmedicos;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class Grabar extends AppCompatActivity implements View.OnClickListener{

    private TextView resultT;
    private Button analizar;
    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar);

        resultT = (TextView)findViewById(R.id.lblResultado);
        analizar = (Button)findViewById(R.id.btnAnalizar);

        analizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intento = new Intent(Grabar.this, Codigo.class);
                intento.putExtra("diagnostico", resultT.getText().toString());
                startActivity(intento);
            }
        });
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.imageButton6){
            TextView result = (TextView)findViewById(R.id.lblResultado);
            promptSpeechInput();
        }
    }

    public void promptSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga algo");

        try {
            startActivityForResult(intent, 100);
        }
        catch(ActivityNotFoundException a){
            Toast.makeText(Grabar.this, "Lo sentimos, su no puede utilizar la aplicacion con su telefono.", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int code, int resultado, Intent intent){
        super.onActivityResult(code, resultado, intent);

        switch(code){
            case 100:
                if(resultado == RESULT_OK && intent != null){
                    ArrayList<String> resultadito = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    resultT.setText(resultadito.get(0));
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {

    }
}