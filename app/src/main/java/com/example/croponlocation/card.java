package com.example.croponlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class card extends AppCompatActivity {

    ImageView im1,im2,im3,im4,im5,im6,im7,im8;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,recom;
    Button bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bn = findViewById(R.id.filternav);
        im1 = findViewById(R.id.im1);
        tv1 = findViewById(R.id.tv1);
        im2 = findViewById(R.id.im2);
        tv2 = findViewById(R.id.tv2);
        im3 = findViewById(R.id.im3);
        tv3 = findViewById(R.id.tv3);
        im4 = findViewById(R.id.im4);
        tv4 = findViewById(R.id.tv4);
        im5 = findViewById(R.id.im5);
        tv5 = findViewById(R.id.tv5);
        im6 = findViewById(R.id.im6);
        tv6 = findViewById(R.id.tv6);
        im7 = findViewById(R.id.im7);
        tv7 = findViewById(R.id.tv7);
        im8 = findViewById(R.id.im8);
        tv8 = findViewById(R.id.tv8);
        recom = findViewById(R.id.recom);

        im1.setVisibility(View.INVISIBLE);
        tv1.setVisibility(View.INVISIBLE);
        im2.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        im3.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        im4.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        im5.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        im6.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);
        im7.setVisibility(View.INVISIBLE);
        tv7.setVisibility(View.INVISIBLE);
        im8.setVisibility(View.INVISIBLE);
        tv8.setVisibility(View.INVISIBLE);


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

        if (img1.equals("Null")) {
            recom.setText("Sorry No Recommendation for \n" +  state + " on this month");
        }
        else {
            recom.setText(" Recommendation for \n" +  state + " on this month");

        if (!img1.equals("Null")) {
            tv1.setText(img1);
            im1.setImageResource(getResources().getIdentifier(img1, "drawable", getPackageName()));
            im1.setVisibility(View.VISIBLE);
            tv1.setVisibility(View.VISIBLE);
        }
        if (!img2.equals("Null")) {
            tv2.setText(img2);
            im2.setImageResource(getResources().getIdentifier(img2, "drawable", getPackageName()));
            im2.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
        }

        if (!img3.equals("Null")) {
            tv3.setText(img3);
            im3.setImageResource(getResources().getIdentifier(img3, "drawable", getPackageName()));
            im3.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
        }

        if (!img4.equals("Null")) {
            tv4.setText(img4);
            im4.setImageResource(getResources().getIdentifier(img4, "drawable", getPackageName()));
            im4.setVisibility(View.VISIBLE);
            tv4.setVisibility(View.VISIBLE);
        }

        if (!img5.equals("Null")) {
            tv5.setText(img5);
            im5.setImageResource(getResources().getIdentifier(img5, "drawable", getPackageName()));
            im5.setVisibility(View.VISIBLE);
            tv5.setVisibility(View.VISIBLE);
        }

        if (!img6.equals("Null")) {
            tv6.setText(img6);
            im6.setImageResource(getResources().getIdentifier(img6, "drawable", getPackageName()));
            im6.setVisibility(View.VISIBLE);
            tv6.setVisibility(View.VISIBLE);
        }

        if (!img7.equals("Null")) {
            tv7.setText(img7);
            im7.setImageResource(getResources().getIdentifier(img7, "drawable", getPackageName()));
            im7.setVisibility(View.VISIBLE);
            tv7.setVisibility(View.VISIBLE);
        }
        if (!img8.equals("Null")) {
            tv8.setText(img8);
            im8.setImageResource(getResources().getIdentifier(img8, "drawable", getPackageName()));
            im8.setVisibility(View.VISIBLE);
            tv8.setVisibility(View.VISIBLE);
        }
        }

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(card.this, NPKActivity.class);
                //Create the bundle
                Bundle bundle = new Bundle();
                bundle.putString("crop1",img1 );
                bundle.putString("crop2",img2 );
                bundle.putString("crop3",img3 );
                bundle.putString("crop4",img4 );
                bundle.putString("crop5",img5 );
                bundle.putString("crop6",img6 );
                bundle.putString("crop7",img7 );
                bundle.putString("crop8",img8 );
                bundle.putString("state",state);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.putExtras(bundle);
                startActivity(i);
            }
        });





    }
}