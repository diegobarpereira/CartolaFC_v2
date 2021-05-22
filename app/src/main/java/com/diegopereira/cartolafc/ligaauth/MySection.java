package com.diegopereira.cartolafc.ligaauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.LeagueActivity;
import com.diegopereira.cartolafc.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static android.content.Context.MODE_PRIVATE;

public class MySection extends StatelessSection {
    String title;
    List<Ligas> list;
    Context context;

    public static final String SHARED_PREF_SLUG = "SHARED";
    public static final String SLUG_SHARED_PREF = "slug";
    public static final String NAME_SHARED_PREF = "nome";



    public MySection(Context context, String title, List<Ligas> list) {
        // call constructor with layout resources for this Section header, footer and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.ligaauth_item)
                .headerResourceId(R.layout.ligaauth_header)
                .build());

        this.title = title;
        this.list = list;
        this.context = context;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemViewHolder = (MyItemViewHolder) holder;

        // bind your view here
        System.out.println(list.get(position).getTotal_times_liga().toString());

            if (list.get(position).getTime_dono_id() != null) {
                Ligas ligas = new Ligas();
                ligas.setNome(list.get(position).getNome());
                ligas.setUrl_flamula_png(list.get(position).getUrl_flamula_png());
                ligas.setTotal_times_liga(list.get(position).getTotal_times_liga());
                ligas.setSlug(list.get(position).getSlug());
                itemViewHolder.tv_liga.setText(ligas.getNome());
                itemViewHolder.tv_membros.setText(String.valueOf(ligas.getTotal_times_liga()));

                Picasso.with(context)
                        .load(ligas.getUrl_flamula_png())
                        .resize(100, 100)
                        .into(itemViewHolder.img_liga);

                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                               @Override
                                                               public void onClick(View v) {
                                                                   Toast.makeText(context, ligas.getSlug(), Toast.LENGTH_SHORT).show();

                                                                   SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_SLUG, MODE_PRIVATE);
                                                                   SharedPreferences.Editor editor = preferences.edit();

                                                                   editor.putString(SLUG_SHARED_PREF, ligas.getSlug());
                                                                   editor.putString(NAME_SHARED_PREF, ligas.getNome());
                                                                   Intent intent = new Intent(context, LeagueActivity.class);
                                                                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                   editor.apply();
                                                                   context.startActivity(intent);


                                                               }
                                                           }
                );

            } else {
                Ligas ligas = new Ligas();
                ligas.setNome(list.get(position).getNome());
                ligas.setUrl_flamula_png(list.get(position).getUrl_flamula_png());
                ligas.setTotal_times_liga(list.get(position).getTotal_times_liga());
                ligas.setSlug(list.get(position).getSlug());
                itemViewHolder.tv_liga.setText(ligas.getNome());
                itemViewHolder.tv_membros.setText(String.valueOf(ligas.getTotal_times_liga()));

                Picasso.with(context)
                        .load(ligas.getUrl_flamula_png())
                        .resize(100, 100)
                        .into(itemViewHolder.img_liga);

                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                               @Override
                                                               public void onClick(View v) {
                                                                   Toast.makeText(context, ligas.getSlug(), Toast.LENGTH_SHORT).show();

                                                                   SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_SLUG, MODE_PRIVATE);
                                                                   SharedPreferences.Editor editor = preferences.edit();

                                                                   editor.putString(SLUG_SHARED_PREF, ligas.getSlug());
                                                                   editor.putString(NAME_SHARED_PREF, ligas.getNome());
                                                                   Intent intent = new Intent(context, LeagueActivity.class);
                                                                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                   editor.apply();
                                                                   context.startActivity(intent);


                                                               }
                                                           }
                );

            }




        //itemViewHolder.tv_liga.setText(list.get(position).getNome());
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder headerHolder = (MyHeaderViewHolder) holder;

        // bind your header view here

        headerHolder.nameliga.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new MyHeaderViewHolder(view);
    }



}
