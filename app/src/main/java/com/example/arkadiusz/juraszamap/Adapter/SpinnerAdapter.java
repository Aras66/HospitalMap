package com.example.arkadiusz.juraszamap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.arkadiusz.juraszamap.Model.BuildSpinner;
import com.example.arkadiusz.juraszamap.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<BuildSpinner> {

    public  SpinnerAdapter(Context context, ArrayList<BuildSpinner> buildSpinners){
        super(context,0,buildSpinners);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

   private View initView(int position,View convertView,ViewGroup parent) {
       if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_build, parent, false
           );
       }
       TextView budynek_tv = convertView.findViewById(R.id.text_build);
       TextView budynek_opis_tv = convertView.findViewById(R.id.text_build_opis);

       BuildSpinner buildSpinner = getItem(position);

       if (buildSpinner != null) {
           budynek_tv.setText(buildSpinner.getBudynek());
           budynek_opis_tv.setText(buildSpinner.getOpisBudynku());

       }
       return convertView;
   }
}
