package com.diegopereira.cartolafc.partidas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.diegopereira.cartolafc.JogosActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JogosRecyclerAdapter extends RecyclerView.Adapter<JogosRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Partida> list;
    private Map<Integer,Clubes> map;
    String casa;
    String visitante;
    private String escudocasa;
    private String escudovis;

    public JogosRecyclerAdapter(Context context, List<Partida> list, Map<Integer, Clubes> map) {
        this.context=context;
        this.list=list;
        this.map = map;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.jogoslist_item,parent,false);
        return new JogosRecyclerAdapter.ViewHolder(v);
    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int rodadaatual = JogosActivity.rodada;
        int rod = JogosActivity.rod;
        System.out.println("RODADAATUAL: " + rodadaatual + " ROD: " + rod);

        Integer poscasa = list.get(position).getClubeCasaPosicao();
        Integer posvis = list.get(position).getClubeVisitantePosicao();

        for(Map.Entry<Integer, Clubes> entry:map.entrySet()) {

            if((list.get(position).getClubeCasaId()).equals(entry.getValue().getId())) {
                if(rodadaatual == rod) {
                    casa = poscasa + "º " + entry.getValue().getNome();
                }
                else {
                    casa = entry.getValue().getNome();
                }
                escudocasa = entry.getValue().getEscudos().get_60x60();
            }
            if((list.get(position).getClubeVisitanteId()).equals(entry.getValue().getId())) {
                if(rodadaatual == rod) {
                    visitante = entry.getValue().getNome() + " " + posvis + "º";
                } else {
                    visitante = entry.getValue().getNome();
                }

                escudovis = entry.getValue().getEscudos().get_60x60();
            }

        }

        Glide.with(context)
                .load(escudocasa)
                .into(holder.img_clube1);
        Glide.with(context)
                .load(escudovis)
                .into(holder.img_clube2);

        holder.tv_clube1.setText(casa);
        holder.tv_clube2.setText(visitante);

        if(list.get(position).getValida() == true) {
            Date currentTime = Calendar.getInstance().getTime();
            Date partidadata = null;
            Date futuredata = null;
            String datapartida = null;
            Date datapartida_ = null;
            final long millisToAdd = 7_200_000;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                futuredata = sdf.parse("2020-10-03 19:01:00");
                partidadata = sdf.parse(list.get(position).getPartidaData());

                datapartida = sdf.format(partidadata);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR, 2);
                cal.setTime(partidadata);
                datapartida_ = addHour(datapartida,2);

            } catch (Exception e) {
                e.printStackTrace();
            }


            //System.out.println("Current : " + currentTime);
            System.out.println("PartidaData: " + partidadata);
            //System.out.println("FutureData: " + futuredata);


            if (currentTime.before(datapartida_)) {
                holder.tv_data.setText(list.get(position).getPartidaData() + " - " + list.get(position).getLocal());
            }
            if (currentTime.after(datapartida_)) {
                holder.tv_data.setText("Partida Encerrada!");
                holder.tv_data.setTextColor(Color.RED);
            }
        } else {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            holder.tv_data.setText("ESTA PARTIDA NÃO É VÁLIDA PARA A RODADA");
        }

        holder.tv_placar1.setText(String.valueOf(list.get(position).getPlacarOficialMandante()).replace("null", "0"));
        holder.tv_placar2.setText(String.valueOf(list.get(position).getPlacarOficialVisitante()).replace("null", "0"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView img_clube1, img_clube2;
        private AppCompatTextView tv_clube1, tv_clube2, tv_local, tv_placar1, tv_placar2, tv_valida;
        private TextView tv_data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_clube1=(AppCompatTextView)itemView.findViewById(R.id.tv_clube1);
            tv_clube2=(AppCompatTextView)itemView.findViewById(R.id.tv_clube2);
            tv_data=(TextView)itemView.findViewById(R.id.tv_data);
            tv_placar1 = (AppCompatTextView)itemView.findViewById(R.id.tv_placar1);
            tv_placar2 = (AppCompatTextView)itemView.findViewById(R.id.tv_placar2);
            img_clube1=(AppCompatImageView) itemView.findViewById(R.id.img_clube1);
            img_clube2=(AppCompatImageView) itemView.findViewById(R.id.img_clube2);
        }
    }

    public static Date addHour(String partidadata,int number)
    {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = df.parse(partidadata);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.HOUR, number);
            String newTime = df.format(cal.getTime());
            Date data = df.parse(newTime);
            return data;
        }
        catch(ParseException e)
        {
            System.out.println(" Parsing Exception");
        }
        return null;

    }
}
