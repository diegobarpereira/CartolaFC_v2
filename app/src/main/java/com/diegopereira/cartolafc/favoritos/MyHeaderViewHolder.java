package com.diegopereira.cartolafc.favoritos;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.liga.Teste;

import org.w3c.dom.Text;

public class MyHeaderViewHolder extends RecyclerView.ViewHolder {
    public final TextView times_name;
    public final TextView league_ultima;
    public final TextView league_total;


    public MyHeaderViewHolder(View itemView) {
        super(itemView);
        times_name = (TextView)itemView.findViewById(R.id.times_name);
        league_ultima = (TextView)itemView.findViewById(R.id.league_ultima);
        league_total= (TextView)itemView.findViewById(R.id.league_total);
    }
}