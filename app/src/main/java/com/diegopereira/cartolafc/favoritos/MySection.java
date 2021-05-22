package com.diegopereira.cartolafc.favoritos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.FavoritosActivity;
import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.groups.DatabaseHelper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MySection extends Section {
    List<TimePontos> list;
    Context context;
    public static boolean isClicked;
    public static boolean ultimaisClicked;
    int i = 0, j = 0;

    DatabaseHelper databaseHelper;
    com.diegopereira.cartolafc.favoritos.DatabaseHelper favdb;


    public MySection(Context context, List<TimePontos> list) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.favlist_item)
                .headerResourceId(R.layout.fav_header)
                .build());

        this.list = list;
        this.context = context;

    }

    @Override
    public int getContentItemsTotal() {
        return list.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemViewHolder = (MyItemViewHolder) holder;
        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));
            double get_pontos;
            double get_ultima;
            double get_patrimonio;
        if (list.get(position).getPontos() == null) {
            get_pontos = 0.00;
        } else {
            get_pontos = list.get(position).getPontos();
        }
        if (list.get(position).getUltima() == null) {
            get_ultima = 0.00;
        } else {
            get_ultima = list.get(position).getUltima();
        }
        if (list.get(position).getPatrimonio() == null) {
            get_patrimonio = 0.00;
        } else {
            get_patrimonio = list.get(position).getPatrimonio();
        }

        Glide.with(context)
                .load(list.get(position).getUrlEscudoPng())
                .into(itemViewHolder.favimg_player);
        itemViewHolder.fav_title.setText(list.get(position).getNome());
        itemViewHolder.fav_points.setText(formatter.format(get_pontos));
        itemViewHolder.fav_ultima.setText(formatter.format(get_ultima));
        itemViewHolder.fav_cash.setText(formatter.format(get_patrimonio));
        itemViewHolder.fav_pos.setText(String.valueOf(position + 1));

        Double diff = 0.00;

        for (int i = 0; i < position; i++) {
            itemViewHolder.fav_dif.setVisibility(View.VISIBLE);

            diff = list.get(position).getPontos()-list.get(0).getPontos();

        }


        if( position == 0) {
            itemViewHolder.fav_dif.setVisibility(View.INVISIBLE);
        }

        itemViewHolder.fav_dif.setText(String.valueOf(formatter.format(diff)));

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           //Toast.makeText(context, list.get(position).getTime_id().toString(), Toast.LENGTH_SHORT).show();
                                                           SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                           SharedPreferences.Editor editor = preferences.edit();

                                                           editor.putString("ID_SHARED_PREF", list.get(position).getTimeId().toString());
                                                           Intent intent = new Intent(context, LigaActivity.class);
                                                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                           editor.apply();
                                                           context.startActivity(intent);
                                                       }
                                                   }
        );

        itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, list.get(position).getNome(), Toast.LENGTH_SHORT).show();
                databaseHelper = new DatabaseHelper(context);
                favdb = new com.diegopereira.cartolafc.favoritos.DatabaseHelper(context);
                databaseHelper.delete(list.get(position).getTimeId());
                favdb.delete(list.get(position).getTimeId());
                list.remove(position);
                FavoritosActivity.sectionAdapter.notifyDataSetChanged();
                System.out.println("LongClick: ");
                return true;
            }

        });


    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;



    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }





}
