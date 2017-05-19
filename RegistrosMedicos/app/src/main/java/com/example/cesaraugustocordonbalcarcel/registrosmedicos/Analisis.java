package com.example.cesaraugustocordonbalcarcel.registrosmedicos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class Analisis extends AppCompatActivity {

    NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
            NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
            "6ce714f9-d8b4-4e6a-8bee-6c55a72b403e",
            "iyUxDgTgi2R8"
    );
    private String diagnostico, modelo;
    private Button analizar;
    private TextView analisis;

    JsonParser json = new JsonParser();

    Llamado llamado = new Llamado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);
        modelo = "20:3e6b8290-ae10-40c0-b06b-a0ba20462db4";
        Intent intento = getIntent();
        diagnostico = intento.getExtras().getString("diagnostico");

        analizar = (Button) findViewById(R.id.btnAnalizar);
        analisis = (TextView) findViewById(R.id.txtAnalisis);

        analisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamado.execute();
            }
        });

    }

    private class Llamado  extends AsyncTask<Void, Void, String>{

            @Override
            protected String doInBackground(Void... params) {
                service.setEndPoint("https://gateway.watsonplatform.net/natural-language-understanding/api");
                EntitiesOptions entities = new EntitiesOptions.Builder().sentiment(true).limit(1).model(modelo).build();
                Features features = new Features.Builder().entities(entities).build();
                AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(diagnostico).features(features).build();
                AnalysisResults results = service.analyze(parameters).execute();

                String analizado = results.toString();
                JSONObject analizar;
                JSONArray registro = null;

                try {
                    analizar = new JSONObject(analizado);
                    registro = analizar.getJSONArray("entities");
                    String resultado = "";
                    for (int a = 0; a < registro.length(); a++) {
                        if (a == 0) {
                            resultado = registro.getJSONObject(a).getString("text");
                        } else if (a == registro.length() - 1) {
                            resultado = resultado + " y" + registro.getJSONObject(a).getString("text");
                        } else {
                            resultado = resultado + ", " + registro.getJSONObject(a).getString("text");
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String finalito) {
                analisis.setText("Su diagnostico es: " + finalito);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();

                params.put("diagnostico", finalito);
                client.get("http://uvgproyectos.esaludgt.org/web/Api/Codigos?", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject responseBody) {
                        JSONObject objeto = null;
                        analisis.setText(responseBody.toString());
                        try {
                            objeto = responseBody;
                            String key = objeto.getString("Key");
                            String value = objeto.getString("Value");
                            analisis.setText(value + ":" + key);
                        }
                        catch (JSONException e) {
                            analisis.setText("No funciona");
                        }
                    }
                });
            }
        }
    }

