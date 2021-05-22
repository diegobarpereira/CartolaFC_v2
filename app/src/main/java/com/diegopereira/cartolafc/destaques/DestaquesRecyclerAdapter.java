package com.diegopereira.cartolafc.destaques;

import android.content.Context;
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

public class DestaquesRecyclerAdapter extends RecyclerView.Adapter<DestaquesRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Destaques> destaques;
    private Destaques destaque;
    
    public DestaquesRecyclerAdapter(Context context, List<Destaques> destaques) {
        this.context=context;
        this.destaques=destaques;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.destaquesgridlist_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    destaque=destaques.get(i);

        DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.GERMANY));
        formatter.setGroupingUsed(true);
        formatter.setGroupingSize(3);

        viewHolder.tv_actorname.setText(String.valueOf(destaque.getAtleta().getApelido()));
        //viewHolder.tv_actorcountry.setText("C$ " + destaque.getAtleta().getPrecoEditorial());
        viewHolder.tv_actordateofbirth.setText("Escalações " + formatter.format(destaque.getEscalacoes()));



        //viewHolder.tv_actorgender.setText(String.valueOf(destaque.getClubeNome()));
        //viewHolder.tv_posicao.setText(destaque.getPosicao());

        String x = destaque.getAtleta().getFoto().replace("FORMATO", "140x140");

        viewHolder.pos.setText(i +1 + "º");

        Picasso.with(context)
                .load(x)
                .resize(100, 100)
                .into(viewHolder.img_actor);

        Picasso.with(context)
                .load(destaque.getEscudoClube())
                .resize(100, 100)
                .into(viewHolder.img_clube);




    }

    @Override
    public int getItemCount() {
        return destaques.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView img_actor, img_clube;
        private AppCompatTextView tv_actorname,tv_actorcountry,tv_actordateofbirth,tv_actorgender,tv_posicao, tv_x, pos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_actor=(AppCompatImageView) itemView.findViewById(R.id.img_actorimage);
            tv_actorname=(AppCompatTextView)itemView.findViewById(R.id.tv_actorname);
            tv_actorcountry=(AppCompatTextView)itemView.findViewById(R.id.tv_actorcountry);
            tv_actordateofbirth=(AppCompatTextView)itemView.findViewById(R.id.tv_actordob);
            tv_actorgender=(AppCompatTextView)itemView.findViewById(R.id.tv_actorgender);
            tv_posicao=(AppCompatTextView)itemView.findViewById(R.id.tv_posicao);
            img_clube=(AppCompatImageView) itemView.findViewById(R.id.img_clube);
            pos=(AppCompatTextView)itemView.findViewById(R.id.pos);

        }
    }
}