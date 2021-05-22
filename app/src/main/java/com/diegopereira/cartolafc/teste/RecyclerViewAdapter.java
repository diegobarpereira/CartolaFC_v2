package com.diegopereira.cartolafc.teste;

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
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.LigaActivity;
import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.Teste2Activity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<TimePontos> teste;

    public static final String SHARED_PREF_ID = "SHARED";
    public static final String ID_SHARED_PREF = "id";

    public RecyclerViewAdapter(Context context, List<TimePontos> teste) {
        this.context=context;
        this.teste=teste;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.testelist_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Double diff = 0.00;

            for (int i = 0; i < position; i++) {
                holder.dif.setVisibility(View.VISIBLE);

                diff = teste.get(position).getPontos()-teste.get(0).getPontos();

            }


        if( position == 0) {
            holder.dif.setVisibility(View.INVISIBLE);
        }



        holder.pos.setText(String.valueOf(position+1));

        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));
        String get_pontos = formatter.format(teste.get(position).getPontos());

        String get_ultima = formatter.format(teste.get(position).getUltima());
        Double get_cash = teste.get(position).getPatrimonio();
        String get_dif = formatter.format(diff);
        System.out.println("DIF: " + get_dif);

        holder.titleText.setText(teste.get(position).getNome());
        holder.subtitleText.setText(String.valueOf(get_pontos));
        holder.parciais.setText(String.valueOf(get_ultima));
        holder.cash.setText("C$ " + formatter.format(get_cash));
        holder.dif.setText(get_dif);


        String image = teste.get(position).getUrlEscudoPng();

        Picasso.with(context)
                .load(image)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Toast.makeText(context, teste.get(position).getTimeId().toString(), Toast.LENGTH_SHORT).show();
                                           SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
                                           SharedPreferences.Editor editor = preferences.edit();

                                           editor.putString(ID_SHARED_PREF, teste.get(position).getTimeId().toString());
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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView img;
        private TextView titleText, subtitleText, parciais, cash, pos, dif;

        public ViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title);
            subtitleText = (TextView) itemView.findViewById(R.id.subtitle);
            parciais = (TextView) itemView.findViewById(R.id.parciais);
            img = (AppCompatImageView) itemView.findViewById(R.id.img_clube);
            cash = (TextView) itemView.findViewById(R.id.cash);
            dif = (TextView) itemView.findViewById(R.id.dif);
            pos = (TextView) itemView.findViewById(R.id.pos);
        }
    }


}
