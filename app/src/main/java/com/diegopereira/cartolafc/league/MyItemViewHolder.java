package com.diegopereira.cartolafc.league;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;

 class MyItemViewHolder extends RecyclerView.ViewHolder {
    public final TextView name, pos, points, dif, var, ultima, cash;
    public final AppCompatImageView img_player;

public MyItemViewHolder(View itemView) {
    super(itemView);
    name=(TextView)itemView.findViewById(R.id.title);
    pos=(TextView)itemView.findViewById(R.id.pos);
    points=(TextView)itemView.findViewById(R.id.points);
    dif=(TextView)itemView.findViewById(R.id.dif);
    var=(TextView)itemView.findViewById(R.id.var);
    ultima=(TextView)itemView.findViewById(R.id.ultima);
    cash=(TextView)itemView.findViewById(R.id.cash);
    img_player=(AppCompatImageView)itemView.findViewById(R.id.img_player);

}
}