package com.example.arkadiusz.juraszamap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyChoice extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_choice);

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
                startActivity(intent);
                finish();
                return false;
            }
        });
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("opis") && getIntent().hasExtra("uwagi") && getIntent().hasExtra("budynek") && getIntent().hasExtra("pietro")) {

            String budynek = getIntent().getStringExtra("budynek");
            String pietro = getIntent().getStringExtra("pietro");
            String opis = getIntent().getStringExtra("opis");
            String uwagi = getIntent().getStringExtra("uwagi");
            String opisBudynku = getIntent().getStringExtra("opisBudynku");

            setMyChoice(budynek, pietro, opis, uwagi, opisBudynku);

        }
    }

    private void setMyChoice(String budynek, String pietro, String opis, String uwagi, String opisBudynku) {
        LinearLayout linearLayout = findViewById(R.id.myChoice);
        linearLayout.setBackgroundResource(getResources().getIdentifier(budynek.toLowerCase(), "drawable", getPackageName()));
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
}