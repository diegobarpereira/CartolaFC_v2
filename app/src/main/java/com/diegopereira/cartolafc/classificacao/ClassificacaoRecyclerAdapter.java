package com.diegopereira.cartolafc.classificacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.R;
import com.diegopereira.cartolafc.jogadores.Atleta;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class ClassificacaoRecyclerAdapter extends RecyclerView.Adapter<ClassificacaoRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Tabela> list;
    //private List<Time> list_time;

    public ClassificacaoRecyclerAdapter( Context context, List<Tabela> list) {
        this.context = context;
        this.list = list;
        //this.list_time = list_time;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.classificacaolist_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_pos.setText(list.get(position).getPosicao() + "º");
        holder.tv_time.setText(list.get(position).getTime());
        holder.tv_pts.setText(String.valueOf(list.get(position).getPontos()));
        holder.tv_jog.setText(String.valueOf(list.get(position).getJogos()));
        holder.tv_vit.setText(String.valueOf(list.get(position).getVitorias()));
        holder.tv_emp.setText(String.valueOf(list.get(position).getEmpates()));
        holder.tv_der.setText(String.valueOf(list.get(position).getDerrotas()));
        holder.tv_gp.setText(String.valueOf(list.get(position).getGolsPro()));
        holder.tv_gc.setText(String.valueOf(list.get(position).getGolsContra()));
        holder.tv_sg.setText(String.valueOf(list.get(position).getSaldoGols()));
        holder.tv_apr.setText(list.get(position).getAproveitamento() + "%");

        holder.tv_var.setText(String.valueOf(list.get(position).getVariacaoPosicao()).replace("-", ""));

        if (list.get(position).getVariacaoPosicao() == 0) {
            Picasso.with(context)
                    .load(R.drawable.ic_baseline_brightness_1_24)
                    .placeholder(R.drawable.ic_baseline_brightness_1_24)
                    .into(holder.img_var);
        }
        if (list.get(position).getVariacaoPosicao() > 0) {
            Picasso.with(context)
                    .load(R.drawable.ic_baseline_arrow_drop_up_24)
                    .placeholder(R.drawable.ic_baseline_arrow_drop_up_24)
                    .into(holder.img_var);
        }
        if (list.get(position).getVariacaoPosicao() < 0) {
            Picasso.with(context)
                    .load(R.drawable.ic_baseline_arrow_drop_down_24)
                    .placeholder(R.drawable.ic_baseline_arrow_drop_down_24)
                    .into(holder.img_var);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_pos, tv_time, tv_pts, tv_jog, tv_vit, tv_emp, tv_der, tv_gp, tv_gc, tv_sg, tv_apr, tv_var;
        private ImageView img_var;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_pos = (TextView) itemView.findViewById(R.id.tv_pos);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_pts = (TextView) itemView.findViewById(R.id.tv_pts);
            tv_jog = (TextView) itemView.findViewById(R.id.tv_jog);
            tv_vit = (TextView) itemView.findViewById(R.id.tv_vit);
            tv_emp = (TextView) itemView.findViewById(R.id.tv_emp);
            tv_der = (TextView) itemView.findViewById(R.id.tv_der);
            tv_gp = (TextView) itemView.findViewById(R.id.tv_gp);
            tv_gc = (TextView) itemView.findViewById(R.id.tv_gc);
            tv_sg = (TextView) itemView.findViewById(R.id.tv_sg);
            tv_apr = (TextView) itemView.findViewById(R.id.tv_apr);
            tv_var = (TextView) itemView.findViewById(R.id.tv_var);
            img_var = (ImageView) itemView.findViewById(R.id.img_var);



        }
    }
}
