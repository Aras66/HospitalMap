package com.example.arkadiusz.juraszamap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.arkadiusz.juraszamap.Adapter.SearchAdapter;
import com.example.arkadiusz.juraszamap.Database.Database;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";
    private boolean switchOnOff;
    int countBack =0;
    String setIntent;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    List<String> suggestList = new ArrayList<>();
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // save name of intent
        setIntent = MainActivity.class.getCanonicalName();
        // check the layout option and set chosen layout
        colorVersion();
        if (switchOnOff) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ads apply
        MobileAds.initialize(this, getString(R.string.adds_in_java));
        AdView mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView = findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        database = new Database(this);

        startSearch();
        // actv
        addSearchBar();


        Button btn1 = findViewById(R.id.other_search_button);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchOnBuild.class);
                intent.putExtra("backIntent", setIntent);
                startActivity(intent);
                finish();
            }
        });

        Button btnOption = findViewById(R.id.option_button);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuOption.class);
                intent.putExtra("backIntent", setIntent);
                startActivity(intent);
                finish();
            }
        });
        final AutoCompleteTextView editText = findViewById(R.id.actv);
        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text;
                text = editText.getText().toString();
                nextSearch(text);
                closeKeyboard();
                editText.setText("");
            }
        });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void addSearchBar() {
        suggestList = database.getOpisy();
        AutoCompleteTextView editText = findViewById(R.id.actv);
        String[] opisy = suggestList.toArray(new String[0]);
        ArrayAdapter<String> adapterACTV = new ArrayAdapter<>(this, R.layout.spinner_layout, opisy);
        editText.setAdapter(adapterACTV);
    }


    private void colorVersion() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
    }

    private void nextSearch(String s) {
        adapter = new SearchAdapter(this, database.getMiejscePoOpis(s));
        recyclerView.setAdapter(adapter);
    }

    private void startSearch() {
        adapter = new SearchAdapter(this, database.getMiejsca());
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed() {
        if(countBack != 2 ){
            countBack=countBack+1;
            Toast.makeText(this, "Naciśnij jeszcze raz aby zamknąc.", Toast.LENGTH_SHORT).show();
        }
        if(countBack == 2){
           System.exit(0);
        }
    }
}