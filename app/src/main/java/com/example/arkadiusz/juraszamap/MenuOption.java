package com.example.arkadiusz.juraszamap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MenuOption extends AppCompatActivity {

    private Switch colorSwitch;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";

    private boolean switchOnOff;
    String  backIntent,setIntent;
    protected void onCreate(Bundle savedInstanceState) {
        backIntent = getIntent().getStringExtra("backIntent");
        // save name of intent
        setIntent = MenuOption.class.getCanonicalName();
        // sprawdza jaki jest zapisany layout
        loadData();
        // ustawia żądany layout
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_options);
        colorSwitch=findViewById(R.id.color_switch);

        // Obsługuje przełączanie w czasie rzeczywistym i zmienia napis

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            colorSwitch.setChecked(true);
            colorSwitch.setText(getString(R.string.color_switch_dark));
        }
        else {
            setTheme(R.style.AppTheme);
            colorSwitch.setText(getString(R.string.color_switch_light));
        }
        colorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveData();
                    colorSwitch.setText(getString(R.string.color_switch_dark));
                    restartApp();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveData();
                    colorSwitch.setText(getString(R.string.color_switch_light));
                    restartApp();
                }
            }
        });

        // adds ads

        MobileAds.initialize(this,getString(R.string.adds_in_java));
        AdView mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        updateViews();
    }

    //zapisuje ustawienie layoutu
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(COLOR_SWITCH, colorSwitch.isChecked());
        editor.apply();
    }
    // sprawdza jaki jest zapisany layout
public void  loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
}
    // sprawdza jaki jest żądany layout
public void  updateViews(){
colorSwitch.setChecked(switchOnOff);
}
// restartuje aplikacje by mogła zmienić layout podczas przełączania switcha
    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), MenuOption.class);
        startActivity(intent);
        finish();
    }
    public void onBackPressed() {
        if(backIntent == null){
            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            try {
                Class newClass = Class.forName(backIntent);
                Intent resume = new Intent(this, newClass);
                resume.putExtra("backIntent", setIntent);
                startActivity(resume);
                finish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}