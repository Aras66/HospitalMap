package com.example.arkadiusz.juraszamap;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MyChoice extends AppCompatActivity {

    private String budynek = "o";
    private String pietro = "j";
    private String opis="p";
    private String uwagi="k";
    private String opisBudynku="m";
    private int id;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";
    private boolean switchOnOff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        colorVersion();
        if(switchOnOff){
            setTheme(R.style.DarkTheme);
        }        else {setTheme(R.style.AppTheme);}

        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_choice);

        // ads apply
        MobileAds.initialize(this,getString(R.string.adds_in_java));
        AdView mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getIncomingIntent();

        assert getSupportActionBar() != null;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("opis");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        RelativeLayout parentLayout;
        parentLayout = findViewById(R.id.relLa);
        parentLayout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(v.getContext(), EditRequest.class);
                intent.putExtra("budynek", budynek);
                intent.putExtra("pietro", pietro);
                intent.putExtra("opis", opis);
                intent.putExtra("uwagi", uwagi);
                intent.putExtra("opisBud", opisBudynku);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                return false;
            }
        });
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("opis") && getIntent().hasExtra("uwagi") && getIntent().hasExtra("budynek") && getIntent().hasExtra("pietro")) {

            budynek = getIntent().getStringExtra("budynek");
            pietro = getIntent().getStringExtra("pietro");
            opis = getIntent().getStringExtra("opis");
            uwagi = getIntent().getStringExtra("uwagi");
            opisBudynku = getIntent().getStringExtra("opisBudynku");
            id = getIntent().getIntExtra("id",id);
            setMyChoice(budynek, pietro, opis, uwagi, opisBudynku, id);
        }
    }

    private void setMyChoice(String budynek, String pietro, String opis, String uwagi, String opisBudynku, Integer id) {
        ImageView imageView = findViewById(R.id.imageView_MyChoice);
        imageView.setImageResource(getResources().getIdentifier(budynek.toLowerCase(), "drawable", getPackageName()));
        TextView budynek2 = findViewById(R.id.myBudynek);
        budynek2.setText(budynek);
        TextView pietro2 = findViewById(R.id.myPietro);
        pietro2.setText(pietro);
        TextView opis2 = findViewById(R.id.myOpis);
        opis2.setText(opis);
        TextView uwagi2 = findViewById(R.id.myUwagi);
        uwagi2.setText(uwagi);
        TextView opisBudy = findViewById(R.id.opisBud);
       opisBudy.setText(opisBudynku);

    }
    private void colorVersion() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
    }
}