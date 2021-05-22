package com.diegopereira.cartolafc.teste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.DBManager;
import com.diegopereira.cartolafc.DatabaseHelper;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.Teste2Activity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import net.grandcentrix.tray.AppPreferences;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerRodadaAdapter extends RecyclerView.Adapter<RecyclerRodadaAdapter.ViewHolder> {

    private Context context;
    private List<TimePontos> teste;

    private Map<String, Double> mapparciais;
    private Map<String, Double> mapliga;

    public static final String SHARED_PREF_ID = "SHARED";
    public static final String ID_SHARED_PREF = "id";

    public static final String SHARED_PREF_NAME = "SHARED";
    public static final String TOTAL_SHARED_PREF = "total";
    public static final String QTY_SHARED_PREF = "qty";

    private DatabaseHelper database;



    public RecyclerRodadaAdapter( Context context, List<TimePontos> teste, Map<String, Double> mapparciais ) {

        this.context = context;
        this.teste = teste;
        this.mapparciais = mapparciais;


        database = new DatabaseHelper(context);





    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.testelist_item, parent, false);
        return new ViewHolder(v);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {

        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        String get_nome = teste.get(position).getNome();
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

            /*
            Double diff = teste.get(position).getPontosrod()-teste.get(0).getPontosrod();

            holder.dif.setText(formatter.format(diff));
            */




            if (i == (teste.size() - 1)) {



                //holder.titleText.setText(teste.get(position).getNome());
                //holder.subtitleText.setText(formatter.format(teste.get(position).getPontosrod()));

                //holder.parciais.setText(formatter.format(parc));
                //holder.qty.setText(qty);

                //id = teste.get(position).getTimeId();
                /*
                String image = teste.get(position).getUrlEscudoPng();

                Picasso.with(context)
                        .load(image)
                        .into(holder.img);
                */

                database.update(position+1, teste.get(position).getNome(), teste.get(position).getParciais(), teste.get(position).getPontosrod(), teste.get(position).getUrlEscudoPng(), teste.get(position).getQty(), teste.get(position).getTimeId());

            }



        }


        List<TimePontos> item = database.getTimes();
        holder.titleText.setText(item.get(position).getNome());
        holder.parciais.setText(formatter.format(item.get(position).getParciais()));
        holder.subtitleText.setText(formatter.format(item.get(position).getPontosrod()));
        holder.qty.setText(item.get(position).getQty());


        Double diff = item.get(position).getPontosrod()-item.get(0).getPontosrod();


        if (position == 0) {
            holder.dif.setVisibility(View.INVISIBLE);
        } else {
            holder.dif.setText(formatter.format(diff));
        }


        //holder.dif.setText(formatter.format(diff));

        //System.out.println(teste.get(item.get(0).getTimeId()).getNome());
        //System.out.println(item.get(0).getNome());


        String image = item.get(position).getUrlEscudoPng();

        Picasso.with(context)
                .load(image)
                .into(holder.img);




        holder.pos.setText(String.valueOf(position + 1));

        id = item.get(position).getTimeId();




        Integer finalId = id;
        String finalQty = item.get(position).getQty();
        Double finalParc = item.get(position).getParciais();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick( View v ) {
                                                   Toast.makeText(context, String.valueOf(finalId), Toast.LENGTH_SHORT).show();
                                                   SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
                                                   SharedPreferences.Editor editor = preferences.edit();

                                                   editor.putString(ID_SHARED_PREF, String.valueOf(finalId));
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




    @Override
    public int getItemCount() {
        return teste.size();

    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img;
        private TextView titleText, subtitleText, parciais, qty, dif, pos;

        public ViewHolder( View itemView ) {
            super(itemView);
            img = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
            titleText = (TextView) itemView.findViewById(R.id.title);
            subtitleText = (TextView) itemView.findViewById(R.id.subtitle);
            parciais = (TextView) itemView.findViewById(R.id.parciais);
            pos = (TextView) itemView.findViewById(R.id.pos);
            dif = (TextView) itemView.findViewById(R.id.dif);
            qty = (TextView) itemView.findViewById(R.id.cash);



        }

    }




}
