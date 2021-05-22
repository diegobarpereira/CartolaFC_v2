package com.diegopereira.cartolafc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.diegopereira.cartolafc.liga.Teste;
import com.diegopereira.cartolafc.teste.Atletas;
import com.diegopereira.cartolafc.teste.TimePontos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static android.content.Context.MODE_PRIVATE;

public class Teste2ListView_ extends ArrayAdapter<TimePontos> {

    private final Activity context;
    private List<TimePontos> teste;
    private Map<String, Double> mapparciais;
    private Map<String, Double> mapliga;
    Integer capitao;


    public Teste2ListView_( Activity context, List<TimePontos> teste, Map<String, Double> mapparciais) {

        super(context, R.layout.testelist_item, teste);

        // TODO Auto-generated constructor stub

        this.context = context;
        this.teste = teste;
        //this.mapliga = mapliga;
        this.mapparciais = mapparciais;
        //this.capitao = capitao;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View getView( int position, View view, ViewGroup parent ) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.testelist_item, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        TextView parciais = (TextView) rowView.findViewById(R.id.parciais);


        DecimalFormat formatter = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.US));

        String get_pontos = formatter.format(teste.get(position).getPontos());
        String get_nome = teste.get(position).getNome();
        Integer get_cap = teste.get(position).getCapitaoId();


        System.out.println("CAPITAO: " + get_cap);




        /*
        Integer x = 0;
        for (int j = 0; j < 28; j++) {
            x = teste.get(position).getAtletas().get(11).getAtletaId();

        }
        System.out.println(x); */
        ////////////////////////////////////////////////////

        //boolean isTrue = x.equals(mapparciais.entrySet());
        //System.out.println(isTrue);
        Integer jogador_id = 0;
        mapliga = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            jogador_id = teste.get(position).getAtletas().get(i).getAtletaId();

            mapliga.put(String.valueOf(jogador_id), 0.0);

        }
        System.out.println("mapliga:" + mapliga);

        HashMap<String, Double> teste_ = new HashMap<>();
        double total = 0.0;
        double totalcap = 0.0;
        String ttotal = "";
        double x = 0.0;
        for (Map.Entry<String, Double> hashmap : mapparciais.entrySet()) {
            //if (hashmap.getKey().contains(String.valueOf(x))){
            if (mapliga.containsKey(hashmap.getKey())) {
                System.out.println(hashmap.getKey() + " - " + hashmap.getValue());
                mapparciais.put(hashmap.getKey(), hashmap.getValue());

                teste_.put(hashmap.getKey(), hashmap.getValue());

                //System.out.println("TESTE: " + teste_);



            }

            if (hashmap.getKey().contains(String.valueOf(get_cap))) {

                x = hashmap.getValue();
                System.out.println("X: " + x);

            }


        }





        for (String k : teste_.keySet()) {
            //System.out.println(k + "\t" + teste_.get(k));

            total += teste_.get(k);
            totalcap = (total+x);
            ttotal = formatter.format(totalcap);

            System.out.println("SUM: " + ttotal);
            parciais.setText(ttotal);

        }

        Double pontorod = teste.get(position).getPontos() + totalcap;
        String rodtotal = formatter.format(pontorod);
        System.out.println(rodtotal);

        titleText.setText(get_nome);
        //subtitleText.setText(get_pontos);
        subtitleText.setText(rodtotal);

      return rowView;

    }


}