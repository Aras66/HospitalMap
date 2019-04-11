package com.example.arkadiusz.juraszamap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arkadiusz.juraszamap.Database.Database;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class EditRequest extends Activity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";
    private boolean switchOnOff;
    private int id=0;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        colorVersion();
        if(switchOnOff){
            setTheme(R.style.DarkTheme);
        }        else {setTheme(R.style.AppTheme);}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_request);

        database = new Database(this);

        // ads apply
        MobileAds.initialize(this,getString(R.string.adds_in_java));
        AdView mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        getIncomingIntent();

        Button btnOption = findViewById(R.id.send_request_button);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRequest();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();}
        });
    }
    private void getIncomingIntent() {

        if (getIntent().hasExtra("opis") && getIntent().hasExtra("uwagi") && getIntent().hasExtra("budynek") && getIntent().hasExtra("pietro") && getIntent().hasExtra("id")) {

            String budynek = getIntent().getStringExtra("budynek");
            String pietro = getIntent().getStringExtra("pietro");
            String opis = getIntent().getStringExtra("opis");
            String uwagi = getIntent().getStringExtra("uwagi");
            id = getIntent().getIntExtra("id", 0);
            setMyChoice(budynek, pietro, opis, uwagi);

        }
    }
    private void setMyChoice(String budynek, String pietro, String opis, String uwagi) {
        EditText budynek2 = findViewById(R.id.myBudynek);
        budynek2.setText(budynek);
        EditText pietro2 = findViewById(R.id.myPietro);
        pietro2.setText(pietro);
        EditText opis2 = findViewById(R.id.myOpis);
        opis2.setText(opis);
        EditText uwagi2 = findViewById(R.id.myUwagi);
        uwagi2.setText(uwagi);
    }
    private void colorVersion() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
    }
    private void setRequest(){
        EditText budynek2 = findViewById(R.id.myBudynek);
        String budynek = String.valueOf(budynek2.getText());
        EditText pietro2 = findViewById(R.id.myPietro);
        String pietro = String.valueOf(pietro2.getText());
        EditText opis2 = findViewById(R.id.myOpis);
        String opis = String.valueOf(opis2.getText());
        EditText uwagi2 = findViewById(R.id.myUwagi);
        String uwagi = String.valueOf(uwagi2.getText());
        database.setEditRequest(budynek, pietro, opis, uwagi, id);
    }
}
