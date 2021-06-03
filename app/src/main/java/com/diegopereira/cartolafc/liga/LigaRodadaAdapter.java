package com.diegopereira.cartolafc.liga;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.squareup.picasso.Picasso;

import org.apache.commons.collections4.CollectionUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;
import static java.util.stream.Collectors.toMap;

public class LigaRodadaAdapter extends RecyclerView.Adapter<LigaRodadaAdapter.ViewHolder> {
    private Context context;
    private List<Atleta> list;
    Map<Integer, Clubes> clubes;
    Map<Integer, Posicoes> posicoes;
    private Integer capitao;
    List<Map.Entry<String, Atletas>> wordList = new ArrayList<Map.Entry<String, Atletas>>();

    double totalparciais = 0.00;

    public LigaRodadaAdapter(Context context, List<Atleta> list, Integer capitao, Map<Integer, Posicoes> posicoes, Map<Integer, Clubes> clubes, List<Map.Entry<String, Atletas>> wordList) {
        this.context = context;
        this.list = list;
        this.capitao = capitao;
        this.clubes = clubes;
        this.posicoes = posicoes;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligalist_item, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

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

        holder.img_cap.setVisibility(View.INVISIBLE);

        Glide.with(context)
                .load(clubeEscudo)
                .into(holder.img_clube);

        holder.tv_jogador.setText(String.valueOf(list.get(position).getApelido()));

        holder.tv_posicao.setText(posicao);

        Integer atletaId = list.get(position).getAtletaId();

        double pts_parciais = 0.00;
        for (int i = 0; i < wordList.size(); i++) {

            if (String.valueOf(atletaId).contains(wordList.get(i).getKey())) {
                pts_parciais = wordList.get(i).getValue().getPontuacao();
            }
        }

        totalparciais += pts_parciais;

        holder.tv_pontos.setText(formatter.format(pts_parciais));

        if (atletaId.equals(capitao)) {
            holder.tv_pontos.setText("(" + formatter.format(pts_parciais) + " x2) " + formatter.format(pts_parciais * 2));
            holder.tv_pontos.setTypeface(null, Typeface.BOLD);
            holder.img_cap.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img_clube, img_cap;
        private AppCompatTextView tv_jogador, tv_posicao, tv_pontos, capitaoId, tv_scouts;

        public ViewHolder(View itemView) {
            super(itemView);

            img_clube = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
            img_cap = (AppCompatImageView) itemView.findViewById(R.id.img_cap);
            tv_jogador = (AppCompatTextView) itemView.findViewById(R.id.tv_jogador);
            tv_posicao = (AppCompatTextView) itemView.findViewById(R.id.tv_posicao);
            tv_pontos = (AppCompatTextView) itemView.findViewById(R.id.tv_pontos);
            tv_scouts = (AppCompatTextView) itemView.findViewById(R.id.tv_scouts);

        }
    }

}