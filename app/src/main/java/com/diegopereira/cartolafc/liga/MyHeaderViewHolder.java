package com.diegopereira.cartolafc.liga;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;


public class MyHeaderViewHolder extends RecyclerView.ViewHolder {
    public final TextView tit_res;


    public MyHeaderViewHolder(View itemView) {
        super(itemView);
        tit_res = (TextView)itemView.findViewById(R.id.tit_res);

    }
}