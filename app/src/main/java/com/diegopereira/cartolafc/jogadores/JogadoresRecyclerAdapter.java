package com.diegopereira.cartolafc.jogadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class JogadoresRecyclerAdapter extends RecyclerView.Adapter<JogadoresRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Atleta> list;
    private Map<Integer,Clubes> map;
    private Map<Integer,Posicoes> posicoes;
    private Map<Integer,Status> status;
    private String clube;
    private String posicao;
    private String stats;

    public JogadoresRecyclerAdapter(Context context, List<Atleta> list, Map<Integer,Clubes> map, Map<Integer,Posicoes> posicoes, Map<Integer,Status> status) {
        this.context = context;
        this.list = list;
        this.map = map;
        this.posicoes = posicoes;
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jogadoreslist_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.get(position).getStatusId().equals("7")) {
            holder.tv_player.setText(String.valueOf(list.get(position).getApelido()));

            DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));
            String get_preco = formatter.format(list.get(position).getPrecoNum());
            holder.tv_preco.setText("C$: " + get_preco);

            String get_media = formatter.format(list.get(position).getMediaNum());
            holder.tv_media.setText("Média: " + get_media);

            String get_ultima = formatter.format((list.get(position).getPontosNum()));
            holder.tv_ultima.setText("Última " + get_ultima);

            for(Map.Entry<Integer, Status> entry:status.entrySet()) {
                //System.out.println(entry.getKey() + " / " + entry.getValue().getNome());
                if ((list.get(position).getStatusId()).equals(entry.getValue().getId())) {
                    stats = entry.getValue().getNome();
                }
            }
            holder.tv_status.setText(stats);

            for(Map.Entry<Integer, Posicoes> entry:posicoes.entrySet()) {
                //System.out.println(entry.getKey() + " / " + entry.getValue().getNome());
                if ((list.get(position).getPosicaoId()).equals(entry.getValue().getId())) {
                    posicao = entry.getValue().getNome();
                }
            }
            holder.tv_posicao.setText(posicao);

            for(Map.Entry<Integer, Clubes> entry:map.entrySet()) {
                if ((list.get(position).getClubeId()).equals(String.valueOf(entry.getValue().getId()))) {
                    clube = entry.getValue().getNome();
                }
            }

            holder.tv_clube.setText(clube);


            if (list.get(position).getFoto() == null) {
                Picasso.with(context)
                        .load(R.drawable.no_pic)
                        .placeholder(R.drawable.no_pic)
                        .resize(70, 70)
                        .into(holder.img_player);
            } else {
                if (list.get(position).getFoto().contains("FORMATO")) {

                    String img = list.get(position).getFoto().replace("FORMATO", "140x140");
                    Picasso.with(context)
                            .load(img)
                            .resize(70, 70)
                            .into(holder.img_player);
                }
            }

        } else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img_player;
        private AppCompatTextView tv_player, tv_posicao, tv_clube, tv_preco, tv_status, tv_media, tv_ultima;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_player = (AppCompatTextView) itemView.findViewById(R.id.tv_player);
            img_player = (AppCompatImageView) itemView.findViewById(R.id.img_player);
            tv_posicao = (AppCompatTextView) itemView.findViewById(R.id.tv_posicao);
            tv_clube = (AppCompatTextView) itemView.findViewById(R.id.tv_clube);
            tv_preco = (AppCompatTextView) itemView.findViewById(R.id.tv_preco);
            tv_status = (AppCompatTextView) itemView.findViewById(R.id.tv_status);
            tv_media = (AppCompatTextView) itemView.findViewById(R.id.tv_media);
            tv_ultima = (AppCompatTextView) itemView.findViewById(R.id.tv_ultima);
        }
    }
}
