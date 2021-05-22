package com.diegopereira.cartolafc.league;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Times> list;

    public RecyclerViewAdapter(Context context, List<Times> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.leaguelist_item,parent,false);
        return new RecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Double diff = 0.00;
        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        for (int i = 0; i < position; i++) {
            holder.dif.setVisibility(View.VISIBLE);

            diff = list.get(position).getPontos().getCampeonato()-list.get(0).getPontos().getCampeonato();

        }


        if( position == 0) {
            holder.dif.setVisibility(View.INVISIBLE);
        }

        holder.dif.setText(String.valueOf(formatter.format(diff)));

        String name = list.get(position).getNome();
        holder.name.setText(name);

        System.out.println(list.get(position).getRanking().getCampeonato());
        Integer posicao = list.get(position).getRanking().getCampeonato();
        String pos = String.valueOf(posicao);
        holder.pos.setText(pos);

        Double pontos = list.get(position).getPontos().getCampeonato();
        String points = String.valueOf(formatter.format(pontos));
        holder.points.setText(points);

        Integer var = list.get(position).getVariacao().getCampeonato();
        if (var > 0) {
            String variacao = String.valueOf(var);
            holder.var.setText("+" + variacao);
            holder.var.setTextColor(Color.GREEN);
        }
        if (var < 0 ) {
            String variacao = String.valueOf(var);
            holder.var.setText(variacao);
            holder.var.setTextColor(Color.RED);
        }
        if (var == 0 ) {
            String variacao = String.valueOf(var);
            holder.var.setText("=");
            holder.var.setTextColor(Color.GRAY);
        }

        Double ultima = list.get(position).getPontos().getRodada();
        String rodada = String.valueOf(formatter.format(ultima));
        holder.ultima.setText(rodada);

        Double cash = list.get(position).getPatrimonio();
        String dinheiro = String.valueOf(formatter.format(cash));
        holder.cash.setText("$" + dinheiro);

        String symbol = list.get(position).getUrl_escudo_png();
        Picasso.with(context)
                .load(symbol)
                .resize(70, 70)
                .into(holder.img_player);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Toast.makeText(context, list.get(position).getTime_id().toString(), Toast.LENGTH_SHORT).show();
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
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private TextView name, pos, points, dif, var, ultima, cash;
        private AppCompatImageView img_player;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name=(TextView)itemView.findViewById(R.id.title);
            pos=(TextView)itemView.findViewById(R.id.pos);
            points=(TextView)itemView.findViewById(R.id.points);
            dif=(TextView)itemView.findViewById(R.id.dif);
            var=(TextView)itemView.findViewById(R.id.var);
            ultima=(TextView)itemView.findViewById(R.id.ultima);
            cash=(TextView)itemView.findViewById(R.id.cash);
            img_player=(AppCompatImageView)itemView.findViewById(R.id.img_player);
        }

    }



    }
