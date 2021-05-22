package com.diegopereira.cartolafc.favoritos;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;

class MyItemViewHolder extends RecyclerView.ViewHolder {
   public final AppCompatImageView favimg_player, button_add;
   public final TextView fav_title, fav_points, fav_ultima, fav_pos, fav_cash, fav_dif;

public MyItemViewHolder(View itemView) {
   super(itemView);
   favimg_player=(AppCompatImageView) itemView.findViewById(R.id.favimg_player);
   button_add=(AppCompatImageView) itemView.findViewById(R.id.button_add);
   fav_title=(TextView)itemView.findViewById(R.id.fav_title);
   fav_points=(TextView)itemView.findViewById(R.id.fav_points);
   fav_ultima=(TextView)itemView.findViewById(R.id.fav_ultima);
   fav_pos=(TextView)itemView.findViewById(R.id.fav_pos);
   fav_cash=(TextView)itemView.findViewById(R.id.fav_cash);
   fav_dif=(TextView)itemView.findViewById(R.id.fav_dif);

}
}