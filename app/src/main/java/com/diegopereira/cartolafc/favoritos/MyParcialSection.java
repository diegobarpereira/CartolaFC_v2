package com.diegopereira.cartolafc.favoritos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.FavoritosActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.MODE_PRIVATE;
import static com.diegopereira.cartolafc.league.MyParcialSection.QTY_SHARED_PREF;
import static com.diegopereira.cartolafc.league.MyParcialSection.TOTAL_SHARED_PREF;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MyParcialSection extends StatelessSection {
    String title;
    List<TimePontos> list;
    Context context;
    Map<String, Double> mapparciais;
    Map<String, Double> mapliga;
    private DatabaseHelper database;
    public static boolean isClicked;
    public static boolean ultimaisClicked;
    int i = 0, j = 0, k = 0;

    public static final String SHARED_PREF_ID = "SHARED";
    public static final String ID_SHARED_PREF = "id";

    public static final String SHARED_PREF_NAME = "SHARED";
    public static final String TOTAL_SHARED_PREF = "total";
    public static final String QTY_SHARED_PREF = "qty";

    public MyParcialSection(Context context, List<TimePontos> list, Map<String, Double> mapparciais) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.favlist_item)
                .headerResourceId(R.layout.fav_header)
                .build());

        this.title = title;
        this.list = list;
        this.context = context;
        this.mapparciais = mapparciais;

        database = new DatabaseHelper(context);

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemViewHolder = (MyItemViewHolder) holder;

        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        Integer get_cap = list.get(position).getCapitaoId();
        //System.out.println("CAP: " + list.get(position).getCapitaoId());

        Integer jogador_id = 0;
        mapliga = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            jogador_id = list.get(position).getAtletas().get(i).getAtletaId();

            mapliga.put(String.valueOf(jogador_id), 0.0);

        }

        HashMap<String, Double> teste_ = new HashMap<>();
        double total = 0.0;
        double totalcap = 0.0;
        String ttotal = "";
        double x = 0.0;
        for (Map.Entry<String, Double> hashmap : mapparciais.entrySet()) {
            if (mapliga.containsKey(hashmap.getKey())) {
                //System.out.println(hashmap.getKey() + " - " + hashmap.getValue());
                mapparciais.put(hashmap.getKey(), hashmap.getValue());

                teste_.put(hashmap.getKey(), hashmap.getValue());

            }

            if (hashmap.getKey().contains(String.valueOf(get_cap))) {

                x = hashmap.getValue();
                //System.out.println("X: " + x);

            }

        }

        for (String k : teste_.keySet()) {
            //System.out.println(k + "\t" + teste_.get(k));

            total += teste_.get(k);
            totalcap = (total + x);
            ttotal = formatter.format(totalcap);

            for (int i = 0; i < list.size(); i++) {
                list.get(i).setParciais(totalcap);
                //teste.get(i).setParciais(0.0);
            }

            //System.out.println("SUM: " + ttotal);
            //holder.parciais.setText(ttotal); //
        }

        Integer id = 0;
        String qty = "";
        Double parc = 0.0;
        for (int i = 0; i < list.size(); i++) {
            Double pontototal = list.get(i).getPontos() + totalcap;
            //Double pontototal = teste.get(i).getPontos();

            qty = (teste_.size() + "/" + mapliga.size());
            parc = list.get(i).getParciais();
            list.get(i).setPontosrod(pontototal);
            list.get(i).setQty(qty);

            database.update(position + 1, list.get(position).getNome(), list.get(position).getParciais(), list.get(position).getPontosrod(),
                    list.get(position).getUrlEscudoPng(), list.get(position).getQty(), list.get(position).getTimeId());

        }

        List<TimePontos> item = database.getTimes();



            Glide.with(context)
                    .load(item.get(position).getUrlEscudoPng())
                    .into(itemViewHolder.favimg_player);


            itemViewHolder.fav_title.setText(item.get(position).getNome());
            itemViewHolder.fav_ultima.setText(formatter.format(item.get(position).getParciais()));

            itemViewHolder.fav_points.setText(formatter.format(item.get(position).getPontosrod()));

            itemViewHolder.fav_cash.setText(item.get(position).getQty());

            itemViewHolder.fav_pos.setText(String.valueOf(position + 1));


        Double diff = 0.00;

        for (int i = 0; i < position; i++) {
            itemViewHolder.fav_dif.setVisibility(View.VISIBLE);

            diff = item.get(position).getPontosrod()-item.get(0).getPontosrod();

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
                                                           editor.putString(QTY_SHARED_PREF, item.get(position).getQty().toString());
                                                           editor.putString(TOTAL_SHARED_PREF, item.get(position).getParciais().toString());
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
        headerHolder.times_name.setText("Times");
        headerHolder.league_ultima.setText("Parciais");


    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }


}
