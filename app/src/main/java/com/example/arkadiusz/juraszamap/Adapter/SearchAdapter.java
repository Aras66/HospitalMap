package com.example.arkadiusz.juraszamap.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arkadiusz.juraszamap.Model.Miejsca;
import com.example.arkadiusz.juraszamap.R;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView budynek, opis, uwagi, pietro;

    public SearchViewHolder(View itemView) {
        super(itemView);
        budynek = itemView.findViewById(R.id.budynek);
        pietro = itemView.findViewById(R.id.pietro);
        opis = itemView.findViewById(R.id.opis);
        uwagi = itemView.findViewById(R.id.uwagi);
    }
}
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<Miejsca> miejsca;

    public SearchAdapter(Context context, List<Miejsca> miejsca){
        this.context = context;
        this.miejsca = miejsca;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.budynek.setText(miejsca.get(position).getBudynek());
        holder.pietro.setText(String.valueOf(miejsca.get(position).getPietro()));
        holder.opis.setText(miejsca.get(position).getOpis());
        holder.uwagi.setText(miejsca.get(position).getUwagi());
    }

    @Override
    public int getItemCount() {
        return miejsca.size();
    }
}
