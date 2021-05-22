package com.diegopereira.cartolafc.parciais;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.jogadores.Atleta;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ParciaisRecyclerAdapter extends RecyclerView.Adapter<ParciaisRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Map.Entry<String, Atletas>> list;
    private Map<Integer, Posicoes> posicoes;
    private Map<Integer, Clubes> clubes;

    public ParciaisRecyclerAdapter( Context context, List<Map.Entry<String, Atletas>> list, Map<Integer, Posicoes> posicoes, Map<Integer, Clubes> clubes) {
        this.context = context;
        this.list = list;
        this.posicoes = posicoes;
        this.clubes = clubes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parciaislist_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        String posicao = null;
        for(Map.Entry<Integer, Posicoes> entry:posicoes.entrySet()) {
            if ((String.valueOf(list.get(position).getValue().getPosicaoId())).equals(entry.getValue().getId())) {
                posicao = entry.getValue().getNome();
            }
        }

        String clubeEscudo = null;
        for(Map.Entry<Integer, Clubes> entry:clubes.entrySet()) {
            if ((list.get(position).getValue().getClubeId()).equals(entry.getValue().getId())) {
                clubeEscudo = entry.getValue().getEscudos().get_60x60();
            }
        }

        List<String> listaScout = new ArrayList<>();
        Map<String,Integer> scmap = list.get(position).getValue().getScout();
        if(scmap != null) {
            for (Map.Entry<String, Integer> entry : scmap.entrySet()) {
                if (entry.getKey() != null) {
                    listaScout.add(entry.getValue() + entry.getKey());
                }
            }
        }

        String id = list.get(position).getKey();
        String apelido = list.get(position).getValue().getApelido();
        double pontuacao = list.get(position).getValue().getPontuacao();

        holder.tv_playerparciais.setText(apelido);
        holder.tv_pontosparciais.setText(String.valueOf(pontuacao));
        holder.tv_posicaoparciais.setText(posicao);
        if (listaScout.isEmpty()) {
            holder.tv_scouts.setText("");
        } else {
            holder.tv_scouts.setText(listaScout.toString());
        }
        Glide.with(context)
                .load(clubeEscudo)
                .into(holder.img_clube);

        System.out.println(list.get(position).getKey() + " : " + list.get(position).getValue().getApelido() + " : " + posicao + " : " + clubeEscudo + " : " + list.get(position).getValue().getPontuacao()
                + " : " + listaScout);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img_clube;
        private AppCompatTextView tv_playerparciais, tv_pontosparciais, tv_posicaoparciais, tv_scouts;

        public ViewHolder( View itemView) {
            super(itemView);

            tv_playerparciais = (AppCompatTextView) itemView.findViewById(R.id.tv_playerparciais);
            tv_pontosparciais = (AppCompatTextView) itemView.findViewById(R.id.tv_pontosparciais);
            tv_posicaoparciais = (AppCompatTextView) itemView.findViewById(R.id.tv_posicaoparciais);
            img_clube = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
            tv_scouts = (AppCompatTextView) itemView.findViewById(R.id.tv_scouts);
        }
    }

}