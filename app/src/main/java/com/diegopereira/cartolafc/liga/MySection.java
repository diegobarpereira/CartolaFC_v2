package com.diegopereira.cartolafc.liga;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.parciais.Atletas;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class MySection extends Section {
    String title;
    List<Atleta> res_list;
    List<Atleta> list;
    Context context;
    Map<Integer, Clubes> clubes = new HashMap();
    Map<Integer, Posicoes> posicoes = new HashMap();
        
    Integer capitao;


    public MySection(Context context, String title, List<Atleta> list, Map<Integer, Clubes> clubes,
                     Map<Integer, Posicoes> posicoes, Integer capitao) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.ligalist_item)
                .headerResourceId(R.layout.liga_header)
                .build());

        this.title = title;
        this.list = list;
        this.context = context;
        this.clubes = clubes;
        this.posicoes = posicoes;
        this.capitao = capitao;

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

        String clubeEscudo = null;
        for (Map.Entry<Integer, Clubes> entry : clubes.entrySet()) {
            if ((list.get(position).getClubeId()).equals(entry.getValue().getId())) {
                clubeEscudo = entry.getValue().getEscudos().get_60x60();
            }
        }

        String posicao = null;
        for (Map.Entry<Integer, Posicoes> entry : posicoes.entrySet()) {
            if ((String.valueOf(list.get(position).getPosicaoId())).equals(entry.getValue().getId())) {
                posicao = entry.getValue().getNome();
            }
        }


        itemViewHolder.img_cap.setVisibility(View.INVISIBLE);

        itemViewHolder.tv_posicao.setText(posicao);

        Glide.with(context)
                .load(clubeEscudo)
                .into(itemViewHolder.img_clube);

        itemViewHolder.name.setText(list.get(position).getApelido());

        Integer atletaId = list.get(position).getAtletaId();

        itemViewHolder.tv_pontos.setText(formatter.format(list.get(position).getPontosNum()));


        if (atletaId.equals(capitao)) {
            itemViewHolder.tv_pontos.setText("(" + formatter.format(list.get(position).getPontosNum()) + " x2) " + formatter.format(list.get(position).getPontosNum() * 2));
            itemViewHolder.tv_pontos.setTypeface(null, Typeface.BOLD);
            itemViewHolder.img_cap.setVisibility(View.VISIBLE);
            itemViewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }




    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;
        headerHolder.tit_res.setText(title);


    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }


}
