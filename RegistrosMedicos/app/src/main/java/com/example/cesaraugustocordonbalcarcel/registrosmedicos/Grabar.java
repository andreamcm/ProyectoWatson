package com.example.cesaraugustocordonbalcarcel.registrosmedicos;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.http.RequestBuilder;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ListModelsResults;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.util.GsonSingleton;
import com.ibm.watson.developer_cloud.util.ResponseConverterUtils;
import com.ibm.watson.developer_cloud.util.Validator;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class Grabar extends AppCompatActivity {

    private TextView resultT;
    private String modelo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar);
        resultT = (TextView)findViewById(R.id.lblResultado);
        modelo = "20:3e6b8290-ae10-40c0-b06b-a0ba20462db4";
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.imageButton6){
            TextView result = (TextView)findViewById(R.id.lblResultado);
            promptSpeechInput();
            NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                    NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                    "6ce714f9-d8b4-4e6a-8bee-6c55a72b403e",
                    "iyUxDgTgi2R8"
            );

            EntitiesOptions entities = new EntitiesOptions.Builder().sentiment(true).limit(1).model(modelo).build();
            Features features = new Features.Builder().entities(entities).build();
            AnalyzeOptions parameters = new AnalyzeOptions.Builder().features(features).text(String.valueOf(resultT)).build();
            AnalysisResults results = service.analyze(parameters).execute();
            results.toString();
            resultT.setText((CharSequence) results);
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
                    resultT.toString();
                }
                break;
        }

    }
}