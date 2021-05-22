package com.diegopereira.cartolafc.league;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MySection extends Section {
    String title;
    List<Times> list;
    Context context;
    public static boolean isClicked;
    public static boolean ultimaisClicked;
    int i = 0, j = 0;


    public MySection( Context context, String title, List<Times> list) {
        // call constructor with layout resources for this Section header, footer and items
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

        // bind your view here
        System.out.println(list);

        Double diff = 0.00;
        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        for (int i = 0; i < position; i++) {
            itemViewHolder.dif.setVisibility(View.VISIBLE);

            if (list.get(position).getPontos().getCampeonato() == null) {

            } else {
                diff = list.get(position).getPontos().getCampeonato() - list.get(0).getPontos().getCampeonato();
            }
        }


        if( position == 0) {
            itemViewHolder.dif.setVisibility(View.INVISIBLE);
        }

        if (diff == null) {
            itemViewHolder.dif.setText("0.00");
        } else {
            itemViewHolder.dif.setText(String.valueOf(formatter.format(diff)));
        }

        String name = list.get(position).getNome();
        itemViewHolder.name.setText(name);

        if (list.get(position).getRanking().getCampeonato() == null) {
            for (int i = 0; i < list.size(); i++) {

                itemViewHolder.pos.setText(String.valueOf(position + 1));
                System.out.println(String.valueOf(position));

            }

            } else {
            Integer posicao = list.get(position).getRanking().getCampeonato();
            String pos = String.valueOf(posicao);
            itemViewHolder.pos.setText(pos);
        }

        if (list.get(position).getPontos().getCampeonato() == null) {
            itemViewHolder.points.setText("0.00");
        } else {
            System.out.println(list.get(position).getPontos().getCampeonato());
            Double pontos = list.get(position).getPontos().getCampeonato();
            String points = String.valueOf(formatter.format(pontos));
            itemViewHolder.points.setText(points);
        }

        if (list.get(position).getVariacao().getCampeonato() == null) {
            itemViewHolder.var.setText("=");
        } else {
            Integer var = list.get(position).getVariacao().getCampeonato();
            if (var > 0) {
                String variacao = String.valueOf(var);
                itemViewHolder.var.setText("+" + variacao);
                itemViewHolder.var.setTextColor(Color.parseColor("#006400"));
            }
            if (var < 0) {
                String variacao = String.valueOf(var);
                itemViewHolder.var.setText(variacao);
                itemViewHolder.var.setTextColor(Color.RED);
            }
            if (var == 0) {
                String variacao = String.valueOf(var);
                itemViewHolder.var.setText("=");
                itemViewHolder.var.setTextColor(Color.GRAY);
            }
        }

        if (list.get(position).getPontos().getRodada() == null) {
            itemViewHolder.ultima.setText("0.00");
        } else {
            Double ultima = list.get(position).getPontos().getRodada();
            String rodada = String.valueOf(formatter.format(ultima));
            itemViewHolder.ultima.setText(rodada);
        }

        Double cash = list.get(position).getPatrimonio();
        String dinheiro = String.valueOf(formatter.format(cash));
        itemViewHolder.cash.setText("$" + dinheiro);

        String symbol = list.get(position).getUrl_escudo_png();
        Glide.with(context)
                .load(symbol)
                .into(itemViewHolder.img_player);

//        if (isClicked || ultimaisClicked) {
//            itemViewHolder.dif.setVisibility(View.INVISIBLE);
//            itemViewHolder.var.setVisibility(View.GONE);
//
//
//        }
//        for (int k = 0; k < list.size(); k++) {
//            if (ultimaisClicked) {
//                itemViewHolder.pos.setText(String.valueOf(position + 1));
//            }
//        }


        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   //Toast.makeText(context, list.get(position).getTime_id().toString(), Toast.LENGTH_SHORT).show();
                                                   SharedPreferences preferences = context.getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);
                                                   SharedPreferences.Editor editor = preferences.edit();

                                                   editor.putString("ID_SHARED_PREF", list.get(position).getTime_id().toString());
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


//        headerHolder.league_total.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (i == 0) {
//                    //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
//                    isClicked = TRUE;
//
//                    LeagueActivity.sortTotalASC();
//                    LeagueActivity.sectionAdapter.notifyDataSetChanged();
//
//                    i++;
//                }
//                else if (i == 1) {
//                    //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
//                    LeagueActivity.sortTotalDESC();
//                    LeagueActivity.sectionAdapter.notifyDataSetChanged();
//
//                    isClicked = FALSE;
//
//                    i = 0;
//                }
//
//            }
//        });

//        headerHolder.league_ultima.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (j == 0) {
//                    //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
//                    ultimaisClicked = TRUE;
//
//                    LeagueActivity.sortUltimaASC();
//                    LeagueActivity.sectionAdapter.notifyDataSetChanged();
//
//
//                    j++;
//                }
//                else if (j == 1) {
//                    //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
//                    LeagueActivity.sortUltimaDESC();
//                    LeagueActivity.sectionAdapter.notifyDataSetChanged();
//
//                    ultimaisClicked = FALSE;
//
//                    j = 0;
//                }
//
//            }
//        });

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }





}
