package com.example.arkadiusz.juraszamap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MenuOption extends AppCompatActivity {

    private Switch colorSwitch;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";
    private AdView mAdView;

    private boolean switchOnOff;
    protected void onCreate(Bundle savedInstanceState) {
        loadData();
        Log.d(SHARED_PREFS, String.valueOf(switchOnOff));
        if(switchOnOff) {
            setTheme(R.style.darkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_options);

// Nie dziala

        colorSwitch = findViewById(R.id.color_switch);
        if(switchOnOff) {colorSwitch.setTextOff("Jasny");}
        else{colorSwitch.setTextOn("Ciemny");}

        // adds ads
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveData();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        updateViews();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(COLOR_SWITCH, colorSwitch.isChecked());
        editor.apply();
    }
public void  loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
}
public void  updateViews(){
colorSwitch.setChecked(switchOnOff);
}
    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), MenuOption.class);
        startActivity(intent);
        finish();
    }
}
