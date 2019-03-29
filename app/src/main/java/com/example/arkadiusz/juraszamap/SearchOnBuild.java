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
import com.example.arkadiusz.juraszamap.Adapter.SearchAdapter;
import com.example.arkadiusz.juraszamap.Database.Database;
import java.util.List;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class SearchOnBuild extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner, spinner2 ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    Database database;
    String budynek, pietro;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COLOR_SWITCH = "colorSwitch";
    private boolean switchOnOff;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        colorVersion();
        if(switchOnOff){
            setTheme(R.style.darkTheme);
        }        else {setTheme(R.style.AppTheme);}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_on_build);


        // ads apply
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        mAdView = this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        database = new Database(this);

        recyclerView=findViewById(R.id.recycler_search);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        loadSpinnerData();

        adapter = new SearchAdapter(this,database.getMiejsca());
        recyclerView.setAdapter(adapter);

        infoSet();

        Button btnOption = findViewById(R.id.option_button);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), MenuOption.class);
                startActivity(intent);
                finish();
            }
        });
        Button btnBack = findViewById(R.id.back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v,
                                       int pos, long id) {
                budynek = av.getItemAtPosition(pos).toString();
                loadSpinnerData2();
                if(pietro!= null) {
                    Database db = new Database(getApplicationContext());
                    db.getOpisPoBudynku(budynek, pietro);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
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
        // database handler
        Database db = new Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllNamesBuilding();
        List<String> lables2 = db.getAllLevelBuilding(budynek);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lables2);

        // Drop down layout style - list view with radio button
        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
    }
    private void loadSpinnerData2() {
        // database handler
        Database db = new Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables2 = db.getAllLevelBuilding(budynek);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lables2);

        // Drop down layout style - list view with radio button
        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
    }


    public void infoSet(){
      /*  Toast.makeText(this, "You selected: " + budynek + " " + pietro ,
                Toast.LENGTH_LONG).show();  */

        adapter = new SearchAdapter(this,database.getOpisPoBudynku(budynek, pietro));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    private void colorVersion() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(COLOR_SWITCH, false);
    }

}