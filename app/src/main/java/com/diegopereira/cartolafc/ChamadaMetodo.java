package com.diegopereira.cartolafc;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diegopereira.cartolafc.parciais.APIInterface;
import com.diegopereira.cartolafc.parciais.Atletas;
import com.diegopereira.cartolafc.parciais.Clubes;
import com.diegopereira.cartolafc.parciais.Parciais;
import com.diegopereira.cartolafc.parciais.Posicoes;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChamadaMetodo extends Service {
    private final IBinder mBinder = new MyBinder();
    private Set<Map.Entry<String, Atletas>> set;
    private List<Map.Entry<String, Atletas>> list;
    private Map<String, Atletas> atletas;
    private Map<Integer, Posicoes> posicoes = new HashMap();
    private Map<Integer, Clubes> clubes = new HashMap();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        addResultValues();
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        addResultValues();
        return mBinder;
    }

    public class MyBinder extends Binder {
        public ChamadaMetodo getService() {
            return ChamadaMetodo.this;
        }
    }

    public List<Map.Entry<String, Atletas>> getWordList() {
        return list;
    }

    public Map<Integer, Posicoes> getPosicoes() {
        return posicoes;
    }

    public Map<Integer, Clubes> getClubes() {
        return clubes;
    }

    private void addResultValues() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl("https://api.jsonstorage.net/v1/json/")
                //.baseUrl("https://jsonkeeper.com")
                //.baseUrl(CONSTANTS.BASE_URL)
                .build();

        APIInterface service = retrofit.create(APIInterface.class);
        Call<Parciais> call = service.getAtletas();

        call.enqueue(new Callback<Parciais>() {
            @Override
            public void onResponse( Call<Parciais> call, Response<Parciais> response ) {

                if (response.code() == 200) {

                    atletas = response.body().getAtletas();
                    posicoes = response.body().getPosicoes();
                    clubes = response.body().getClubes();

                    set = atletas.entrySet();
                    list = new ArrayList<Map.Entry<String, Atletas>>(set);

                    Collections.sort(list, new Comparator<Map.Entry<String, Atletas>>() {
                        @Override
                        public int compare( Map.Entry<String, Atletas> t1, Map.Entry<String, Atletas> t2 ) {
                            return t2.getValue().getPontuacao().compareTo(t1.getValue().getPontuacao());
                        }
                    });

                }
                if (response.code() == 204 || response.code() == 404) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Parciais não disponíveis!!! \n Aguarde a rodada \n Iniciar!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    LinearLayout toastLL = (LinearLayout) toast.getView();
                    toastLL.setBackgroundColor(Color.WHITE);
                    TextView toastTV = (TextView) toastLL.getChildAt(0);
                    toastTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    toastTV.setTextColor(Color.BLACK);
                    toastTV.setTextSize(22);
                    toast.show();
                }


            }

            @Override
            public void onFailure( Call<Parciais> call, Throwable t ) {
                System.out.println(t.getMessage());
            }
        });
    }

}