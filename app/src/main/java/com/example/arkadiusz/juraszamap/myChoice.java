package com.example.arkadiusz.juraszamap;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myChoice extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_choice);

        getIncomingIntent();

        //bez tego ActionBar = nullpointer ex | nie wyświetla action bar

      /*  assert getSupportActionBar() != null;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("opis");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("opis") && getIntent().hasExtra("uwagi") && getIntent().hasExtra("budynek") && getIntent().hasExtra("pietro") && getIntent().hasExtra("opisBud")) {

            String budynek = getIntent().getStringExtra("budynek");
            String pietro = getIntent().getStringExtra("pietro");
            String opis = getIntent().getStringExtra("opis");
            String uwagi = getIntent().getStringExtra("uwagi");
            String opisBud = getIntent().getStringExtra("opisBud");
            setMyChoice(budynek, pietro, opis, uwagi, opisBud);
        }
    }


    private void setMyChoice(String budynek, String pietro, String opis, String uwagi, String opisBud) {

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
        opisBudy.setText(opisBud);


    }
}
