package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MyParcialSection extends StatelessSection {
    String title;
    List<Times> list;
    List<TimePontos> teste;
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



    public MyParcialSection( Context context, String title, List<TimePontos> teste, Map<String, Double> mapparciais) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.leaguelist_item)
                .headerResourceId(R.layout.league_header)
                .build());

        this.title = title;
        this.list = list;
        this.teste = teste;
        this.context = context;
        this.mapparciais = mapparciais;

        database = new DatabaseHelper(context);

    }

    @Override
    public int getContentItemsTotal() {
        return teste.size();
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



        Integer get_cap = teste.get(position).getCapitaoId();




        Integer jogador_id = 0;
        mapliga = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            jogador_id = teste.get(position).getAtletas().get(i).getAtletaId();

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

            for (int i = 0; i < teste.size(); i++) {
                teste.get(i).setParciais(totalcap);
                //teste.get(i).setParciais(0.0);
            }

            //System.out.println("SUM: " + ttotal);
            //holder.parciais.setText(ttotal); //
        }

        Integer id = 0;
        String qty = "";
        Double parc = 0.0;
        for (int i = 0; i < teste.size(); i++) {
            Double pontototal = teste.get(i).getPontos() + totalcap;
            //Double pontototal = teste.get(i).getPontos();

            qty = (teste_.size() + "/" + mapliga.size());
            parc = teste.get(i).getParciais();
            teste.get(i).setPontosrod(pontototal);
            teste.get(i).setQty(qty);





                database.update(position+1, teste.get(position).getNome(), teste.get(position).getParciais(), teste.get(position).getPontosrod(),
                        teste.get(position).getUrlEscudoPng(), teste.get(position).getQty(), teste.get(position).getTimeId());

        }

        /*
            List<TimePontos> item = database.getTimes();

            Glide.with(context)
                    .load(item.get(position).getUrlEscudoPng())
                    .into(itemViewHolder.img_player);

            itemViewHolder.name.setText(item.get(position).getNome());
            itemViewHolder.ultima.setText(formatter.format(item.get(position).getParciais()));
            itemViewHolder.points.setText(formatter.format(item.get(position).getPontosrod()));

            itemViewHolder.cash.setText(item.get(position).getQty());

            itemViewHolder.pos.setText(String.valueOf(position + 1));
            itemViewHolder.var.setVisibility(View.GONE);
            itemViewHolder.dif.setVisibility(View.GONE);

            System.out.println("LEN: " + (item.size() - 1));

         */
            List<TimePontos> item = database.getTimes();

            if ( k == 0 ) {
                //item = database.getTimes();

                Glide.with(context)
                        .load(item.get(position).getUrlEscudoPng())
                        .into(itemViewHolder.img_player);

                itemViewHolder.name.setText(item.get(position).getNome());
                itemViewHolder.ultima.setText(formatter.format(item.get(position).getParciais()));
                itemViewHolder.points.setText(formatter.format(item.get(position).getPontosrod()));

                itemViewHolder.cash.setText(item.get(position).getQty());

                itemViewHolder.pos.setText(String.valueOf(position + 1));
                itemViewHolder.var.setVisibility(View.GONE);

                Double diff = 0.00;

                for (int i = 0; i < position; i++) {
                    itemViewHolder.dif.setVisibility(View.VISIBLE);

                    diff = item.get(position).getPontosrod()-item.get(0).getPontosrod();

                }


                if( position == 0) {
                    itemViewHolder.dif.setVisibility(View.INVISIBLE);
                }

                itemViewHolder.dif.setText(String.valueOf(formatter.format(diff)));

                id = item.get(position).getTimeId();

                Integer finalId = id;
                String finalQty = item.get(position).getQty();
                Double finalParc = item.get(position).getParciais();
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick( View v ) {
                                                           Toast.makeText(context, String.valueOf(finalId), Toast.LENGTH_SHORT).show();
                                                           SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                           SharedPreferences.Editor editor = preferences.edit();

                                                           editor.putString("ID_SHARED_PREF", String.valueOf(finalId));
                                                           editor.putString(QTY_SHARED_PREF, finalQty);
                                                           editor.putString(TOTAL_SHARED_PREF, String.valueOf(finalParc));


                                                           Intent intent = new Intent(context, LigaActivity.class);
                                                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                           editor.apply();
                                                           context.startActivity(intent);
                                                       }
                                                   }
                );

            }

            if ( k == 3) {
                item = database.getDESCTotalTimes();
                Glide.with(context)
                        .load(item.get(position).getUrlEscudoPng())
                        .into(itemViewHolder.img_player);

                itemViewHolder.name.setText(item.get(position).getNome());
                itemViewHolder.ultima.setText(formatter.format(item.get(position).getParciais()));
                itemViewHolder.points.setText(formatter.format(item.get(position).getPontosrod()));

                itemViewHolder.cash.setText(item.get(position).getQty());

                itemViewHolder.pos.setText(String.valueOf(position + 1));
                itemViewHolder.var.setVisibility(View.GONE);
                itemViewHolder.dif.setVisibility(View.GONE);

                id = item.get(position).getTimeId();

                Integer finalId = id;
                String finalQty = item.get(position).getQty();
                Double finalParc = item.get(position).getParciais();
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick( View v ) {
                                                           Toast.makeText(context, String.valueOf(finalId), Toast.LENGTH_SHORT).show();
                                                           SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                           SharedPreferences.Editor editor = preferences.edit();

                                                           editor.putString("ID_SHARED_PREF", String.valueOf(finalId));
                                                           editor.putString(QTY_SHARED_PREF, finalQty);
                                                           editor.putString(TOTAL_SHARED_PREF, String.valueOf(finalParc));


                                                           Intent intent = new Intent(context, LigaActivity.class);
                                                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                           editor.apply();
                                                           context.startActivity(intent);
                                                       }
                                                   }
                );
            }

            if ( k == 1 ) {
                item = database.getASCTimes();
                Glide.with(context)
                        .load(item.get(position).getUrlEscudoPng())
                        .into(itemViewHolder.img_player);

                itemViewHolder.name.setText(item.get(position).getNome());
                itemViewHolder.ultima.setText(formatter.format(item.get(position).getParciais()));
                itemViewHolder.points.setText(formatter.format(item.get(position).getPontosrod()));

                itemViewHolder.cash.setText(item.get(position).getQty());

                itemViewHolder.pos.setText(String.valueOf(position + 1));
                itemViewHolder.var.setVisibility(View.GONE);
                itemViewHolder.dif.setVisibility(View.GONE);

                id = item.get(position).getTimeId();

                Integer finalId = id;
                String finalQty = item.get(position).getQty();
                Double finalParc = item.get(position).getParciais();
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick( View v ) {
                                                           Toast.makeText(context, String.valueOf(finalId), Toast.LENGTH_SHORT).show();
                                                           SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                           SharedPreferences.Editor editor = preferences.edit();

                                                           editor.putString("ID_SHARED_PREF", String.valueOf(finalId));
                                                           editor.putString(QTY_SHARED_PREF, finalQty);
                                                           editor.putString(TOTAL_SHARED_PREF, String.valueOf(finalParc));


                                                           Intent intent = new Intent(context, LigaActivity.class);
                                                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                           editor.apply();
                                                           context.startActivity(intent);
                                                       }
                                                   }
                );

            } else if ( k == 2 ) {
                item = database.getDESCTimes();
                Glide.with(context)
                        .load(item.get(position).getUrlEscudoPng())
                        .into(itemViewHolder.img_player);

                itemViewHolder.name.setText(item.get(position).getNome());
                itemViewHolder.ultima.setText(formatter.format(item.get(position).getParciais()));
                itemViewHolder.points.setText(formatter.format(item.get(position).getPontosrod()));

                itemViewHolder.cash.setText(item.get(position).getQty());

                itemViewHolder.pos.setText(String.valueOf(position + 1));
                itemViewHolder.var.setVisibility(View.GONE);
                itemViewHolder.dif.setVisibility(View.GONE);

                id = item.get(position).getTimeId();

                Integer finalId = id;
                String finalQty = item.get(position).getQty();
                Double finalParc = item.get(position).getParciais();

                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick( View v ) {
                                                           Toast.makeText(context, String.valueOf(finalId), Toast.LENGTH_SHORT).show();
                                                           SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                           SharedPreferences.Editor editor = preferences.edit();

                                                           editor.putString("ID_SHARED_PREF", String.valueOf(finalId));
                                                           editor.putString(QTY_SHARED_PREF, finalQty);
                                                           editor.putString(TOTAL_SHARED_PREF, String.valueOf(finalParc));


                                                           Intent intent = new Intent(context, LigaActivity.class);
                                                           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                           editor.apply();
                                                           context.startActivity(intent);
                                                       }
                                                   }
                );
            }




    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;
        headerHolder.league_name.setText(title);
        headerHolder.league_ultima.setText("Parciais");

        headerHolder.league_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
                    isClicked = TRUE;

                    LeagueActivity.sectionAdapter.notifyDataSetChanged();

                    i++;
                    k = 3;

                }
                else if (i == 1) {
                    //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
                    isClicked = FALSE;

                    LeagueActivity.sectionAdapter.notifyDataSetChanged();

                    i = 0;
                    k = 0;
                }

            }
        });

        headerHolder.league_ultima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if (j == 0) {
                    Toast.makeText(context, String.valueOf(j), Toast.LENGTH_SHORT).show();
                    ultimaisClicked = TRUE;

                    LeagueActivity.sectionAdapter.notifyDataSetChanged();
                    j++;
                    k = 1;
                }
                else if (j == 1) {
                    Toast.makeText(context, String.valueOf(j), Toast.LENGTH_SHORT).show();

                    ultimaisClicked = FALSE;
                    LeagueActivity.sectionAdapter.notifyDataSetChanged();
                    j = 0;
                    k = 2;
                }
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }


}
