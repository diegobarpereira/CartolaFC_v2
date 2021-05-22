package com.diegopereira.cartolafc.ligaauth;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;

public class MyHeaderViewHolder extends RecyclerView.ViewHolder {
    public final TextView nameliga;

    public MyHeaderViewHolder(View itemView) {
        super(itemView);
        nameliga = (TextView)itemView.findViewById(R.id.nameliga);

    }
}