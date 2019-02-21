package com.example.arkadiusz.juraszamap;

import android.content.Intent;
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
import com.example.arkadiusz.juraszamap.Database.Database;

import java.util.List;

public class searchOnBuild extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner, spinner2 ;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_on_build);

        database = new Database(this);

        recyclerView=findViewById(R.id.recycler_search);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        loadSpinnerData();

        adapter = new SearchAdapter(this,database.getMiejsca());
        recyclerView.setAdapter(adapter);



        Button btn1 = findViewById(R.id.back_button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadSpinnerData() {
        // database handler
        Database db = new Database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllNamesBuilding();
        List<String> lables2 = db.getAllLevelBuilding();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables2);

        // Drop down layout style - list view with radio button
        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}