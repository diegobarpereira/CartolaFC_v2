package com.diegopereira.cartolafc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diegopereira.cartolafc.favoritos.APIInterface;
import com.diegopereira.cartolafc.favoritos.ApiClient;
import com.diegopereira.cartolafc.favoritos.MyParcialSection;
import com.diegopereira.cartolafc.favoritos.MySection;
import com.diegopereira.cartolafc.favoritos.Players;
import com.diegopereira.cartolafc.favoritos.TimePontos;
import com.diegopereira.cartolafc.groups.DatabaseHelper;
import com.diegopereira.cartolafc.groups.Input;
import com.diegopereira.cartolafc.parciais.Parciais;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.diegopereira.cartolafc.MainActivity.MAIN_SHARED_PREF;
import static com.diegopereira.cartolafc.MainActivity.SHAREDMAIN_PREF_NAME;

public class FavoritosActivity extends AppCompatActivity {

    List<Input> time_id = new ArrayList<>();
    ArrayList<TimePontos> teste = new ArrayList<>();
    Map<String, Double> mapparciais = new HashMap<>(); //
    RecyclerView rv_fav;
    //public static FavRecyclerAdapter adapter;
    public static SectionedRecyclerViewAdapter sectionAdapter;

    com.diegopereira.cartolafc.favoritos.DatabaseHelper databaseHelper;

    String stat;
    String atleta_ids;
    Double pontos;


    LinearLayoutManager linearLayoutManager;

    private DatabaseHelper database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos);

        database = new DatabaseHelper(getApplicationContext());
        time_id = database.getIds();

        SharedPreferences sharedPref = getSharedPreferences(SHAREDMAIN_PREF_NAME, MODE_PRIVATE);
        stat = sharedPref.getString(MAIN_SHARED_PREF, "N/A");

        rv_fav = findViewById(R.id.rv_fav);
        rv_fav.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_fav.setLayoutManager(linearLayoutManager);
        rv_fav.addItemDecoration(new DividerItemDecoration(rv_fav.getContext(), DividerItemDecoration.VERTICAL));

        sectionAdapter = new SectionedRecyclerViewAdapter();


        //adapter = new FavRecyclerAdapter(getApplicationContext(), teste);

        //rv_fav.setAdapter(adapter);

        if (stat.equals("1")) {
            loadLiga();

        } if (stat.equals("2")) {
            databaseHelper = new com.diegopereira.cartolafc.favoritos.DatabaseHelper(getApplicationContext());

            //for (int j = 0; j < time_id.size(); j++) {
                databaseHelper.insert("nome", 0.0, 0.0, "", "", 0);
            //}

            MyParcialSection section2 = new MyParcialSection(getApplicationContext(), teste, mapparciais);
            sectionAdapter.addSection(section2);
            rv_fav.setAdapter(sectionAdapter);
            loadParciais();
        }



    }

    @Override
    public void onResume() {
        super.onResume();

        //adapter.notifyDataSetChanged();
    }


    public void loadLiga() {
        MySection section1 = new MySection(getApplicationContext(), teste);
        sectionAdapter.addSection(section1);
        if (!String.valueOf(time_id).equals("")) {
            for (int i = 0; i < time_id.size(); i++) {
                APIInterface apiInterface = ApiClient.getRetrofit().create(APIInterface.class);
                Call<Players> call = apiInterface.getTime(time_id.get(i).getTimeId().toString());

                int finalI = i;
                call.enqueue(new Callback<Players>() {
                    @Override
                    public void onResponse(Call<Players> call, Response<Players> response) {
                        System.out.println("FavResp: " + response.code());
                        if (response.code() == 200) {
                            TimePontos timePontos = new TimePontos();
                            timePontos.setNome(response.body().getTime().getNome());
                            timePontos.setUltima(response.body().getPontos());
                            timePontos.setPontos(response.body().getPontosCampeonato());
                            timePontos.setUrlEscudoPng(response.body().getTime().getUrlEscudoPng());
                            timePontos.setPatrimonio(response.body().getPatrimonio());
                            timePontos.setTimeId(Integer.valueOf(time_id.get(finalI).getTimeId().toString()));
                            teste.add(timePontos);

                            Collections.sort(teste, new Comparator<TimePontos>() {
                                @Override
                                public int compare( TimePontos o1, TimePontos o2 ) {
                                    return o2.getPontos().compareTo(o1.getPontos());
                                }
                            });



                            rv_fav.setAdapter(sectionAdapter);
                            //sectionAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onFailure(Call<Players> call, Throwable t) {
                        System.out.println("Error: " + t.getMessage());

                    }
                });
            }
        }
    }

    public void loadParciais() {
        for (int i = 0; i < time_id.size(); i++) {
            APIInterface parciais = ApiClient.getRetrofit().create(APIInterface.class);
            Call<Players> call = parciais.getTime(time_id.get(i).getTimeId().toString());
            int finalI = i;
            call.enqueue(new Callback<Players>() {
                @Override
                public void onResponse(Call<Players> call, Response<Players> response) {
                    if (response.code() == 200) {
                        TimePontos timePontos = new TimePontos();
                        timePontos.setNome(response.body().getTime().getNome());
                        timePontos.setPontos(response.body().getPontosCampeonato());
                        timePontos.setUltima(response.body().getPontos());
                        timePontos.setAtletas(response.body().getAtletas());
                        timePontos.setUrlEscudoPng(response.body().getTime().getUrlEscudoPng());
                        timePontos.setCapitaoId(response.body().getCapitaoId());
                        timePontos.setPatrimonio(response.body().getPatrimonio());
                        timePontos.setTimeId(Integer.valueOf(time_id.get(finalI).getTimeId().toString()));
                        teste.add(timePontos);

                        final Gson gson = new GsonBuilder()
                                .setLenient()
                                .serializeNulls()
                                .create();
                        Retrofit retrofit = new Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .baseUrl(CONSTANTS.BASE_URL)
                                .build();
                        com.diegopereira.cartolafc.parciais.APIInterface requestInterface = retrofit.create(com.diegopereira.cartolafc.parciais.APIInterface.class);
                        Call<Parciais> call2 = requestInterface.getAtletas();

                        call2.enqueue(new Callback<Parciais>() {

                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onResponse(Call<Parciais> call, Response<Parciais> response) {
                                //loadProgress.setVisibility(View.GONE);

                                if (response.code() == 200) {

                                    try {
                                        // edited here ,add toJson
                                        String jsonResponse = new Gson().toJson(response.body());
                                        JSONObject jsonObject = null;
                                        if (jsonResponse != null) {
                                            try {
                                                jsonObject = new JSONObject(jsonResponse);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            String rodada = jsonObject.optString("rodada");
                                            JSONObject key2 = jsonObject.optJSONObject("atletas");

                                            Iterator<String> sIterator = key2.keys();

                                            //mapparciais = new HashMap<>();

                                            while (sIterator.hasNext()) {
                                                atleta_ids = sIterator.next();
                                                JSONObject atletas = key2.optJSONObject(atleta_ids);

                                                String apelido = atletas.getString("apelido");

                                                pontos = atletas.getDouble("pontuacao");
                                                //System.out.println("Atletas IDS: " + atleta_ids);

                                                mapparciais.put(atleta_ids, pontos);
                                                //System.out.println("MAPPARCIAISACT: " + mapparciais);

                                            }
                                        }

                                        sectionAdapter.notifyDataSetChanged();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<Parciais> call, Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        });


                        }

                    }

                @Override
                public void onFailure(Call<Players> call, Throwable t) {
                    System.out.println(t.getMessage());

                }
            });
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

}