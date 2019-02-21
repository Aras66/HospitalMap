package com.example.arkadiusz.juraszamap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.arkadiusz.juraszamap.Adapter.SearchAdapter;
import com.example.arkadiusz.juraszamap.Database.Database;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_search);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = findViewById(R.id.search_bar);

        database = new Database(this);

        materialSearchBar.setHint("Szukaj");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {
                    adapter = new SearchAdapter(getBaseContext(),database.getMiejsca());
                    recyclerView.setAdapter(adapter);

                }
            }
            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

      adapter = new SearchAdapter(this,database.getMiejsca());
      recyclerView.setAdapter(adapter);

      Button btn1 = findViewById(R.id.other_search_button);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), searchOnBuild.class);
                startActivity(intent);
                finish();
                }
        });

        Button btn2 = findViewById(R.id.end_button);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
    }

    private void startSearch(String s) {
        adapter = new SearchAdapter(this,database.getMiejscePoOpis(s));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getOpisy();
        materialSearchBar.setLastSuggestions(suggestList);
    }




}
