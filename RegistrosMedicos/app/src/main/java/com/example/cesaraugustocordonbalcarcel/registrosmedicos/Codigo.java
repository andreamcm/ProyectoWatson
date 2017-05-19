package com.example.cesaraugustocordonbalcarcel.registrosmedicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.*;

import okhttp3.OkHttpClient;

public class Codigo extends AppCompatActivity {

    String diagnostico;
    TextView codigo, enfermedad, descripcion, nombreP;
    Button analizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        codigo = (TextView) findViewById(R.id.textView3);
        enfermedad = (TextView) findViewById(R.id.textView2);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        nombreP = (TextView) findViewById(R.id.lblNombrePaciente);
        analizar = (Button) findViewById(R.id.btnAnalizar2);

        Intent intento = getIntent();
        diagnostico = intento.getExtras().getString("diagnostico");
        enfermedad.setText(diagnostico);

        if (diagnostico == "cáncer") {
            enfermedad.setText("Cáncer");
            codigo.setText("C:80");
            descripcion.setText("Tumor maligno de sitios no especificados");
        } if (diagnostico == "gripe") {
            enfermedad.setText("Gripe");
            codigo.setText("J:22:X");
            descripcion.setText("Otras infecciones respiratorias agudas");
        } if (diagnostico == "diabetes") {
            enfermedad.setText("Diabetes");
            codigo.setText("E:10");
            descripcion.setText("Diabetes mellitus insulinodependiente");
        } if (diagnostico == "neumonía") {
            enfermedad.setText("Neumonía");
            codigo.setText("J:18:9");
            descripcion.setText("Neumonia y bronconeumonias");
        } if (diagnostico == "infección") {
            enfermedad.setText("Infección");
            codigo.setText("A:49:9");
            descripcion.setText("Infeccion bacteriana, no especificada");
        } if (diagnostico == "bronquitis") {
            enfermedad.setText("Bronquitis");
            codigo.setText("J:20");
            descripcion.setText("Bronquitis aguda");
        } if (diagnostico == "hepatitis") {
            enfermedad.setText("Hepatitis");
            codigo.setText("K:75:9");
            descripcion.setText("Hepatitis viral");
        } if (diagnostico == "sida") {
            enfermedad.setText("Sida (VIH)");
            codigo.setText("B:24");
            descripcion.setText("Enfermedad por virus de la inmunodeficiencia humana [VIH], sin otra especificacion");
        } if (diagnostico == "herpes") {
            enfermedad.setText("Herpes");
            codigo.setText("B:02");
            descripcion.setText("Herpes zoster");
        }
    }




   /* public String revisar(String diagnostico) {
            if (diagnostico == "cáncer") {
                enfermedad.setText("Cáncer");
                codigo.setText("C:80");
                descripcion.setText("Tumor maligno de sitios no especificados");
            } else if (diagnostico == "gripe") {
                enfermedad.setText("Gripe");
                codigo.setText("J:22:X");
                descripcion.setText("Otras infecciones respiratorias agudas");
            } else if (diagnostico == "diabetes") {
                enfermedad.setText("Diabetes");
                codigo.setText("E:10");
                descripcion.setText("Diabetes mellitus insulinodependiente");
            } else if (diagnostico == "neumonía") {
                enfermedad.setText("Neumonía");
                codigo.setText("J:18:9");
                descripcion.setText("Neumonia y bronconeumonias");
            } else if (diagnostico == "infección") {
                enfermedad.setText("Infección");
                codigo.setText("A:49:9");
                descripcion.setText("Infeccion bacteriana, no especificada");
            } else if (diagnostico == "bronquitis") {
                enfermedad.setText("Bronquitis");
                codigo.setText("J:20");
                descripcion.setText("Bronquitis aguda");
            } else if (diagnostico == "hepatitis") {
                enfermedad.setText("Hepatitis");
                codigo.setText("K:75:9");
                descripcion.setText("Hepatitis viral");
            } else if (diagnostico == "sida") {
                enfermedad.setText("Sida (VIH)");
                codigo.setText("B:24");
                descripcion.setText("Enfermedad por virus de la inmunodeficiencia humana [VIH], sin otra especificacion");
            } else if (diagnostico == "herpes") {
                enfermedad.setText("Herpes");
                codigo.setText("B:02");
                descripcion.setText("Herpes zoster");
            }
            return diagnostico;

        }

    @Override
    public void onClick(View v) {
        Intent explicit_intent;
        switch (v.getId()){
            case R.id.btnAnalizar2:
                revisar(diagnostico);
                break;


        }*/
    }



