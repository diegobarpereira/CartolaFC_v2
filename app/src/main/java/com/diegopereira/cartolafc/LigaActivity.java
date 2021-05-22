package com.diegopereira.cartolafc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diegopereira.cartolafc.liga.APIInterface;
import com.diegopereira.cartolafc.liga.Atleta;
import com.diegopereira.cartolafc.liga.LigaRecyclerAdapter;
import com.diegopereira.cartolafc.liga.LigaRodadaAdapter;
import com.diegopereira.cartolafc.liga.Players;
import com.diegopereira.cartolafc.liga.ServiceGenerator;
import com.diegopereira.cartolafc.liga.Teste;
import com.diegopereira.cartolafc.parciais.Parciais;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;
//import static com.diegopereira.cartolafc.teste.RecyclerRodadaAdapter.TOTAL_SHARED_PREF;
//import static com.diegopereira.cartolafc.teste.RecyclerRodadaAdapter.QTY_SHARED_PREF;

import static com.diegopereira.cartolafc.league.MyParcialSection.TOTAL_SHARED_PREF;
import static com.diegopereira.cartolafc.league.MyParcialSection.QTY_SHARED_PREF;


public class LigaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Atleta> list = new ArrayList<>();
    List<Teste> tmpList = new ArrayList<>();
    List<Teste> newlist = new ArrayList<>();
    Map<String, Double> mapliga;
    Map<String, Double> mapparciais;
    Integer capitao, atleta_id, idgol, idlat, idzag, idmei, idata, idtec, jogador_id;
    String timename, image, atleta_ids;
    Double timepts, timetot, pontos;

    String vault = "";
    String qty = "";
    String cap = "";
    String stat = "";
    String time_id = "";

    AppCompatTextView tv_timename, tv_timepts, tv_timetot, tv_qty;
    AppCompatImageView img_time;

    LigaRecyclerAdapter adapter;
    LigaRodadaAdapter rodadapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liga);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        tv_timename = (AppCompatTextView) findViewById(R.id.tv_timename);
        img_time = (AppCompatImageView) findViewById(R.id.img_time);
        tv_timepts = (AppCompatTextView) findViewById(R.id.tv_timepts);
        tv_timetot = (AppCompatTextView) findViewById(R.id.tv_timetot);
        tv_qty = (AppCompatTextView) findViewById(R.id.tv_qty);

        recyclerView = findViewById(R.id.rv_liga);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //preferences = getSharedPreferences(SHARED_PREF_ID, MODE_PRIVATE);
        preferences = getSharedPreferences("SHARED_PREF_ID", MODE_PRIVATE);

        //time_id = preferences.getString(ID_SHARED_PREF, "");
        time_id = preferences.getString("ID_SHARED_PREF", "");

        qty = preferences.getString(QTY_SHARED_PREF, String.valueOf("0/0"));

        SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");

        if (stat.equals("1")) {
            loadLiga();
            loadParciais();

        } if (stat.equals("2")) {
            loadLiga();
            loadParciais();
        }



        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh6);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
                stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");

                if (stat.equals("1")) {
                    adapter.notifyDataSetChanged();
                    loadLiga(); // your code
                    loadParciais();

                }
                if (stat.equals("2")) {
                    loadLiga();
                    rodadapter.notifyDataSetChanged();
                    loadParciais();
                }

                pullToRefresh.setRefreshing(false);
            }
        });


    }



    public void loadLiga() {

        APIInterface status = ServiceGenerator.getRetrofit().create(APIInterface.class);
        //Call<Players> call = status.getPlayers();
        Call<Players> call = status.getTime(String.valueOf(time_id));

        call.enqueue(new Callback<Players>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse( Call<Players> call, Response<Players> response ) {
                if (response.isSuccessful()) {
                    timename = response.body().getTime().getNome();
                    tv_timename.setText(timename);

                    image = response.body().getTime().getUrlEscudoPng();

                    Picasso.with(getApplicationContext())
                            .load(image)
                            .into(img_time);


                    System.out.println("GETAtl: " + stat + ":" + response.body().getRodadaAtual());
                    int rodadaatual = response.body().getRodadaAtual();
                    if((stat.equals("1")) && (String.valueOf(rodadaatual).equals("1"))) {
                   // if (mensagem.contains("Este time ainda não foi escalado na temporada.") || (response.body().getAtletas().isEmpty())) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Este time ainda não foi \n escalado na temporada.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        LinearLayout toastLL = (LinearLayout) toast.getView();
                        toastLL.setBackgroundColor(Color.WHITE);
                        TextView toastTV = (TextView) toastLL.getChildAt(0);
                        toastTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        toastTV.setTextColor(Color.BLACK);
                        toastTV.setTextSize(22);
                        toast.show();
                    } else {
                        System.out.println("Rodada Atual: " + response.body().getRodadaAtual());
                        System.out.println("Mensagem: " + response.body().getMensagem());
                        list = response.body().getAtletas();

                        idgol = response.body().getPosicoes().get1().getId();
                        idlat = response.body().getPosicoes().get2().getId();
                        idzag = response.body().getPosicoes().get3().getId();
                        idmei = response.body().getPosicoes().get4().getId();
                        idata = response.body().getPosicoes().get5().getId();
                        idtec = response.body().getPosicoes().get6().getId();

                        //System.out.println("ID1: " + idgol + " ID2: " + idlat + " ID3: " + idzag + " ID4: " + idmei +
                        //                   " ID5: " + idata + " ID6: " + idtec);

                        Collections.sort(list, new Comparator<Atleta>() {
                            @Override
                            public int compare(Atleta o1, Atleta o2) {
                                return o1.getPosicaoId().compareTo(o2.getPosicaoId());
                            }
                        });

                        capitao = response.body().getCapitaoId();
                        atleta_id = response.body().getTime().getTimeId();



                        //
                        mapliga = new HashMap<>();
                        for (int i = 0; i < 12; i++) {
                            jogador_id = response.body().getAtletas().get(i).getAtletaId();

                            Teste newtemp = new Teste();
                            newtemp.setId(String.valueOf(jogador_id));
                            //newtemp.setPontuacao(pontos);

                            tmpList.add(newtemp);

                            mapliga.put(String.valueOf(jogador_id), 0.0);
                            //System.out.println("JOGADOR_ID:" + jogador_id);
                        }
                        SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);

                        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");


                        if (stat.equals("1")) {
                            timepts = response.body().getPontos();
                            tv_timepts.setText(String.format(Locale.US, "%.2f", timepts));
                            timetot = response.body().getPontosCampeonato();
                            tv_timetot.setText(String.format(Locale.US, "%.2f", timetot));
                            tv_qty.setVisibility(View.INVISIBLE);
                            adapter = new LigaRecyclerAdapter(getApplicationContext(), list, capitao, idgol, idlat, idzag, idmei, idata, idtec, mapliga);
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);


                        }
                        if (stat.equals("2")) {
                            loadParciais();

                            vault = preferences.getString(TOTAL_SHARED_PREF, String.valueOf(0.0));
                            qty = preferences.getString(QTY_SHARED_PREF, String.valueOf("0/0"));

                            Double vvault = Double.valueOf(vault);
                            Double timepts_ = (vvault);
                            tv_timepts.setText(String.format(Locale.US, "%.2f", timepts_));

                            tv_qty.setText(qty);

                            timetot = response.body().getPontosCampeonato();
                            tv_timetot.setText(String.format(Locale.US, "%.2f", (timetot + timepts_)));

                        }
                    }
                }

            }

            @Override
            public void onFailure( Call<Players> call, Throwable t ) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }


    public void loadParciais() {

        final Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.baseUrl("https://jsonkeeper.com")
                .baseUrl(CONSTANTS.BASE_URL)
                .build();
        com.diegopereira.cartolafc.parciais.APIInterface requestInterface = retrofit.create(com.diegopereira.cartolafc.parciais.APIInterface.class);
        Call<Parciais> call2 = requestInterface.getAtletas();

        call2.enqueue(new Callback<Parciais>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse( Call<Parciais> call, Response<Parciais> response ) {
                if (response.code() == 200) {

                    try {
                        // edited here ,add toJson
                        String jsonResponse = new Gson().toJson(response.body());
                        JSONObject jsonObject = null;
                        Map<String, Integer> map = null;

                        if (jsonResponse != null) {
                            try {
                                jsonObject = new JSONObject(jsonResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String rodada = jsonObject.optString("rodada");
                            JSONObject key2 = jsonObject.optJSONObject("atletas");

                            Iterator<String> sIterator = key2.keys();

                            mapparciais = new HashMap<>();

                            while (sIterator.hasNext()) {
                                atleta_ids = sIterator.next();
                                JSONObject atletas = key2.optJSONObject(atleta_ids);

                                String apelido = atletas.getString("apelido");

                                pontos = atletas.getDouble("pontuacao");
                                //System.out.println("Atletas IDS: " + atleta_ids);

                                mapparciais.put(atleta_ids, pontos);
                                //System.out.println("MAPPARCIAISACT: " + mapparciais);

                                Teste tmp = new Teste();
                                tmp.setId(atleta_ids);
                                //tmp.setApelido(apelido);
                                tmp.setPontuacao(pontos);

                                JSONObject scouts = atletas.optJSONObject("scout");

                                map = new HashMap<>();
                                ;
                                String chave = "";
                                int valor = 0;
                                if (scouts != null) {
                                    JSONArray scoutsTags = atletas.getJSONObject("scout").names();

                                    if (scoutsTags != null) {
                                        //System.out.println("scoutsTags: " + scoutsTags);
                                        for (int i = 0; i < scoutsTags.length(); i++) {
                                            chave = scoutsTags.getString(i);

                                            valor = scouts.getInt(chave);

                                            //System.out.println("CHAVE: " + chave + " VALOR: " + valor);


                                            map.put(chave, valor);
                                            //System.out.println("MAP: " + map);


                                        }


                                    }
                                }

                                tmp.setScout(map);
                                newlist.add(tmp);



                            }
                        }

                        rodadapter = new LigaRodadaAdapter(getApplicationContext(), list, capitao, idgol, idlat, idzag, idmei, idata, idtec, mapparciais, mapliga);
                        rodadapter.notifyDataSetChanged();
                        recyclerView.setAdapter(rodadapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure( Call<Parciais> call, Throwable t ) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if (id == R.id.menu_mais) {
            Intent intent = new Intent(getApplicationContext(), MaisEscalados.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.partidas) {
            Intent intent = new Intent(getApplicationContext(), JogosActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.jogadores) {
            Intent intent = new Intent(getApplicationContext(), JogadoresActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.parciais) {
            Intent intent = new Intent(getApplicationContext(), ParciaisActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.liga) {
            Intent intent = new Intent(getApplicationContext(), LigaActivity.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

}