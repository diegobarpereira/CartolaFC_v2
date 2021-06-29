package com.diegopereira.cartolafc.liga;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;


class MyItemViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView name, tv_posicao, tv_pontos, tv_scouts;
    public final AppCompatImageView img_clube, img_cap;
//           , pos, points, dif, var, ultima, cash;
//   public final AppCompatImageView img_player;

    public MyItemViewHolder(View itemView) {
        super(itemView);
        name = (AppCompatTextView) itemView.findViewById(R.id.tv_jogador);
        tv_posicao = (AppCompatTextView) itemView.findViewById(R.id.tv_posicao);
        tv_pontos = (AppCompatTextView) itemView.findViewById(R.id.tv_pontos);
        tv_scouts = (AppCompatTextView) itemView.findViewById(R.id.tv_scouts);
//   pos=(TextView)itemView.findViewById(R.id.pos);
//   points=(TextView)itemView.findViewById(R.id.points);
//   dif=(TextView)itemView.findViewById(R.id.dif);
//   var=(TextView)itemView.findViewById(R.id.var);
//   ultima=(TextView)itemView.findViewById(R.id.ultima);
//   cash=(TextView)itemView.findViewById(R.id.cash);

        img_clube = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
        img_cap = (AppCompatImageView) itemView.findViewById(R.id.img_cap);

    }
}