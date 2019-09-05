package com.example.arkadiusz.juraszamap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arkadiusz.juraszamap.Adapter.SearchAdapter;
import com.example.arkadiusz.juraszamap.Adapter.SpinnerAdapter;
import com.example.arkadiusz.juraszamap.Database.Database;

import java.util.ArrayList;
import java.util.List;

import com.example.arkadiusz.juraszamap.Model.BuildSpinner;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class SearchOnBuild extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner, spinner2 ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    SpinnerAdapter adapterSpinner;
    Database database;
    String budynek, pietro, backIntent,setIntent;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";
    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        backIntent = getIntent().getStringExtra("backIntent");
        // save name of intent
        setIntent = SearchOnBuild.class.getCanonicalName();

        colorVersion();
        if(switchOnOff){
            setTheme(R.style.DarkTheme);
        }        else {setTheme(R.style.AppTheme);}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_on_build);


        // ads apply
        MobileAds.initialize(this,getString(R.string.adds_in_java));
        AdView mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        database = new Database(this);

        recyclerView=findViewById(R.id.recycler_search);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        spinner = findViewById(R.id.spinner_building);
        spinner.setOnItemSelectedListener(this);
        spinner2 = findViewById(R.id.spinner_level_of_building);
        spinner2.setOnItemSelectedListener(this);
        loadSpinnerData();

        adapter = new SearchAdapter(this,database.getMiejsca());
        recyclerView.setAdapter(adapter);

        infoSet();

        Button btnOption = findViewById(R.id.option_button);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MenuOption.class);
                // send name of this intent
                intent.putExtra("backIntent", setIntent);
                //start next intent
                startActivity(intent);
                // close this intent
                finish();
            }
        });
        Button btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("backIntent", "SearchOnBuild");
                startActivity(intent);
                finish();
            }
        });

        // load data to the spinner_level_of_building

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v,
                                       int pos, long id) {
                BuildSpinner onlyHear = (BuildSpinner) av.getItemAtPosition(pos);
                budynek = onlyHear.getBudynek();
                loadSpinnerData2();
                if(pietro!= null) {
                    Database db = new Database(getApplicationContext());
                    db.getOpisPoBudynku(budynek, pietro);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        // load data to the spinner_building
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v,
                                       int pos, long id) {
                pietro = av.getItemAtPosition(pos).toString();
                if(budynek!= null) {
                    Database db = new Database(getApplicationContext());
                    db.getOpisPoBudynku(budynek, pietro);
                    infoSet();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

    }

    private void loadSpinnerData() {

        adapterSpinner = new SpinnerAdapter(this, (ArrayList<BuildSpinner>) database.getAllNamesBuilding());
        spinner.setAdapter(adapterSpinner);

    }

    private void loadSpinnerData2() {
        // database handler
        Database db = new Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables2 = db.getAllLevelBuilding(budynek);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this,
                R.layout.spinner_layout, lables2);

        // Drop down layout style - list view with radio button
        dataAdapter2
                .setDropDownViewResource(R.layout.spinner_layout);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
    }

    // showed loading new data selected by user

    public void infoSet(){

       adapter = new SearchAdapter(this,database.getOpisPoBudynku(budynek, pietro));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    private void colorVersion() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
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