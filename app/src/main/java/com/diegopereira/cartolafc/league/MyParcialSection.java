package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.parciais.Atletas;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

import static android.content.Context.MODE_PRIVATE;

public class MyParcialSection extends Section {
    String title;
    List<Times> list;
    Context context;

    public static boolean isClicked;
    public static boolean ultimaisClicked;
    //int i = 0, j = 0, k = 0;

    public static final String SHARED_PREF_ID = "SHARED";
    public static final String ID_SHARED_PREF = "id";

    public static final String SHARED_PREF_NAME = "SHARED";
    public static final String TOTAL_SHARED_PREF = "total";
    public static final String QTY_SHARED_PREF = "qty";

    public MyParcialSection(Context context, String title, List<Times> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.leaguelist_item)
                .headerResourceId(R.layout.league_header)
                .build());

        this.title = title;
        this.list = list;
        this.context = context;

    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
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


        Double diff = list.get(position).getTotal()-list.get(0).getTotal();

        if (position == 0) {
            itemViewHolder.dif.setVisibility(View.INVISIBLE);
        } else {
            itemViewHolder.dif.setText(formatter.format(diff));
        }

        Glide.with(context)
                .load(list.get(position).getUrl_escudo_png())
                .into(itemViewHolder.img_player);
        itemViewHolder.name.setText(list.get(position).getNome());
        itemViewHolder.ultima.setText(formatter.format(list.get(position).getPontosParciais()));
        itemViewHolder.points.setText(formatter.format(list.get(position).getTotal()));

        itemViewHolder.pos.setText(String.valueOf(position + 1));

        Integer new_variacao = list.get(position).getNew_variacao() - (position + 1);
        if (new_variacao > 0) {

            itemViewHolder.var.setText(String.valueOf("+" + new_variacao));
            itemViewHolder.var.setTextColor(Color.GREEN);
        }
        if (new_variacao < 0 ) {

            itemViewHolder.var.setText(String.valueOf(new_variacao));
            itemViewHolder.var.setTextColor(Color.RED);
        }
        if (new_variacao == 0 ) {

            itemViewHolder.var.setText("=");
            itemViewHolder.var.setTextColor(Color.GRAY);
        }

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           //Toast.makeText(context, list.get(position).getTime_id().toString(), Toast.LENGTH_SHORT).show();
                                                           SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                           SharedPreferences.Editor editor = preferences.edit();

                                                           editor.putString("ID_SHARED_PREF", list.get(position).getTime_id().toString());
                                                           editor.putString(TOTAL_SHARED_PREF, String.valueOf(list.get(position).getPontosParciais()));
                                                           Intent intent = new Intent(context, LigaActivity.class);
                                                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                           editor.apply();
                                                           context.startActivity(intent);
                                                       }
                                                   }
        );


    }



    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;
        headerHolder.league_name.setText(title);
        headerHolder.league_ultima.setText("Parciais");

        headerHolder.league_ultima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeagueActivity.sortUltimaDESC();
            }
        });

        headerHolder.league_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeagueActivity.sortTotalDESC();
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }


}
