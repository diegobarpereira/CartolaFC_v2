package com.diegopereira.cartolafc.ligaauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.teste.TimePontos;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Ligas> ligas;

    public static final String SHARED_PREF_SLUG = "SHARED";
    public static final String SLUG_SHARED_PREF = "slug";



    public RecyclerViewAdapter( Context context, List<Ligas> ligas) {
        this.context=context;
        this.ligas=ligas;
    }

    @NonNull
    @Override
    public com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligaauth_item, parent, false);
        return new com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( com.diegopereira.cartolafc.ligaauth.RecyclerViewAdapter.ViewHolder holder, int position) {
        String liga = ligas.get(position).getNome();
        holder.tv_liga.setText(liga);

        String img_liga = ligas.get(position).getUrl_flamula_png();
        Picasso.with(context)
                .load(img_liga)
                .resize(100, 100)
                .into(holder.img_liga);
        Integer tv_membros = ligas.get(position).getTotal_times_liga();
        holder.tv_membros.setText(String.valueOf(tv_membros));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Toast.makeText(context, ligas.get(position).getSlug().toString(), Toast.LENGTH_SHORT).show();

                                                   SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_SLUG, MODE_PRIVATE);
                                                   SharedPreferences.Editor editor = preferences.edit();

                                                   editor.putString(SLUG_SHARED_PREF, ligas.get(position).getSlug());
                                                   Intent intent = new Intent(context, LeagueActivity.class);
                                                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   editor.apply();
                                                   context.startActivity(intent);


                                               }
                                           }
        );

    }

    @Override
    public int getItemCount() {
        return ligas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img_liga;
        private TextView tv_liga, tv_membros;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_liga = (AppCompatTextView)itemView.findViewById(R.id.tv_liga);
            img_liga = (AppCompatImageView)itemView.findViewById(R.id.img_liga);
            tv_membros = (AppCompatTextView)itemView.findViewById(R.id.tv_membros);

        }
    }

    }


