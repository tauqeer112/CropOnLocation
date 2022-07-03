package com.example.croponlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button button_location;
    TextView textView_location;
    LocationManager locationManager;
    ProgressBar pb;
    String url = "https://crop-recommendation-server.herokuapp.com/predict";

//    String url = "http://localhost:5000/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        textView_location = findViewById(R.id.text_location);
        pb = findViewById(R.id.progress);
        button_location = findViewById(R.id.button_location);
        //Runtime permissions
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                //create method
                getLocation();


            }
        });

    }



    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,50,MainActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
//        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);
            String state = addresses.get(0).getAdminArea();
//            System.out.println(state);
//            textView_location.setText(address);
            // hit the API -> Volley
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

                                Intent i = new Intent(MainActivity.this, card.class);
                                //Create the bundle
                                Bundle bundle = new Bundle();
                                bundle.putString("state",state);
                                bundle.putString("crop1",crop1 );
                                bundle.putString("crop2",crop2 );
                                bundle.putString("crop3",crop3 );
                                bundle.putString("crop4",crop4 );
                                bundle.putString("crop5",crop5 );
                                bundle.putString("crop6",crop6 );
                                bundle.putString("crop7",crop7 );
                                bundle.putString("crop8",crop8 );
                                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.putExtras(bundle);
                                pb.setVisibility(View.INVISIBLE);
                                startActivity(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){

                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("state",state);
                    return params;
                }

            };
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}