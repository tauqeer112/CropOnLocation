package com.example.croponlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NPKActivity extends AppCompatActivity {

    private TextInputLayout nitrogen, phosphorous, potassium ;
    Button btn;
    String url = "https://crop-recommendation-server.herokuapp.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npkactivity);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        nitrogen = findViewById(R.id.nitrogen);
        phosphorous = findViewById(R.id.phosphorous);
        potassium = findViewById(R.id.potassium);
        btn =  findViewById(R.id.filter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                String img1 = bundle.getString("crop1");
                String img2 = bundle.getString("crop2");
                String img3 = bundle.getString("crop3");
                String img4 = bundle.getString("crop4");
                String img5 = bundle.getString("crop5");
                String img6 = bundle.getString("crop6");
                String img7 = bundle.getString("crop7");
                String img8 = bundle.getString("crop8");
                String state = bundle.getString("state");
                String N = nitrogen.getEditText().getText().toString().trim();
                String P = phosphorous.getEditText().getText().toString().trim();
                String K = potassium.getEditText().getText().toString().trim();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
//                                String crops = jsonObject.getString("crop1");
                                    ParseAndStartAc(jsonObject);

//
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            private void ParseAndStartAc(JSONObject jsonObject) {

                                try {
                                    String crop1 = jsonObject.getString("crop1");
                                    String crop2 = jsonObject.getString("crop2");
                                    String crop3 = jsonObject.getString("crop3");
                                    String crop4 = jsonObject.getString("crop4");
                                    String crop5 = jsonObject.getString("crop5");
                                    String crop6 = jsonObject.getString("crop6");
                                    String crop7 = jsonObject.getString("crop7");
                                    String crop8 = jsonObject.getString("crop8");

                                    Intent i = new Intent(NPKActivity.this, card.class);
                                    //Create the bundle
                                    Bundle bundle = new Bundle();
                                    bundle.putString("crop1",crop1 );
                                    bundle.putString("crop2",crop2 );
                                    bundle.putString("crop3",crop3 );
                                    bundle.putString("crop4",crop4 );
                                    bundle.putString("crop5",crop5 );
                                    bundle.putString("crop6",crop6 );
                                    bundle.putString("crop7",crop7 );
                                    bundle.putString("crop8",crop8 );
                                    bundle.putString("state",state);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    i.putExtras(bundle);
                                    startActivity(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(NPKActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("state", state);
                        params.put("crop1",img1);
                        params.put("crop2",img2);
                        params.put("crop3",img3);
                        params.put("crop4",img4);
                        params.put("crop5",img5);
                        params.put("crop6",img6);
                        params.put("crop7",img7);
                        params.put("crop8",img8);
                        params.put("N",N);
                        params.put("P",P);
                        params.put("K",K);



                        return params;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(NPKActivity.this);
                queue.add(stringRequest);
            }
        });




    }
}

