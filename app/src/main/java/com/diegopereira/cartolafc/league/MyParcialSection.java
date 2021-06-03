package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.liga.Atleta;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.parciais.Clubes;
import com.diegopereira.cartolafc.parciais.Posicoes;
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
import java.util.stream.Collectors;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MyParcialSection extends StatelessSection {
    String title;
    List<Times> list;
    List<Map.Entry<String, Atletas>> wordList = new ArrayList<Map.Entry<String, Atletas>>();
    List<com.diegopereira.cartolafc.teste.Atletas> atletas;
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


    public MyParcialSection(Context context, String title, List<Times> list, List<com.diegopereira.cartolafc.teste.Atletas> atletas, List<Map.Entry<String, Atletas>> wordList) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.leaguelist_item)
                .headerResourceId(R.layout.league_header)
                .build());

        this.title = title;
        this.list = list;
        this.wordList = wordList;
        this.atletas = atletas;
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
        Double diff = 0.00;

        String ids_parciais = wordList.get(position).getKey();
        //System.out.println("IDS: " + ids_parciais);

        String atletas_ids = null;
        for (int j = 0; j < 12; j++) {
            atletas_ids = String.valueOf(atletas.get(j).getAtletaId());
            //System.out.println("Atletas_IDS: " + atletas_ids);
        }

        String soma = "";
        List<com.diegopereira.cartolafc.teste.Atletas> pts = atletas.stream().filter(e -> wordList.contains(e)).collect(Collectors.toList());
        System.out.println(pts);
        if (String.valueOf(atletas_ids).contains(ids_parciais)) {
            System.out.println("Sim");
            soma = String.valueOf(wordList.get(position).getValue().getPontuacao());

        }
        itemViewHolder.ultima.setText(soma);

        for (int i = 0; i < position; i++) {
            itemViewHolder.dif.setVisibility(View.VISIBLE);

            if (list.get(position).getPontos().getCampeonato() == null) {

            } else {
                diff = list.get(position).getPontos().getCampeonato() - list.get(0).getPontos().getCampeonato();
            }
        }


        if (position == 0) {
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
                //System.out.println(String.valueOf(position));

            }

        } else {
            Integer posicao = list.get(position).getRanking().getCampeonato();
            String pos = String.valueOf(posicao);
            itemViewHolder.pos.setText(pos);
        }

        if (list.get(position).getPontos().getCampeonato() == null) {
            itemViewHolder.points.setText("0.00");
        } else {
            //System.out.println(list.get(position).getPontos().getCampeonato());
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


    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;
        headerHolder.league_name.setText(title);
        headerHolder.league_ultima.setText("Parciais");


    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }


}
