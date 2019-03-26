package com.example.arkadiusz.juraszamap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arkadiusz.juraszamap.Model.Miejsca;
import com.example.arkadiusz.juraszamap.MyChoice;
import com.example.arkadiusz.juraszamap.R;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView budynek, opis, uwagi, pietro, opisBud;

    public SearchViewHolder(View itemView) {
        super(itemView);
        budynek = itemView.findViewById(R.id.budynek);
        pietro = itemView.findViewById(R.id.pietro);
        opis = itemView.findViewById(R.id.opis);
        uwagi = itemView.findViewById(R.id.uwagi);
        opisBud = itemView.findViewById(R.id.opisBud);
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Miejsca> miejsca;

    public SearchAdapter(Context context, List<Miejsca> miejsca) {
        this.context = context;
        this.miejsca = miejsca;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        holder.budynek.setText(miejsca.get(position).getBudynek());
        holder.pietro.setText(String.valueOf(miejsca.get(position).getPietro()));
        holder.opis.setText(miejsca.get(position).getOpis());
        holder.uwagi.setText(miejsca.get(position).getUwagi());
        holder.opisBud.setText(miejsca.get(position).getOpisBudynku());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyChoice.class);
                intent.putExtra("budynek", miejsca.get(position).getBudynek());
                intent.putExtra("pietro", String.valueOf(miejsca.get(position).getPietro()));
                intent.putExtra("opis", miejsca.get(position).getOpis());
                intent.putExtra("uwagi", miejsca.get(position).getUwagi());
                intent.putExtra("opisBud", miejsca.get(position).getOpisBudynku());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return miejsca.size();
    }
}