package com.diegopereira.cartolafc.ligaauth;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;

    class MyItemViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatImageView img_liga;
    public final TextView tv_liga, tv_membros;

    public MyItemViewHolder(View itemView) {
        super(itemView);
        tv_liga = (TextView)itemView.findViewById(R.id.tv_liga);
        img_liga = (AppCompatImageView)itemView.findViewById(R.id.img_liga);
        tv_membros = (TextView)itemView.findViewById(R.id.tv_membros);

    }
}