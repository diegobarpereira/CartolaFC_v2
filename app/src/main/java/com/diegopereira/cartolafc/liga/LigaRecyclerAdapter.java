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

import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import org.apache.commons.collections4.CollectionUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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


public class LigaRecyclerAdapter extends RecyclerView.Adapter<LigaRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Atleta> list;
    //private List<Teste> newlist;
    //private List<Teste> tmpList;
    //private Map<String, Double> mapparciais;
    private Map<String, Double> mapliga;
    private Integer capitao, idgol, idlat, idzag, idmei, idata, idtec;
    private String stat;

    public static final String SHARED_PREF_NAME = "SHARED";
    public static final String SHAREDCAP_PREF_NAME = "SHAREDCAP";
    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String TOTAL_SHARED_PREF = "total";
    public static final String QTY_SHARED_PREF = "qty";
    public static final String CAP_SHARED_PREF = "cap";



    //public LigaRecyclerAdapter(Context context, List<Atleta> list, Integer capitao, Integer idgol, Integer idlat,
      //                         Integer idzag, Integer idmei, Integer idata, Integer idtec) {
        public LigaRecyclerAdapter(Context context, List<Atleta> list, Integer capitao, Integer idgol, Integer idlat,
                Integer idzag, Integer idmei, Integer idata, Integer idtec, Map<String, Double> mapliga) {
        this.context = context;
        this.list = list;
        this.capitao = capitao;
        this.idgol = idgol;
        this.idlat = idlat;
        this.idzag = idzag;
        this.idmei = idmei;
        this.idata = idata;
        this.idtec = idtec;
        //this.newlist = newlist;
        //this.tmpList = tmpList;
        //this.mapparciais = mapparciais;
        this.mapliga = mapliga;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ligalist_item,parent,false);
        return new ViewHolder(v);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.img_cap.setVisibility(View.INVISIBLE);

        String clube = String.valueOf(list.get(position).getClubeId());
        if (clube.equals("262")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/04/09/Flamengo-30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("263")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/02/04/botafogo-30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("264")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/30/Corinthians_30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("265")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/bahia_30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }

        if (clube.equals("266")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2015/01/12/Fluminense-escudo.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("267")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/07/29/Vasco-30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("275")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/palmeiras_30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }

        if (clube.equals("276")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/sao_paulo_30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("277")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/santos_30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("280")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/01/01/30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("282")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/11/23/Atletico-Mineiro-escudo30px.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("284")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2014/04/14/gremio_30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("285")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2016/05/03/inter30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("290")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/05/01/Goias_30px.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("292")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2015/07/21/sport30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("293")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/09/09/Athletico-PR-30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("294")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2017/03/29/coritiba30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("354")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2019/10/10/ceara-30x30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("356")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2018/06/10/fortaleza-ec-30px.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }
        if (clube.equals("373")) {
            Picasso.with(context)
                    .load("https://s.glbimg.com/es/sde/f/organizacoes/2020/07/02/atletico-go-2020-30.png")
                    .resize(70, 70)
                    .into(holder.img_clube);
        }

        holder.tv_jogador.setText(String.valueOf(list.get(position).getApelido()));

        if (list.get(position).getPosicaoId().equals(idgol)) {
            holder.tv_posicao.setText(String.valueOf(idgol).replace("1", "Goleiro"));
        }
        if (list.get(position).getPosicaoId().equals(idlat)) {
            holder.tv_posicao.setText(String.valueOf(idlat).replace("2", "Lateral"));
        }
        if (list.get(position).getPosicaoId().equals(idzag)) {
            holder.tv_posicao.setText(String.valueOf(idzag).replace("3", "Zagueiro"));
        }
        if (list.get(position).getPosicaoId().equals(idmei)) {
            holder.tv_posicao.setText(String.valueOf(idmei).replace("4", "Meia"));
        }
        if (list.get(position).getPosicaoId().equals(idata)) {
            holder.tv_posicao.setText(String.valueOf(idata).replace("5", "Atacante"));
        }
        if (list.get(position).getPosicaoId().equals(idtec)) {
            holder.tv_posicao.setText(String.valueOf(idtec).replace("6", "Técnico"));
        }

        Integer atletaId = list.get(position).getAtletaId();

        holder.atletaId.setText(String.valueOf(atletaId));
        holder.atletaId.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPref = context.getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");


        if (stat.equals("1")) {
            DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));
            holder.tv_pontos.setText(formatter.format(list.get(position).getPontosNum()));
            System.out.println(formatter.format(list.get(position).getPontosNum()));
            if (atletaId.equals(capitao)) {
                holder.tv_pontos.setText("(" + formatter.format(list.get(position).getPontosNum()) + " x2) " + formatter.format(list.get(position).getPontosNum() * 2));
                holder.tv_pontos.setTypeface(null, Typeface.BOLD);
                holder.img_cap.setVisibility(View.VISIBLE);
                holder.itemView.setBackgroundColor(Color.LTGRAY);
            }


        } if (stat.equals("2")) {

            System.out.println("mapliga: " + mapliga);

            //System.out.println("mapparciais: " + mapparciais);

            holder.tv_pontos.setText("-");

            HashMap<String, Double> teste = new HashMap<>();
/*
            for (Map.Entry<String, Double> hashmap : mapparciais.entrySet()) {
                if (mapliga.containsKey(hashmap.getKey())) {
                    System.out.println(hashmap.getKey() + " - " + hashmap.getValue());
                    mapparciais.put(hashmap.getKey(), hashmap.getValue());

                    teste.put(hashmap.getKey(), hashmap.getValue());

                    System.out.println("TESTE: " + teste);
                    System.out.println("SIZE: " + teste.size() + "/" + mapliga.size());

                    double total = 0.0;
                    String ttotal = "";
                    for (String k : teste.keySet()) {
                        System.out.println(k + "\t" + teste.get(k));
                        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

                        total += teste.get(k);
                        ttotal = formatter.format(total);
                    }
                    System.out.println("SUM: " + ttotal);

                    SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(TOTAL_SHARED_PREF, ttotal);
                    editor.putString(QTY_SHARED_PREF, teste.size() + "/" + mapliga.size());

                    editor.apply();
                }

                DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

                if (hashmap.getKey().contains(String.valueOf(atletaId))) {
                    holder.tv_pontos.setText(String.valueOf(formatter.format(hashmap.getValue())));
                }

                if (atletaId.equals(capitao)) {

                    holder.tv_pontos.setTypeface(null, Typeface.BOLD);
                    holder.img_cap.setVisibility(View.VISIBLE);
                    holder.itemView.setBackgroundColor(Color.LTGRAY);

                    if (hashmap.getKey().contains(String.valueOf(capitao))) {

                        holder.tv_pontos.setText("(" + formatter.format(hashmap.getValue()) + " x2) " + formatter.format(hashmap.getValue() * 2));
                        SharedPreferences preferences_ = context.getSharedPreferences(SHAREDCAP_PREF_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor_ = preferences_.edit();
                        Double x = hashmap.getValue();
                        System.out.println("X: " + x);
                        editor_.putString(CAP_SHARED_PREF, formatter.format(x));
                        editor_.apply();

                    }

                }

            } */
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView img_clube, img_cap;
        private AppCompatTextView tv_jogador, tv_posicao, tv_pontos, atletaId, capitaoId, tv_timename;
        public ViewHolder(View itemView) {
            super(itemView);

            img_clube = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
            img_cap = (AppCompatImageView) itemView.findViewById(R.id.img_cap);
            tv_jogador = (AppCompatTextView) itemView.findViewById(R.id.tv_jogador);
            tv_posicao = (AppCompatTextView) itemView.findViewById(R.id.tv_posicao);
            tv_pontos = (AppCompatTextView) itemView.findViewById(R.id.tv_pontos);
            atletaId = (AppCompatTextView) itemView.findViewById(R.id.atletaId);

            tv_timename = (AppCompatTextView) itemView.findViewById(R.id.tv_timename);

        }
    }


    }